/**
 * Copyright (c) 2000-2020 Liferay, Inc. All rights reserved.
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
public class InputHiddenGeneralTester extends InputHiddenTester {

	@Test
	public void runInputHiddenGeneralTest() throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, "inputHidden", "general");

		// Test that an empty value submits successfully.
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);

		WaitingAsserter waitingAsserter = getWaitingAsserter();
		waitingAsserter.assertElementDisplayed(success1Xpath);

		// Test that a hidden value submits successfully.
		browserDriver.clickElement(copyValidValueButton1Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);
		waitingAsserter.assertTextPresentInElement("1234", modelValue1Xpath);

		// Test that the hidden value clears successfully.
		browserDriver.clickElement(clearButton1Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);
		waitingAsserter.assertElementPresent(modelValueEmpty1Xpath);

		// Test that the web page shows an error message when a value is required and an empty value is submitted.
		testRequiredCheckboxError(browserDriver, waitingAsserter);

		// Test that the error message disappears when a valid value is submitted.
		browserDriver.clickElement(copyValidValueButton1Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);
		waitingAsserter.assertTextPresentInElement("1234", modelValue1Xpath);
		waitingAsserter.assertElementNotDisplayed(valueIsRequiredError1Xpath);
	}
}
