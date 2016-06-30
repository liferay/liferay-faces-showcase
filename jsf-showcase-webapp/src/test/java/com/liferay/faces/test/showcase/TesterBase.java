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

import org.openqa.selenium.WebElement;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.IntegrationTesterBase;
import com.liferay.faces.test.selenium.TestUtil;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class TesterBase extends IntegrationTesterBase {

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
		"(//div[contains(@class,'field form-group has-error') and contains(., 'Validation Error: Value is required.')])[1]";

	static {

		String projectVersion = System.getProperty("integration.showcase.version");
		String defaultContext = "/com.liferay.faces.demo.jsf.showcase.webapp-" + projectVersion +
			"/web/guest/showcase/-/component/h";

		if ("liferay".equals(TestUtil.CONTAINER)) {
			defaultContext = "/web/guest/jsf-showcase/-/jsf-tag/h";
		}
		else if ("pluto".equals(TestUtil.CONTAINER)) {
			defaultContext = TestUtil.DEFAULT_PLUTO_CONTEXT + "/jsf-showcase";
		}

		String context = TestUtil.getSystemPropertyOrDefault("integration.context", defaultContext);
		TEST_CONTEXT_URL = TestUtil.BASE_URL + context;
	}

	// Private Members
	private boolean firstPlutoUseCaseNavigation = true;

	protected void navigateToUseCase(Browser browser, String useCaseURL) {

		if ("pluto".equals(TestUtil.CONTAINER)) {

			if (firstPlutoUseCaseNavigation) {

				// Note: in pluto it is necessary to navigate to the showcase so that elements can be used to obtain the
				// useCaseURL in the navigateToUseCase() method.
				browser.get(TEST_CONTEXT_URL);
				firstPlutoUseCaseNavigation = false;
			}

			// Since pluto does not support friendly URLs, obtain the "general" use case URL from the showcase accordion
			// and replace "general" with the specified use case. Note: non-"general" use cases are shown conditionally
			// so we cannot rely on those links being present, but the "general" use case links are always present.
			int index = useCaseURL.lastIndexOf("/");
			String componentUseCase = useCaseURL.substring(index + 1);
			String showcaseURLWithoutUseCase = useCaseURL.substring(0, index);
			index = showcaseURLWithoutUseCase.lastIndexOf("/");

			String componentName = showcaseURLWithoutUseCase.substring(index + 1);
			WebElement componentLinkElement = browser.findElementByXpath("//a[contains(@href, '" + componentName +
					"') and contains(@href, 'general')]");
			String hrefAttribute = componentLinkElement.getAttribute("href");
			String plutoUseCaseURL = hrefAttribute.replace("general", componentUseCase);
			browser.get(plutoUseCaseURL);
		}
		else {
			browser.get(useCaseURL);
		}
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
}
