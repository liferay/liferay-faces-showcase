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

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, componentName, "conversion");

		// Test that the selected option submits successfully and the "Incorrect!" message appears.
		String option1Xpath = "(" + selectOne1Xpath + optionChildXpath + ")[1]";
		browserDriver.clickElement(option1Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);

		WaitingAsserter waitingAsserter = getWaitingAsserter();
		String answer1 = "Oct 31, 1517 AD";
		waitingAsserter.assertTextPresentInElement(answer1, modelValue1Xpath);
		waitingAsserter.assertElementDisplayed(conversionIncorrectMessage1Xpath);

		// Test that selecting another value changes the model value and the "Correct!" message appears.
		String option2Xpath = "(" + selectOne1Xpath + optionChildXpath + ")[2]";
		browserDriver.clickElement(option2Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);
		waitingAsserter.assertTextNotPresentInElement(answer1, modelValue1Xpath);
		waitingAsserter.assertTextPresentInElement("Jul 4, 1776 AD", modelValue1Xpath);
		waitingAsserter.assertElementDisplayed(conversionCorrectMessage1Xpath);
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

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, componentName, "data-model");

		// Test that the selected option submits successfully.
		WaitingAsserter waitingAsserter = getWaitingAsserter();
		testSelectOne(browserDriver, waitingAsserter, selectOne1Xpath, optionChildXpath, submitButton1Xpath,
			modelValue1Xpath);
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

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, componentName, "default-value");

		// Test that a default value is in the model.
		WaitingAsserter waitingAsserter = getWaitingAsserter();
		String answer3 = "3";
		waitingAsserter.assertTextPresentInElement(answer3, modelValue1Xpath);

		// Test that selecting another value changes the model value.
		String option1Xpath = "(" + selectOne1Xpath + optionChildXpath + ")[1]";
		browserDriver.clickElement(option1Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);
		waitingAsserter.assertTextNotPresentInElement(answer3, modelValue1Xpath);
		waitingAsserter.assertTextPresentInElement("1", modelValue1Xpath);
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

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, componentName, "immediate");

		// Test that the selected option submits successfully and the valueChangeListener method is called during the
		// APPLY_REQUEST_VALUES phase.
		WaitingAsserter waitingAsserter = getWaitingAsserter();
		testSelectOne(browserDriver, waitingAsserter, selectOne1Xpath, optionChildXpath, submitButton1Xpath,
			modelValue1Xpath);
		waitingAsserter.assertElementDisplayed(immediateMessage1Xpath);

		// Test that the selected option submits successfully and the valueChangeListener method is called during the
		// PROCESS_VALIDATIONS phase.
		testSelectOne(browserDriver, waitingAsserter, selectOne2Xpath, optionChildXpath, submitButton2Xpath,
			modelValue2Xpath);
		waitingAsserter.assertElementDisplayed(immediateMessage2Xpath);
	}

	/**
	 * @param  browserDriver
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
	protected void testSelectOne(BrowserDriver browserDriver, WaitingAsserter waitingAsserter, String selectOneXpath,
		String optionChildXpath, String submitButtonXpath, String modelValueXpath) {

		// Test that the selected option submits successfully.
		String option1Xpath = "(" + selectOneXpath + optionChildXpath + ")[1]";
		browserDriver.clickElement(option1Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButtonXpath);

		String answer1 = "1";
		waitingAsserter.assertTextPresentInElement(answer1, modelValueXpath);

		// Test that selecting another option changes the model value.
		String option3Xpath = "(" + selectOneXpath + optionChildXpath + ")[3]";
		browserDriver.clickElement(option3Xpath);
		browserDriver.clickElementAndWaitForRerender(submitButtonXpath);
		waitingAsserter.assertTextNotPresentInElement(answer1, modelValueXpath);
		waitingAsserter.assertTextPresentInElement("3", modelValueXpath);
	}
}
