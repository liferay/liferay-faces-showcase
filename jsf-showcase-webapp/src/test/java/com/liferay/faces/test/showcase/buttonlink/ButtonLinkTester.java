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
package com.liferay.faces.test.showcase.buttonlink;

import java.util.Locale;

import org.junit.Assume;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.WaitingAsserter;
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
		"//a[contains(text(),'Text for a link')][not(./img[contains(@src,'jsf-logo-small.png')])]";

	protected void runButtonLinkGeneralTest(String componentName, String buttonLink1xpath, String buttonLink2xpath)
		throws Exception {

		// Skip the test if it's not the JSF showcase. other showcases should
		// include their own test for this use case.
		Assume.assumeTrue(SHOWCASE_CONTEXT_URL.contains("jsf"));

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, componentName, "general");

		// Test that both buttons/links render on the page visibly and are clickable.
		WaitingAsserter waitingAsserter = getWaitingAsserter();
		waitingAsserter.assertElementDisplayed(buttonLink1xpath);
		clickButtonLink(browserDriver, buttonLink1xpath);
		waitingAsserter.assertElementDisplayed(buttonLink2xpath);
		clickButtonLink(browserDriver, buttonLink2xpath);

		String lowerCaseComponentName = componentName.toLowerCase(Locale.ENGLISH);

		// Test that the images render on the link use cases successfully.
		if (lowerCaseComponentName.contains("link")) {
			assertImageRendered(browserDriver, waitingAsserter, getExampleImageXpath("children"));
		}
	}

	protected void runButtonLinkImmediateTest(String componentName) throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, componentName, "immediate");

		// Test that the value submits successfully and the valueChangeListener
		// method is called during the
		// APPLY_REQUEST_VALUES phase.
		browserDriver.clickElementAndWaitForRerender(
			"(//*[contains(@onclick,'mojarra.ab')][contains(text(),'Submit') or contains(@value,'Submit')])[1]");

		WaitingAsserter waitingAsserter = getWaitingAsserter();
		waitingAsserter.assertElementDisplayed(immediateMessage1Xpath);

		// Test that the value submits successfully and the valueChangeListener
		// method is called during the
		// PROCESS_VALIDATIONS phase.
		browserDriver.clickElementAndWaitForRerender(
			"(//*[contains(@onclick,'mojarra.ab')][contains(text(),'Submit') or contains(@value,'Submit')])[2]");
		waitingAsserter.assertElementDisplayed("//li[contains(text(),'INVOKE_APPLICATION ')]");
	}

	protected void runButtonLinkNavigationParamTest(String componentName) throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, componentName, "navigation");

		WaitingAsserter waitingAsserter = getWaitingAsserter();
		waitingAsserter.assertElementDisplayed("//pre/span[text()='foo=']");

		String toParamPageXpath =
			"(//a[contains(.,'To Param page')]|//input[contains(@value,'To Param page')]|//button[contains(.,'To Param page')])";
		String backToNavigationXpath =
			"(//a[contains(.,'Back to Navigation with foo=1234')]|//input[contains(@value,'Back to Navigation with foo=1234')]|//button[contains(.,'Back to Navigation with foo=1234')])";
		testNavigationPage(browserDriver, waitingAsserter, toParamPageXpath, backToNavigationXpath);
		testParamPage(browserDriver, waitingAsserter, toParamPageXpath, backToNavigationXpath);

		if (componentName.startsWith("command")) {

			// Click "To Param page" and check that it opens the Param page
			String ajaxCheckbox1Xpath = "//label[contains(text(),'Ajax')]/input[@type='checkbox']";
			browserDriver.clickElementAndWaitForRerender(ajaxCheckbox1Xpath);

			String ajaxButtonLinkXpathSuffix =
				"[contains(@onclick,'mojarra.ab') or contains(@onclick,'jsf.ajax.request')]";

			testNavigationPage(browserDriver, waitingAsserter, toParamPageXpath + ajaxButtonLinkXpathSuffix,
				backToNavigationXpath);

			// Click "Back to Navigation with foo=1234" and assert that the value "1234" submits successfully.
			browserDriver.clickElementAndWaitForRerender(ajaxCheckbox1Xpath);
			testParamPage(browserDriver, waitingAsserter, toParamPageXpath,
				backToNavigationXpath + ajaxButtonLinkXpathSuffix);
		}
	}

	protected void runVariousStylesTest(String componentName) throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, componentName, "various-styles");

		// Test the values and classes of every button.
		WaitingAsserter waitingAsserter = getWaitingAsserter();
		waitingAsserter.assertElementDisplayed(
			"//*[contains(@class,'btn ')][contains(@class,'btn-primary')][@value='Primary' or contains(.,'Primary')]");
		waitingAsserter.assertElementDisplayed(
			"//*[contains(@class,'btn ')][contains(@class,'btn-info')][@value='Info' or contains(.,'Info')]");
		waitingAsserter.assertElementDisplayed(
			"//*[contains(@class,'btn ')][contains(@class,'btn-success')][@value='Success' or contains(.,'Success')]");
		waitingAsserter.assertElementDisplayed(
			"//*[contains(@class,'btn ')][contains(@class,'btn-warning')][@value='Warning' or contains(.,'Warning')]");
		waitingAsserter.assertElementDisplayed(
			"//*[contains(@class,'btn ')][contains(@class,'btn-danger')][@value=' Danger' or contains(.,' Danger')]");
		waitingAsserter.assertElementDisplayed(
			"//*[contains(@class,'btn ')][contains(@class,'btn-link')][@value='Link' or contains(.,'Link')]");
		waitingAsserter.assertElementDisplayed(
			"//*[contains(@class,'btn ')][contains(@class,'btn-primary')][contains(@class,'btn-lg') or contains(@class,'btn-large')][@value='Large' or contains(.,'Large')]");
		waitingAsserter.assertElementDisplayed(
			"//*[contains(@class,'btn ')][contains(@class,'btn-lg') or contains(@class,'btn-large')][@value='Large' or contains(.,'Large')][2]");
		waitingAsserter.assertElementDisplayed(
			"//*[contains(@class,'btn ')][contains(@class,'btn-primary')][@value='Default' or contains(.,'Default')]");
		waitingAsserter.assertElementDisplayed(
			"//*[contains(@class,'btn ') or @class='btn'][@value='Default' or contains(.,'Default')][2]");
		waitingAsserter.assertElementDisplayed(
			"//*[contains(@class,'btn ')][contains(@class,'btn-primary')][contains(@class,'btn-sm') or contains(@class,'btn-small')][@value='Small' or contains(.,'Small')]");
		waitingAsserter.assertElementDisplayed(
			"//*[contains(@class,'btn ')][contains(@class,'btn-sm') or contains(@class,'btn-small')][@value='Small' or contains(.,'Small')][2]");
		waitingAsserter.assertElementDisplayed(
			"//*[contains(@class,'btn ')][contains(@class,'btn-primary')][contains(@class,'btn-mini') or contains(@class,'btn-xs')][@value='Mini' or contains(.,'Mini')]");
		waitingAsserter.assertElementDisplayed(
			"//*[contains(@class,'btn ')][contains(@class,'btn-mini') or contains(@class,'btn-xs')][@value='Mini' or contains(.,'Mini')][2]");
		waitingAsserter.assertElementDisplayed(
			"//*[contains(@class,'btn ')][contains(@class,'btn-primary')][@value='Disabled' or contains(.,'Disabled')]");
		waitingAsserter.assertElementDisplayed(
			"(//*[contains(@class,'btn ') or @class='btn'][@value='Disabled' or contains(.,'Disabled')])[2]");
		waitingAsserter.assertElementDisplayed(
			"//*[contains(@class,'btn ')][contains(@class,'btn-primary')][@value='Block' or contains(.,'Block')]");
		waitingAsserter.assertElementDisplayed(
			"//*[contains(@class,'btn ')][contains(@class,'btn-block')][@value='Block' or contains(.,'Block')][2]");

		// Click first button.
		clickButtonLink(browserDriver,
			"//*[contains(@class,'btn ')][contains(@class,'btn-primary')][@value='Primary' or contains(.,'Primary')]");
	}

	private void clickButtonLink(BrowserDriver browserDriver, String xpath) {

		WebElement webElement = browserDriver.findElementByXpath(xpath);
		String tagName = webElement.getTagName();
		String onclick = webElement.getAttribute("onclick");
		browserDriver.clickElement(xpath);

		// If the clicking the button/link will cause the page to reload.
		if (!"button".equals(tagName) || ((onclick != null) && !"".equals(onclick))) {

			browserDriver.waitFor(ExpectedConditions.stalenessOf(webElement));
			waitForShowcasePageReady(browserDriver);
		}
	}

	private void testNavigationPage(BrowserDriver browserDriver, WaitingAsserter waitingAsserter,
		String toParamPageXpath, String backToNavigationXpath) {

		// Click "To Param page" and check that it opens the Param page.
		browserDriver.clickElement(toParamPageXpath);
		waitingAsserter.assertElementDisplayed(backToNavigationXpath);
		waitForShowcasePageReady(browserDriver);
	}

	private void testParamPage(BrowserDriver browserDriver, WaitingAsserter waitingAsserter, String toParamPageXpath,
		String backToNavigationXpath) {

		// Click "Back to Navigation with foo=1234" and assert that the value "1234" appears in the model value.
		browserDriver.clickElement(backToNavigationXpath);
		waitingAsserter.assertElementDisplayed(toParamPageXpath);
		waitingAsserter.assertElementDisplayed("//pre/span[text()='foo=1234']");
		waitForShowcasePageReady(browserDriver);
	}
}
