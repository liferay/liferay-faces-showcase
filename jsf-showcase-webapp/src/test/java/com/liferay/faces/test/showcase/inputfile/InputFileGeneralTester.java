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
package com.liferay.faces.test.showcase.inputfile;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class InputFileGeneralTester extends InputFileTester {

	@Test
	public void runInputFileGeneralTest() throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, "inputfile", "general");

		// Test that inputfile uploads and submits file successfully.
		browser.findElementByXpath(fileUploadChooserXpath).sendKeys(LIFERAY_JSF_JERSEY_PNG_FILE_PATH);
		browser.click(submitButton1Xpath);
		browser.waitUntil(ExpectedConditions.presenceOfElementLocated(By.xpath(uploadedFileXpath)));
		SeleniumAssert.assertElementTextVisible(browser, uploadedFileXpath, "jersey");
	}
}
