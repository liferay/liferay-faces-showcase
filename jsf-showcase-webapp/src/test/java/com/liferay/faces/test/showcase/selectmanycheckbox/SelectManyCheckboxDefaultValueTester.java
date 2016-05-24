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
public class SelectManyCheckboxDefaultValueTester extends SelectManyCheckboxTester {

	@Test
	public void runSelectManyCheckboxDefaultValueTest() throws Exception {
		Browser browser = Browser.getInstance();
		browser.get(selectManyCheckboxURL + "/default-value");

		// Wait to begin the test until the submit button is rendered.
		browser.waitForElementVisible(submitButton1Xpath);

		// Test that the checked values are submitted by default.
		String answer2 = "2";
		String answer4 = "4";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer2);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer4);

		// Test that the checked value submits successfully.
		String answer1 = "1";
		browser.click(manyCheckbox1Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer1);
	}
}
