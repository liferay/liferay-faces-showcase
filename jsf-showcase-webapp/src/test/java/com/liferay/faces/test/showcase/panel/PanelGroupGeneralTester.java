/**
 * Copyright (c) 2000-2018 Liferay, Inc. All rights reserved.
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

import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.WaitingAsserter;
import com.liferay.faces.test.showcase.TesterBase;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class PanelGroupGeneralTester extends TesterBase {

	@Test
	public void runPanelGroupGeneralTest() throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, "panelGroup", "general");

		// Test that a div or a span is rendered.
		WaitingAsserter waitingAsserter = getWaitingAsserter();
		assertElementTextMatchesElementType(waitingAsserter, "div");
		assertElementTextMatchesElementType(waitingAsserter, "span");
	}

	private void assertElementTextMatchesElementType(WaitingAsserter waitingAsserter, String component) {
		waitingAsserter.assertTextPresentInElement("This text is inside a <" + component + "> HTML tag",
			"//div[@class='showcase-example-usage']/" + component + "[contains(@class,'showcase-example-panel')]");
	}
}
