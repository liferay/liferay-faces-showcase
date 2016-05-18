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
		browser.waitForElementVisible(submitButtonXpath);

		// Test that a hidden valid value submits successfully.
		String text = "test@liferay.com";
		String hiddenButtonValid = "(//button[contains(text(),'a valid')])[1]";
		browser.click(hiddenButtonValid);
		browser.clickAndWaitForAjaxRerender(submitButtonXpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValueXpath, text);

		// Test that the web page shows an error message when an invalid value is submitted.
		String hiddenButtonInvalid = "(//button[contains(text(),'an invalid')])[1]";
		browser.click(hiddenButtonInvalid);
		browser.clickAndWaitForAjaxRerender(submitButtonXpath);
		SeleniumAssert.assertElementVisible(browser, errorXpath);

		// Test that the hidden value clears successfully.
		browser.click(hiddenButtonValid);
		browser.clickAndWaitForAjaxRerender(submitButtonXpath);
		browser.click(clearButtonXpath);
		browser.clickAndWaitForAjaxRerender(submitButtonXpath);
		SeleniumAssert.assertElementVisible(browser, errorXpath);

		// Test that a hidden valid value submits successfully.
		hiddenButtonValid = "(//button[contains(text(),'a valid')])[2]";
		browser.click(hiddenButtonValid);
		browser.clickAndWaitForAjaxRerender(submitButtonXpathRight);
		SeleniumAssert.assertElementTextVisible(browser, modelValueXpathRight, text);

		// Test that the web page shows an error message when an invalid value is submitted.
		hiddenButtonInvalid = "(//button[contains(text(),'an invalid')])[2]";
		browser.click(hiddenButtonInvalid);
		browser.clickAndWaitForAjaxRerender(submitButtonXpathRight);
		SeleniumAssert.assertElementVisible(browser, errorXpathRight);

		// Test that the hidden value clears successfully.
		browser.click(hiddenButtonValid);
		browser.clickAndWaitForAjaxRerender(submitButtonXpathRight);
		browser.click(clearButtonXpathRight);
		browser.clickAndWaitForAjaxRerender(submitButtonXpathRight);
		SeleniumAssert.assertElementVisible(browser, errorXpathRight);

	}
}
