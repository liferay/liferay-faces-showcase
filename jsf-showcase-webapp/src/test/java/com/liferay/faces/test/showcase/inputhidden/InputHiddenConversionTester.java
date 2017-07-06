/**
 * Copyright (c) 2000-2017 Liferay, Inc. All rights reserved.
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
public class InputHiddenConversionTester extends InputHiddenTester {

	@Test
	public void runInputHiddenConversionTest() throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, "inputHidden", "conversion");

		// Test that a hidden valid value submits successfully.
		browserDriver.clickElement(copyValidValueButton1Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);

		WaitingAsserter waitingAsserter = getWaitingAsserter();
		waitingAsserter.assertTextPresentInElement("Apr 5, 0033", modelValue1Xpath);

		// Test that the hidden value clears successfully.
		browserDriver.clickElement(clearButton1Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);
		waitingAsserter.assertElementPresent(modelValueEmpty1Xpath);

		// Test that a hidden valid value submits successfully.
		browserDriver.clickElement(copyValidValueButton2Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton2Xpath);
		waitingAsserter.assertTextPresentInElement("04/05/0033", modelValue2Xpath);

		// Test that the hidden value clears successfully.
		browserDriver.clickElement(clearButton2Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton2Xpath);
		waitingAsserter.assertElementPresent(modelValueEmpty2Xpath);
	}
}
