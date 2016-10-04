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
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class OutputStylesheetGeneralTester extends OutputTester {

	@Test
	public void runOutputStylesheetGeneralTest() throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, "outputStylesheet", "general");

		// Test that both buttons render on the page successfully.
		String button1Xpath = "(//button[normalize-space(text())='Button'])[1]";
		SeleniumAssert.assertElementVisible(browser, button1Xpath);

		String button2Xpath = "(//button[normalize-space(text())='Button'])[2]";
		SeleniumAssert.assertElementVisible(browser, button2Xpath);

		// Test that the first button's opacity is correct.
		WebElement buttonElement = browser.findElementByXpath(button1Xpath);
		String buttonOpacity = buttonElement.getCssValue("opacity");

		// Since the opacity is a floating point value, it's value may not match .65 exactly.
		Assert.assertTrue("The button's opacity is not .65, instead it is " + buttonOpacity,
			(buttonOpacity.contains(".649") || buttonOpacity.contains(".65")));

		// Test that the second button's display is block.
		buttonElement = browser.findElementByXpath(button2Xpath);

		String buttonDisplay = buttonElement.getCssValue("display");
		Assert.assertEquals("The button's display is not block, instead it is " + buttonDisplay, "block",
			buttonDisplay);
	}
}
