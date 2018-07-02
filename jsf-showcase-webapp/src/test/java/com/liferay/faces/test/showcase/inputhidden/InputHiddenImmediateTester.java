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
package com.liferay.faces.test.showcase.inputhidden;

import org.junit.Test;

import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.WaitingAsserter;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class InputHiddenImmediateTester extends InputHiddenTester {

	@Test
	public void runInputHiddenImmediateTest() throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, "inputHidden", "immediate");

		// Test that the hidden value submits successfully and the valueChangeListener method is called during the
		// APPLY_REQUEST_VALUES phase.
		browserDriver.clickElement(copyValidValueButton1Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);

		WaitingAsserter waitingAsserter = getWaitingAsserter();
		String text = "1234";
		waitingAsserter.assertTextPresentInElement(text, modelValue1Xpath);
		waitingAsserter.assertElementDisplayed(immediateMessage1Xpath);

		browserDriver.clickElement(clearButton1Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);
		waitingAsserter.assertElementPresent(modelValueEmpty1Xpath);

		// Test that the value submits successfully and the valueChangeListener method is called during the
		// PROCESS_VALIDATIONS phase.
		browserDriver.clickElement(copyValidValueButton2Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton2Xpath);
		waitingAsserter.assertTextPresentInElement(text, modelValue2Xpath);
		waitingAsserter.assertElementDisplayed(immediateMessage2Xpath);

		browserDriver.clickElement(clearButton2Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton2Xpath);
		waitingAsserter.assertElementPresent(modelValueEmpty2Xpath);
	}
}
