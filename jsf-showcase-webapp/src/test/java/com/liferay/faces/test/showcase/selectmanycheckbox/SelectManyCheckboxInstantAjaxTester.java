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
package com.liferay.faces.test.showcase.selectmanycheckbox;

import org.junit.Test;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class SelectManyCheckboxInstantAjaxTester extends SelectManyCheckboxTester {

	@Test
	public void runSelectManyCheckboxInstantAjaxTest() throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, "selectManyCheckbox", "instant-ajax");

		// Test that multiple checked checkboxes submit successfully.
		String checkbox1Xpath = "(" + CHECKBOX_CHILD_XPATH + ")[1]";
		clickAndWaitForAjaxRerender(browser, checkbox1Xpath);

		String checkbox3Xpath = "(" + CHECKBOX_CHILD_XPATH + ")[3]";
		clickAndWaitForAjaxRerender(browser, checkbox3Xpath);

		String checkbox4Xpath = "(" + CHECKBOX_CHILD_XPATH + ")[4]";
		clickAndWaitForAjaxRerender(browser, checkbox4Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "1");
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "3");
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "4");

		// Test that unchecking a checkbox removes the value from the model.
		clickAndWaitForAjaxRerender(browser, checkbox1Xpath);
		SeleniumAssert.assertElementTextInvisible(browser, modelValue1Xpath, "1");
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "3");
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "4");
	}
}
