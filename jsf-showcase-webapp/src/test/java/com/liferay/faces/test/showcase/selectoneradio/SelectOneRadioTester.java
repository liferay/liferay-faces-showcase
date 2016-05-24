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
package com.liferay.faces.test.showcase.selectoneradio;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;
import com.liferay.faces.test.showcase.select.SelectTester;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class SelectOneRadioTester extends SelectTester {

	protected static final String selectOneRadioURL = TEST_CONTEXT_URL + "/selectoneradio";
	protected static final String oneRadio1Xpath = "(//input[contains(@id,':selectOneRadio')])[1]";
	protected static final String oneRadio2Xpath = "(//input[contains(@id,':selectOneRadio')])[2]";
	protected static final String oneRadio3Xpath = "(//input[contains(@id,':selectOneRadio')])[3]";
	protected static final String oneRadio4Xpath = "(//input[contains(@id,':selectOneRadio')])[4]";

	protected void testOneRadios(Browser browser, String answer1, String answer4) {

		// Test that the first value of the radio submits successfully.
		browser.click(oneRadio1Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer1);

		// Test that only the fourth value of the radio submits successfully.
		browser.click(oneRadio4Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementTextInvisible(browser, modelValue1Xpath, answer1);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer4);
	}
}
