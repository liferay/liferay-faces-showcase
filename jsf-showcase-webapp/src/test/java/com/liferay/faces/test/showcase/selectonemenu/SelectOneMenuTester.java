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
package com.liferay.faces.test.showcase.selectonemenu;

import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.WaitingAsserter;
import com.liferay.faces.test.showcase.select.SelectOneTester;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class SelectOneMenuTester extends SelectOneTester {

	protected void runSelectOneMenuGeneralTest(String componentName) {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, componentName, "general");
		browserDriver.centerElementInCurrentWindow(select1Xpath);

		WaitingAsserter waitingAsserter = getWaitingAsserter();
		testRequiredCheckboxError(browserDriver, waitingAsserter);

		// Test that the selected option submits successfully and the "required" error message disappears. Note: In the
		// general use case, the first option is the "-- Select --" noSelectionOption, so select the second option
		// (which has a value of 1) instead of the first one.
		String option2Xpath = "(" + select1Xpath + OPTION_CHILD_XPATH + ")[2]";
		browserDriver.clickElement(option2Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);
		waitingAsserter.assertElementNotDisplayed(valueIsRequiredError1Xpath);

		String answer1 = "1";
		waitingAsserter.assertTextPresentInElement(answer1, modelValue1Xpath);

		// Test that selecting another value changes the model value.
		String option4Xpath = "(" + select1Xpath + OPTION_CHILD_XPATH + ")[4]";
		browserDriver.clickElement(option4Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);
		waitingAsserter.assertTextNotPresentInElement(answer1, modelValue1Xpath);
		waitingAsserter.assertTextPresentInElement("3", modelValue1Xpath);
	}

	protected void runSelectOneMenuInstantAjaxTest(String componentName) throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, componentName, "instant-ajax");

		// Test that the selected option submits successfully. Note: selectOneMenu will not perform an ajax request if
		// the displayed option is clicked, so the test clicks the third option instead.
		String option3Xpath = "(" + select1Xpath + OPTION_CHILD_XPATH + ")[3]";
		clickOptionAndWaitForRerender(browserDriver, option3Xpath);

		WaitingAsserter waitingAsserter = getWaitingAsserter();
		String answer3 = "3";
		waitingAsserter.assertTextPresentInElement(answer3, modelValue1Xpath);

		// Test that selecting another value changes the model value.
		String option1Xpath = "(" + select1Xpath + OPTION_CHILD_XPATH + ")[1]";
		clickOptionAndWaitForRerender(browserDriver, option1Xpath);
		waitingAsserter.assertTextNotPresentInElement(answer3, modelValue1Xpath);
		waitingAsserter.assertTextPresentInElement("1", modelValue1Xpath);
	}
}
