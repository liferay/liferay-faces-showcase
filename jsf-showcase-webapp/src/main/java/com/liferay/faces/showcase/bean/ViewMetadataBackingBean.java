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
package com.liferay.faces.showcase.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import com.liferay.faces.showcase.dto.Country;


/**
 * @author  Juan Gonzalez
 */
@ManagedBean
@RequestScoped
public class ViewMetadataBackingBean {

	private String viewActionText;
	private Country viewCountry;

	public String getViewActionText() {
		return viewActionText;
	}

	public Country getViewCountry() {
		return viewCountry;
	}

	public void setViewActionText(String viewActionText) {
		this.viewActionText = viewActionText;
	}

	public void setViewCountry(Country viewCountry) {
		this.viewCountry = viewCountry;
	}

	public String viewAction() {
		PhaseId phaseId = FacesContext.getCurrentInstance().getCurrentPhaseId();

		String phaseName = phaseId.toString();

		this.viewActionText = "View action was executed in phase " + phaseName;

		return null;
	}
}
