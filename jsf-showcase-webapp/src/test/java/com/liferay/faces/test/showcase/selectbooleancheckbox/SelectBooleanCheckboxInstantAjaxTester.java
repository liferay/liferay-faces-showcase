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
package com.liferay.faces.test.showcase.selectbooleancheckbox;

import org.junit.Test;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class SelectBooleanCheckboxInstantAjaxTester extends SelectBooleanCheckboxTester {

	@Test
	public void runSelectBooleanCheckboxInstantAjaxTest() throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, "selectBooleanCheckbox", "instant-ajax");

		// Test that a checked checkbox submits successfully.
		browser.clickAndWaitForAjaxRerender(checkbox1Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "true");

		// Test that an unchecked checkbox submits successfully.
		browser.clickAndWaitForAjaxRerender(checkbox1Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, "false");
	}
}
