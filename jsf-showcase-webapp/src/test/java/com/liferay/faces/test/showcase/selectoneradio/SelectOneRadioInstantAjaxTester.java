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
		navigateToUseCase(browser, "selectOneRadio", "instant-ajax");

		// Test that the selected option submits successfully. Note: selectOneMenu will not perform an ajax request if
		// the visible option is clicked, so the test clicks the third option instead.
		String option1Xpath = "(" + selectOneRadio1Xpath + RADIO_CHILD_XPATH + ")[1]";
		clickAndWaitForAjaxRerender(browser, option1Xpath);

		String answer1 = "1";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer1);

		// Test that selecting another value changes the model value.
		String option3Xpath = "(" + selectOneRadio1Xpath + RADIO_CHILD_XPATH + ")[3]";
		clickAndWaitForAjaxRerender(browser, option3Xpath);
		SeleniumAssert.assertElementTextInvisible(browser, modelValue1Xpath, answer1);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "3");
	}
}
