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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;


/**
 * @author  Kyle Stiemann
 */
@ManagedBean
@RequestScoped
public class FieldBackingBean {

	private static final Logger logger = LoggerFactory.getLogger(FieldBackingBean.class);

	@ManagedProperty(value = "#{fieldModelBean}")
	private FieldModelBean fieldModelBean;

	public void errorValidator(FacesContext facesContext, UIComponent uiComponent, Object value)
		throws ValidatorException {

		FacesMessage facesMessage = new FacesMessage();
		facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		facesMessage.setDetail("This is an error message.");
		facesContext.addMessage(uiComponent.getClientId(), facesMessage);
	}

	public void infoValidator(FacesContext facesContext, UIComponent uiComponent, Object value)
		throws ValidatorException {

		FacesMessage facesMessage = new FacesMessage();
		facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
		facesMessage.setDetail("This is an info message.");
		facesContext.addMessage(uiComponent.getClientId(), facesMessage);
	}

	public void setFieldModelBean(FieldModelBean fieldModelBean) {
		this.fieldModelBean = fieldModelBean;
	}

	public void submit() {

		FacesMessage globalFacesMessage = new FacesMessage("Your request processed successfully.");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, globalFacesMessage);

		logger.info("submit: firstName = " + fieldModelBean.getFirstName());
	}

	public void warningValidator(FacesContext facesContext, UIComponent uiComponent, Object value)
		throws ValidatorException {

		FacesMessage facesMessage = new FacesMessage();
		facesMessage.setSeverity(FacesMessage.SEVERITY_WARN);
		facesMessage.setDetail("This is a warning message.");
		facesContext.addMessage(uiComponent.getClientId(), facesMessage);
	}
}
