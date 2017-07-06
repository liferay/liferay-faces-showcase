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
package com.liferay.faces.test.showcase.select;

import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.WaitingAsserter;


/**
 * @author  Kyle Stiemann
 */
public class SelectManyTester extends SelectTester {

	/**
	 * @param  componentName
	 * @param  selectMany1Xpath  The xpath for the first selectMany component. The element may be any element type that
	 *                           is rendered by a selectMany component including a div (alloy:selectManyCheckbox),table
	 *                           (h:selectBooleanCheckbox), or select (h:selectManyMenu).
	 * @param  optionChildXpath  The xpath for a generic option child of the selectMany element. The element may be any
	 *                           element type that is rendered by a selectMany component including an option (for a
	 *                           selectManyMenu) or an input (for selectManyCheckbox). The xpath should not include a
	 *                           numeric qualifier since the method uses this xpath to obtain multiple elements.
	 */
	protected void runSelectManyConversionTest(String componentName, String selectMany1Xpath, String optionChildXpath) {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, componentName, "conversion");

		// Test that multiple selected options submit successfully and the "Incorrect!" message appears.
		String option1Xpath = "(" + selectMany1Xpath + optionChildXpath + ")[1]";
		browserDriver.clickElement(option1Xpath);

		String option3Xpath = "(" + selectMany1Xpath + optionChildXpath + ")[3]";
		browserDriver.clickElement(option3Xpath);

		String option4Xpath = "(" + selectMany1Xpath + optionChildXpath + ")[4]";
		browserDriver.clickElement(option4Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);

		WaitingAsserter waitingAsserter = getWaitingAsserter();
		String date1 = "Apr 5, 0033 AD";
		waitingAsserter.assertTextPresentInElement(date1, modelValue1Xpath);

		String date2 = "Jul 4, 1776 AD";
		waitingAsserter.assertTextPresentInElement(date2, modelValue1Xpath);

		String date3 = "Jul 14, 1789 AD";
		waitingAsserter.assertTextPresentInElement(date3, modelValue1Xpath);
		waitingAsserter.assertElementDisplayed(conversionIncorrectMessage1Xpath);

		// Test that deselecting a option removes the value from the model and the "Correct!" message appears.
		// Note: when selecting an <option> element manually, browsers will deselect all other elements. However, this
		// is not the case in selenium, so elements must be deselected by clicking them again.
		// https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/1899#issuecomment-191480860
		browserDriver.clickElement(option1Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);
		waitingAsserter.assertTextNotPresentInElement(date1, modelValue1Xpath);
		waitingAsserter.assertTextPresentInElement(date2, modelValue1Xpath);
		waitingAsserter.assertTextPresentInElement(date3, modelValue1Xpath);
		waitingAsserter.assertElementDisplayed(conversionCorrectMessage1Xpath);
	}

	/**
	 * @param  componentName
	 * @param  selectMany1Xpath  The xpath for the first selectMany component. The element may be any element type that
	 *                           is rendered by a selectMany component including a div (alloy:selectManyCheckbox),table
	 *                           (h:selectBooleanCheckbox), or select (h:selectManyMenu).
	 * @param  optionChildXpath  The xpath for a generic option child of the selectMany element. The element may be any
	 *                           element type that is rendered by a selectMany component including an option (for a
	 *                           selectManyMenu) or an input (for selectManyCheckbox). The xpath should not include a
	 *                           numeric qualifier since the method uses this xpath to obtain multiple elements.
	 */
	protected void runSelectManyDataModelTest(String componentName, String selectMany1Xpath, String optionChildXpath) {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, componentName, "data-model");

		// Test that the selected values submit successfully.
		WaitingAsserter waitingAsserter = getWaitingAsserter();
		testSelectMany(browserDriver, waitingAsserter, selectMany1Xpath, optionChildXpath, submitButton1Xpath,
			modelValue1Xpath);
	}

	/**
	 * @param  componentName
	 * @param  selectMany1Xpath  The xpath for the first selectMany component. The element may be any element type that
	 *                           is rendered by a selectMany component including a div (alloy:selectManyCheckbox),table
	 *                           (h:selectBooleanCheckbox), or select (h:selectManyMenu).
	 * @param  optionChildXpath  The xpath for a generic option child of the selectMany element. The element may be any
	 *                           element type that is rendered by a selectMany component including an option (for a
	 *                           selectManyMenu) or an input (for selectManyCheckbox). The xpath should not include a
	 *                           numeric qualifier since the method uses this xpath to obtain multiple elements.
	 */
	protected void runSelectManyDefaultValueTest(String componentName, String selectMany1Xpath,
		String optionChildXpath) {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, componentName, "default-value");

		// Test that the default values are in the model.
		WaitingAsserter waitingAsserter = getWaitingAsserter();
		String answer2 = "2";
		waitingAsserter.assertTextPresentInElement(answer2, modelValue1Xpath);

		String answer4 = "4";
		waitingAsserter.assertTextPresentInElement(answer4, modelValue1Xpath);

		// Test that multiple selected options submit successfully.
		String option1Xpath = "(" + selectMany1Xpath + optionChildXpath + ")[1]";
		browserDriver.clickElement(option1Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);

		String answer1 = "1";
		waitingAsserter.assertTextPresentInElement(answer1, modelValue1Xpath);
		waitingAsserter.assertTextPresentInElement(answer2, modelValue1Xpath);
		waitingAsserter.assertTextPresentInElement(answer4, modelValue1Xpath);

		// Test that deselcting an option removes the value from the model.
		// Note: when selecting an <option> element manually, browsers will deselect all other elements. However, this
		// is not the case in selenium, so elements must be deselected by clicking them again.
		// https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/1899#issuecomment-191480860
		browserDriver.clickElement(option1Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);
		waitingAsserter.assertTextNotPresentInElement(answer1, modelValue1Xpath);
		waitingAsserter.assertTextPresentInElement(answer2, modelValue1Xpath);
		waitingAsserter.assertTextPresentInElement(answer4, modelValue1Xpath);
	}

	/**
	 * @param  componentName
	 * @param  selectMany1Xpath  The xpath for the first selectMany component. The element may be any element type that
	 *                           is rendered by a selectMany component including a div (alloy:selectManyCheckbox),table
	 *                           (h:selectBooleanCheckbox), or select (h:selectManyMenu).
	 * @param  selectMany2Xpath  The xpath for the second selectMany component. The element may be any element type that
	 *                           is rendered by a selectMany component including a div (alloy:selectManyCheckbox),table
	 *                           (h:selectBooleanCheckbox), or select (h:selectManyMenu).
	 * @param  optionChildXpath  The xpath for a generic option child of the selectMany element. The element may be any
	 *                           element type that is rendered by a selectMany component including an option (for a
	 *                           selectManyMenu) or an input (for selectManyCheckbox). The xpath should not include a
	 *                           numeric qualifier since the method uses this xpath to obtain multiple elements.
	 */
	protected void runSelectManyImmediateTest(String componentName, String selectMany1Xpath, String selectMany2Xpath,
		String optionChildXpath) {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, componentName, "immediate");

		// Test that the value submits successfully and the valueChangeListener method is called during the
		// APPLY_REQUEST_VALUES phase.
		WaitingAsserter waitingAsserter = getWaitingAsserter();
		testSelectMany(browserDriver, waitingAsserter, selectMany1Xpath, optionChildXpath, submitButton1Xpath,
			modelValue1Xpath);
		waitingAsserter.assertElementDisplayed(immediateMessage1Xpath);

		// Test that the value submits successfully and the valueChangeListener method is called during the
		// PROCESS_VALIDATIONS phase.
		testSelectMany(browserDriver, waitingAsserter, selectMany2Xpath, optionChildXpath, submitButton2Xpath,
			modelValue2Xpath);
		waitingAsserter.assertElementDisplayed(immediateMessage2Xpath);
	}

	/**
	 * @param  browserDriver
	 * @param  selectManyXpath    The xpath for the outermost selectMany component. The element may be any element type
	 *                            that is rendered by a selectMany component including a div(alloy:selectManyCheckbox),
	 *                            table (h:selectBooleanCheckbox), or select (h:selectManyMenu).
	 * @param  optionChildXpath   The xpath for a generic option child of the selectMany element. The element may be any
	 *                            element type that is rendered by a selectMany component including an option (for a
	 *                            selectManyMenu) or an input (for selectManyCheckbox). The xpath should not include a
	 *                            numeric qualifier since the method uses this xpath to obtain multiple elements.
	 * @param  submitButtonXpath
	 * @param  modelValueXpath
	 */
	protected final void testSelectMany(BrowserDriver browserDriver, WaitingAsserter waitingAsserter,
		String selectManyXpath, String optionChildXpath, String submitButtonXpath, String modelValueXpath) {

		// Test that multiple selected options submit successfully.
		String option1Xpath = "(" + selectManyXpath + optionChildXpath + ")[1]";
		browserDriver.clickElement(option1Xpath);

		String option3Xpath = "(" + selectManyXpath + optionChildXpath + ")[3]";
		browserDriver.clickElement(option3Xpath);

		String option4Xpath = "(" + selectManyXpath + optionChildXpath + ")[4]";
		browserDriver.clickElement(option4Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButtonXpath);

		String answer1 = "1";
		waitingAsserter.assertTextPresentInElement(answer1, modelValueXpath);

		String answer3 = "3";
		waitingAsserter.assertTextPresentInElement(answer3, modelValueXpath);

		String answer4 = "4";
		waitingAsserter.assertTextPresentInElement(answer4, modelValueXpath);

		// Test that deselecting an option removes the value from the model.
		// Note: when selecting an <option> element manually, browsers will deselect all other elements. However, this
		// is not the case in selenium, so elements must be deselected by clicking them again.
		// https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/1899#issuecomment-191480860
		browserDriver.clickElement(option1Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButtonXpath);
		waitingAsserter.assertTextNotPresentInElement(answer1, modelValueXpath);
		waitingAsserter.assertTextPresentInElement(answer3, modelValueXpath);
		waitingAsserter.assertTextPresentInElement(answer4, modelValueXpath);
	}
}
