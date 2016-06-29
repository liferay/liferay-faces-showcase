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
package com.liferay.faces.test.showcase.selectmanycheckbox;

import org.junit.Test;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class SelectManyCheckboxConversionTester extends SelectManyCheckboxTester {

	@Test
	public void runSelectManyCheckboxConversionTest() throws Exception {
		Browser browser = Browser.getInstance();
		browser.get(selectManyCheckboxURL + "/conversion");

		// Wait to begin the test until the submit button is rendered.
		browser.waitForElementVisible(submitButton1Xpath);

		// Test that the first checkbox has not yet been clicked.
		SeleniumAssert.assertElementNotPresent(browser, modelValueElement1Xpath);

		// Test that the checked values submit successfully.
		browser.click(manyCheckbox1Xpath);
		browser.click(manyCheckbox3Xpath);
		browser.click(manyCheckbox4Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "Apr 5, 0033 AD");
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "Jul 4, 1776 AD");
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "Jul 14, 1789 AD");

		// Test that the incorrect and correct checked values submit successfully.
		SeleniumAssert.assertElementVisible(browser, conversionIncorrectMessage1Xpath);
		browser.click(manyCheckbox1Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementVisible(browser, conversionCorrectMessage1Xpath);

	}
}
