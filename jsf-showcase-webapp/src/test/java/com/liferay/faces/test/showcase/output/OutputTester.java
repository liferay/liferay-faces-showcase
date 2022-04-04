/**
 * Copyright (c) 2000-2022 Liferay, Inc. All rights reserved.
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
package com.liferay.faces.test.showcase.output;

import org.openqa.selenium.interactions.Action;

import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.WaitingAsserter;
import com.liferay.faces.test.showcase.TesterBase;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class OutputTester extends TesterBase {

	// Common Xpath
	protected static final String exampleText1Xpath = "(//div[@class='showcase-example-usage'])[1]";
	protected static final String input1Xpath = "(//input[contains(@id,':inputText')])[1]";

	protected void testCharCountMessage(BrowserDriver browserDriver, WaitingAsserter waitingAsserter, String inputXpath,
		String submitButtonXpath, String messageXpath) {

		String text = "hello";
		testMessage(browserDriver, waitingAsserter, inputXpath, text, submitButtonXpath, messageXpath,
			"You typed " + text.length() + " characters.");
	}

	/**
	 * Tests that the text value submits successfully and the message is displayed.
	 */
	protected void testMessage(BrowserDriver browserDriver, WaitingAsserter waitingAsserter, String inputXpath,
		String text, String submitButtonXpath, String messageXpath, String message) {

		browserDriver.sendKeysToElement(inputXpath, text);

		Action submitButtonClickAction = browserDriver.createClickElementAction(submitButtonXpath);
		browserDriver.performAndWaitForRerender(submitButtonClickAction, inputXpath);
		waitingAsserter.assertTextPresentInElement(message, messageXpath);
	}
}
