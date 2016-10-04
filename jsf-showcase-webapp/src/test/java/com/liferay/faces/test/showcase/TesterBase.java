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

import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.IntegrationTesterBase;
import com.liferay.faces.test.selenium.TestUtil;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class TesterBase extends IntegrationTesterBase {

	// Logger
	private static final Logger logger = Logger.getLogger(TesterBase.class.getName());

	// Protected Constants
	protected static final String TEST_CONTEXT_URL;

	// Common Xpath
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
	private static final String DEFAULT_COMPONENT_PREFIX = TestUtil.getSystemPropertyOrDefault(
			"integration.default.component.prefix", "h");
	private static final boolean SIGN_IN;

	static {

		String defaultContext = "/com.liferay.faces.demo.jsf.showcase.webapp/web/guest/showcase/-/component";
		boolean signIn = false;

		if (TestUtil.CONTAINER.contains("liferay")) {
			defaultContext = "/web/guest/jsf-showcase/-/jsf-tag";
		}
		else if (TestUtil.CONTAINER.contains("pluto")) {

			defaultContext = TestUtil.DEFAULT_PLUTO_CONTEXT + "/jsf-showcase";
			signIn = true;
		}

		logger.log(Level.INFO, "defaultContext = " + defaultContext);

		String context = TestUtil.getSystemPropertyOrDefault("integration.context", defaultContext);
		logger.log(Level.INFO, "context = " + context);

		TEST_CONTEXT_URL = TestUtil.BASE_URL + context;
		SIGN_IN = signIn;
	}

	@Override
	protected void doSetUp() {

		if (SIGN_IN) {
			TestUtil.signIn(Browser.getInstance());
		}
	}

	protected void navigateToUseCase(Browser browser, String componentName, String componentUseCase) {
		navigateToUseCase(browser, DEFAULT_COMPONENT_PREFIX, componentName, componentUseCase);
	}

	protected void navigateToUseCase(Browser browser, String componentPrefix, String componentName,
		String componentUseCase) {

		if (TestUtil.CONTAINER.contains("pluto")) {

			// Since pluto does not support friendly URLs, obtain the "general" use case URL from the showcase accordion
			// and replace "general" with the specified use case. Note: non-"general" use cases are shown conditionally
			// so we cannot rely on those links being present, but the "general" use case links are always present.
			String componentLinkXpath =
				"//a[contains(@href, 'general')][contains(@class,'showcase-link')][normalize-space(text())='" +
				componentPrefix + ":" + componentName + "']";
			List<WebElement> componentLinkElements = browser.findElements(By.xpath(componentLinkXpath));

			// Initially, navigateToUseCase() may be called when the browser is on the default pluto page, so navigate
			// to the showcase if no component link element is found.
			if (componentLinkElements.isEmpty()) {

				browser.get(TEST_CONTEXT_URL);
				waitForShowcasePageReady(browser);
				browser.click("//img[contains(@src,'max.png')]/parent::a[contains(@href,'maximized')]");
				waitForShowcasePageReady(browser);
				componentLinkElements = browser.findElements(By.xpath(componentLinkXpath));
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
			browser.get(plutoUseCaseURL);
		}
		else {
			browser.get(TEST_CONTEXT_URL + "/" + componentPrefix + "/" + componentName.toLowerCase(Locale.ENGLISH) +
				"/" + componentUseCase);
		}

		waitForShowcasePageReady(browser);
	}

	/**
	 * Test that the web page shows an error message when a value is required and an empty value is submitted.
	 */
	protected void testRequiredCheckboxError(Browser browser) {

		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementNotPresent(browser, valueIsRequiredError1Xpath);
		browser.click(requiredCheckbox1Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementVisible(browser, valueIsRequiredError1Xpath);
	}

	protected void waitForShowcasePageReady(Browser browser) {
		browser.waitUntil(ExpectedConditions.jsReturnsValue("return window.liferay_faces_showcase_ready;"));
	}
}
