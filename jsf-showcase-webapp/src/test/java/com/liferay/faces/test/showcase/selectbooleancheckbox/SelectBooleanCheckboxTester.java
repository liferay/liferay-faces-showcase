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

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;
import com.liferay.faces.test.showcase.select.SelectTester;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class SelectBooleanCheckboxTester extends SelectTester {
	protected void testSelectBooleanCheckbox(Browser browser, String submitButtonXpath, String modelValueXpath,
		String checkboxXpath) {

		// Test that an unchecked checkbox submits successfully.
		String text = "false";
		browser.clickAndWaitForAjaxRerender(submitButtonXpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValueXpath, text);

		// Test that a checked checkbox submits successfully.
		browser.click(checkboxXpath);
		text = "true";
		browser.clickAndWaitForAjaxRerender(submitButtonXpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValueXpath, text);
	}
}
