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
package com.liferay.faces.test.showcase.miscellaneous;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.Assert;

import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.BrowserStateAsserter;
import com.liferay.faces.test.showcase.TesterBase;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class MiscellaneousTester extends TesterBase {

	protected void assertElementContentEqualsFileContent(BrowserDriver browserDriver, String elementXpath,
		String filePath) throws FileNotFoundException {

		String fileContent = getFileContentAsString(filePath);
		BrowserStateAsserter browserStateAsserter = getBrowserStateAsserter();
		browserStateAsserter.assertElementDisplayed(elementXpath);

		String pageElementText = browserDriver.findElementByXpath(elementXpath).getText();

		// Replace groups of whitespace with single spaces before comparing since the file is rendered in the
		// browser with different whitespace.
		Assert.assertEquals(fileContent.replaceAll("\\s+", " "), pageElementText.replaceAll("\\s+", " "));
	}

	protected void runMiscellaneousGeneralTest(String componentPrefix, String componentName) throws Exception {

		BrowserDriver browserDriver = getBrowserDriver();
		navigateToUseCase(browserDriver, componentPrefix, componentName, "general");

		// Test that the webapp*.xhtml file content is displayed correctly in the browser.
		assertElementContentEqualsFileContent(browserDriver, "(//div[contains(@class,'tab-pane')]/pre)[1]",
			"/webapp" + capitalize(componentName) + ".xhtml");

		//J-
		// TODO move portlet testing (and portlet*.xhtml) into bridge-impl.
		//J+

		// Test that the portlet*.xhtml file content is displayed correctly in the browser.
		browserDriver.clickElement("//a[contains(text(),'portlet" + capitalize(componentName) + ".xhtml')]");

		String tabContent2Xpath = "(//div[contains(@class,'tab-pane')]/pre)[2]";
		assertElementContentEqualsFileContent(browserDriver, tabContent2Xpath,
			"/portlet" + capitalize(componentName) + ".xhtml");
	}

	private String getFileContentAsString(String resource) throws FileNotFoundException {

		String resourceContent = "";
		InputStream inputStream = null;
		Scanner scanner = null;

		try {
			inputStream = getClass().getResourceAsStream(resource);
			scanner = new Scanner(inputStream, "UTF-8");

			resourceContent = scanner.useDelimiter("\\A").next();
		}
		catch (NoSuchElementException e) {
			// Empty file.
		}
		finally {

			if (scanner != null) {
				scanner.close();
			}

			if (inputStream != null) {

				try {
					inputStream.close();
				}
				catch (IOException e) {
					// do nothing.
				}
			}
		}

		return resourceContent;
	}
}
