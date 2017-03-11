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

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;


/**
 * @author  Juan Gonzalez
 */
public class PhaseListenerBean implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		InputTextModelBean inputTextModelBean = getInputTextModelBean(event.getFacesContext());
		inputTextModelBean.setText(inputTextModelBean.getText() + "<br/>PhaseListenerBean in afterPhase of " +
			event.getPhaseId());

	}

	@Override
	public void beforePhase(PhaseEvent event) {
		InputTextModelBean inputTextModelBean = getInputTextModelBean(event.getFacesContext());
		inputTextModelBean.setText(inputTextModelBean.getText() + "<br/>PhaseListenerBean in beforePhase of " +
			event.getPhaseId());

	}

	public InputTextModelBean getInputTextModelBean(FacesContext facesContext) {
		ELResolver elResolver = facesContext.getApplication().getELResolver();
		ELContext elContext = facesContext.getELContext();
		InputTextModelBean inputTextModelBean = (InputTextModelBean) elResolver.getValue(elContext, null,
				"inputTextModelBean");

		if (inputTextModelBean.getText() == null) {
			inputTextModelBean.setText("");
		}

		return inputTextModelBean;
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
}
