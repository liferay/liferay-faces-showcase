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


/**
 * @author  Neil Griffin
 */
@RequestScoped
@ManagedBean
public class FormBackingBean {

	public void submit() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;

		if (facesContext.getPartialViewContext().isAjaxRequest()) {
			facesMessage = new FacesMessage("The form was submitted via Ajax and re-rendered with updates to the DOM.");
		}
		else {
			facesMessage = new FacesMessage(
					"The form was submitted as a full page postback and the entire page was re-rendered.");
		}

		facesContext.addMessage(null, facesMessage);
	}
}
