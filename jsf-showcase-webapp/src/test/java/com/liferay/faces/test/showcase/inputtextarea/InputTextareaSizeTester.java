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
package com.liferay.faces.test.showcase.inputtextarea;

import org.junit.Assert;
import org.junit.Test;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class InputTextareaSizeTester extends InputTextareaTester {

	@Test
	public void runInputTextareaSizeTest() throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, "inputTextarea", "size");

		// Wait to begin the test until the submit button is rendered.
		browser.waitForElementVisible(submitButton1Xpath);

		// Test that the value submits successfully
		String text = "Hello World!";
		browser.sendKeys(textarea1Xpath, text);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, text);
		SeleniumAssert.assertElementVisible(browser, "//textarea[@cols='50'][@rows='6']");

		// Test that the value submits successfully
		browser.sendKeys(textarea2Xpath, text);
		browser.clickAndWaitForAjaxRerender(submitButton2Xpath);
		SeleniumAssert.assertElementTextVisible(browser, modelValue2Xpath, text);

		WebElement textarea2 = browser.findElementByXpath(textarea2Xpath);
		Dimension size = textarea2.getSize();
		int expectedWidth = 150;
		int width = size.getWidth();
		Assert.assertEquals("Width of element " + textarea2 + " is not \"" + expectedWidth + "\". Instead it is \"" +
			width + "\".", 150, width);

		int expectedHeight = 150;
		int height = size.getHeight();
		Assert.assertEquals("Width of element " + textarea2 + " is not \"" + expectedHeight + "\". Instead it is \"" +
			height + "\".", 150, height);
	}
}
