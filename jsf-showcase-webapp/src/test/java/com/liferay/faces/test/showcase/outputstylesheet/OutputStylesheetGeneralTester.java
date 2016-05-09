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
package com.liferay.faces.test.showcase.outputstylesheet;

import org.junit.Assert;
import org.junit.Test;

import org.openqa.selenium.WebElement;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;
import com.liferay.faces.test.showcase.output.OutputTester;


/**
 * @author  Neil Griffin
 */
public class OutputStylesheetGeneralTester extends OutputTester {

	@Test
	public void runOutputStylesheetGeneralTest() throws Exception {

		Browser browser = Browser.getInstance();
		browser.get(TEST_CONTEXT_URL + "/outputstylesheet/general");

		// Wait to begin the test until an attribute is rendered.
		String buttonXpath = "(//input[@type='button'])[1]";
		browser.waitForElementVisible(buttonXpath);

		// Test that both buttons render on the page successfully.
		SeleniumAssert.assertElementVisible(browser, buttonXpath);

		String buttonXpathRight = "(//input[@type='button'])[2]";
		SeleniumAssert.assertElementVisible(browser, buttonXpathRight);

		// Test that the first button's styling CSS renders on the page successfully.
		WebElement buttonElement = browser.findElementByXpath(buttonXpath);
		String buttonOpacity = buttonElement.getCssValue("opacity");
		Assert.assertTrue("The button's opacity is not .65, instead it is " + buttonOpacity,
			buttonOpacity.contains(".64") || buttonOpacity.contains(".65") || buttonOpacity.contains(".66"));

		// Test that the second button's styling CSS renders on the page successfully.
		buttonElement = browser.findElementByXpath(buttonXpathRight);

		String buttonDisplay = buttonElement.getCssValue("display");
		Assert.assertEquals("The button's display is not block, instead it is " + buttonDisplay, buttonDisplay,
			"block");
	}
}
