/**
 * Copyright (c) 2000-2017 Liferay, Inc. All rights reserved.
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

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.junit.Assert;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.liferay.faces.test.selenium.IntegrationTesterBase;
import com.liferay.faces.test.selenium.TestUtil;
import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.BrowserStateAsserter;
import com.liferay.faces.test.selenium.expectedconditions.WindowOpened;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class TesterBase extends IntegrationTesterBase {

	// Logger
	private static final Logger logger = LoggerFactory.getLogger(TesterBase.class);

	// Protected Constants
	protected static final String DEFAULT_COMPONENT_PREFIX = TestUtil.getSystemPropertyOrDefault(
			"integration.default.component.prefix", "h");
	protected static final String SHOWCASE_CONTEXT_URL;

	// Common Xpath
	protected static final String error1Xpath =
		"(//div[contains(@class,'field form-group has-error') or contains(@class,'field control-group error')])[1]";
	protected static final String immediateMessage1Xpath = "//li[contains(text(),'APPLY_REQUEST_VALUES')]";
	protected static final String immediateMessage2Xpath = "//li[contains(text(),'PROCESS_VALIDATIONS')]";
	protected static final String modelValue1Xpath = "(//span[contains(@id,':modelValue')])[1]";
	protected static final String modelValue2Xpath = "(//span[contains(@id,':modelValue')])[2]";
	protected static final String requiredCheckbox1Xpath = "//input[contains(@id,':requiredCheckbox')]";
	protected static final String submitButton1Xpath = "(//*[contains(@value,'Submit')])[1]";
	protected static final String submitButton2Xpath = "(//*[contains(@value,'Submit')])[2]";
	protected static final String valueIsRequiredError1Xpath =
		"(//div[(contains(@class,'field form-group has-error') or contains(@class,'field control-group error')) and contains(., 'Validation Error: Value is required.')])[1]";

	// Private Constants
	private static final String CONTAINER = TestUtil.getContainer("tomcat");
	private static final boolean SIGN_IN;

	static {

		String defaultShowcaseContext = "/com.liferay.faces.demo.jsf.showcase.webapp/web/guest/showcase/-/component";
		boolean signIn = false;

		if (CONTAINER.contains("liferay")) {
			defaultShowcaseContext = "/web/guest/jsf-showcase/-/jsf-tag";
		}
		else if (CONTAINER.contains("pluto")) {

			defaultShowcaseContext = TestUtil.DEFAULT_PLUTO_CONTEXT + "/jsf-showcase";
			signIn = true;
		}

		logger.info("defaultShowcaseContext = {0}", defaultShowcaseContext);

		String showcaseContext = TestUtil.getSystemPropertyOrDefault("integration.showcase.context",
				defaultShowcaseContext);
		logger.info("showcaseContext = {0}", showcaseContext);

		SHOWCASE_CONTEXT_URL = TestUtil.DEFAULT_BASE_URL + showcaseContext;
		SIGN_IN = signIn;
	}

	protected void assertImageRendered(BrowserDriver browserDriver, BrowserStateAsserter browserStateAsserter,
		String imageXpath) {

		browserStateAsserter.assertElementDisplayed(imageXpath);

		WebElement image = browserDriver.findElementByXpath(imageXpath);
		String imageSrc = image.getAttribute("src");
		Assert.assertTrue("Image src " + imageSrc + " is not a valid JSF resource URL.",
			imageSrc.matches(".*javax.faces.resource\\p{Punct}[a-z-]+[.]png.*") &&
			imageSrc.matches(".*ln\\p{Punct}images.*"));

		Boolean imageRendered = (Boolean) browserDriver.executeScriptInCurrentWindow(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				image);
		Assert.assertTrue("Image " + imageXpath + " (src=\"" + imageSrc + "\") is not rendered in the DOM.",
			imageRendered);
	}

	protected String capitalize(String string) {

		String capitalizedString = string;

		if (string != null) {

			if (string.length() > 1) {
				capitalizedString = string.substring(0, 1).toUpperCase(Locale.ENGLISH) + string.substring(1);
			}
			else {
				capitalizedString = string.toUpperCase(Locale.ENGLISH);
			}
		}

		return capitalizedString;
	}

	@Override
	protected void doSetUp() {

		if (SIGN_IN) {
			signIn(getBrowserDriver(), CONTAINER);
		}
	}

	protected String getExampleImageXpath(String exampleLabelText) {
		return "//label[contains(.,'Example')][contains(.,'" + exampleLabelText +
			"')]/ancestor::div[@class='showcase-example']//img[contains(@src,'javax.faces.resource')][contains(@src,'ln=images') or contains(@src,'ln:images')]";
	}

	protected boolean isHeadlessChrome(BrowserDriver browserDriver) {
		return "chrome".equals(browserDriver.getBrowserName()) && browserDriver.isBrowserHeadless();
	}

	protected void navigateToUseCase(BrowserDriver browserDriver, String componentName, String componentUseCase) {
		navigateToUseCase(browserDriver, DEFAULT_COMPONENT_PREFIX, componentName, componentUseCase);
	}

	protected void navigateToUseCase(BrowserDriver browserDriver, String componentPrefix, String componentName,
		String componentUseCase) {

		if (CONTAINER.contains("pluto")) {

			// Since pluto does not support friendly URLs, obtain the "general" use case URL from the showcase accordion
			// and replace "general" with the specified use case. Note: non-"general" use cases are shown conditionally
			// so we cannot rely on those links being present, but the "general" use case links are always present.

			String linkText = componentPrefix + ":" + componentName;

			if (componentPrefix.equals("util")) {
				linkText = componentName;
			}

			String componentLinkXpath =
				"//a[contains(@href, 'general')][contains(@class,'showcase-link')][normalize-space(text())='" +
				linkText + "']";
			List<WebElement> componentLinkElements = browserDriver.findElementsByXpath(componentLinkXpath);

			// Initially, navigateToUseCase() may be called when the browser is on the default pluto page, so
			// navigate to the showcase if no component link element is found.
			if (componentLinkElements.isEmpty()) {

				browserDriver.navigateWindowTo(SHOWCASE_CONTEXT_URL);
				waitForShowcasePageReady(browserDriver);
				browserDriver.clickElement("//img[contains(@src,'max.png')]/parent::a[contains(@href,'maximized')]");
				waitForShowcasePageReady(browserDriver);
				componentLinkElements = browserDriver.findElementsByXpath(componentLinkXpath);
			}

			if (componentLinkElements.isEmpty()) {
				throw new Error("Could not obtain pluto use case URL because no link for " + componentPrefix + ":" +
					componentName + " was found on the page.");
			}

			if (componentLinkElements.size() > 1) {
				throw new IllegalStateException(
					"Could not obtain pluto use case URL because multiple links matching xpath \"" +
					componentLinkXpath + "\" for " + componentPrefix + ":" + componentName +
					" were found on the page.\nXpath is ambiguous.");
			}

			String hrefAttribute = componentLinkElements.get(0).getAttribute("href");
			String plutoUseCaseURL = hrefAttribute.replace("general", componentUseCase);
			browserDriver.navigateWindowTo(plutoUseCaseURL);
		}
		else {
			browserDriver.navigateWindowTo(SHOWCASE_CONTEXT_URL + "/" + componentPrefix + "/" +
				componentName.toLowerCase(Locale.ENGLISH) + "/" + componentUseCase);
		}

		waitForShowcasePageReady(browserDriver);
	}

	/**
	 * Click the link and assert that it opens a new window/tab with the correct domain name.
	 */
	protected void testLink(BrowserDriver browserDriver, BrowserStateAsserter browserStateAsserter,
		String exampleLinkXpath, String domainName) {

		browserStateAsserter.assertElementDisplayed(exampleLinkXpath);

		String newTabURL = null;
		String originalWindowHandle = null;
		Set<String> originalWindowIds = browserDriver.getWindowIds();
		int initialNumberOfWindows = originalWindowIds.size();
		WebElement linkElement = browserDriver.findElementByXpath(exampleLinkXpath);
		linkElement.click();
		browserStateAsserter.assertTrue(new WindowOpened(initialNumberOfWindows));
		browserDriver.setPageLoadTimeout(5);

		try {

			Set<String> windowIds = new HashSet<String>(browserDriver.getWindowIds());
			originalWindowHandle = browserDriver.getCurrentWindowId();

			// Obtain the URL of the newly opened tab.
			windowIds.removeAll(originalWindowIds);
			browserDriver.switchToWindow(windowIds.iterator().next());
			newTabURL = browserDriver.getCurrentWindowUrl();
		}
		catch (TimeoutException e) {
			// The browser likely could not connect to the website.
		}

		// Reset to page load timeout to inifinity (the default).
		browserDriver.setPageLoadTimeout(-1);

		// If the url is null or "about:blank", the browser likely could not connect to the website, so fall back
		// to testing the link on the original page.
		if ((newTabURL == null) || "about:blank".equals(newTabURL)) {

			if (originalWindowHandle != null) {

				if (!isHeadlessChrome(browserDriver)) {
					browserDriver.closeCurrentWindow();
				}

				browserDriver.switchToWindow(originalWindowHandle);
			}

			linkElement = browserDriver.findElementByXpath(exampleLinkXpath);
			newTabURL = linkElement.getAttribute("href");
		}

		Assert.assertTrue("The url does not contain " + domainName + " instead it is " + newTabURL + ".",
			newTabURL.contains(domainName));

		if ((originalWindowHandle != null) && !browserDriver.getCurrentWindowId().equals(originalWindowHandle)) {

			if (!isHeadlessChrome(browserDriver)) {
				browserDriver.closeCurrentWindow();
			}

			browserDriver.switchToWindow(originalWindowHandle);
		}
	}

	/**
	 * Test that the web page shows an error message when a value is required and an empty value is submitted.
	 */
	protected void testRequiredCheckboxError(BrowserDriver browserDriver, BrowserStateAsserter browserStateAsserter) {

		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);
		browserStateAsserter.assertElementNotDisplayed(valueIsRequiredError1Xpath);
		browserDriver.clickElement(requiredCheckbox1Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);
		browserStateAsserter.assertElementDisplayed(valueIsRequiredError1Xpath);
	}

	protected void waitForShowcasePageReady(BrowserDriver browserDriver) {
		browserDriver.waitFor(ExpectedConditions.jsReturnsValue("return window.liferay_faces_showcase_ready;"));
	}
}
