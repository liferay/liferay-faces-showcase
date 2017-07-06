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

import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.WaitingAsserter;
import com.liferay.faces.test.showcase.TesterBase;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class OutputLinkGeneralTester extends TesterBase {

	@Test
	public void runOutputLinkGeneralTest() throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, "outputLink", "general");

		// Click the link and check that it opens a new window/tab with the correct domain name.
		WaitingAsserter waitingAsserter = getWaitingAsserter();
		testLink(browserDriver, waitingAsserter, "(//div[@class='showcase-example-usage'])[1]/a", "liferay.[a-z]+");

		// Click the link and check that it opens a new window/tab with the correct domain name.
		String exampleLink2Xpath = "(//div[@class='showcase-example-usage'])[2]/a";
		testLink(browserDriver, waitingAsserter, exampleLink2Xpath, "liferay.[a-z]+");

		// Click the link and check that it opens a new window/tab with the correct domain name.
		String exampleLink3Xpath = "(//div[@class='showcase-example-usage'])[3]/a";
		testLink(browserDriver, waitingAsserter, exampleLink3Xpath, "google.[a-z]+");
	}
}
