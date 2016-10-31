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
package com.liferay.faces.test.showcase.miscellaneous;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Assert;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;
import com.liferay.faces.test.showcase.TesterBase;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class MiscellaneousTester extends TesterBase {

	protected static final String tab2Xpath = "(//ul[contains(@class,'nav-tabs')]/li)[2]";
	protected static final String tabContent2Xpath = "(//div[contains(@class,'tab-pane')]/pre)[2]";
	protected static final String pageContentXpath =
		"(//div[contains(@class,'tab-pane')][contains(@class,'active')]/pre)";

	protected void assertElementContentEqualsFileContent(Browser browser, String elementXpath, String filePath)
		throws FileNotFoundException {

		String fileContent = getFileContentAsString(filePath);
		SeleniumAssert.assertElementVisible(browser, elementXpath);

		String pageElementText = browser.findElementByXpath(elementXpath).getText();
		Assert.assertEquals(stripWhiteSpace(fileContent), stripWhiteSpace(pageElementText));
	}

	protected String getFileContentAsString(String filePath) throws FileNotFoundException {

		Scanner scanner = null;

		try {
			File file = new File(filePath);
			scanner = new Scanner(file, "UTF-8");

			String fileContent = scanner.useDelimiter("\\A").next();

			return fileContent;
		}
		finally {

			if (scanner != null) {
				scanner.close();
			}
		}
	}

	protected String stripWhiteSpace(String text) {
		return text.replaceAll(" +", "").replace("\t", "").replace("\n", "");
	}
}
