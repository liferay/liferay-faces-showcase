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
		browser.waitForElementVisible(submitButton1Xpath);

		// Test that the web page shows an error message when an empty value is submitted.
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);

		String valueRequiredText = "firstName: Validation Error: Value is required.";
		String field1Xpath = "(//div[@class='showcase-example-usage'])[1]/div";
		SeleniumAssert.assertElementTextVisible(browser, field1Xpath, valueRequiredText);

		// Test that the value submits successfully and is displayed in the model value
		String inputFirstName1Xpath = "(//input[contains(@id,':firstName')])[1]";
		browser.centerElementInView(inputFirstName1Xpath);

		String text = "Philip";
		browser.sendKeys(inputFirstName1Xpath, text);
		browser.clickAndWaitForAjaxRerender(submitButton1Xpath);

		String modelValue1Xpath = "(//span[contains(@id,':modelValue')])[1]";
		SeleniumAssert.assertElementTextVisible(browser, modelValue1Xpath, text);

		String valueRequiredMessage1Xpath =
			"(//div[@class='showcase-example-usage'])[1]/div[contains(text(),'Value is required.')]";
		SeleniumAssert.assertElementNotPresent(browser, valueRequiredMessage1Xpath);

		// Test that the success message is rendered.
		String successMessage1Xpath = "(//div[@class='showcase-example-usage'])[1]/table/tbody/tr/td";
		SeleniumAssert.assertElementTextVisible(browser, successMessage1Xpath, "Your request processed successfully.");
	}
}
