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
package com.liferay.faces.test.showcase.panel;

import org.junit.Test;

import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.WaitingAsserter;
import com.liferay.faces.test.showcase.TesterBase;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class PanelGridGeneralTester extends TesterBase {

	@Test
	public void runPanelGridGeneralTest() throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, "panelGrid", "general");

		// Test that each cell is rendered in the correct row and column.
		WaitingAsserter waitingAsserter = getWaitingAsserter();
		assertCellTextMatchesCellRowCol(waitingAsserter, 1, 1);
		assertCellTextMatchesCellRowCol(waitingAsserter, 1, 2);
		assertCellTextMatchesCellRowCol(waitingAsserter, 1, 3);
		assertCellTextMatchesCellRowCol(waitingAsserter, 2, 1);
		assertCellTextMatchesCellRowCol(waitingAsserter, 2, 2);
		assertCellTextMatchesCellRowCol(waitingAsserter, 2, 3);
		assertCellTextMatchesCellRowCol(waitingAsserter, 3, 1);
	}

	private void assertCellTextMatchesCellRowCol(WaitingAsserter waitingAsserter, int row, int col) {
		waitingAsserter.assertTextPresentInElement("Row " + row + " Col " + col,
			"//div[contains(@class,'showcase-example-usage')]/table/tbody/tr[" + row + "]/td[" + col + "]");
	}
}
