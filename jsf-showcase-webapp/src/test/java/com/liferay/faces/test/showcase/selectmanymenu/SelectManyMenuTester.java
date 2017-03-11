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
package com.liferay.faces.test.showcase.selectmanymenu;

import org.openqa.selenium.support.ui.Select;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;
import com.liferay.faces.test.showcase.select.SelectManyTester;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class SelectManyMenuTester extends SelectManyTester {

	protected void runSelectManyMenuGeneralTest(String componentName) {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentName, "general");

		// Test that the web page shows an error message when a value is required and an empty value is submitted.
		Select select = new Select(browser.findElementByXpath(select1Xpath));
		select.deselectAll();
		testRequiredCheckboxError(browser);
		select = new Select(browser.findElementByXpath(select1Xpath));
		select.deselectAll();

		// Test that multiple checked checkboxes submit successfully and the "required" error message disappears. Note:
		// In the general use case, the first option is the "-- Select --" noSelectionOption, so select the second
		// option (which has a value of 1) instead of the first one.
		String option2Xpath = "(" + select1Xpath + OPTION_CHILD_XPATH + ")[2]";
		browser.click(option2Xpath);

		String option4Xpath = "(" + select1Xpath + OPTION_CHILD_XPATH + ")[4]";
		browser.click(option4Xpath);

		String option5Xpath = "(" + select1Xpath + OPTION_CHILD_XPATH + ")[5]";
		browser.click(option5Xpath);
		clickAndWaitForAjaxRerender(browser, submitButton1Xpath);
		SeleniumAssert.assertElementNotPresent(browser, valueIsRequiredError1Xpath);

		String answer1 = "1";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer1);

		String answer3 = "3";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer3);

		String answer4 = "4";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer4);

		// Test that unchecking the checkbox removes the value from the model.
		// Note: when selecting an <option> element manually, browsers will deselect all other elements. However, this
		// is not the case in selenium, so elements must be deselected by clicking them again.
		// https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/1899#issuecomment-191480860
		browser.click(option2Xpath);
		clickAndWaitForAjaxRerender(browser, submitButton1Xpath);
		SeleniumAssert.assertElementTextInvisible(browser, modelValue1Xpath, answer1);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer3);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, answer4);
	}

	protected void runSelectManyMenuInstantAjaxTest(String componentName) {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentName, "instant-ajax");

		// Test that multiple selected options submit successfully.
		String option1Xpath = "(" + select1Xpath + OPTION_CHILD_XPATH + ")[1]";
		clickOptionAndWaitForAjaxRerender(browser, option1Xpath);

		String option3Xpath = "(" + select1Xpath + OPTION_CHILD_XPATH + ")[3]";
		clickOptionAndWaitForAjaxRerender(browser, option3Xpath);

		String option4Xpath = "(" + select1Xpath + OPTION_CHILD_XPATH + ")[4]";
		clickOptionAndWaitForAjaxRerender(browser, option4Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "1");
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "3");
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "4");

		// Test that deselecting an option removes the value from the model.
		// Note: when selecting an <option> element manually, browsers will deselect all other elements. However, this
		// is not the case in selenium, so elements must be deselected by clicking them again.
		// https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/1899#issuecomment-191480860
		clickOptionAndWaitForAjaxRerender(browser, option1Xpath);
		SeleniumAssert.assertElementTextInvisible(browser, modelValue1Xpath, "1");
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "3");
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "4");
	}
}
