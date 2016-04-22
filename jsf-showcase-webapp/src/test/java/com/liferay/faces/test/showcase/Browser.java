/**
 * Copyright (c) 2000-2016 Liferay, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.faces.test.showcase;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * @author  Kyle Stiemann
 */
public class Browser {

	private static WebDriver webDriver = null;
	private static WebDriverWait wait = null;
	private static Browser instance = null;
	private static final Logger logger = Logger.getLogger(Browser.class.getName());

	static {

		String logLevelString = System.getProperty("integration.log.level", "WARNING");
		Level logLevel = Level.parse(logLevelString);
		logger.setLevel(logLevel);
	}

	private Browser() {

		String browser = System.getProperty("integration.browser", "firefox");

		if ("phantomjs".equals(browser)) {
			webDriver = new PhantomJSDriver();
		}
		else if ("chrome".equals(browser)) {
			webDriver = new ChromeDriver();
		}
		else if ("firefox".equals(browser)) {
			webDriver = new FirefoxDriver();
		}

		webDriver.manage();
		wait = new WebDriverWait(webDriver, 5);
	}

	public static Browser getInstance() {

		if (instance == null) {
			instance = new Browser();
		}

		return instance;
	}

	public void assertElementPresent(String xpath) {

		WebElement element = getElement(xpath);
		Assert.assertNotNull("Element " + xpath + " is not present in the DOM.", element);
	}

	public void assertElementTextVisible(String xpath, String text) {

		WebElement element = getElement(xpath);
		Assert.assertNotNull("Element " + xpath + " is not present in the DOM.", element);

		boolean elementDisplayed = element.isDisplayed();
		Assert.assertTrue("Element " + xpath + " is not displayed.", elementDisplayed);

		String elementText = element.getText();
		Assert.assertEquals("Element " + xpath + " does not contain text \"" + text +
			"\". Instead it contains text \"" + elementText + "\".", text, elementText);
	}

	// Currently unused:
	public void assertElementValue(String xpath, String value) {

		WebElement element = getElement(xpath);
		Assert.assertNotNull("Element " + xpath + " is not present in the DOM.", element);

		boolean elementDisplayed = element.isDisplayed();
		Assert.assertTrue("Element " + xpath + " is not displayed.", elementDisplayed);

		String elementValue = element.getAttribute("value");
		Assert.assertEquals("Element " + xpath + " does contain the value \"" + value +
			"\". Instead it contains the value \"" + elementValue + "\".", value, elementValue);
	}

	public void assertElementVisible(String xpath) {

		WebElement element = getElement(xpath);
		Assert.assertNotNull("Element " + xpath + " is not present in the DOM.", element);

		boolean elementDisplayed = element.isDisplayed();
		Assert.assertTrue("Element " + xpath + " is not displayed.", elementDisplayed);
	}

	public void click(String xpath) {
		getElement(xpath).click();
	}

	/**
	 * Clicks on the element specified via xpath and waits for the clicked element to be rerendered via Ajax. This
	 * method will only work if the element clicked is also rerendered via Ajax. If the clicked element will not be
	 * rerendered via Ajax, then use {@link
	 * Browser#performAndWaitForAjaxRerender(org.openqa.selenium.interactions.Action, java.lang.String)} with {@link
	 * Browser#createClickAction(java.lang.String)} and the xpath of an element which will be rerendered instead.
	 *
	 * @param  xpath  The xpath of the element to be clicked and rerendered.
	 */
	public void clickAndWaitForAjaxRerender(String xpath) {
		performAndWaitForAjaxRerender(createClickAction(xpath), xpath);
	}

	// Currently unused:
	public Actions createActions() {
		return new Actions(webDriver);
	}

	public Action createClickAction(String xpath) {

		Actions actions = new Actions(webDriver);
		WebElement element = getElement(xpath);
		actions.click(element);

		return actions.build();
	}

	// Currently unused:
	public Action createSendKeysAction(String xpath, CharSequence... keys) {

		Actions actions = new Actions(webDriver);
		WebElement element = getElement(xpath);
		actions.sendKeys(element, keys);

		return actions.build();
	}

	public void navigateToURL(String url) {
		webDriver.navigate().to(url);
	}

	/**
	 * Performs an {@link Action} and waits for an element to be rerendered via Ajax.
	 *
	 * @param  action         The action which will cause the Ajax rerender.
	 * @param  rerenderXpath  The xpath of the element which will be rerendered.
	 */
	public void performAndWaitForAjaxRerender(Action action, String rerenderXpath) {

		WebElement rerenderElement = getElement(rerenderXpath);
		action.perform();
		logger.log(Level.INFO, "Waiting for element {0} to be stale.", rerenderXpath);
		wait.until(ExpectedConditions.stalenessOf(rerenderElement));
		logger.log(Level.INFO, "Element {0} is stale.", rerenderXpath);
		waitForElementVisible(rerenderXpath);
	}

	public void quit() {
		webDriver.quit();
	}

	public void sendKeys(String xpath, CharSequence... keys) {
		getElement(xpath).sendKeys(keys);
	}

	// Currently unused:
	/**
	 * Sends keys to the element specified via xpath and waits for the element to be rerendered via Ajax. This method
	 * will only work if the element receiving the keys is also rerendered via Ajax. If the element receiving the keys
	 * will not be rerendered via Ajax, then use {@link
	 * Browser#performAndWaitForAjaxRerender(org.openqa.selenium.interactions.Action, java.lang.String)} with {@link
	 * Browser#createSendKeysAction(java.lang.String, java.lang.CharSequence...)} and the xpath of an element which will
	 * be rerendered instead.
	 *
	 * @param  xpath  The xpath of the element to be clicked and rerendered.
	 * @param  keys   The keys to be sent.
	 */
	public void sendKeysAndWaitForAjaxRerender(String xpath, CharSequence... keys) {
		performAndWaitForAjaxRerender(createSendKeysAction(xpath, keys), xpath);
	}

	// Currently unused:
	public void waitForElementNotPresent(String xpath) {

		logger.log(Level.INFO, "Waiting for element {0} to not be present in the DOM.", xpath);
		wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(By.ByXPath.xpath(xpath))));
		logger.log(Level.INFO, "Element {0} is not present in the DOM.", xpath);
	}

	// Currently unused:
	public void waitForElementPresent(String xpath) {

		logger.log(Level.INFO, "Waiting for element {0} to be present in the DOM.", xpath);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.ByXPath.xpath(xpath)));
		logger.log(Level.INFO, "Element {0} is present in the DOM.", xpath);
	}

	// Currently unused:
	public void waitForElementTextVisible(String xpath, String text) {

		String[] loggerArgs = new String[] { xpath, text };
		waitForElementVisible(xpath);
		logger.log(Level.INFO, "Waiting for element {0} to contain text \"{1}\".", loggerArgs);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.ByXPath.xpath(xpath), text));
		logger.log(Level.INFO, "Element {0} is visible and contains text \"{1}\".", loggerArgs);
	}

	// Currently unused:
	public void waitForElementValue(String xpath, String value) {

		String[] loggerArgs = new String[] { xpath, value };
		waitForElementVisible(xpath);
		logger.log(Level.INFO, "Waiting for element {0} to contain value \"{1}\".", loggerArgs);
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.ByXPath.xpath(xpath), value));
		logger.log(Level.INFO, "Element {0} is visible and contains value \"{1}\".", loggerArgs);
	}

	public void waitForElementVisible(String xpath) {

		logger.log(Level.INFO, "Waiting for element {0} to be visible.", xpath);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.ByXPath.xpath(xpath)));
		logger.log(Level.INFO, "Element {0} is visible.", xpath);
	}

	// Currently unused:
	public void waitUntil(ExpectedCondition expectedCondition) {
		wait.until(expectedCondition);
	}

	public WebElement getElement(String xpath) {
		return webDriver.findElement(By.xpath(xpath));
	}
}
