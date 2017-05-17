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
package com.liferay.faces.test.showcase.messages;

import org.junit.Test;

import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.BrowserStateAsserter;
import com.liferay.faces.test.showcase.output.OutputTester;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class MessagesGeneralTester extends OutputTester {

	@Test
	public void runMessagesGeneralTest() throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, "messages", "general");

		// Test that the first value submits successfully and message text is displayed
		BrowserStateAsserter browserStateAsserter = getBrowserStateAsserter();
		String text = "hello";
		String messages1Xpath = "(//div[@class='showcase-example-usage'])[1]/ul/li";
		testCharCountMessage(browserDriver, browserStateAsserter, input1Xpath, submitButton1Xpath, messages1Xpath);

		// Test that the second value submits successfully and message text is displayed
		String input2Xpath = "(//input[contains(@id,':inputText')])[2]";
		String messages2Xpath = "(//div[@class='showcase-example-usage'])[2]/table/tbody/tr/td";
		testCharCountMessage(browserDriver, browserStateAsserter, input2Xpath, submitButton2Xpath, messages2Xpath);

		// Test that the third value submits successfully and message text is displayed
		String input3Xpath = "(//input[contains(@id,':inputText')])[3]";
		String messages3Xpath = "(//div[@class='showcase-example-usage'])[3]/table/tbody/tr/td";
		String submitButton3Xpath = "(//*[contains(@value, 'Submit')])[3]";
		testMessage(browserDriver, browserStateAsserter, input3Xpath, text, submitButton3Xpath, messages3Xpath,
			"Your request processed successfully.");
	}
}
