/**
 * Copyright (c) 2000-2019 Liferay, Inc. All rights reserved.
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

import org.openqa.selenium.WebElement;

import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.WaitingAsserter;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class InputTextareaSizeTester extends InputTextareaTester {

	@Test
	public void runInputTextareaSizeTest() throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, "inputTextarea", "size");

		// Test that the value submits successfully
		String text = "Hello World!";
		browserDriver.sendKeysToElement(textarea1Xpath, text);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);

		WaitingAsserter waitingAsserter = getWaitingAsserter();
		waitingAsserter.assertTextPresentInElement(text, modelValue1Xpath);
		waitingAsserter.assertElementDisplayed("//textarea[@cols='50'][@rows='6']");

		// Test that the value submits successfully
		browserDriver.sendKeysToElement(textarea2Xpath, text);
		browserDriver.clickElementAndWaitForRerender(submitButton2Xpath);
		waitingAsserter.assertTextPresentInElement(text, modelValue2Xpath);

		WebElement textarea2 = browserDriver.findElementByXpath(textarea2Xpath);
		String expectedWidth = "150px";
		String width = textarea2.getCssValue("width");
		Assert.assertEquals("Width of element " + textarea2 + " is not \"" + expectedWidth + "\". Instead it is \"" +
			width + "\".", expectedWidth, width);

		String expectedHeight = "150px";
		String height = textarea2.getCssValue("height");
		Assert.assertEquals("Height of element " + textarea2 + " is not \"" + expectedHeight + "\". Instead it is \"" +
			height + "\".", expectedHeight, height);
	}
}
