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
package com.liferay.faces.test.showcase.inputtextarea;

import org.junit.Test;

import org.openqa.selenium.WebElement;

import com.liferay.faces.test.showcase.Browser;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class InputTextareaConversionTester extends InputTextareaTester {

	@Test
	public void runInputTextareaConversionTest() throws Exception {

		Browser browser = Browser.getInstance();
		browser.navigateToURL(inputTextURL + "conversion");

		// Wait to begin the test until the submit button is rendered.
		browser.waitForElementVisible(submitButtonXpath);

		// Test that the web page shows an error message when an invalid value is submitted.
		WebElement input = browser.getElement(inputXpath);
		input.clear();

		String invalidText = "apr 3 33";
		input.sendKeys(invalidText);
		browser.clickAndWaitForAjaxRerender(submitButtonXpath);
		browser.assertElementVisible(errorXpath);

		// Test that a valid value submits successfully.
		input = browser.getElement(inputXpath);
		input.clear();

		String text = "apr 3, 33";
		input.sendKeys(text);
		browser.clickAndWaitForAjaxRerender(submitButtonXpath);

		String textOutput = "Apr 3, 0033";
		browser.assertElementTextVisible(modelValueXpath, textOutput);

		// Test that the web page shows an error message when an invalid value is submitted.
		input = browser.getElement(inputXpathRight);
		input.clear();
		invalidText = "4/333";
		input.sendKeys(invalidText);
		browser.clickAndWaitForAjaxRerender(submitButtonXpathRight);
		browser.assertElementVisible(errorXpath);

		// Test that a valid value submits successfully.
		input = browser.getElement(inputXpathRight);
		input.clear();
		text = "4/3/33";
		input.sendKeys(text);
		browser.clickAndWaitForAjaxRerender(submitButtonXpathRight);
		textOutput = "04/03/0033";
		browser.assertElementTextVisible(modelValueXpathRight, textOutput);
	}
}
