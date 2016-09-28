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

	protected static final String generalButton1Xpath = "//input[contains(@src,'jsf-logo-small.png')][@type='image']";
	protected static final String generalLink1Xpath =
		"//img[contains(@src,'jsf-logo-small.png')]/parent::a[contains(text(),'Text for a link')]";
	protected static final String generalLink2Xpath =
		"//a[contains(text(),'Text for a link')][not(contains(@src,'jsf-logo-small.png'))]";

	protected void runButtonLinkGeneralTest(String componentName, String buttonLink1xpath, String buttonLink2xpath)
		throws Exception {

		// Skip the test if it's not the JSF showcase. other showcases should
		// include their own test for this use case.
		Assume.assumeTrue(TEST_CONTEXT_URL.contains("jsf"));

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentName, "general");

		// Test that both buttons/links render on the page visibly and are clickable.
		SeleniumAssert.assertElementVisible(browser, buttonLink1xpath);
		SeleniumAssert.assertElementVisible(browser, buttonLink2xpath);
		browser.click(buttonLink1xpath);
		browser.click(buttonLink2xpath);
	}

	protected void runButtonLinkImmediateTest(String componentName) throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentName, "immediate");

		// Test that the value submits successfully and the valueChangeListener
		// method is called during the
		// APPLY_REQUEST_VALUES phase.
		browser.clickAndWaitForAjaxRerender(
			"(//*[contains(@onclick,'mojarra.ab')][contains(text(),'Submit') or contains(@value,'Submit')])[1]");
		SeleniumAssert.assertElementVisible(browser, immediateMessage1Xpath);

		// Test that the value submits successfully and the valueChangeListener
		// method is called during the
		// PROCESS_VALIDATIONS phase.
		browser.clickAndWaitForAjaxRerender(
			"(//*[contains(@onclick,'mojarra.ab')][contains(text(),'Submit') or contains(@value,'Submit')])[2]");
		SeleniumAssert.assertElementVisible(browser, "//li[contains(text(),'INVOKE_APPLICATION ')]");
	}

	protected void runButtonLinkNavigationParamTest(String componentName) throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentName, "navigation");
		SeleniumAssert.assertElementVisible(browser, "//pre/span[text()='foo=']");

		String toParamPageXpath = "//*[contains(text(),'To Param page') or contains(@value,'To Param page')]";
		String backToNavigationXpath =
			"//*[contains(text(),'Back to Navigation with foo=1234') or contains(@value,'Back to Navigation with foo=1234')]";
		testNavigationPage(browser, toParamPageXpath, backToNavigationXpath);
		testParamPage(browser, toParamPageXpath, backToNavigationXpath);

		if (componentName.startsWith("command")) {

			// Click "To Param page" and check that it opens the Param page
			String ajaxCheckbox1Xpath = "//label[contains(text(),'Ajax')]/input[@type='checkbox']";
			browser.clickAndWaitForAjaxRerender(ajaxCheckbox1Xpath);
			testNavigationPage(browser, toParamPageXpath, backToNavigationXpath);

			// Click "Back to Navigation with foo=1234" and assert that the value "1234" submits successfully.
			browser.clickAndWaitForAjaxRerender(ajaxCheckbox1Xpath);
			testParamPage(browser, toParamPageXpath, backToNavigationXpath);
		}
	}

	protected void runVariousStylesTest(String componentName) throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentName, "various-styles");

		// Test the values and classes of every button.
		SeleniumAssert.assertElementVisible(browser,
			"//*[contains(@class,'btn ')][contains(@class,'btn-primary')][@value='Primary' or contains(.,'Primary')]");
		SeleniumAssert.assertElementVisible(browser,
			"//*[contains(@class,'btn ')][contains(@class,'btn-info')][@value='Info' or contains(.,'Info')]");
		SeleniumAssert.assertElementVisible(browser,
			"//*[contains(@class,'btn ')][contains(@class,'btn-success')][@value='Success' or contains(.,'Success')]");
		SeleniumAssert.assertElementVisible(browser,
			"//*[contains(@class,'btn')][contains(@class,'btn-warning')][@value='Warning' or contains(.,'Warning')]");
		SeleniumAssert.assertElementVisible(browser,
			"//*[contains(@class,'btn ')][contains(@class,'btn-danger')][@value=' Danger' or contains(.,' Danger')]");
		SeleniumAssert.assertElementVisible(browser,
			"//*[contains(@class,'btn ')][contains(@class,'btn-link')][@value='Link' or contains(.,'Link')]");
		SeleniumAssert.assertElementVisible(browser,
			"//*[contains(@class,'btn ')][contains(@class,'btn-primary')][@value='Large' or contains(.,'Large')]");
		SeleniumAssert.assertElementVisible(browser,
			"//*[contains(@class,'btn ')][contains(@class,'btn-lg')][@value='Large' or contains(.,'Large')][2]");
		SeleniumAssert.assertElementVisible(browser,
			"//*[contains(@class,'btn ')][contains(@class,'btn-primary')][@value='Default' or contains(.,'Default')]");
		SeleniumAssert.assertElementVisible(browser,
			"//*[contains(@class,'btn ') or @class='btn'][@value='Default' or contains(.,'Default')][2]");
		SeleniumAssert.assertElementVisible(browser,
			"//*[contains(@class,'btn ')][contains(@class,'btn-primary')][@value='Small' or contains(.,'Small')]");
		SeleniumAssert.assertElementVisible(browser,
			"//*[contains(@class,'btn ')][contains(@class,'btn-sm')][@value='Small' or contains(.,'Small')][2]");
		SeleniumAssert.assertElementVisible(browser,
			"//*[contains(@class,'btn ')][contains(@class,'btn-primary')][@value='Mini' or contains(.,'Mini')]");
		SeleniumAssert.assertElementVisible(browser,
			"//*[contains(@class,'btn ')][contains(@class,'btn-xs')][@value='Mini' or contains(.,'Mini')][2]");
		SeleniumAssert.assertElementVisible(browser,
			"//*[contains(@class,'btn ')][contains(@class,'btn-primary')][@value='Disabled' or contains(.,'Disabled')]");
		SeleniumAssert.assertElementVisible(browser,
			"(//*[contains(@class,'btn ')][@value='Disabled' or contains(.,'Disabled')])[2]");
		SeleniumAssert.assertElementVisible(browser,
			"//*[contains(@class,'btn ')][contains(@class,'btn-primary')][@value='Block' or contains(.,'Block')]");
		SeleniumAssert.assertElementVisible(browser,
			"//*[contains(@class,'btn ')][contains(@class,'btn-block')][@value='Block' or contains(.,'Block')][2]");

		// click first button
		browser.click(
			"//*[contains(@class,'btn ')][contains(@class,'btn-primary')][@value='Primary' or contains(.,'Primary')]");
	}

	private void testNavigationPage(Browser browser, String toParamPageXpath, String backToNavigationXpath) {

		// Click "To Param page" and check that it opens the Param page.
		browser.click(toParamPageXpath);
		browser.waitForElementVisible(backToNavigationXpath);
		waitForShowcasePageReady(browser);
		SeleniumAssert.assertElementVisible(browser, backToNavigationXpath);
	}

	private void testParamPage(Browser browser, String toParamPageXpath, String backToNavigationXpath) {

		// Click "Back to Navigation with foo=1234" and assert that the value "1234" appears in the model value.
		browser.click(backToNavigationXpath);
		browser.waitForElementVisible(toParamPageXpath);
		waitForShowcasePageReady(browser);
		SeleniumAssert.assertElementVisible(browser, "//pre/span[text()='foo=1234']");
	}
}
