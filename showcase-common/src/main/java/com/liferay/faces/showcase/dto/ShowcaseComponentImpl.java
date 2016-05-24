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
package com.liferay.faces.showcase.dto;

import java.io.Serializable;
import java.util.List;


/**
 * @author  Neil Griffin
 */
public class ShowcaseComponentImpl implements Serializable, ShowcaseComponent {

	// serialVersionUID
	private static final long serialVersionUID = 1206732371727535048L;

	// Private Data Members
	private String camelCaseName;
	private int categoryIndex;
	private String fullName;
	private String key;
	private String lowerCaseName;
	private String prefix;
	private List<UseCase> useCases;

	public ShowcaseComponentImpl(int categoryIndex, String prefix, String camelCaseName, String lowerCaseName,
		List<UseCase> useCases) {
		this.categoryIndex = categoryIndex;
		this.prefix = prefix;
		this.camelCaseName = camelCaseName;
		this.lowerCaseName = lowerCaseName;
		this.useCases = useCases;

		if ("util".equals(prefix)) {
			this.fullName = camelCaseName;
		}
		else {
			this.fullName = prefix + ":" + camelCaseName;
		}

		this.key = prefix + "-" + lowerCaseName;
	}

	@Override
	public String getCamelCaseName() {
		return camelCaseName;
	}

	@Override
	public int getCategoryIndex() {
		return categoryIndex;
	}

	@Override
	public String getFullName() {
		return fullName;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getLowerCaseName() {
		return lowerCaseName;
	}

	@Override
	public String getPrefix() {
		return prefix;
	}

	@Override
	public List<UseCase> getUseCases() {
		return useCases;
	}

	@Override
	public String toString() {
		return fullName;
	}
}
