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
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;


/**
 * @author  Juan Gonzalez
 */
@ManagedBean
@RequestScoped
public class EventBackingBean {

	private StringBuilder text = new StringBuilder();

	public String getText() {
		return text.toString();
	}

	public void preRenderComponent(ComponentSystemEvent event) throws AbortProcessingException {
		text.append("Component with id " + event.getComponent().getId() + " generated an event.<br/>");
	}
}
