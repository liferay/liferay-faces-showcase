/**
 * Copyright (c) 2000-2022 Liferay, Inc. All rights reserved.
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
package com.liferay.faces.test.showcase.image;

import org.junit.Test;

import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.WaitingAsserter;
import com.liferay.faces.test.showcase.TesterBase;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class ImageGeneralTester extends TesterBase {

	@Test
	public void runImageGeneralTest() throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		String componentName = "image";

		if (DEFAULT_COMPONENT_PREFIX.equals("h")) {
			componentName = "graphicImage";
		}

		navigateToUseCase(browserDriver, componentName, "general");

		// Test that the images render on the page successfully.
		WaitingAsserter waitingAsserter = getWaitingAsserter();
		assertImageRendered(browserDriver, waitingAsserter, getExampleImageXpath("Context-relative"));
		assertImageRendered(browserDriver, waitingAsserter, getExampleImageXpath("#{resource}"));
		assertImageRendered(browserDriver, waitingAsserter, getExampleImageXpath("name"));
		assertImageRendered(browserDriver, waitingAsserter, getExampleImageXpath("usemap"));

		// Click the image links on both areas of the example 4 image usemap and check that it opens a new window/tab
		// with the correct domain name.
		testLink(browserDriver, waitingAsserter,
			"(//div[contains(@class,'showcase-example-usage')]//area[contains(@title,'JSR 362')])", "jcp.org");
		testLink(browserDriver, waitingAsserter,
			"(//div[contains(@class,'showcase-example-usage')]//area[contains(@title,'JSR 378')])", "jcp.org");
	}
}
