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
package com.liferay.faces.test.showcase.selectoneradio;

import com.liferay.faces.test.showcase.select.SelectOneTester;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class SelectOneRadioTester extends SelectOneTester {

	// Common Xpath
	protected static final String selectOneRadio1Xpath =
		"(//table[contains(@id,':selectOneRadio')]|//div[contains(@id,':selectOneRadio')])[1]";
	protected static final String selectOneRadio2Xpath =
		"(//table[contains(@id,':selectOneRadio')]|//div[contains(@id,':selectOneRadio')])[2]";

	// Protected Constants
	protected static final String RADIO_CHILD_XPATH = "//input[contains(@id,':selectOneRadio')]";
}
