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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Test;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;
import com.liferay.faces.test.showcase.output.OutputTester;


/**
 * @author  Neil Griffin
 */
public class OutputTextConversionTester extends OutputTester {

	@Test
	public void runOutputTextConversionTest() throws Exception {

		Browser browser = Browser.getInstance();
		browser.get(TEST_CONTEXT_URL + "/outputtext/conversion");

		// Wait to begin the test until the example text is rendered.
		browser.waitForElementVisible(exampleText1Xpath);

		// Test that today's date is rendered on the page.
		Date today = GregorianCalendar.getInstance().getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMM dd, yyyy");
		TimeZone gmtTimeZone = TimeZone.getTimeZone("GMT");
		dateFormat.setTimeZone(gmtTimeZone);

		String todayString = dateFormat.format(today);
		String exampleConversionDate1Xpath = "(//div[@class='showcase-example-usage'][text()])";
		SeleniumAssert.assertElementTextVisible(browser, exampleConversionDate1Xpath, todayString);
	}
}
