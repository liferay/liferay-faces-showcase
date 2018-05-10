/**
 * Copyright (c) 2000-2018 Liferay, Inc. All rights reserved.
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

import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.WaitingAsserter;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class SelectOneRadioInstantAjaxTester extends SelectOneRadioTester {

	@Test
	public void runSelectOneRadioInstantAjaxTest() throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, "selectOneRadio", "instant-ajax");

		// Test that the selected option submits successfully. Note: selectOneMenu will not perform an ajax request if
		// the displayed option is clicked, so the test clicks the third option instead.
		String option1Xpath = "(" + selectOneRadio1Xpath + RADIO_CHILD_XPATH + ")[1]";
		browserDriver.clickElementAndWaitForRerender(option1Xpath);

		WaitingAsserter waitingAsserter = getWaitingAsserter();
		String answer1 = "1";
		waitingAsserter.assertTextPresentInElement(answer1, modelValue1Xpath);

		// Test that selecting another value changes the model value.
		String option3Xpath = "(" + selectOneRadio1Xpath + RADIO_CHILD_XPATH + ")[3]";
		browserDriver.clickElementAndWaitForRerender(option3Xpath);
		waitingAsserter.assertTextNotPresentInElement(answer1, modelValue1Xpath);
		waitingAsserter.assertTextPresentInElement("3", modelValue1Xpath);
	}
}
