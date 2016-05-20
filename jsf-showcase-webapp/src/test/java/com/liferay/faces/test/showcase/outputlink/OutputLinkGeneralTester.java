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

import com.liferay.faces.test.selenium.Browser;


/**
 * @author  Neil Griffin
 */
public class OutputLinkGeneralTester extends OutputLinkTester {

	@Test
	public void runOutputLinkGeneralTest() throws Exception {

		Browser browser = Browser.getInstance();
		browser.get(outputLinkURL + "/general");

		// Wait to begin the test until the example link is rendered.
		String link1Xpath = "(//div[@class='showcase-example-usage'])[1]/a";
		browser.waitForElementVisible(link1Xpath);

		// Click the link and check that it opens a new window/tab with the correct domain name.
		testLink(browser, link1Xpath, "liferay.com");

		// Click the link and check that it opens a new window/tab with the correct domain name.
		String exampleLink2Xpath = "(//div[@class='showcase-example-usage'])[2]/a";
		testLink(browser, exampleLink2Xpath, "liferay.com");

		// Click the link and check that it opens a new window/tab with the correct domain name.
		String exampleLink3Xpath = "(//div[@class='showcase-example-usage'])[3]/a";
		browser.centerElementInView(exampleLink3Xpath);
		testLink(browser, exampleLink3Xpath, "google.com");
	}
}
