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
import com.liferay.faces.test.showcase.select.SelectTester;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class SelectBooleanCheckboxInstantAjaxTester extends SelectTester {

	@Test
	public void runSelectBooleanCheckboxInstantAjaxTest() throws Exception {
		Browser browser = Browser.getInstance();
		browser.get(TEST_CONTEXT_URL + "/selectbooleancheckbox/instant-ajax");

		// Wait to begin the test until an element is rendered.
		browser.waitForElementVisible(checkbox1Xpath);

		// Test that a checked checkbox submits successfully.
		browser.clickAndWaitForAjaxRerender(checkbox1Xpath);

		String text = "true";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, text);

		// Test that an unchecked checkbox submits successfully.
		browser.clickAndWaitForAjaxRerender(checkbox1Xpath);
		text = "false";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, text);
	}
}
