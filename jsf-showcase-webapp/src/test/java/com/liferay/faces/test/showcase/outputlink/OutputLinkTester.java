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

import java.util.Set;

import org.junit.Assert;

import org.openqa.selenium.WebElement;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;
import com.liferay.faces.test.selenium.expectedconditions.PageLoaded;
import com.liferay.faces.test.showcase.output.OutputTester;


/**
 * @author  Neil Griffin
 */
public class OutputLinkTester extends OutputTester {

	protected static final String outputLinkURL = TEST_CONTEXT_URL + "/outputlink";

	/**
	 * Click the link and assert that it opens a new window/tab with the correct domain name.
	 */
	protected void testLink(Browser browser, String exampleLink1Xpath, String domainName) {
		SeleniumAssert.assertElementVisible(browser, exampleLink1Xpath);

		WebElement linkElement = browser.findElementByXpath(exampleLink1Xpath);
		linkElement.click();

		String originalWindowHandle = browser.getWindowHandle();
		Set<String> windowHandles = browser.getWindowHandles();

		for (String windowHandle : windowHandles) {

			if (!originalWindowHandle.equals(windowHandle)) {
				browser.switchTo().window(windowHandle);
			}
		}

		browser.waitUntil(new PageLoaded());

		String currentURL = browser.getCurrentUrl();
		Assert.assertTrue("The url does not contain " + domainName + " instead it is " + currentURL + ".",
			currentURL.contains(domainName));
		browser.close();
		browser.switchTo().window(originalWindowHandle);
	}
}
