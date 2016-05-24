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
package com.liferay.faces.test.showcase.inputhidden;

import org.junit.Test;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class InputHiddenImmediateTester extends InputHiddenTester {

	@Test
	public void runInputHiddenImmediateTest() throws Exception {

		Browser browser = Browser.getInstance();
		browser.get(inputHiddenURL + "/immediate");

		// Wait to begin the test until the submit button is rendered.
		browser.waitForElementVisible(submitButton1Xpath);

		// Test that the hidden value submits successfully and the valueChangeListener method is called during the
		// APPLY_REQUEST_VALUES phase.
		browser.click(copyValidValueButton1Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);

		String text = "1234";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, text);
		SeleniumAssert.assertElementVisible(browser, immediateMessage1Xpath);

		browser.click(clearButton1Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementPresent(browser, modelValueEmpty1Xpath);

		// Test that the value submits successfully and the valueChangeListener method is called during the
		// PROCESS_VALIDATIONS phase.
		browser.click(copyValidValueButton2Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton2Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue2Xpath, text);
		SeleniumAssert.assertElementVisible(browser, immediateMessage2Xpath);

		browser.click(clearButton2Xpath);
		browser.clickAndWaitForAjaxRerender(submitButton2Xpath);
		SeleniumAssert.assertElementPresent(browser, modelValueEmpty2Xpath);
	}
}
