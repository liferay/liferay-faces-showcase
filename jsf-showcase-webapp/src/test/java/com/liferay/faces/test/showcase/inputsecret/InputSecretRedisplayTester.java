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
package com.liferay.faces.test.showcase.inputsecret;

import org.junit.Test;

import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.BrowserStateAsserter;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class InputSecretRedisplayTester extends InputSecretTester {

	@Test
	public void runInputSecretRedisplayTest() throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, "inputSecret", "redisplay");

		// Test that the value submits successfully and the alloy:inputSecret component is intentionally
		// not re-rendered in the DOM.
		String text = "Hello World!";
		browserDriver.sendKeysToElement(inputSecret1Xpath, text);
		browserDriver.performAndWaitForRerender(browserDriver.createClickElementAction(submitButton1Xpath),
			modelValue1Xpath);

		BrowserStateAsserter browserStateAsserter = getBrowserStateAsserter();
		browserStateAsserter.assertTextPresentInElement(text, modelValue1Xpath);

		String redisplayMessage1Xpath = "//td[contains(text(),'was intentionally not re-rendered')]";
		browserStateAsserter.assertElementDisplayed(redisplayMessage1Xpath);

		// Test that the value submits successfully and the entire form (including the alloy:inputSecret component)
		// is re-rendered in the DOM.
		browserDriver.sendKeysToElement(inputSecret2Xpath, text);
		browserDriver.clickElementAndWaitForRerender(submitButton2Xpath);
		browserStateAsserter.assertTextPresentInElement(text, modelValue2Xpath);

		String redisplayMessage2Xpath = "//td[contains(text(),'entire form')]";
		browserStateAsserter.assertElementDisplayed(redisplayMessage2Xpath);
	}
}
