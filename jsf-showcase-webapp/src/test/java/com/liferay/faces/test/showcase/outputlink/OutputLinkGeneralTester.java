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

		// Wait to begin the test until a property is rendered.
		String exampleLinkXpath1 = "(//div[@class='showcase-example-usage'])[1]/a";
		browser.waitForElementVisible(exampleLinkXpath1);

		// Test that the first example text property renders on the page successfully.
		testLink(browser, exampleLinkXpath1, "liferay.com");

		// Test that the second example text property renders on the page successfully.
		String exampleLinkXpath2 = "(//div[@class='showcase-example-usage'])[2]/a";
		testLink(browser, exampleLinkXpath2, "liferay.com");

		// Test that the third example text property renders on the page successfully.
		String exampleLinkXpath3 = "(//div[@class='showcase-example-usage'])[3]/a";
		browser.centerElementInView(exampleLinkXpath3);
		testLink(browser, exampleLinkXpath3, "google.com");
	}
}
