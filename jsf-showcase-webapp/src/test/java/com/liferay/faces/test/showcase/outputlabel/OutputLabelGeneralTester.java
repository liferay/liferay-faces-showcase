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
package com.liferay.faces.test.showcase.outputlabel;

import org.junit.Test;

import org.openqa.selenium.WebElement;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;
import com.liferay.faces.test.showcase.output.OutputTester;


/**
 * @author  Neil Griffin
 */
public class OutputLabelGeneralTester extends OutputTester {

	@Test
	public void runOutputLabelGeneralTest() throws Exception {

		Browser browser = Browser.getInstance();
		browser.get(TEST_CONTEXT_URL + "/outputlabel/general");

		// Wait to begin the test until the submit button is rendered.
		browser.waitForElementVisible(submitButtonXpath);

		// Test that an error message is displayed when a value is required and an empty value is submitted.
		browser.clickAndWaitForAjaxRerender(submitButtonXpath);

		String valueRequiredText = "firstName: Validation Error: Value is required.";
		String fieldXpath = "(//div[@class='showcase-example-usage'])[1]/div";
		SeleniumAssert.assertElementTextVisible(browser, fieldXpath, valueRequiredText);

		// Test that the value submits successfully and is displayed in the model value
		String inputLabelXpath = "(//input[contains(@id,':firstName')])[1]";
		browser.centerElementInView(inputLabelXpath);

		WebElement input = browser.findElementByXpath(inputLabelXpath);
		String text = "Philip";
		input.sendKeys(text);
		browser.clickAndWaitForAjaxRerender(submitButtonXpath);

		String modelValueXpath = "(//span[contains(@id,':modelValue')])[1]";
		SeleniumAssert.assertElementTextVisible(browser, modelValueXpath, text);

		String valueRequiredMessageXpath =
			"(//div[@class='showcase-example-usage'])[1]/div[contains(text(),'Value is required.')]";
		SeleniumAssert.assertElementNotPresent(browser, valueRequiredMessageXpath);

		String successMessageXpath = "(//div[@class='showcase-example-usage'])[1]/table/tbody/tr/td";
		SeleniumAssert.assertElementVisible(browser, successMessageXpath);
	}
}
