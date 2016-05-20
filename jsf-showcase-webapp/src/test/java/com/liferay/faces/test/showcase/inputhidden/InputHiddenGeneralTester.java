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
public class InputHiddenGeneralTester extends InputHiddenTester {

	@Test
	public void runInputHiddenGeneralTest() throws Exception {

		Browser browser = Browser.getInstance();
		browser.get(inputHiddenURL + "/general");

		// Wait to begin the test until the submit button is rendered.
		browser.waitForElementVisible(submitButton1Xpath);

		// Test that an empty value submits successfully.
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementVisible(browser, success1Xpath);

		// Test that the web page shows an error message when a value is required and an empty value is submitted.
		browser.click(requiredCheckbox1Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementVisible(browser, error1Xpath);

		// Test that a hidden value submits successfully.
		browser.click(copyValidValueButton1Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "1234");

		// Test that a hidden value clears successfully.
		browser.click(requiredCheckbox1Xpath);
		browser.click(copyValidValueButton1Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		browser.click(clearButton1Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementPresent(browser, modelValueEmpty1Xpath);
	}
}
