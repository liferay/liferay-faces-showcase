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
package com.liferay.faces.test.showcase.select;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;


/**
 * @author  Kyle Stiemann
 */
public class SelectManyTester extends SelectTester {

	/**
	 * @param  componentName
	 * @param  componentUseCase
	 * @param  selectMany1Xpath  The xpath for the first selectMany component. The element may be any element type that
	 *                           is rendered by a selectMany component including a div (alloy:selectManyCheckbox),table
	 *                           (h:selectBooleanCheckbox), or select (h:selectManyMenu).
	 * @param  optionChildXpath  The xpath for a generic option child of the selectMany element. The element may be any
	 *                           element type that is rendered by a selectMany component including an option (for a
	 *                           selectManyMenu) or an input (for selectManyCheckbox). The xpath should not include a
	 *                           numeric qualifier since the method uses this xpath to obtain multiple elements.
	 */
	protected void runSelectManyConversionTest(String componentName, String selectMany1Xpath, String optionChildXpath) {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentName, "conversion");

		// Test that multiple selected options submit successfully and the "Incorrect!" message appears.
		String option1Xpath = "(" + selectMany1Xpath + optionChildXpath + ")[1]";
		browser.click(option1Xpath);

		String option3Xpath = "(" + selectMany1Xpath + optionChildXpath + ")[3]";
		browser.click(option3Xpath);

		String option4Xpath = "(" + selectMany1Xpath + optionChildXpath + ")[4]";
		browser.click(option4Xpath);
		clickAndWaitForAjaxRerender(browser, submitButton1Xpath);

		String date1 = "Apr 5, 0033 AD";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, date1);

		String date2 = "Jul 4, 1776 AD";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, date2);

		String date3 = "Jul 14, 1789 AD";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, date3);
		SeleniumAssert.assertElementVisible(browser, conversionIncorrectMessage1Xpath);

		// Test that deselecting a option removes the value from the model and the "Correct!" message appears.
		// Note: when selecting an <option> element manually, browsers will deselect all other elements. However, this
		// is not the case in selenium, so elements must be deselected by clicking them again.
		// https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/1899#issuecomment-191480860
		browser.click(option1Xpath);
		clickAndWaitForAjaxRerender(browser, submitButton1Xpath);
		SeleniumAssert.assertElementTextInvisible(browser, modelValue1Xpath, date1);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, date2);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, date3);
		SeleniumAssert.assertElementVisible(browser, conversionCorrectMessage1Xpath);
	}

	/**
	 * @param  componentName
	 * @param  componentUseCase
	 * @param  selectMany1Xpath  The xpath for the first selectMany component. The element may be any element type that
	 *                           is rendered by a selectMany component including a div (alloy:selectManyCheckbox),table
	 *                           (h:selectBooleanCheckbox), or select (h:selectManyMenu).
	 * @param  optionChildXpath  The xpath for a generic option child of the selectMany element. The element may be any
	 *                           element type that is rendered by a selectMany component including an option (for a
	 *                           selectManyMenu) or an input (for selectManyCheckbox). The xpath should not include a
	 *                           numeric qualifier since the method uses this xpath to obtain multiple elements.
	 */
	protected void runSelectManyDataModelTest(String componentName, String selectMany1Xpath, String optionChildXpath) {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentName, "data-model");

		// Test that the selected values submit successfully.
		testSelectMany(browser, selectMany1Xpath, optionChildXpath, submitButton1Xpath, modelValue1Xpath);
	}

	/**
	 * @param  componentName
	 * @param  componentUseCase
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

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentName, "default-value");

		// Test that the default values are in the model.
		String answer2 = "2";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer2);

		String answer4 = "4";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer4);

		// Test that multiple selected options submit successfully.
		String option1Xpath = "(" + selectMany1Xpath + optionChildXpath + ")[1]";
		browser.click(option1Xpath);
		clickAndWaitForAjaxRerender(browser, submitButton1Xpath);

		String answer1 = "1";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer1);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer2);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer4);

		// Test that deselcting an option removes the value from the model.
		// Note: when selecting an <option> element manually, browsers will deselect all other elements. However, this
		// is not the case in selenium, so elements must be deselected by clicking them again.
		// https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/1899#issuecomment-191480860
		browser.click(option1Xpath);
		clickAndWaitForAjaxRerender(browser, submitButton1Xpath);
		SeleniumAssert.assertElementTextInvisible(browser, modelValue1Xpath, answer1);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer2);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer4);
	}

	/**
	 * @param  componentName
	 * @param  componentUseCase
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

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentName, "immediate");

		// Test that the value submits successfully and the valueChangeListener method is called during the
		// APPLY_REQUEST_VALUES phase.
		testSelectMany(browser, selectMany1Xpath, optionChildXpath, submitButton1Xpath, modelValue1Xpath);
		SeleniumAssert.assertElementVisible(browser, immediateMessage1Xpath);

		// Test that the value submits successfully and the valueChangeListener method is called during the
		// PROCESS_VALIDATIONS phase.
		testSelectMany(browser, selectMany2Xpath, optionChildXpath, submitButton2Xpath, modelValue2Xpath);
		SeleniumAssert.assertElementVisible(browser, immediateMessage2Xpath);
	}

	/**
	 * @param  browser
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
	protected final void testSelectMany(Browser browser, String selectManyXpath, String optionChildXpath,
		String submitButtonXpath, String modelValueXpath) {

		// Test that multiple selected options submit successfully.
		String option1Xpath = "(" + selectManyXpath + optionChildXpath + ")[1]";
		browser.click(option1Xpath);

		String option3Xpath = "(" + selectManyXpath + optionChildXpath + ")[3]";
		browser.click(option3Xpath);

		String option4Xpath = "(" + selectManyXpath + optionChildXpath + ")[4]";
		browser.click(option4Xpath);
		clickAndWaitForAjaxRerender(browser, submitButtonXpath);

		String answer1 = "1";
		SeleniumAssert.assertElementTextVisible(browser, modelValueXpath, answer1);

		String answer3 = "3";
		SeleniumAssert.assertElementTextVisible(browser, modelValueXpath, answer3);

		String answer4 = "4";
		SeleniumAssert.assertElementTextVisible(browser, modelValueXpath, answer4);

		// Test that deselecting an option removes the value from the model.
		// Note: when selecting an <option> element manually, browsers will deselect all other elements. However, this
		// is not the case in selenium, so elements must be deselected by clicking them again.
		// https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/1899#issuecomment-191480860
		browser.click(option1Xpath);
		clickAndWaitForAjaxRerender(browser, submitButtonXpath);
		SeleniumAssert.assertElementTextInvisible(browser, modelValueXpath, answer1);
		SeleniumAssert.assertElementTextVisible(browser, modelValueXpath, answer3);
		SeleniumAssert.assertElementTextVisible(browser, modelValueXpath, answer4);
	}
}
