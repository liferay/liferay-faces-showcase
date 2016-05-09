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
package com.liferay.faces.test.showcase.messages;

import org.junit.Test;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.showcase.output.OutputTester;


/**
 * @author  Neil Griffin
 */
public class MessagesGeneralTester extends OutputTester {

	@Test
	public void runMessagesGeneralTest() throws Exception {

		Browser browser = Browser.getInstance();
		browser.get(TEST_CONTEXT_URL + "/messages/general");

		// Wait to begin the test until the submit button is rendered.
		String submitButtonXpath1 =
			"(//div[@class='showcase-example-usage'])[1]/input[2][contains(@value, 'Submit and Re-Render')]";
		browser.waitForElementVisible(submitButtonXpath1);

		// Test that the first value submits successfully and message text is displayed
		String text = "hello";
		String messagesXpath1 = "(//div[@class='showcase-example-usage'])[1]/ul/li";
		testCharCountMessage(browser, inputTextXpath, submitButtonXpath1, messagesXpath1);

		// Test that the second value submits successfully and message text is displayed
		String inputTextXpath2 = "(//input[contains(@id,':inputText')])[2]";
		String messagesXpath2 = "(//div[@class='showcase-example-usage'])[2]/table/tbody/tr/td";
		String submitButtonXpath2 =
			"(//div[@class='showcase-example-usage'])[2]/input[2][contains(@value, 'Submit and Re-Render')]";
		testCharCountMessage(browser, inputTextXpath2, submitButtonXpath2, messagesXpath2);

		// Test that the third value submits successfully and message text is displayed
// browser.navigate().refresh();
		String inputTextXpath3 = "(//input[contains(@id,':inputText')])[3]";
		String messagesXpath3 = "(//div[@class='showcase-example-usage'])[3]/table/tbody/tr/td";
		String submitButtonXpath3 =
			"(//div[@class='showcase-example-usage'])[3]/input[2][contains(@value, 'Submit and Re-Render')]";
		browser.centerElementInView(submitButtonXpath3);

//      browser.executeScript("arguments[0].scrollIntoView()", browser.findElementByXpath("(//div[@class='showcase-example-usage'])[3]"));
//      browser.waitUntil(ExpectedConditions.elementToBeClickable(By.xpath(submitButtonXpath3)));
		testMessage(browser, inputTextXpath3, text, submitButtonXpath3, messagesXpath3,
			"Your request processed successfully.");
	}
}
