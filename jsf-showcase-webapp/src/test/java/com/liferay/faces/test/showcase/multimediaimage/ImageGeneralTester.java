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
package com.liferay.faces.test.showcase.multimediaimage;

import org.junit.Test;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;
import com.liferay.faces.test.showcase.TesterBase;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class ImageGeneralTester extends TesterBase {

	@Test
	public void runImageGeneralTest() throws Exception {

		Browser browser = Browser.getInstance();

		if (DEFAULT_COMPONENT_PREFIX.equals("h")) {
			navigateToUseCase(browser, "graphicImage", "general");
		}
		else {
			navigateToUseCase(browser, "image", "general");
		}

		// Test that the images render on the page successfully.
		SeleniumAssert.assertElementVisible(browser,
			"//div//label//a[contains(text(),'value')]/ancestor::div[@class='showcase-example']//img[contains(@src,'javax.faces.resource') and contains(@src,'ln=images')]");
		SeleniumAssert.assertElementVisible(browser,
			"//div//label//code[contains(text(),'#{resource}')]/ancestor::div[@class='showcase-example']//img[contains(@src,'javax.faces.resource') and contains(@src,'ln=images')]");
		SeleniumAssert.assertElementVisible(browser,
			"//div//label//a[contains(text(),'name')]/ancestor::div[@class='showcase-example']//img[contains(@src,'javax.faces.resource') and contains(@src,'ln=images')]");
		SeleniumAssert.assertElementVisible(browser,
			"//div//label//a[contains(text(),'usemap')]/ancestor::div[@class='showcase-example']//img[contains(@src,'javax.faces.resource') and contains(@src,'ln=images')]");

		// Click the image links on both areas of the example 4 image usemap and check that it opens a new window/tab
		// with the correct domain name.
		testLink(browser, "(//div[contains(@class,'showcase-example-usage')]//area[contains(@title,'JSR 362')])",
			"https://jcp.org/en/jsr/detail?id=362");
		testLink(browser, "(//div[contains(@class,'showcase-example-usage')]//area[contains(@title,'JSR 378')])",
			"https://www.jcp.org/en/jsr/detail?id=378");
	}
}
