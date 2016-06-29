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
package com.liferay.faces.test.showcase.select;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.showcase.TesterBase;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class SelectTester extends TesterBase {

	protected static final String modelValueElement1Xpath = "(//div[@class='results-content'])[1]/pre/text()";
	protected static final String checkbox1Xpath = "//input[contains(@id,':checkbox')]";
	protected static final String checkbox2Xpath = "(//input[contains(@id,':checkbox')])[2]";
	protected static final String option1Xpath = "(//select[contains(@id, 'select')]/option)[1]";
	protected static final String option2Xpath = "(//select[contains(@id, 'select')]/option)[2]";
	protected static final String option3Xpath = "(//select[contains(@id, 'select')]/option)[3]";
	protected static final String option4Xpath = "(//select[contains(@id, 'select')]/option)[4]";
	protected static final String select2Option1Xpath = "(//select[contains(@id, 'select')])[2]/option[1]";
	protected static final String select2Option3Xpath = "(//select[contains(@id, 'select')])[2]/option[3]";
	protected static final String conversionIncorrectMessage1Xpath = "//li[contains(text(),'Incorrect!')]";
	protected static final String conversionCorrectMessage1Xpath = "//li[contains(text(),'Correct!')]";

	// When <select multiple="multiple">, it is necessary to deselect options.
	protected void deselectByValueAndWaitForAjaxRerender(Browser browser, String selectXpath, String value) {
		WebElement selectElement = browser.findElementByXpath(selectXpath);
		Select select = new Select(selectElement);
		select.deselectByValue(value);

		// phantomjs browser does not fire the change event when <select multiple="multiple">, so the event must be
		// fired manually.
		if ("phantomjs".equals(browser.getName()) && (select.isMultiple())) {
			browser.executeScript(
				"var changeEvent = document.createEvent('HTMLEvents'); changeEvent.initEvent('change', true, true); arguments[0].dispatchEvent(changeEvent);",
				selectElement);
		}

		browser.waitUntil(ExpectedConditions.stalenessOf(selectElement));
		browser.waitForElementVisible(selectXpath);
	}

	protected void selectByValueAndWaitForAjaxRerender(Browser browser, String selectXpath, String value) {
		WebElement selectElement = browser.findElementByXpath(selectXpath);
		Select select = new Select(selectElement);
		select.selectByValue(value);

		// phantomjs browser does not fire the change event when <select multiple="multiple">, so the event must be
		// fired manually.
		if ("phantomjs".equals(browser.getName()) && select.isMultiple()) {
			browser.executeScript(
				"var changeEvent = document.createEvent('HTMLEvents'); changeEvent.initEvent('change', true, true); arguments[0].dispatchEvent(changeEvent);",
				selectElement);
		}

		browser.waitUntil(ExpectedConditions.stalenessOf(selectElement));
		browser.waitForElementVisible(selectXpath);
	}
}
