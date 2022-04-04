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
package com.liferay.faces.test.showcase.commandbutton;

import org.junit.Test;

import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.WaitingAsserter;
import com.liferay.faces.test.showcase.buttonlink.ButtonLinkTester;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class CommandButtonValueAttributeTester extends ButtonLinkTester {

	@Test
	public void runCommandButtonValueAttributeTest() throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, "commandButton", "value-attribute");

		// Test that the "John Adams" option submits successfully.
		String name = "John Adams";
		browserDriver.clickElementAndWaitForRerender(getValueButton(name));

		WaitingAsserter waitingAsserter = getWaitingAsserter();
		waitingAsserter.assertTextPresentInElement(name, modelValue1Xpath);

		// Test that the "Carter Braxton" option submits successfully.
		name = "Carter Braxton";
		browserDriver.clickElementAndWaitForRerender(getValueButton(name));
		waitingAsserter.assertTextPresentInElement(name, modelValue1Xpath);
	}

	private String getValueButton(String name) {
		return "(//text()[contains(.,'" + name + "')]|//span[contains(text(),'" + name +
			"')])/preceding-sibling::*[contains(@onclick,'mojarra.ab')][1]";
	}
}
