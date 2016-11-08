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
package com.liferay.faces.test.showcase.form;

import org.junit.Test;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;
import com.liferay.faces.test.showcase.TesterBase;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class FormNonAjaxTester extends TesterBase {

	@Test
	public void runFormNonAjaxTest() throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, "form", "non-ajax");

		// Test that the form submits successfully.
		browser.click("(//*[contains(text(),'Submit') or contains(@value,'Submit')])[1]");
		waitForShowcasePageReady(browser);
		SeleniumAssert.assertElementVisible(browser,
			"//td[contains(text(),'The form was submitted as a full page postback and the entire page was re-rendered.')]");
	}
}
