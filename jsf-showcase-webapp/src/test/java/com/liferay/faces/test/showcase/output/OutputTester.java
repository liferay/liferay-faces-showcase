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
package com.liferay.faces.test.showcase.output;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;
import com.liferay.faces.test.showcase.TesterBase;


/**
 * @author  Neil Griffin
 */
public class OutputTester extends TesterBase {

	protected static final String exampleTextXpath = "(//div[@class='showcase-example-usage'])[1]";
	protected static final String inputTextXpath = "(//input[contains(@id,':inputText')])[1]";
	protected static final String submitButtonXpath = "(//input[contains(@value,'Submit and Re-Render')][1])";

	protected void testCharCountMessage(Browser browser, String inputTextXpath, String submitButtonXpath,
		String messageXpath) {
		String text = "hello";
		testMessage(browser, inputTextXpath, text, submitButtonXpath, messageXpath,
			"You typed " + text.length() + " characters.");
	}

	/**
	 * Tests that the text submits successfully and the message is displayed.
	 */
	protected void testMessage(Browser browser, String inputTextXpath, String text, String submitButtonXpath,
		String messageXpath, String message) {

		browser.sendKeys(inputTextXpath, text);

		System.out.println(submitButtonXpath);
//		browser.executeScript("arguments[0].scrollIntoView()", browser.findElementByXpath(submitButtonXpath));
//		browser.executeScript("window.scrollBy(0, -1000)");
//		browser.waitUntil(ExpectedConditions.elementToBeClickable(By.xpath(submitButtonXpath)));
		Action submitButtonClickAction = browser.createClickAction(submitButtonXpath);
		browser.performAndWaitForAjaxRerender(submitButtonClickAction, inputTextXpath);
		SeleniumAssert.assertElementTextVisible(browser, messageXpath, message);
	}
}
