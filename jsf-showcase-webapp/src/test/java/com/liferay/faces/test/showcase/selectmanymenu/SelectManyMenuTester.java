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
package com.liferay.faces.test.showcase.selectmanymenu;

import org.openqa.selenium.support.ui.Select;

import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.WaitingAsserter;
import com.liferay.faces.test.showcase.select.SelectManyTester;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class SelectManyMenuTester extends SelectManyTester {

	protected void runSelectManyMenuGeneralTest(String componentName) {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, componentName, "general");

		// Test that the web page shows an error message when a value is required and an empty value is submitted.
		Select select = new Select(browserDriver.findElementByXpath(select1Xpath));
		select.deselectAll();

		WaitingAsserter waitingAsserter = getWaitingAsserter();
		testRequiredCheckboxError(browserDriver, waitingAsserter);
		select = new Select(browserDriver.findElementByXpath(select1Xpath));
		select.deselectAll();

		// Test that multiple checked checkboxes submit successfully and the "required" error message disappears. Note:
		// In the general use case, the first option is the "-- Select --" noSelectionOption, so select the second
		// option (which has a value of 1) instead of the first one.
		String option2Xpath = "(" + select1Xpath + OPTION_CHILD_XPATH + ")[2]";
		browserDriver.clickElement(option2Xpath);

		String option4Xpath = "(" + select1Xpath + OPTION_CHILD_XPATH + ")[4]";
		browserDriver.clickElement(option4Xpath);

		String option5Xpath = "(" + select1Xpath + OPTION_CHILD_XPATH + ")[5]";
		browserDriver.clickElement(option5Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);
		waitingAsserter.assertElementNotDisplayed(valueIsRequiredError1Xpath);

		String answer1 = "1";
		waitingAsserter.assertTextPresentInElement(answer1, modelValue1Xpath);

		String answer3 = "3";
		waitingAsserter.assertTextPresentInElement(answer3, modelValue1Xpath);

		String answer4 = "4";
		waitingAsserter.assertTextPresentInElement(answer4, modelValue1Xpath);

		// Test that unchecking the checkbox removes the value from the model.
		// Note: when selecting an <option> element manually, browsers will deselect all other elements. However, this
		// is not the case in selenium, so elements must be deselected by clicking them again.
		// https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/1899#issuecomment-191480860
		browserDriver.clickElement(option2Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);
		waitingAsserter.assertTextNotPresentInElement(answer1, modelValue1Xpath);
		waitingAsserter.assertTextPresentInElement(answer3, modelValue1Xpath);
		waitingAsserter.assertTextPresentInElement(answer4, modelValue1Xpath);
	}

	protected void runSelectManyMenuInstantAjaxTest(String componentName) {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, componentName, "instant-ajax");

		// Test that multiple selected options submit successfully.
		String option1Xpath = "(" + select1Xpath + OPTION_CHILD_XPATH + ")[1]";
		clickOptionAndWaitForRerender(browserDriver, option1Xpath);

		String option3Xpath = "(" + select1Xpath + OPTION_CHILD_XPATH + ")[3]";
		clickOptionAndWaitForRerender(browserDriver, option3Xpath);

		String option4Xpath = "(" + select1Xpath + OPTION_CHILD_XPATH + ")[4]";
		clickOptionAndWaitForRerender(browserDriver, option4Xpath);

		WaitingAsserter waitingAsserter = getWaitingAsserter();
		waitingAsserter.assertTextPresentInElement("1", modelValue1Xpath);
		waitingAsserter.assertTextPresentInElement("3", modelValue1Xpath);
		waitingAsserter.assertTextPresentInElement("4", modelValue1Xpath);

		// Test that deselecting an option removes the value from the model.
		// Note: when selecting an <option> element manually, browsers will deselect all other elements. However, this
		// is not the case in selenium, so elements must be deselected by clicking them again.
		// https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/1899#issuecomment-191480860
		clickOptionAndWaitForRerender(browserDriver, option1Xpath);
		waitingAsserter.assertTextNotPresentInElement("1", modelValue1Xpath);
		waitingAsserter.assertTextPresentInElement("3", modelValue1Xpath);
		waitingAsserter.assertTextPresentInElement("4", modelValue1Xpath);
	}
}
