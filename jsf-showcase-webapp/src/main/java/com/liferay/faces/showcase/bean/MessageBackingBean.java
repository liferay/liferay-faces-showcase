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
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;


/**
 * @author  Neil Griffin
 */
@RequestScoped
@ManagedBean
public class MessageBackingBean {

	public void submit() {

		FacesMessage globalFacesMessage = new FacesMessage("Your request processed successfully.");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, globalFacesMessage);
	}

	public void valueChangeListener(ValueChangeEvent valueChangeEvent) {

		Object newValue = valueChangeEvent.getNewValue();
		int totalChars = 0;

		if (newValue != null) {
			totalChars = newValue.toString().length();
		}

		FacesMessage globalFacesMessage = new FacesMessage("You typed " + totalChars + " characters.");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String componentClientId = valueChangeEvent.getComponent().getClientId();
		facesContext.addMessage(componentClientId, globalFacesMessage);
	}
}
