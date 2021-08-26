/**
 * Copyright (c) 2000-2021 Liferay, Inc. All rights reserved.
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

import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.WaitingAsserter;
import com.liferay.faces.test.showcase.output.OutputTester;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class OutputLabelGeneralTester extends OutputTester {

	@Test
	public void runOutputLabelGeneralTest() throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, "outputLabel", "general");

		// Test that the web page shows an error message when an empty value is submitted.
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);

		WaitingAsserter waitingAsserter = getWaitingAsserter();
		String valueRequiredText = "Name: Validation Error: Value is required.";
		String field1Xpath = "(//div[@class='showcase-example-usage'])[1]/div";
		waitingAsserter.assertTextPresentInElement(valueRequiredText, field1Xpath);

		// Test that the value submits successfully and is displayed in the model value
		String inputFirstName1Xpath = "(//input[contains(@id,':firstName')])[1]";

		String text = "Philip";
		browserDriver.sendKeysToElement(inputFirstName1Xpath, text);
		browserDriver.clickElementAndWaitForRerender(submitButton1Xpath);

		String modelValue1Xpath = "(//span[contains(@id,':modelValue')])[1]";
		waitingAsserter.assertTextPresentInElement(text, modelValue1Xpath);

		String valueRequiredMessage1Xpath =
			"(//div[@class='showcase-example-usage'])[1]/div[contains(text(),'Value is required.')]";
		waitingAsserter.assertElementNotDisplayed(valueRequiredMessage1Xpath);

		// Test that the success message is rendered.
		String successMessage1Xpath = "(//div[@class='showcase-example-usage'])[1]/table/tbody/tr/td";
		waitingAsserter.assertTextPresentInElement("Your request processed successfully.", successMessage1Xpath);
	}
}
