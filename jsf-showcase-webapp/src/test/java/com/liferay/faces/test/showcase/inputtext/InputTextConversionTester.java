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
public class InputTextConversionTester extends InputTextTester {

	@Test
	public void runInputTextConversionTest() throws Exception {

		Browser browser = Browser.getInstance();
		browser.get(inputTextURL + "/conversion");

		// Wait to begin the test until the submit button is rendered.
		browser.waitForElementVisible(submitButton1Xpath);

		// Test that the web page shows an error message when an invalid value is submitted.
		WebElement input = browser.findElementByXpath(input1Xpath);
		input.clear();

		String invalidText1 = "apr 3 33";
		input.sendKeys(invalidText1);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementVisible(browser, error1Xpath);

		// Test that a valid value submits successfully.
		input = browser.findElementByXpath(input1Xpath);
		input.clear();

		String text1 = "apr 3, 33";
		input.sendKeys(text1);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);

		String textOutput1 = "Apr 3, 0033";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, textOutput1);

		// Test that the web page shows an error message when an invalid value is submitted.
		input = browser.findElementByXpath(input2Xpath);
		input.clear();

		String invalidText2 = "4/333";
		input.sendKeys(invalidText2);
		browser.clickAndWaitForAjaxRerender(submitButton2Xpath);
		SeleniumAssert.assertElementVisible(browser, error1Xpath);

		// Test that a valid value submits successfully.
		input = browser.findElementByXpath(input2Xpath);
		input.clear();

		String text2 = "4/3/33";
		input.sendKeys(text2);
		browser.clickAndWaitForAjaxRerender(submitButton2Xpath);

		String textOutput2 = "04/03/0033";
		SeleniumAssert.assertElementTextVisible(browser, modelValue2Xpath, textOutput2);
	}
}
