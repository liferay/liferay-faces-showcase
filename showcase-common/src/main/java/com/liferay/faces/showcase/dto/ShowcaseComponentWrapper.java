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

import java.util.List;

import javax.faces.FacesWrapper;


/**
 * @author  Neil Griffin
 */
public abstract class ShowcaseComponentWrapper implements ShowcaseComponent, FacesWrapper<ShowcaseComponent> {

	@Override
	public abstract ShowcaseComponent getWrapped();

	@Override
	public String getCamelCaseName() {
		return getWrapped().getCamelCaseName();
	}

	@Override
	public int getCategoryIndex() {
		return getWrapped().getCategoryIndex();
	}

	@Override
	public String getFullName() {
		return getWrapped().getFullName();
	}

	@Override
	public String getKey() {
		return getWrapped().getKey();
	}

	@Override
	public String getLowerCaseName() {
		return getWrapped().getLowerCaseName();
	}

	@Override
	public String getPrefix() {
		return getWrapped().getPrefix();
	}

	@Override
	public List<UseCase> getUseCases() {
		return getWrapped().getUseCases();
	}
}
