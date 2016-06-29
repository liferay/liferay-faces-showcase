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
package com.liferay.faces.test.showcase;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.IntegrationTesterBase;
import com.liferay.faces.test.selenium.TestUtil;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class TesterBase extends IntegrationTesterBase {

	// Protected Constants
	protected static final String TEST_CONTEXT_URL;

	// Common Xpaths
	protected static final String modelValue1Xpath = "(//span[contains(@id,':modelValue')])[1]";
	protected static final String modelValue2Xpath = "(//span[contains(@id,':modelValue')])[2]";
	protected static final String error1Xpath = "(//div[contains(@class,'field form-group has-error')])[1]";
	protected static final String requiredCheckboxXpath = "//input[contains(@id,':requiredCheckbox')]";
	protected static final String submitButton1Xpath = "(//*[contains(@value,'Submit')])[1]";
	protected static final String submitButton2Xpath = "(//*[contains(@value,'Submit')])[2]";
	protected static final String immediateMessage1Xpath = "//li[contains(text(),'APPLY_REQUEST_VALUES')]";
	protected static final String immediateMessage2Xpath = "//li[contains(text(),'PROCESS_VALIDATIONS')]";

	static {

		String defaultContext =
			"/com.liferay.faces.demo.jsf.showcase.webapp-3.0.0-SNAPSHOT/web/guest/showcase/-/component/h";

		if (CONTAINER.contains("liferay")) {
			defaultContext = "/web/guest/jsf-showcase/-/jsf-tag/h";
		}
		else if (CONTAINER.contains("pluto")) {

			// Note: the showcase tests will not work in pluto because pluto does not support friendly URLs.
			defaultContext = DEFAULT_PLUTO_CONTEXT + "/jsf-showcase";
		}

		String context = TestUtil.getSystemPropertyOrDefault("integration.context", defaultContext);
		TEST_CONTEXT_URL = BASE_URL + context;
	}

	protected void testRequiredCheckbox(Browser browser) {
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementNotPresent(browser, error1Xpath);
		browser.click(requiredCheckboxXpath);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementVisible(browser, error1Xpath);
	}
}
