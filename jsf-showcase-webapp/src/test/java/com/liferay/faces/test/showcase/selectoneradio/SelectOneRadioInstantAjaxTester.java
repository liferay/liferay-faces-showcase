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
public class SelectOneRadioInstantAjaxTester extends SelectOneRadioTester {

	@Test
	public void runSelectOneRadioInstantAjaxTest() throws Exception {
		Browser browser = Browser.getInstance();
		browser.get(selectOneRadioURL + "/instant-ajax");

		// Wait to begin the test until an element is rendered.
		browser.waitForElementVisible(oneRadio4Xpath);

		// Test that the first value of the radio has not yet been submitted
		SeleniumAssert.assertElementNotPresent(browser, modelValueElement1Xpath);

		// Test that the first value of the radio submits successfully.
		browser.clickAndWaitForAjaxRerender(oneRadio1Xpath);

		String answer1 = "1";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer1);

		// Test that only the fourth value of the radio submits successfully.
		browser.clickAndWaitForAjaxRerender(oneRadio4Xpath);
		SeleniumAssert.assertElementTextInvisible(browser, modelValue1Xpath, answer1);

		String answer4 = "4";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer4);
	}
}
