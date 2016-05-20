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
package com.liferay.faces.test.showcase.outputscript;

import com.liferay.faces.test.showcase.output.OutputTester;


/**
 * @author  Neil Griffin
 */
public class OutputScriptTester extends OutputTester {

	protected static final String outputScriptURL = TEST_CONTEXT_URL + "/outputscript";
	protected static final String exampleScriptText1Xpath = "(//div[@class='showcase-example-usage'])[1]/span[text()]";
}
