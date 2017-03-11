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

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;


/**
 * @author  Vernon Singleton
 */
@ManagedBean
@RequestScoped
public class SelectBooleanCheckboxBackingBean {

	private static final Logger logger = LoggerFactory.getLogger(SelectBooleanCheckboxBackingBean.class);

	@ManagedProperty(name = "selectBooleanCheckboxModelBean", value = "#{selectBooleanCheckboxModelBean}")
	private SelectBooleanCheckboxModelBean selectBooleanCheckboxModelBean;

	public void setSelectBooleanCheckboxModelBean(SelectBooleanCheckboxModelBean selectBooleanCheckboxModelBean) {
		this.selectBooleanCheckboxModelBean = selectBooleanCheckboxModelBean;
	}

	public void submit() {
		PhaseId phaseId = FacesContext.getCurrentInstance().getCurrentPhaseId();
		logger.info("submit: phaseId=[{0}] agree=[{1}]", phaseId.toString(), selectBooleanCheckboxModelBean.getAgree());
	}

	public void valueChangeListener(ValueChangeEvent valueChangeEvent) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		PhaseId phaseId = facesContext.getCurrentPhaseId();
		logger.debug("valueChangeListener: phaseId=[{0}]", phaseId.toString());

		String phaseName = phaseId.toString();
		FacesMessage facesMessage = new FacesMessage("The valueChangeListener method was called during the " +
				phaseName + " phase of the JSF lifecycle.");
		facesContext.addMessage(null, facesMessage);
	}
}
