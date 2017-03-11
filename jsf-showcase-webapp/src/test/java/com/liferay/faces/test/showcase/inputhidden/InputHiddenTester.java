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
package com.liferay.faces.test.showcase.inputhidden;

import com.liferay.faces.test.showcase.input.InputTester;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class InputHiddenTester extends InputTester {

	// Common Xpath
	protected static final String modelValueEmpty1Xpath =
		"(//span[contains(@id,':modelValue')][not(normalize-space())])[1]";
	protected static final String copyValidValueButton1Xpath = "(//button[contains(text(),'Copy')])[1]";
	protected static final String clearButton1Xpath = "(//button[contains(text(),'Clear')])[1]";
	protected static final String hiddenValueEmpty1Xpath = "(//input[contains(@id,':hidden')][contains(@value,'')])[1]";
	protected static final String modelValueEmpty2Xpath =
		"(//span[contains(@id,':modelValue')][not(normalize-space())])[2]";
	protected static final String copyValidValueButton2Xpath = "(//button[contains(text(),'Copy')])[2]";
	protected static final String clearButton2Xpath = "(//button[contains(text(),'Clear')])[2]";
	protected static final String hiddenValueEmpty2Xpath = "(//input[contains(@id,':hidden')][contains(@value,'')])[2]";
}
