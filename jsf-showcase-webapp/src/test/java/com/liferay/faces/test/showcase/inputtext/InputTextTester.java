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

import org.openqa.selenium.WebElement;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;
import com.liferay.faces.test.showcase.input.InputTester;


/**
 * @author  Kyle Stiemann
 */
public class InputTextTester extends InputTester {

	// Common Xpath
	protected static final String input1Xpath = "(//input[contains(@id,':text')])[1]";
	protected static final String input2Xpath = "(//input[contains(@id,':text')])[2]";

	protected void runInputTextConversionTest(String componentName, String inputText1Xpath, String inputText2Xpath)
		throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentName, "conversion");

		// Test that the web page shows an error message when an invalid value is submitted.
		WebElement input = browser.findElementByXpath(inputText1Xpath);
		input.clear();

		String invalidText1 = "apr 3 33";
		input.sendKeys(invalidText1);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementVisible(browser, error1Xpath);

		// Test that a valid value submits successfully.
		input = browser.findElementByXpath(inputText1Xpath);
		input.clear();

		String text1 = "apr 3, 33";
		input.sendKeys(text1);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);

		String textOutput1 = "Apr 3, 0033";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, textOutput1);

		// Test that the web page shows an error message when an invalid value is submitted.
		input = browser.findElementByXpath(inputText2Xpath);
		input.clear();

		String invalidText2 = "4/333";
		input.sendKeys(invalidText2);
		browser.clickAndWaitForAjaxRerender(submitButton2Xpath);
		SeleniumAssert.assertElementVisible(browser, error1Xpath);

		// Test that a valid value submits successfully.
		input = browser.findElementByXpath(inputText2Xpath);
		input.clear();

		String text2 = "4/3/33";
		input.sendKeys(text2);
		browser.clickAndWaitForAjaxRerender(submitButton2Xpath);

		String textOutput2 = "04/03/0033";
		SeleniumAssert.assertElementTextVisible(browser, modelValue2Xpath, textOutput2);
	}

	protected void runInputTextGeneralTest(String componentName, String inputText1Xpath) throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentName, "general");

		// Test that an empty value submits successfully.
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementVisible(browser, success1Xpath);

		// Test that the web page shows an error message when a value is required and an empty value is submitted.
		testRequiredCheckboxError(browser);

		// Test that a text value submits successfully and the "required" error message disappears.
		String text = "Hello World!";
		browser.sendKeys(inputText1Xpath, text);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, text);
		SeleniumAssert.assertElementNotPresent(browser, valueIsRequiredError1Xpath);
	}

	protected void runInputTextImmediateTest(String componentName, String inputText1Xpath, String inputText2Xpath)
		throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentName, "immediate");

		// Test that the value submits successfully and the valueChangeListener method is called during the
		// APPLY_REQUEST_VALUES phase.
		String text = "Hello World!";
		browser.sendKeys(inputText1Xpath, text);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, text);
		SeleniumAssert.assertElementVisible(browser, immediateMessage1Xpath);

		// Test that the value submits successfully and the valueChangeListener method is called during the
		// PROCESS_VALIDATIONS phase.
		browser.sendKeys(inputText2Xpath, text);
		browser.clickAndWaitForAjaxRerender(submitButton2Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue2Xpath, text);
		SeleniumAssert.assertElementVisible(browser, immediateMessage2Xpath);
	}

	protected void runInputTextValidationTest(String componentName, String inputText1Xpath, String inputText2Xpath)
		throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentName, "validation");

		// Test that the web page shows an error message when an invalid value is submitted.
		WebElement input = browser.findElementByXpath(inputText1Xpath);
		input.clear();

		String invalidText = "HelloWorldcom";
		input.sendKeys(invalidText);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementVisible(browser, error1Xpath);

		// Test that a valid value submits successfully.
		input = browser.findElementByXpath(inputText1Xpath);
		input.clear();

		String text = "Hello@World.com";
		input.sendKeys(text);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, text);

		// Test that the web page shows an error message when an invalid value is submitted.
		input = browser.findElementByXpath(inputText2Xpath);
		input.clear();
		input.sendKeys(invalidText);
		browser.clickAndWaitForAjaxRerender(submitButton2Xpath);
		SeleniumAssert.assertElementVisible(browser, error1Xpath);

		// Test that a valid value submits successfully.
		input = browser.findElementByXpath(inputText2Xpath);
		input.clear();
		input.sendKeys(text);
		browser.clickAndWaitForAjaxRerender(submitButton2Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue2Xpath, text);
	}
}
