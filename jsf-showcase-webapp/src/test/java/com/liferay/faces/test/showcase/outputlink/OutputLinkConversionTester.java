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

		// Wait to begin the test until a property is rendered.
		String selectXpath = "(//select[contains(@id,':selectOneMenuId')])";
		browser.waitForElementVisible(selectXpath);

		// Test that selecting a different link option renders on the page successfully.
		Select select = new Select(browser.findElementByXpath(selectXpath));
		select.selectByVisibleText("United States");

		String mapLinkXpath = "(//a[contains(text(),'United States')])";
		browser.centerElementInView(mapLinkXpath);
		browser.waitForElementVisible(mapLinkXpath);
		SeleniumAssert.assertElementTextVisible(browser, mapLinkXpath, "Link to a map of United States");
		testLink(browser, mapLinkXpath, "google.com");
	}
}
