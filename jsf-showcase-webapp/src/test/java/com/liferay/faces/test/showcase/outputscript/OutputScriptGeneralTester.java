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
package com.liferay.faces.test.showcase.outputscript;

import org.junit.Test;

import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.WaitingAsserter;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class OutputScriptGeneralTester extends OutputScriptTester {

	@Test
	public void runOutputScriptGeneralTest() throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, "outputScript", "general");

		// Test that the script has successfully added the text to the page.
		WaitingAsserter waitingAsserter = getWaitingAsserter();
		waitingAsserter.assertTextPresentInElement("rendered inline", exampleScriptText1Xpath);

		// Test that the script has successfully added the text to the page.
		String exampleScriptText2Xpath = "(//div[@class='showcase-example-usage'])[2]/span[text()]";
		waitingAsserter.assertTextPresentInElement("rendered immediately before the closing", exampleScriptText2Xpath);
	}
}
