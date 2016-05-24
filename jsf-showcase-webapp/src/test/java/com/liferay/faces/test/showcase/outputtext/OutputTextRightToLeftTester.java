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
package com.liferay.faces.test.showcase.outputtext;

import org.junit.Test;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;
import com.liferay.faces.test.showcase.output.OutputTester;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class OutputTextRightToLeftTester extends OutputTester {

	@Test
	public void runOutputTextRightToLeftTest() throws Exception {

		Browser browser = Browser.getInstance();
		browser.get(TEST_CONTEXT_URL + "/outputtext/right-to-left");

		// Wait to begin the test until the example text is rendered.
		String RTLModelValue1Xpath = "(//span[@dir='RTL'])";
		browser.waitForElementVisible(RTLModelValue1Xpath);

		// Test that the right-to-left text is rendered on the page.
		SeleniumAssert.assertElementTextVisible(browser, RTLModelValue1Xpath,
			"בְּרֵאשִׁית בָּרָא אֱלֹהִים אֵת הַשָּׁמַיִם וְאֵת הָאָֽרֶץ");
	}
}
