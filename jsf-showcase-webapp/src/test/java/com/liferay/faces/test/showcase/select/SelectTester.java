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
package com.liferay.faces.test.showcase.select;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
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

	// Common Xpath
	protected static final String conversionCorrectMessage1Xpath = "//li[contains(text(),'Correct!')]";
	protected static final String conversionIncorrectMessage1Xpath = "//li[contains(text(),'Incorrect!')]";
	protected static final String select1Xpath = "(//select[contains(@id, 'select')])[1]";
	protected static final String select2Xpath = "(//select[contains(@id, 'select')])[2]";

	// Protected Constants
	protected static final String OPTION_CHILD_XPATH = "/option";

	// Private Constants
	private static final String FIRE_SELECT_CHANGE_EVENT_SCRIPT =
		"var changeEvent = document.createEvent('HTMLEvents');" +
		"changeEvent.initEvent('change', true, true); arguments[0].parentNode.dispatchEvent(changeEvent);";

	protected void clickAndWaitForAjaxRerender(Browser browser, String xpath) {

		// SelectOneMenu and SelectManyMenu tests occasionally fail due to elements moving off screen, so center the
		// element in the view.
		if (this.getClass().getName().contains("Menu")) {
			browser.centerElementInView(xpath);
		}

		browser.clickAndWaitForAjaxRerender(xpath);
	}

	/**
	 * Click an option and wait for Ajax to rerender the option. This method exists because {@link
	 * Browser#clickAndWaitForAjaxRerender(java.lang.String)} does not work on selectOneMenu, selectManyListbox, and
	 * SelectManyMenu. For more information see method comments.
	 */
	protected void clickOptionAndWaitForAjaxRerender(Browser browser, String optionXpath) {

		// SelectOneMenu and SelectManyMenu tests occasionally fail due to elements moving off screen, so center the
		// element in the view.
		if (this.getClass().getName().contains("Menu")) {
			browser.centerElementInView(optionXpath);
		}

		WebElement optionElement = browser.findElementByXpath(optionXpath);

		// Note: clicking a selectOneMenu option on Chrome and PhantomJS via Actions.click(WebElement) (which is used
		// by Browser.clickAndWaitForAjaxRerender(String)) does not fire the select element's change event, so the
		// element must be clicked via Element.click().
		optionElement.click();

		// phantomjs browser does not fire a change event when a <select multiple="multiple"> <option> is clicked, so
		// the event must be fired manually.
		if ("phantomjs".equals(browser.getName())) {

			try {

				WebElement selectElement = optionElement.findElement(By.xpath(".."));
				Select select = new Select(selectElement);

				if (select.isMultiple()) {
					browser.executeScript(FIRE_SELECT_CHANGE_EVENT_SCRIPT, optionElement);
				}
			}
			catch (StaleElementReferenceException e) {
				// do nothing. The element is stale because an ajax rerender has correctly occured.
			}
		}

		browser.waitUntil(ExpectedConditions.stalenessOf(optionElement));
		browser.waitForElementVisible(optionXpath);
	}
}
