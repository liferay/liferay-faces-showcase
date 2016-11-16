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
public class PanelGridGeneralTester extends TesterBase {

	@Test
	public void runPanelGridGeneralTest() throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, "panelGrid", "general");

		// Test the visibility of text elements in every row and column of the table.
		assertRowColTextCorrect(browser, 1, 1);
		assertRowColTextCorrect(browser, 1, 2);
		assertRowColTextCorrect(browser, 1, 3);
		assertRowColTextCorrect(browser, 2, 1);
		assertRowColTextCorrect(browser, 2, 2);
		assertRowColTextCorrect(browser, 2, 3);
		assertRowColTextCorrect(browser, 3, 1);
	}

	private void assertRowColTextCorrect(Browser browser, int row, int col) {
		SeleniumAssert.assertElementTextVisible(browser,
			"//div[contains(@class,'showcase-example-usage')]/table/tbody/tr[" + row + "]/td[" + col + "]",
			"Row " + row + " Col " + col);
	}
}
