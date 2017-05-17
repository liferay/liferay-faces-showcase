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
package com.liferay.faces.test.showcase.datatable;

import org.junit.Test;

import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.BrowserStateAsserter;
import com.liferay.faces.test.showcase.TesterBase;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class DataTableGeneralTester extends TesterBase {

	@Test
	public void runDataTableGeneralTest() throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, "dataTable", "general");

		BrowserStateAsserter browserStateAsserter = getBrowserStateAsserter();
		browserStateAsserter.assertElementDisplayed(
			"(//div[contains(@class,'showcase-example-usage')])/table/thead/tr/th[contains(.,'Customers')]");
		browserStateAsserter.assertElementDisplayed(
			"(//div[contains(@class,'showcase-example-usage')])/table/thead/tr/th[contains(.,'First Name')]");
		browserStateAsserter.assertElementDisplayed(
			"(//div[contains(@class,'showcase-example-usage')])/table/thead/tr/th[contains(.,'Last Name')]");
		browserStateAsserter.assertElementDisplayed(
			"(//div[contains(@class,'showcase-example-usage')])/table/thead/tr/th[contains(.,'Date of Birth')]");
		browserStateAsserter.assertElementDisplayed(
			"(//div[contains(@class,'showcase-example-usage')])/table/thead/tr/th[contains(.,'Country')]");
		browserStateAsserter.assertElementDisplayed(
			"(//div[contains(@class,'showcase-example-usage')])/table/tbody/tr/td[contains(.,'Elbridge')]");
		browserStateAsserter.assertElementDisplayed(
			"(//div[contains(@class,'showcase-example-usage')])/table/tbody/tr/td[contains(.,'Braxton')]");
		browserStateAsserter.assertElementDisplayed(
			"(//div[contains(@class,'showcase-example-usage')])/table/tbody/tr/td[contains(.,'Apr 21, 1713')]");
		browserStateAsserter.assertElementDisplayed(
			"((//div[contains(@class,'showcase-example-usage')])/table/tbody/tr/td[contains(.,'United States')])[1]");
		browserStateAsserter.assertElementDisplayed(
			"(//div[contains(@class,'showcase-example-usage')])/table/tfoot/tr/td[contains(.,'Footer')]");
	}
}
