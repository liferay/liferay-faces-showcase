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
package com.liferay.faces.test.showcase.image;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.liferay.faces.test.selenium.Browser;
import com.liferay.faces.test.selenium.assertion.SeleniumAssert;
import com.liferay.faces.test.showcase.TesterBase;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class ImageTester extends TesterBase {

	protected void assertImageRendered(Browser browser, String imageXpath) {

		SeleniumAssert.assertElementVisible(browser, imageXpath);

		WebElement image = browser.findElementByXpath(imageXpath);
		String imageSrc = image.getAttribute("src");
		Assert.assertTrue("Image src " + imageSrc + " is not a valid JSF resource URL.",
			imageSrc.matches(".*javax.faces.resource\\p{Punct}[a-z-]+[.]png.*") &&
			imageSrc.matches(".*ln\\p{Punct}images.*"));

		Boolean imageRendered = (Boolean) browser.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				image);
		Assert.assertTrue("Image " + imageXpath + " (src=\"" + imageSrc + "\") is not rendered in the DOM.",
			imageRendered);
	}
}
