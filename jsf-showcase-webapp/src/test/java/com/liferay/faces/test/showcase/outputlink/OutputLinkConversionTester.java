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
package com.liferay.faces.test.showcase.outputlink;

import org.junit.Test;

import org.openqa.selenium.support.ui.Select;

import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.BrowserStateAsserter;
import com.liferay.faces.test.showcase.TesterBase;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class OutputLinkConversionTester extends TesterBase {

	@Test
	public void runOutputLinkConversionTest() throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, "outputLink", "conversion");

		// Test that selecting a different country changes the text in the link.
		Select select = new Select(browserDriver.findElementByXpath("(//select[contains(@id,':selectOneMenuId')])"));
		select.selectByVisibleText("United States");

		BrowserStateAsserter browserStateAsserter = getBrowserStateAsserter();
		String mapLink1Xpath = "//a[contains(., 'United States')]";
		browserStateAsserter.assertTextPresentInElement("Link to a map of United States", mapLink1Xpath);

		// Click the link and check that it opens a new window/tab with the correct domain name.
		testLink(browserDriver, browserStateAsserter, mapLink1Xpath, "google.com");
	}
}
