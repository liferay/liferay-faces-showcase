/**
 * Copyright (c) 2000-2021 Liferay, Inc. All rights reserved.
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
public class InputHiddenValidationTester extends InputHiddenTester {

	@Test
	public void runInputHiddenValidationTest() throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, "inputHidden", "validation");

		// Test that a hidden valid value submits successfully.
		String text = "test@liferay.com";
		String validButton1Xpath = "(//button[contains(text(),'a valid')])[1]";
		browserDriver.clickElement(validButton1Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);

		WaitingAsserter waitingAsserter = getWaitingAsserter();
		waitingAsserter.assertTextPresentInElement(text, modelValue1Xpath);

		// Test that the web page shows an error message when an invalid value is submitted.
		String invalidButton1Xpath = "(//button[contains(text(),'an invalid')])[1]";
		browserDriver.clickElement(invalidButton1Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);
		waitingAsserter.assertElementDisplayed(error1Xpath);

		// Test that the hidden value clears successfully.
		browserDriver.clickElement(validButton1Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);
		browserDriver.clickElement(clearButton1Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);
		waitingAsserter.assertElementDisplayed(error1Xpath);

		// Test that a hidden valid value submits successfully.
		String validButton2Xpath = "(//button[contains(text(),'a valid')])[2]";
		browserDriver.clickElement(validButton2Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton2Xpath);
		waitingAsserter.assertTextPresentInElement(text, modelValue2Xpath);

		// Test that the web page shows an error message when an invalid value is submitted.
		String invalidButton2Xpath = "(//button[contains(text(),'an invalid')])[2]";
		browserDriver.clickElement(invalidButton2Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton2Xpath);
		waitingAsserter.assertElementDisplayed(error2Xpath);

		// Test that the hidden value clears successfully.
		browserDriver.clickElement(invalidButton2Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton2Xpath);
		browserDriver.clickElement(clearButton2Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton2Xpath);
		waitingAsserter.assertElementDisplayed(error2Xpath);

	}
}
