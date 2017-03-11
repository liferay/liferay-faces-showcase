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

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;


/**
 * @author  Kyle Stiemann
 */
public class SelectOneTester extends SelectTester {

	/**
	 * @param  componentName
	 * @param  componentUseCase
	 * @param  selectOne1Xpath   The xpath for the first selectOne component. The element may be any element type that
	 *                           is rendered by a selectOne component including a div (alloy:selectOneCheckbox),table
	 *                           (h:selectBooleanCheckbox), or select (h:selectOneMenu).
	 * @param  optionChildXpath  The xpath for a generic option child of the selectOne element. The element may be any
	 *                           element type that is rendered by a selectOne component including an option (for a
	 *                           selectOneMenu) or an input (for selectOneCheckbox). The xpath should not include a
	 *                           numeric qualifier since the method uses this xpath to obtain multiple elements.
	 */
	public void runSelectOneConversionTest(String componentName, String selectOne1Xpath, String optionChildXpath)
		throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentName, "conversion");

		// Test that the selected option submits successfully and the "Incorrect!" message appears.
		String option1Xpath = "(" + selectOne1Xpath + optionChildXpath + ")[1]";
		browser.click(option1Xpath);
		clickAndWaitForAjaxRerender(browser, submitButton1Xpath);

		String answer1 = "Oct 31, 1517 AD";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer1);
		SeleniumAssert.assertElementVisible(browser, conversionIncorrectMessage1Xpath);

		// Test that selecting another value changes the model value and the "Correct!" message appears.
		String option2Xpath = "(" + selectOne1Xpath + optionChildXpath + ")[2]";
		browser.click(option2Xpath);
		clickAndWaitForAjaxRerender(browser, submitButton1Xpath);
		SeleniumAssert.assertElementTextInvisible(browser, modelValue1Xpath, answer1);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "Jul 4, 1776 AD");
		SeleniumAssert.assertElementVisible(browser, conversionCorrectMessage1Xpath);
	}

	/**
	 * @param  componentName
	 * @param  componentUseCase
	 * @param  selectOne1Xpath   The xpath for the first selectOne component. The element may be any element type that
	 *                           is rendered by a selectOne component including a div (alloy:selectOneCheckbox),table
	 *                           (h:selectBooleanCheckbox), or select (h:selectOneMenu).
	 * @param  optionChildXpath  The xpath for a generic option child of the selectOne element. The element may be any
	 *                           element type that is rendered by a selectOne component including an option (for a
	 *                           selectOneMenu) or an input (for selectOneCheckbox). The xpath should not include a
	 *                           numeric qualifier since the method uses this xpath to obtain multiple elements.
	 */
	public void runSelectOneDataModelTest(String componentName, String selectOne1Xpath, String optionChildXpath)
		throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentName, "data-model");

		// Test that the selected option submits successfully.
		testSelectOne(browser, selectOne1Xpath, optionChildXpath, submitButton1Xpath, modelValue1Xpath);
	}

	/**
	 * @param  componentName
	 * @param  componentUseCase
	 * @param  selectOne1Xpath   The xpath for the first selectOne component. The element may be any element type that
	 *                           is rendered by a selectOne component including a div (alloy:selectOneCheckbox),table
	 *                           (h:selectBooleanCheckbox), or select (h:selectOneMenu).
	 * @param  optionChildXpath  The xpath for a generic option child of the selectOne element. The element may be any
	 *                           element type that is rendered by a selectOne component including an option (for a
	 *                           selectOneMenu) or an input (for selectOneCheckbox). The xpath should not include a
	 *                           numeric qualifier since the method uses this xpath to obtain multiple elements.
	 */
	public void runSelectOneDefaultValueTest(String componentName, String selectOne1Xpath, String optionChildXpath)
		throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentName, "default-value");

		// Test that a default value is in the model.
		String answer3 = "3";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer3);

		// Test that selecting another value changes the model value.
		String option1Xpath = "(" + selectOne1Xpath + optionChildXpath + ")[1]";
		browser.click(option1Xpath);
		clickAndWaitForAjaxRerender(browser, submitButton1Xpath);
		SeleniumAssert.assertElementTextInvisible(browser, modelValue1Xpath, answer3);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "1");
	}

	/**
	 * @param  componentName
	 * @param  componentUseCase
	 * @param  selectOne1Xpath   The xpath for the first selectOne component. The element may be any element type that
	 *                           is rendered by a selectOne component including a div (alloy:selectOneCheckbox),table
	 *                           (h:selectBooleanCheckbox), or select (h:selectOneMenu).
	 * @param  selectOne2Xpath   The xpath for the second selectOne component. The element may be any element type that
	 *                           is rendered by a selectOne component including a div (alloy:selectOneCheckbox),table
	 *                           (h:selectBooleanCheckbox), or select (h:selectOneMenu).
	 * @param  optionChildXpath  The xpath for a generic option child of the selectOne element. The element may be any
	 *                           element type that is rendered by a selectOne component including an option (for a
	 *                           selectOneMenu) or an input (for selectOneCheckbox). The xpath should not include a
	 *                           numeric qualifier since the method uses this xpath to obtain multiple elements.
	 */
	public void runSelectOneImmediateTest(String componentName, String selectOne1Xpath, String selectOne2Xpath,
		String optionChildXpath) throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentName, "immediate");

		// Test that the selected option submits successfully and the valueChangeListener method is called during the
		// APPLY_REQUEST_VALUES phase.
		testSelectOne(browser, selectOne1Xpath, optionChildXpath, submitButton1Xpath, modelValue1Xpath);
		SeleniumAssert.assertElementVisible(browser, immediateMessage1Xpath);

		// Test that the selected option submits successfully and the valueChangeListener method is called during the
		// PROCESS_VALIDATIONS phase.
		testSelectOne(browser, selectOne2Xpath, optionChildXpath, submitButton2Xpath, modelValue2Xpath);
		SeleniumAssert.assertElementVisible(browser, immediateMessage2Xpath);
	}

	/**
	 * @param  browser
	 * @param  selectOneXpath     The xpath for the outermost selectOne component. The element may be any element type
	 *                            that is rendered by a selectOne component including a div(alloy:selectManyCheckbox),
	 *                            table (h:selectBooleanCheckbox), or select (h:selectManyMenu).
	 * @param  optionChildXpath   The xpath for a generic option child of the selectOne element. The element may be any
	 *                            element type that is rendered by a selectOne component including an option (for a
	 *                            selectOneMenuinput (for selectOneRadio). The xpath should not include a numeric
	 *                            qualifier since the method uses this xpath to obtain multiple elements.
	 * @param  submitButtonXpath
	 * @param  modelValueXpath
	 */
	protected void testSelectOne(Browser browser, String selectOneXpath, String optionChildXpath,
		String submitButtonXpath, String modelValueXpath) {

		// Test that the selected option submits successfully.
		String option1Xpath = "(" + selectOneXpath + optionChildXpath + ")[1]";
		browser.click(option1Xpath);
		clickAndWaitForAjaxRerender(browser, submitButtonXpath);

		String answer1 = "1";
		SeleniumAssert.assertElementTextVisible(browser, modelValueXpath, answer1);

		// Test that selecting another option changes the model value.
		String option3Xpath = "(" + selectOneXpath + optionChildXpath + ")[3]";
		browser.click(option3Xpath);
		clickAndWaitForAjaxRerender(browser, submitButtonXpath);
		SeleniumAssert.assertElementTextInvisible(browser, modelValueXpath, answer1);
		SeleniumAssert.assertElementTextVisible(browser, modelValueXpath, "3");
	}
}
