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
package com.liferay.faces.test.showcase.panel;

import org.junit.Test;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;
import com.liferay.faces.test.showcase.TesterBase;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class PanelGroupGeneralTester extends TesterBase {

	@Test
	public void runPanelGroupGeneralTest() throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, "panelGroup", "general");

		// Test that the first example component renders a <span> and the second one a <div>.
		assertElementComponentTextVisible(browser, "div");
		assertElementComponentTextVisible(browser, "span");
	}

	private void assertElementComponentTextVisible(Browser browser, String component) {
		SeleniumAssert.assertElementTextVisible(browser,
			"(//div[@class='showcase-example-usage']/" + component + "[contains(@class,'-panel')])[1]",
			"This text is inside a <" + component + "> HTML tag");
	}
}
