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
package com.liferay.faces.test.showcase.input;

import com.liferay.faces.test.showcase.IntegrationTesterBase;


/**
 * @author  Kyle Stiemann
 */
public class InputTester extends IntegrationTesterBase {

	protected static final String immediateMessageRight =
		"//ul[@class='feedback']/li[contains(text(),'PROCESS_VALIDATIONS')]";
	protected static final String immediateMessage =
		"//ul[@class='feedback']/li[contains(text(),'APPLY_REQUEST_VALUES')]";
	protected static final String requiredCheckboxXpath = "//input[contains(@id,':requiredCheckbox')]";
	protected static final String modelValueXpath = "(//span[contains(@id,':modelValue')])[1]";
	protected static final String submitButtonXpath = "(//input[@value='Submit'])[1]";
	protected static final String errorXpath = "(//div[contains(@class,'field form-group has-error')])[1]";
	protected static final String submitButtonXpathRight = "(//input[@value='Submit'])[2]";
	protected static final String modelValueXpathRight = "(//span[contains(@id,':modelValue')])[2]";
	protected static final String errorXpathRight = "(//div[contains(@class,'field form-group has-error')])[2]";
	protected static final String successXpath = "(//div[contains(@class,'field form-group has-success')])[1]";
}
