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
package com.liferay.faces.test.showcase.selectmanycheckbox;

import org.junit.Test;

import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.WaitingAsserter;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class SelectManyCheckboxGeneralTester extends SelectManyCheckboxTester {

	@Test
	public void runSelectManyCheckboxGeneralTest() throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, "selectManyCheckbox", "general");

		WaitingAsserter waitingAsserter = getWaitingAsserter();
		testRequiredCheckboxError(browserDriver, waitingAsserter);

		// Test that the checked values submit successfully and the "required" error message disappears.
		testSelectMany(browserDriver, waitingAsserter, selectManyCheckbox1Xpath, CHECKBOX_CHILD_XPATH,
			submitButton1Xpath, modelValue1Xpath);
		waitingAsserter.assertElementNotDisplayed(valueIsRequiredError1Xpath);
	}
}
