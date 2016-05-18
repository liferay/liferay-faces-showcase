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
package com.liferay.faces.test.showcase.inputtext;

import org.junit.Test;

import org.openqa.selenium.WebElement;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class InputTextValidationTester extends InputTextTester {

	@Test
	public void runInputTextValidationTest() throws Exception {

		Browser browser = Browser.getInstance();
		browser.get(inputTextURL + "/validation");

		// Wait to begin the test until the submit button is rendered.
		browser.waitForElementVisible(submitButtonXpath);

		// Test that the web page shows an error message when an invalid value is submitted.
		WebElement input = browser.findElementByXpath(inputXpath);
		input.clear();

		String invalidText = "HelloWorldcom";
		input.sendKeys(invalidText);
		browser.clickAndWaitForAjaxRerender(submitButtonXpath);
		SeleniumAssert.assertElementVisible(browser, errorXpath);

		// Test that a valid value submits successfully.
		input = browser.findElementByXpath(inputXpath);
		input.clear();

		String text = "Hello@World.com";
		input.sendKeys(text);
		browser.clickAndWaitForAjaxRerender(submitButtonXpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValueXpath, text);

		// Test that the web page shows an error message when an invalid value is submitted.
		input = browser.findElementByXpath(inputXpathRight);
		input.clear();
		input.sendKeys(invalidText);
		browser.clickAndWaitForAjaxRerender(submitButtonXpathRight);
		SeleniumAssert.assertElementVisible(browser, errorXpath);

		// Test that a valid value submits successfully.
		input = browser.findElementByXpath(inputXpathRight);
		input.clear();
		input.sendKeys(text);
		browser.clickAndWaitForAjaxRerender(submitButtonXpathRight);
		SeleniumAssert.assertElementTextVisible(browser, modelValueXpathRight, text);
	}
}
