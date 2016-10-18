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
package com.liferay.faces.test.showcase.inputfile;

import com.liferay.faces.test.showcase.input.InputTester;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class InputFileTester extends InputTester {

	protected static final String LIFERAY_JSF_JERSEY_PNG_FILE_PATH = System.getProperty("java.io.tmpdir") +
			"liferay-jsf-jersey.png";

	protected static final String fileUploadChooserXpath = "//input[@type='file']";
	protected static final String uploadedFileXpath = "//*[contains(text(),'jersey')]";
}
