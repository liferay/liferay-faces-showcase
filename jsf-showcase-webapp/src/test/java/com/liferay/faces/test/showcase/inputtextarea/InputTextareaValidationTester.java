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
package com.liferay.faces.test.showcase.inputtextarea;

import org.junit.Test;

import org.openqa.selenium.WebElement;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class InputTextareaValidationTester extends InputTextareaTester {

	@Test
	public void runInputTextareaValidationTest() throws Exception {

		Browser browser = Browser.getInstance();
		browser.get(inputTextareaURL + "/validation");

		// Wait to begin the test until the submit button is rendered.
		browser.waitForElementVisible(submitButton1Xpath);

		// Test that the web page shows an error message when an invalid value is submitted.
		WebElement input = browser.findElementByXpath(textarea1Xpath);
		input.clear();

		String invalidText = "HelloWorldcom";
		input.sendKeys(invalidText);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementVisible(browser, error1Xpath);

		// Test that a valid value submits successfully.
		input = browser.findElementByXpath(textarea1Xpath);
		input.clear();

		String text = "Hello@World.com";
		input.sendKeys(text);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, text);

		// Test that the web page shows an error message when an invalid value is submitted.
		input = browser.findElementByXpath(textarea2Xpath);
		input.clear();
		input.sendKeys(invalidText);
		browser.clickAndWaitForAjaxRerender(submitButton2Xpath);
		SeleniumAssert.assertElementVisible(browser, error1Xpath);

		// Test that a valid value submits successfully.
		input = browser.findElementByXpath(textarea2Xpath);
		input.clear();
		input.sendKeys(text);
		browser.clickAndWaitForAjaxRerender(submitButton2Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue2Xpath, text);
	}
}
