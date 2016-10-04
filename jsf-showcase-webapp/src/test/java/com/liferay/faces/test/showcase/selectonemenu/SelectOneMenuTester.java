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
package com.liferay.faces.test.showcase.selectonemenu;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;
import com.liferay.faces.test.showcase.select.SelectOneTester;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class SelectOneMenuTester extends SelectOneTester {

	protected void runSelectOneMenuGeneralTest(String componentName) {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentName, "general");
		testRequiredCheckboxError(browser);

		// Test that the selected option submits successfully and the "required" error message disappears. Note: In the
		// general use case, the first option is the "-- Select --" noSelectionOption, so select the second option
		// (which has a value of 1) instead of the first one.
		String option2Xpath = "(" + select1Xpath + OPTION_CHILD_XPATH + ")[2]";
		browser.click(option2Xpath);
		clickAndWaitForAjaxRerender(browser, submitButton1Xpath);
		SeleniumAssert.assertElementNotPresent(browser, valueIsRequiredError1Xpath);

		String answer1 = "1";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer1);

		// Test that selecting another value changes the model value.
		String option4Xpath = "(" + select1Xpath + OPTION_CHILD_XPATH + ")[4]";
		browser.click(option4Xpath);
		clickAndWaitForAjaxRerender(browser, submitButton1Xpath);
		SeleniumAssert.assertElementTextInvisible(browser, modelValue1Xpath, answer1);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "3");
	}

	protected void runSelectOneMenuInstantAjaxTest(String componentName) throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentName, "instant-ajax");

		// Test that the selected option submits successfully. Note: selectOneMenu will not perform an ajax request if
		// the visible option is clicked, so the test clicks the third option instead.
		String option3Xpath = "(" + select1Xpath + OPTION_CHILD_XPATH + ")[3]";
		clickOptionAndWaitForAjaxRerender(browser, option3Xpath);

		String answer3 = "3";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer3);

		// Test that selecting another value changes the model value.
		String option1Xpath = "(" + select1Xpath + OPTION_CHILD_XPATH + ")[1]";
		clickOptionAndWaitForAjaxRerender(browser, option1Xpath);
		SeleniumAssert.assertElementTextInvisible(browser, modelValue1Xpath, answer3);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "1");
	}
}
