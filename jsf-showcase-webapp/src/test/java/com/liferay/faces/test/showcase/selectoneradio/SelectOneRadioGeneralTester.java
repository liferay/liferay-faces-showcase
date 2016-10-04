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
package com.liferay.faces.test.showcase.selectoneradio;

import org.junit.Test;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class SelectOneRadioGeneralTester extends SelectOneRadioTester {

	@Test
	public void runSelectOneRadioGeneralTest() throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, "selectOneRadio", "general");
		testRequiredCheckboxError(browser);

		// Test that a radio value submits successfully and the "required" error message disappears.
		testSelectOne(browser, selectOneRadio1Xpath, RADIO_CHILD_XPATH, submitButton1Xpath, modelValue1Xpath);
		SeleniumAssert.assertElementNotPresent(browser, valueIsRequiredError1Xpath);
	}
}
