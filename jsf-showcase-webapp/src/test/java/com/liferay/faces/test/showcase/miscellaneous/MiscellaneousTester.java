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
import java.util.Locale;
import java.util.NoSuchElementException;
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

	protected void assertElementContentEqualsFileContent(Browser browser, String elementXpath, String filePath)
		throws FileNotFoundException {

		String fileContent = getFileContentAsString(filePath);
		SeleniumAssert.assertElementVisible(browser, elementXpath);

		String pageElementText = browser.findElementByXpath(elementXpath).getText();

		// Replace groups of whitespace with single spaces before comparing since the file is rendered in the browser
		// with different whitespace.
		Assert.assertEquals(fileContent.replaceAll("\\s+", " "), pageElementText.replaceAll("\\s+", " "));
	}

	protected void runMiscellaneousGeneralTest(String componentPrefix, String componentName) throws Exception {

		Browser browser = Browser.getInstance();
		navigateToUseCase(browser, componentPrefix, componentName, "general");

		String fileNameTemplate = "src/main/webapp/WEB-INF/component/" + componentPrefix + "/" + componentName +
			"/general/{0}" + capitalize(componentName) + ".xhtml";

		// Test that the webapp*.xhtml file content is displayed correctly in the browser.
		assertElementContentEqualsFileContent(browser, "(//div[contains(@class,'tab-pane')]/pre)[1]",
			fileNameTemplate.replace("{0}", "webapp"));

		//J-
		// TODO move portlet testing (and portlet*.xhtml) into bridge-impl.
		//J+

		// Test that the portlet*.xhtml file content is displayed correctly in the browser.
		browser.click("//a[contains(text(),'portlet" + capitalize(componentName) + ".xhtml')]");

		String tabContent2Xpath = "(//div[contains(@class,'tab-pane')]/pre)[2]";
		browser.waitForElementVisible(tabContent2Xpath);
		assertElementContentEqualsFileContent(browser, tabContent2Xpath, fileNameTemplate.replace("{0}", "portlet"));
	}

	private String capitalize(String string) {

		String capitalizedString = string;

		if (string != null) {

			if (string.length() > 1) {
				capitalizedString = string.substring(0, 1).toUpperCase(Locale.ENGLISH) + string.substring(1);
			}
			else {
				capitalizedString = string.toUpperCase(Locale.ENGLISH);
			}
		}

		return capitalizedString;
	}

	private String getFileContentAsString(String filePath) throws FileNotFoundException {

		String fileContent = "";
		Scanner scanner = null;

		try {
			File file = new File(filePath);
			scanner = new Scanner(file, "UTF-8");

			fileContent = scanner.useDelimiter("\\A").next();
		}
		catch (NoSuchElementException e) {
			// Empty file.
		}
		finally {

			if (scanner != null) {
				scanner.close();
			}
		}

		return fileContent;
	}
}
