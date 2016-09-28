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
package com.liferay.faces.test.showcase.inputsecret;

import org.junit.Test;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class InputSecretRedisplayTester extends InputSecretTester {

	@Test
	public void runInputSecretRedisplayTest() throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, "inputSecret", "redisplay");

		// Test that the value submits successfully and the alloy:inputSecret component is intentionally
		// not re-rendered in the DOM.
		String text = "Hello World!";
		browser.sendKeys(inputSecret1Xpath, text);
		browser.performAndWaitForAjaxRerender(browser.createClickAction(submitButton1Xpath), modelValue1Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, text);

		String redisplayMessage1Xpath = "//td[contains(text(),'was intentionally not re-rendered')]";
		SeleniumAssert.assertElementVisible(browser, redisplayMessage1Xpath);

		// Test that the value submits successfully and the entire form (including the alloy:inputSecret component)
		// is re-rendered in the DOM.
		browser.sendKeys(inputSecret2Xpath, text);
		browser.clickAndWaitForAjaxRerender(submitButton2Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue2Xpath, text);

		String redisplayMessage2Xpath = "//td[contains(text(),'entire form')]";
		SeleniumAssert.assertElementVisible(browser, redisplayMessage2Xpath);
	}
}
