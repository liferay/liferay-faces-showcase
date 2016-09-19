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
package com.liferay.faces.test.showcase.buttonlink;

import org.junit.Assume;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;
import com.liferay.faces.test.showcase.TesterBase;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class ButtonLinkTester extends TesterBase {

	protected static final String divSubmitButton1Xpath = "(//div[@class='showcase-example-usage'])[1]/*";
	protected static final String divSubmitButton2Xpath = "(//div[@class='showcase-example-usage'])[2]/*";
	protected static final String imageTypeButton1Xpath = "//*[contains(@type,'image') or contains(@type,'button') and @value='1234'][1]";
	protected static final String buttonTypeButton1Xpath = "//*[contains(@type,'button') and contains(@value,'1234')][1]";
	protected static final String submitTypeButton1Xpath = "(//*[contains(@type,'submit')])[1]";

	protected void runButtonLinkGeneralTest(String componentName, String buttonLink1xpath, String buttonLink2xpath, String type)
			throws Exception {
		// Skip the test if it's not the JSF showcase. other showcases should
		// include their own test for this use case.
		Assume.assumeTrue(TEST_CONTEXT_URL.contains("jsf"));
		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentName, "general");

		// Wait to begin the test until a button is rendered.
		browser.waitForElementVisible(buttonLink2xpath);

		// Test that both buttons render on the page visibly and successfully.
		SeleniumAssert.assertElementVisible(browser, buttonLink1xpath);
		SeleniumAssert.assertElementVisible(browser, buttonLink2xpath);

		if (this.getClass().getName().contains("Button")) {
			// Test that the first button's styling type is image.
			SeleniumAssert.assertElementType(browser, buttonLink1xpath, "image");

			// Test that the second button's styling type is button.
			SeleniumAssert.assertElementType(browser, buttonLink2xpath, type);
		}

		// click first button
		browser.clickAndWaitForAjaxRerender(buttonLink1xpath);

		// click second button
		browser.clickAndWaitForAjaxRerender(buttonLink2xpath);
	}

	protected void runButtonLinkImmediateTest(String componentName)
			throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentName, "immediate");

		// Wait to begin the test until the submit button is rendered.
		browser.waitForElementVisible("(//*[contains(@onclick,'mojarra.ab')][contains(text(),'Submit') or contains(@value,'Submit')])[1]");

		// Test that the value submits successfully and the valueChangeListener
		// method is called during the
		// APPLY_REQUEST_VALUES phase.
		browser.clickAndWaitForAjaxRerender("(//*[contains(@onclick,'mojarra.ab')][contains(text(),'Submit') or contains(@value,'Submit')])[1]");
		SeleniumAssert.assertElementVisible(browser, immediateMessage1Xpath);

		// Test that the value submits successfully and the valueChangeListener
		// method is called during the
		// PROCESS_VALIDATIONS phase.
		browser.clickAndWaitForAjaxRerender("(//*[contains(@onclick,'mojarra.ab')][contains(text(),'Submit') or contains(@value,'Submit')])[2]");
		String immediateMessage2Xpath = "//li[contains(text(),'INVOKE_APPLICATION ')]";
		SeleniumAssert.assertElementVisible(browser, immediateMessage2Xpath);
	}

	protected void runButtonLinkNavigationParamTest(String componentName)
			throws Exception {
		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentName, "navigation");

		// Wait to begin the test until a button is rendered.
		String toParamPageXpath = "(//*[contains(text(),'To Param page') or contains(@value,'To Param page')])";
		browser.waitForElementVisible(toParamPageXpath);

		SeleniumAssert.assertElementVisible(browser, "(//pre/span[text()='foo='])");

		// Click "To Param page" and check that it opens the Param page
		browser.click(toParamPageXpath);
		String backToNavigationXpath = "(//*[contains(text(),'Back to Navigation with foo=1234') or contains(@value,'Back to Navigation with foo=1234')])";
		browser.waitForElementVisible(backToNavigationXpath);
		SeleniumAssert.assertElementVisible(browser, backToNavigationXpath);

		// Click "Back to Navigation with foo=1234" and assert that the value "1234" submits successfully.
		browser.click(backToNavigationXpath);
		browser.waitForElementVisible(toParamPageXpath);
		SeleniumAssert.assertElementVisible(browser, "(//pre/span[text()='foo=1234'])");

		if (this.getClass().getName().contains("Command")) {
			// Click "To Param page" and check that it opens the Param page
			String ajaxCheckboxXpath = "(//input[contains(@type,'checkbox')])[1]";
			browser.performAndWaitForAjaxRerender(browser.createClickAction(ajaxCheckboxXpath), toParamPageXpath);
			browser.click(toParamPageXpath);
			browser.waitForElementVisible(backToNavigationXpath);
			SeleniumAssert.assertElementVisible(browser, backToNavigationXpath);

			// Click "Back to Navigation with foo=1234" and assert that the value "1234" submits successfully.
			browser.performAndWaitForAjaxRerender(browser.createClickAction(ajaxCheckboxXpath), backToNavigationXpath);
			browser.click(backToNavigationXpath);
			browser.waitForElementVisible(toParamPageXpath);
			SeleniumAssert.assertElementVisible(browser, "(//pre/span[text()='foo=1234'])");
		}
	}
}
