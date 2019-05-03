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
package com.liferay.faces.test.showcase.facescontexthelperutil;

import org.junit.Test;

import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.WaitingAsserter;
import com.liferay.faces.test.showcase.TesterBase;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class FacesContextHelperUtilGeneralTester extends TesterBase {

	@Test
	public void runFacesContextHelperUtilGeneralTest() throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, "util", "FacesContextHelperUtil", "general");

		// Test that clicking the "Show Modal" button shows the modal.
		browserDriver.clickElement("(//*[contains(@value,'Show Modal')])[1]");

		WaitingAsserter waitingAsserter = getWaitingAsserter();
		String modalDialogXpath = "//div[contains(@class,'modal-dialog')]";
		waitingAsserter.assertElementDisplayed(modalDialogXpath);

		// Test that the modal is still displayed (and shows an error message) when an empty value is submitted.
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);
		waitingAsserter.assertElementDisplayed(modalDialogXpath);
		waitingAsserter.assertTextPresentInElement("Email: Validation Error: Value is required.", error1Xpath);

		// Test that the modal is still displayed (and shows an error message) when an invalid value is submitted.
		String emailAddressInputTextXpath = "(//input[contains(@id,':text')])[1]";
		browserDriver.sendKeysToElement(emailAddressInputTextXpath, "HelloWorldcom");
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);
		waitingAsserter.assertElementDisplayed(modalDialogXpath);
		waitingAsserter.assertTextPresentInElement("Invalid E-Mail Address", error1Xpath);

		// Test that a valid value submits successfully and the modal disappears.
		browserDriver.clearElement(emailAddressInputTextXpath);
		browserDriver.sendKeysToElement(emailAddressInputTextXpath, "hello@world.com");
		browserDriver.clickElement(submitButton1Xpath);
		waitingAsserter.assertElementNotDisplayed(modalDialogXpath);
	}
}
