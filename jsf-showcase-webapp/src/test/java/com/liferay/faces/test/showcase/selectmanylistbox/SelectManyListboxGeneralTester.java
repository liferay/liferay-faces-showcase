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
package com.liferay.faces.test.showcase.selectmanylistbox;

import org.junit.Test;

import org.openqa.selenium.support.ui.Select;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.showcase.selectmanymenu.SelectManyMenuTester;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class SelectManyListboxGeneralTester extends SelectManyMenuTester {

	@Test
	public void runSelectManyListboxGeneralTest() throws Exception {
		Browser browser = Browser.getInstance();
		browser.get(TEST_CONTEXT_URL + "/selectmanylistbox/general");

		// Wait to begin the test until the submit button is rendered.
		browser.waitForElementVisible(submitButton1Xpath);

		// Test that the web page shows an error message when a value is required and an empty value is submitted.
		String select1Xpath = "//select[contains(@id,':selectManyListbox')]";
		Select select = new Select(browser.findElementByXpath(select1Xpath));
		select.deselectAll();
		testRequiredCheckbox(browser);
		select = new Select(browser.findElementByXpath(select1Xpath));
		select.deselectAll();

		// Test that the first and third values in the listbox submits successfully.
		String answer2 = "1";
		String answer3 = "2";
		String answer4 = "3";
		testManyMenuGeneral(browser, answer2, answer3, answer4);
	}
}
