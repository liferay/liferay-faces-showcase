/**
 * Copyright (c) 2000-2022 Liferay, Inc. All rights reserved.
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
package com.liferay.faces.test.showcase.selectbooleancheckbox;

import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.WaitingAsserter;
import com.liferay.faces.test.showcase.TesterBase;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class SelectBooleanCheckboxTester extends TesterBase {

	// Common Xpath
	protected static final String checkbox1Xpath = "(//input[contains(@id,':checkbox')])[1]";
	protected static final String checkbox2Xpath = "(//input[contains(@id,':checkbox')])[2]";

	protected void testSelectBooleanCheckbox(BrowserDriver browserDriver, WaitingAsserter waitingAsserter,
		String submitButtonXpath, String modelValueXpath, String checkboxXpath) {

		// Test that an unchecked checkbox submits successfully.
		browserDriver.clickElementAndWaitForRerender(submitButtonXpath);
		waitingAsserter.assertTextPresentInElement("false", modelValueXpath);

		// Test that a checked checkbox submits successfully.
		browserDriver.clickElement(checkboxXpath);
		browserDriver.clickElementAndWaitForRerender(submitButtonXpath);
		waitingAsserter.assertTextPresentInElement("true", modelValueXpath);
	}
}
