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
package com.liferay.faces.test.showcase.form;

import org.junit.Test;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;
import com.liferay.faces.test.showcase.TesterBase;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class FormGeneralTester extends TesterBase {

	@Test
	public void runFormGeneralTest() throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, "form", "general");

		// Test that the form submits successfully via Ajax.
		browser.clickAndWaitForAjaxRerender(
			"(//*[contains(@onclick,'mojarra.ab')][contains(text(),'Submit') or contains(@value,'Submit')])[1]");
		SeleniumAssert.assertElementVisible(browser,
			"//td[contains(text(),'The form was submitted via Ajax and re-rendered with updates to the DOM.')]");
	}
}
