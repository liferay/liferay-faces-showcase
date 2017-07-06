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
package com.liferay.faces.test.showcase.inputfile;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.TestUtil;
import com.liferay.faces.test.selenium.browser.WaitingAsserter;
import com.liferay.faces.test.showcase.input.InputTester;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class InputFileTester extends InputTester {

	// Private Constants
	private static final String LIFERAY_JSF_JERSEY_PNG_FILE_PATH = TestUtil.JAVA_IO_TMPDIR + "liferay-jsf-jersey.png";
	private static final String ALERT_CONFIRMATION_WORKAROUND = "window.alert = function(msg){ console.log(msg); };" +
		"window.confirm = function(msg){ console.log(msg); return true; };";

	// Private Xpaths
	private static final String fileUploadChooserXpath = "//input[@type='file']";
	private static final String uploadedFileXpath = "(//pre/table/tbody/tr/td[2]|//pre/table/tbody/tr/td[2]/span)";
	private static final String deleteFileXpath =
		"(//input[contains(@src,'icon-delete.png')]|//button/span[contains(@class,'trash')])";

	protected void runInputFileTest(String useCase) {
		runInputFileTest(null, useCase);
	}

	protected void runInputFileTest(String componentPrefix, String useCase) {

		BrowserDriver browserDriver = getBrowserDriver();

		if (componentPrefix != null) {
			navigateToUseCase(browserDriver, componentPrefix, "inputFile", useCase);
		}
		else {
			navigateToUseCase(browserDriver, "inputFile", useCase);
		}

		String browserDriverName = browserDriver.getBrowserName();

		// Workaround https://github.com/ariya/phantomjs/issues/10993 by removing the multiple attribute from <input
		// type="file" />
		if (browserDriverName.equals("phantomjs")) {

			browserDriver.executeScriptInCurrentWindow(
				"var multipleFileUploadElements = document.querySelectorAll('input[type=\"file\"][multiple]');" +
				"for (var i = 0; i < multipleFileUploadElements.length; i++) {" +
				"multipleFileUploadElements[i].removeAttribute('multiple'); }");
		}

		browserDriver.sendKeysToElement(fileUploadChooserXpath, LIFERAY_JSF_JERSEY_PNG_FILE_PATH);

		if (!useCase.equals("instant-ajax")) {
			browserDriver.clickElement(submitButton1Xpath);
		}

		WaitingAsserter waitingAsserter = getWaitingAsserter();
		waitingAsserter.assertTextPresentInElement("jersey", uploadedFileXpath);

		// Workaround https://github.com/detro/ghostdriver/issues/20: Implement all the Session Commands related to JS
		// Alert, Prompt and Confirm.
		if (isHeadlessChrome(browserDriver) || browserDriverName.contains("phantomjs")) {

			browserDriver.executeScriptInCurrentWindow(ALERT_CONFIRMATION_WORKAROUND);
			browserDriver.clickElement(deleteFileXpath);
		}
		else {

			browserDriver.clickElement(deleteFileXpath);
			browserDriver.waitFor(ExpectedConditions.alertIsPresent());
			browserDriver.acceptAlert();
		}

		By byXpath = By.xpath(uploadedFileXpath);
		ExpectedCondition<Boolean> invisibilityOfElement = ExpectedConditions.invisibilityOfElementLocated(byXpath);
		ExpectedCondition<Boolean> textNotToBePresentInElement = ExpectedConditions.not(ExpectedConditions
				.textToBePresentInElementLocated(byXpath, "jersey"));
		waitingAsserter.assertTrue(ExpectedConditions.or(invisibilityOfElement, textNotToBePresentInElement));
	}
}
