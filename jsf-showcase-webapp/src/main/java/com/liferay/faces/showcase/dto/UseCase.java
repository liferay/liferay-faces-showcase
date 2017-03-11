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
package com.liferay.faces.showcase.dto;

import java.io.Serializable;
import java.util.List;


/**
 * @author  Neil Griffin
 */
public class UseCase implements Serializable {

	// serialVersionUID
	private static final long serialVersionUID = 5675514126286108464L;

	// Private Data Members
	private String name;
	private List<CodeExample> codeExamples;

	public UseCase(String name, List<CodeExample> codeExamples) {
		this.name = name;
		this.codeExamples = codeExamples;
	}

	public List<CodeExample> getCodeExamples() {
		return codeExamples;
	}

	public String getName() {
		return name;
	}
}
