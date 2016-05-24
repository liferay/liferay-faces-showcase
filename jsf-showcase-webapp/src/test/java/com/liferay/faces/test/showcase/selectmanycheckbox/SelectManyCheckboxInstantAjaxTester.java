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
public class SelectManyCheckboxInstantAjaxTester extends SelectManyCheckboxTester {

	@Test
	public void runSelectManyCheckboxInstantAjaxTest() throws Exception {
		Browser browser = Browser.getInstance();
		browser.get(selectManyCheckboxURL + "/instant-ajax");

		// Wait to begin the test until an element is rendered.
		browser.waitForElementVisible(manyCheckbox4Xpath);

		// Test that the first checkbox has not yet been clicked.
		SeleniumAssert.assertElementNotPresent(browser, modelValueElement1Xpath);

		// Test that the first checked value submits successfully.
		browser.clickAndWaitForAjaxRerender(manyCheckbox1Xpath);

		String answer1 = "1";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer1);

		// Click the same checkbox to deselect:
		// https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/1899#issuecomment-191480860
		browser.clickAndWaitForAjaxRerender(manyCheckbox1Xpath);

		// Test that only the fourth checkbox submits successfully.
		browser.clickAndWaitForAjaxRerender(manyCheckbox4Xpath);
		SeleniumAssert.assertElementTextInvisible(browser, modelValue1Xpath, answer1);

		String answer4 = "4";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer4);
	}
}
