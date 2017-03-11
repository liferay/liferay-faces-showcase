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
package com.liferay.faces.test.showcase.facesrequestcontext;

import org.junit.Assert;
import org.junit.Test;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;
import com.liferay.faces.test.showcase.TesterBase;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class FacesRequestContextGeneralTester extends TesterBase {

	@Test
	public void runFacesRequestContextGeneralTest() throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, "util", "FacesRequestContext", "general");

		// Test that clicking the "Show Modal" button shows the modal.
		browser.click("(//*[contains(@value,'Show Modal')])[1]");
		browser.waitForElementVisible(submitButton1Xpath);

		String modalDialogXpath = "//div[contains(@class,'modal-dialog')]";
		SeleniumAssert.assertElementVisible(browser, modalDialogXpath);

		// Test that the modal is still visible (and shows an error message) when an empty value is submitted.
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementVisible(browser, modalDialogXpath);
		SeleniumAssert.assertElementTextVisible(browser, error1Xpath, "Email: Validation Error: Value is required.");

		// Test that the modal is still visible (and shows an error message) when an invalid value is submitted.
		String emailAddressInputTextXpath = "(//input[contains(@id,':text')])[1]";
		browser.sendKeys(emailAddressInputTextXpath, "HelloWorldcom");
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);
		SeleniumAssert.assertElementVisible(browser, modalDialogXpath);
		SeleniumAssert.assertElementTextVisible(browser, error1Xpath, "Invalid E-Mail Address");

		// Test that a valid value submits successfully and the modal disappears.
		browser.clear(emailAddressInputTextXpath);
		browser.sendKeys(emailAddressInputTextXpath, "hello@world.com");

		WebElement submitButton1 = browser.findElementByXpath(submitButton1Xpath);
		submitButton1.click();
		browser.waitUntil(ExpectedConditions.stalenessOf(submitButton1));
		browser.waitForElementPresent(submitButton1Xpath);

		WebElement modalElement = browser.findElementByXpath(modalDialogXpath);
		Assert.assertTrue("Element " + modalDialogXpath + " is displayed.", !modalElement.isDisplayed());
	}
}
