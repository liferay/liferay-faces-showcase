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
package com.liferay.faces.test.showcase.inputhidden;

import org.junit.Test;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class InputHiddenValidationTester extends InputHiddenTester {

	@Test
	public void runInputHiddenValidationTest() throws Exception {

		Browser browser = Browser.getInstance();
		browser.get(inputHiddenURL + "/validation");

		// Wait to begin the test until the submit button is rendered.
		browser.waitForElementVisible(submitButton1Xpath);

		// Test that a hidden valid value submits successfully.
		String text = "test@liferay.com";
		String validButton1Xpath = "(//button[contains(text(),'a valid')])[1]";
		browser.click(validButton1Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, text);

		// Test that the web page shows an error message when an invalid value is submitted.
		String invalidButton1Xpath = "(//button[contains(text(),'an invalid')])[1]";
		browser.click(invalidButton1Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementVisible(browser, error1Xpath);

		// Test that the hidden value clears successfully.
		browser.click(validButton1Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		browser.click(clearButton1Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementVisible(browser, error1Xpath);

		// Test that a hidden valid value submits successfully.
		String validButton2Xpath = "(//button[contains(text(),'a valid')])[2]";
		browser.click(validButton2Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton2Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue2Xpath, text);

		// Test that the web page shows an error message when an invalid value is submitted.
		String invalidButton2Xpath = "(//button[contains(text(),'an invalid')])[2]";
		browser.click(invalidButton2Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton2Xpath);
		SeleniumAssert.assertElementVisible(browser, error2Xpath);

		// Test that the hidden value clears successfully.
		browser.click(invalidButton2Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton2Xpath);
		browser.click(clearButton2Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton2Xpath);
		SeleniumAssert.assertElementVisible(browser, error2Xpath);

	}
}
