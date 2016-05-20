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
package com.liferay.faces.test.showcase.outputlink;

import org.junit.Test;

import org.openqa.selenium.support.ui.Select;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;


/**
 * @author  Neil Griffin
 */
public class OutputLinkConversionTester extends OutputLinkTester {

	@Test
	public void runOutputLinkConversionTest() throws Exception {

		Browser browser = Browser.getInstance();
		browser.get(outputLinkURL + "/conversion");

		// Wait to begin the test until the select is rendered.
		String select1Xpath = "(//select[contains(@id,':selectOneMenuId')])";
		browser.waitForElementVisible(select1Xpath);

		// Test that selecting a different country changes the text in the link.
		Select select = new Select(browser.findElementByXpath(select1Xpath));
		select.selectByVisibleText("United States");

		String mapLink1Xpath = "(//a[contains(text(),'United States')])";
		browser.waitForElementVisible(mapLink1Xpath);
		browser.centerElementInView(mapLink1Xpath);
		SeleniumAssert.assertElementTextVisible(browser, mapLink1Xpath, "Link to a map of United States");

		// Click the link and check that it opens a new window/tab with the correct domain name.
		testLink(browser, mapLink1Xpath, "google.com");
	}
}
