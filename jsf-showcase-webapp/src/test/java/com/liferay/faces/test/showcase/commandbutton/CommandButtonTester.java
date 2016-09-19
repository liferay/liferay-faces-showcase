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
package com.liferay.faces.test.showcase.commandbutton;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;
import com.liferay.faces.test.showcase.buttonlink.ButtonLinkTester;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class CommandButtonTester extends ButtonLinkTester {

	protected void runVariousStylesTest(String componentName)
			throws Exception {
		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentName, "various-styles");

		// Wait to begin the test until a button is rendered.
		browser.waitForElementVisible("//*[contains(@class, 'btn') and contains(@class, 'btn-primary')][@value='Block' or contains(., 'Block')]");

		// Test the values and classes of every button.
		SeleniumAssert.assertElementVisible(browser, "//*[contains(@class, 'btn') and contains(@class, 'btn-primary')][@value='Primary' or contains(., 'Primary')]");
		SeleniumAssert.assertElementVisible(browser, "//*[contains(@class, 'btn') and contains(@class, 'btn-info')][@value='Info' or contains(., 'Info')]");
		SeleniumAssert.assertElementVisible(browser, "//*[contains(@class, 'btn') and contains(@class, 'btn-success')][@value='Success' or contains(., 'Success')]");
		SeleniumAssert.assertElementVisible(browser, "//*[contains(@class,'btn') and contains(@class, 'btn-warning')][@value='Warning' or contains(., 'Warning')]");
		SeleniumAssert.assertElementVisible(browser, "//*[contains(@class, 'btn') and contains(@class, 'btn-danger')][@value=' Danger' or contains(., ' Danger')]");
		SeleniumAssert.assertElementVisible(browser, "//*[contains(@class, 'btn') and contains(@class, 'btn-link')][@value='Link' or contains(., 'Link')]");
		SeleniumAssert.assertElementVisible(browser,
				"//*[contains(@class, 'btn') and contains(@class, 'btn-primary')][@value='Large' or contains(., 'Large')]");
		SeleniumAssert.assertElementVisible(browser, "//*[contains(@class, 'btn') and contains(@class, 'btn-lg')][@value='Large' or contains(., 'Large')][2]");
		SeleniumAssert.assertElementVisible(browser, "//*[contains(@class, 'btn') and contains(@class, 'btn-primary')][@value='Default' or contains(., 'Default')]");
		SeleniumAssert.assertElementVisible(browser, "//*[contains(@class, 'btn')][@value='Default' or contains(., 'Default')][2]");
		SeleniumAssert.assertElementVisible(browser,
				"//*[contains(@class, 'btn') and contains(@class, 'btn-primary')][@value='Small' or contains(., 'Small')]");
		SeleniumAssert.assertElementVisible(browser, "//*[contains(@class, 'btn') and contains(@class, 'btn-sm')][@value='Small' or contains(., 'Small')][2]");
		SeleniumAssert.assertElementVisible(browser,
				"//*[contains(@class, 'btn') and contains(@class, 'btn-primary')][@value='Mini' or contains(., 'Mini')]");
		SeleniumAssert.assertElementVisible(browser, "//*[contains(@class, 'btn') and contains(@class, 'btn-xs')][@value='Mini' or contains(., 'Mini')][2]");
		SeleniumAssert.assertElementVisible(browser,
				"//*[contains(@class, 'btn') and contains(@class, 'btn-primary')][@value='Disabled' or contains(., 'Disabled')]");
		SeleniumAssert.assertElementVisible(browser,
				"(//*[contains(@class, 'btn')][@value='Disabled' or contains(., 'Disabled')])[2]");
		SeleniumAssert.assertElementVisible(browser,
				"//*[contains(@class, 'btn') and contains(@class, 'btn-primary')][@value='Block' or contains(., 'Block')]");
		SeleniumAssert.assertElementVisible(browser, "//*[contains(@class, 'btn') and contains(@class, 'btn-block')][@value='Block' or contains(., 'Block')][2]");

		// click first button
		browser.click("//*[contains(@class, 'btn') and contains(@class, 'btn-primary')][@value='Primary' or contains(., 'Primary')]");
	}
}
