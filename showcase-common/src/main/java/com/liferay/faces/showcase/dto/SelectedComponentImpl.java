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
public class SelectedComponentImpl extends ShowcaseComponentWrapper implements SelectedComponent, Serializable {

	// serialVersionUID
	private static final long serialVersionUID = 3286285311354181810L;

	// Private Data Members
	private boolean rendered;
	private boolean required;
	private String useCaseKey;
	private String useCaseName;
	private List<CodeExample> useCaseCodeExamples;
	private ShowcaseComponent wrappedShowcaseComponent;

	public SelectedComponentImpl(ShowcaseComponent showcaseComponent, String useCaseName) {

		this.wrappedShowcaseComponent = showcaseComponent;
		this.rendered = true;
		this.required = false;
		this.useCaseName = useCaseName;

		List<UseCase> useCases = showcaseComponent.getUseCases();

		for (UseCase useCase : useCases) {

			if (useCase.getName().equals(useCaseName)) {
				this.useCaseCodeExamples = useCase.getCodeExamples();

				break;
			}
		}

		this.useCaseKey = showcaseComponent.getKey() + "-" + useCaseName;
	}

	@Override
	public List<CodeExample> getUseCaseCodeExamples() {
		return useCaseCodeExamples;
	}

	@Override
	public String getUseCaseKey() {
		return useCaseKey;
	}

	@Override
	public String getUseCaseName() {
		return useCaseName;
	}

	@Override
	public ShowcaseComponent getWrapped() {
		return wrappedShowcaseComponent;
	}

	@Override
	public boolean isRendered() {
		return rendered;
	}

	@Override
	public boolean isRequired() {
		return required;
	}

	@Override
	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}

	@Override
	public void setRequired(boolean required) {
		this.required = required;
	}
}
