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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.PhaseId;

import com.liferay.faces.showcase.dto.Customer;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;


/**
 * @author  Vernon Singleton
 * @author  Kyle Stiemann
 */
@ManagedBean
@RequestScoped
public class CommandBackingBean {

	private static final Logger logger = LoggerFactory.getLogger(CommandBackingBean.class);

	// Injections
	@ManagedProperty(value = "#{commandModelBean}")
	private CommandModelBean commandModelBean;

	public void actionListener(ActionEvent actionEvent) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		PhaseId phaseId = facesContext.getCurrentPhaseId();
		logger.debug("actionListener: phaseId=[{0}]", phaseId.toString());

		String phaseName = phaseId.toString();
		FacesMessage facesMessage = new FacesMessage("The actionListener method was called during the " + phaseName +
				" phase of the JSF lifecycle.");
		facesContext.addMessage(null, facesMessage);
	}

	public void ajaxListener(AjaxBehaviorEvent ajaxBehaviorEvent) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		PhaseId phaseId = facesContext.getCurrentPhaseId();
		logger.debug("ajaxListener: phaseId=[{0}]", phaseId.toString());

		String phaseName = phaseId.toString();
		FacesMessage facesMessage = new FacesMessage("The ajaxListener method was called during the " + phaseName +
				" phase of the JSF lifecycle.");
		facesContext.addMessage(null, facesMessage);
	}

	public void attributeActionListener(ActionEvent actionEvent) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		PhaseId phaseId = facesContext.getCurrentPhaseId();
		logger.debug("actionListener: phaseId=[{0}]", phaseId.toString());

		String value = (String) actionEvent.getComponent().getAttributes().get("attribute");

		String phaseName = phaseId.toString();
		FacesMessage facesMessage = new FacesMessage("The actionListener method was called during the " + phaseName +
				" phase of the JSF lifecycle.The attribute value is " + value);
		facesContext.addMessage(null, facesMessage);
	}

	public void attributesActionListener(ActionEvent actionEvent) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		PhaseId phaseId = facesContext.getCurrentPhaseId();
		logger.debug("actionListener: phaseId=[{0}]", phaseId.toString());

		Map<String, Object> attributes = actionEvent.getComponent().getAttributes();
		String value1 = (String) attributes.get("attribute1");
		String value2 = (String) attributes.get("attribute2");

		String phaseName = phaseId.toString();
		FacesMessage facesMessage = new FacesMessage("The actionListener method was called during the " + phaseName +
				" phase of the JSF lifecycle.The attributes value are " + value1 + " and " + value2);
		facesContext.addMessage(null, facesMessage);
	}

	public void feedbackListener(ActionEvent actionEvent) {

		String value = "";

		List<UIComponent> children = actionEvent.getComponent().getChildren();

		for (UIComponent uiComponent : children) {

			if (uiComponent instanceof UIOutput) {
				value = (String) ((UIOutput) uiComponent).getValue();
			}
		}

		FacesContext facesContext = FacesContext.getCurrentInstance();
		logger.debug("feedbackListener: You selected the '" + value + "' menu item.");

		FacesMessage facesMessage = new FacesMessage("You selected the '" + value + "' menu item.");
		facesContext.addMessage(null, facesMessage);
	}

	public Map<String, Object> getAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("attribute1", "value1");
		attributes.put("attribute2", "value2");

		return attributes;
	}

	public void parameterActionListener() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		PhaseId phaseId = facesContext.getCurrentPhaseId();
		logger.debug("actionListener: phaseId=[{0}]", phaseId.toString());

		Map<String, String> requestParameterMap = facesContext.getExternalContext().getRequestParameterMap();
		String value = requestParameterMap.get("parameter");

		String phaseName = phaseId.toString();
		FacesMessage facesMessage = new FacesMessage("The actionListener method was called during the " + phaseName +
				" phase of the JSF lifecycle.The parameter value is " + value);
		facesContext.addMessage(null, facesMessage);
	}

	public void selectionListener(ActionEvent actionEvent) {

		UICommand uiCommand = (UICommand) actionEvent.getComponent();
		Customer customer = (Customer) uiCommand.getValue();
		commandModelBean.setSelectedCustomer(customer);
	}

	public void setCommandModelBean(CommandModelBean commandModelBean) {
		this.commandModelBean = commandModelBean;
	}
}
