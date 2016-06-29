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

import org.junit.Test;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class SelectOneRadioImmediateTester extends SelectOneRadioTester {

	@Test
	public void runSelectOneRadioImmediateTest() throws Exception {
		Browser browser = Browser.getInstance();
		browser.get(selectOneRadioURL + "/immediate");

		// Wait to begin the test until the submit button is rendered.
		browser.waitForElementVisible(submitButton1Xpath);

		// Test that the first value of the radio has not yet been submitted
		SeleniumAssert.assertElementNotPresent(browser, modelValueElement1Xpath);

		// Test the first and fourth values of the radios submit successfully.
		String answer1 = "1";
		String answer4 = "4";
		testOneRadios(browser, answer1, answer4);
		SeleniumAssert.assertElementVisible(browser, immediateMessage1Xpath);

		// Test the second group of radio values successfully.
		String oneRadio1Xpath2 = "(//input[contains(@id,':selectOneRadio')])[6]";
		browser.click(oneRadio1Xpath2);
		browser.centerElementInView(submitButton2Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton2Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue2Xpath, answer1);

		String oneRadio4Xpath2 = "(//input[contains(@id,':selectOneRadio')])[9]";
		browser.click(oneRadio4Xpath2);
		browser.clickAndWaitForAjaxRerender(submitButton2Xpath);
		SeleniumAssert.assertElementTextInvisible(browser, modelValue2Xpath, answer1);
		SeleniumAssert.assertElementTextVisible(browser, modelValue2Xpath, answer4);
		SeleniumAssert.assertElementVisible(browser, immediateMessage2Xpath);
	}
}
