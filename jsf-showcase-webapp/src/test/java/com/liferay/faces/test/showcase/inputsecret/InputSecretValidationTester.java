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
package com.liferay.faces.test.showcase.inputsecret;

import org.junit.Test;

import org.openqa.selenium.WebElement;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class InputSecretValidationTester extends InputSecretTester {

	@Test
	public void runInputSecretValidationTest() throws Exception {

		Browser browser = Browser.getInstance();
		browser.get(inputSecretURL + "/validation");

		// Wait to begin the test until the submit button is rendered.
		browser.waitForElementVisible(submitButton1Xpath);

		// Test that the web page shows an error message when an invalid value is submitted.
		WebElement input = browser.findElementByXpath(input1Xpath);
		input.clear();

		String invalidText = "HelloWorldcom";
		input.sendKeys("HelloWorldcom");
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementVisible(browser, error1Xpath);

		// Test that a valid value submits successfully.
		input = browser.findElementByXpath(input1Xpath);
		input.clear();

		String text = "Hello@World.com";
		input.sendKeys(text);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, text);

		// Test that the web page shows an error message when an invalid value is submitted.
		input = browser.findElementByXpath(input2Xpath);
		input.clear();
		input.sendKeys(invalidText);
		browser.clickAndWaitForAjaxRerender(submitButton2Xpath);
		SeleniumAssert.assertElementVisible(browser, error1Xpath);

		// Test that a valid value submits successfully.
		input = browser.findElementByXpath(input2Xpath);
		input.clear();
		input.sendKeys(text);
		browser.clickAndWaitForAjaxRerender(submitButton2Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue2Xpath, text);
	}
}
