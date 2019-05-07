/**
 * Copyright (c) 2000-2019 Liferay, Inc. All rights reserved.
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
public class SelectManyCheckboxInstantAjaxTester extends SelectManyCheckboxTester {

	@Test
	public void runSelectManyCheckboxInstantAjaxTest() throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, "selectManyCheckbox", "instant-ajax");

		// Test that multiple checked checkboxes submit successfully.
		String checkbox1Xpath = "(" + CHECKBOX_CHILD_XPATH + ")[1]";
		browserDriver.clickElementAndWaitForRerender(checkbox1Xpath);

		String checkbox3Xpath = "(" + CHECKBOX_CHILD_XPATH + ")[3]";
		browserDriver.clickElementAndWaitForRerender(checkbox3Xpath);

		String checkbox4Xpath = "(" + CHECKBOX_CHILD_XPATH + ")[4]";
		browserDriver.clickElementAndWaitForRerender(checkbox4Xpath);

		WaitingAsserter waitingAsserter = getWaitingAsserter();
		waitingAsserter.assertTextPresentInElement("1", modelValue1Xpath);
		waitingAsserter.assertTextPresentInElement("3", modelValue1Xpath);
		waitingAsserter.assertTextPresentInElement("4", modelValue1Xpath);

		// Test that unchecking a checkbox removes the value from the model.
		browserDriver.clickElementAndWaitForRerender(checkbox1Xpath);
		waitingAsserter.assertTextNotPresentInElement("1", modelValue1Xpath);
		waitingAsserter.assertTextPresentInElement("3", modelValue1Xpath);
		waitingAsserter.assertTextPresentInElement("4", modelValue1Xpath);
	}
}
