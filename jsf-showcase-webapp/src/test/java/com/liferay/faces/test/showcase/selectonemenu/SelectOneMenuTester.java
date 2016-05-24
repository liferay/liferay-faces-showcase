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
package com.liferay.faces.test.showcase.selectonemenu;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;
import com.liferay.faces.test.showcase.select.SelectTester;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class SelectOneMenuTester extends SelectTester {

	protected void runSelectOneMenuConversionTest(String url) {
		Browser browser = Browser.getInstance();
		browser.get(url);

		// Wait to begin the test until an element is rendered.
		browser.waitForElementVisible(option1Xpath);

		// Test that the first value in the menu has not yet been clicked
		SeleniumAssert.assertElementNotPresent(browser, modelValueElement1Xpath);

		// Test that the first incorrect value in the menu submits successfully.
		browser.click(option1Xpath);

		String answer1 = "Oct 31, 1517 AD";
		browser.centerElementInView(submitButton1Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer1);
		SeleniumAssert.assertElementVisible(browser, conversionIncorrectMessage1Xpath);

		// Test that only the third correct value in the menu submits successfully.
		browser.click(option2Xpath);
		browser.centerElementInView(submitButton1Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementTextInvisible(browser, modelValue1Xpath, answer1);

		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "Jul 4, 1776 AD");
		SeleniumAssert.assertElementVisible(browser, conversionCorrectMessage1Xpath);
	}

	protected void runSelectOneMenuDataModelTest(Browser browser) {

		// Wait to begin the test until an element is rendered.
		browser.waitForElementVisible(option1Xpath);

		// Test that the first value in the menu has not yet been clicked
		SeleniumAssert.assertElementNotPresent(browser, modelValueElement1Xpath);

		// Test that the first value in the menu submits successfully.
		browser.click(option1Xpath);
		browser.centerElementInView(submitButton1Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);

		String answer1 = "1";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer1);

		// Test that only the fourth value in the menu submits successfully.
		browser.click(option4Xpath);
		browser.centerElementInView(submitButton1Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementTextInvisible(browser, modelValue1Xpath, answer1);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "4");
	}

	protected void runSelectOneMenuDefaultValueTest(String url) {
		Browser browser = Browser.getInstance();
		browser.get(url);

		// Wait to begin the test until an element is rendered.
		browser.waitForElementVisible(modelValue1Xpath);

		// Test that the menu value is submitted by default.
		String answer3 = "3";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer3);

		// Test that the menu value submits successfully.
		browser.click(option1Xpath);
		browser.centerElementInView(submitButton1Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementTextInvisible(browser, modelValue1Xpath, answer3);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "1");
	}

	protected void runSelectOneMenuGeneralTest(Browser browser) {

		// Wait to begin the test until the submit button is rendered.
		browser.waitForElementVisible(submitButton1Xpath);

		// Test that the web page shows an error message when a value is required and an empty value is submitted.
		testRequiredCheckbox(browser);

		// Test that the first value in the menu submits successfully.
		browser.click(option2Xpath);
		browser.centerElementInView(submitButton1Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);

		String answer1 = "1";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer1);

		// Test that only the third value in the menu submits successfully.
		browser.click(option4Xpath);
		browser.centerElementInView(submitButton1Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementTextInvisible(browser, modelValue1Xpath, answer1);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "3");
	}

	protected void runSelectOneMenuImmediateTest(String url) {
		Browser browser = Browser.getInstance();
		browser.get(url);

		// Wait to begin the test until the submit button is rendered.
		browser.waitForElementVisible(submitButton1Xpath);

		// Test that the first value in the menu has not yet been clicked
		SeleniumAssert.assertElementNotPresent(browser, modelValueElement1Xpath);

		// Test that the first value in the menu submits successfully.
		browser.click(option1Xpath);
		browser.centerElementInView(submitButton1Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);

		String answer1 = "1";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer1);

		// Test that only the third value in the menu submits successfully.
		browser.click(option3Xpath);
		browser.centerElementInView(submitButton1Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementTextInvisible(browser, modelValue1Xpath, answer1);

		String answer3 = "3";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer3);
		SeleniumAssert.assertElementVisible(browser, immediateMessage1Xpath);

		// Test the second group of menu values successfully.
		browser.click(select2Option1Xpath);
		browser.centerElementInView(submitButton2Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton2Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue2Xpath, answer1);

		browser.click(select2Option3Xpath);
		browser.centerElementInView(submitButton2Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton2Xpath);
		SeleniumAssert.assertElementTextInvisible(browser, modelValue2Xpath, answer1);
		SeleniumAssert.assertElementTextVisible(browser, modelValue2Xpath, answer3);
		SeleniumAssert.assertElementVisible(browser, immediateMessage2Xpath);
	}

	protected void runSelectOneMenuInstantAjaxTest(String url, String select1Xpath) {
		Browser browser = Browser.getInstance();
		browser.get(url);

		// Wait to begin the test until an element is rendered.
		browser.waitForElementVisible(option4Xpath);

		// Test that the first value in the menu has not yet been clicked
		SeleniumAssert.assertElementNotPresent(browser, modelValueElement1Xpath);

		// Test that the third value in the menu submits successfully.
		String answer3 = "3";
		selectByValueAndWaitForAjaxRerender(browser, select1Xpath, answer3);

		// Test that only the first value in the menu submits successfully.
		String answer1 = "1";
		selectByValueAndWaitForAjaxRerender(browser, select1Xpath, answer1);
		SeleniumAssert.assertElementTextInvisible(browser, modelValue1Xpath, answer3);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer1);
	}
}
