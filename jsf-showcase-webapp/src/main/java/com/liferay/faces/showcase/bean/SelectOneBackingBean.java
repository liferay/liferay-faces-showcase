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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;


/**
 * @author  Vernon Singleton
 */
@ManagedBean
@RequestScoped
public class SelectOneBackingBean {

	private static final Logger logger = LoggerFactory.getLogger(SelectOneBackingBean.class);

	@ManagedProperty(name = "selectOneModelBean", value = "#{selectOneModelBean}")
	private SelectOneModelBean selectOneModelBean;

	public SelectItem[] getSelectItems() {
		SelectItem[] selectItems = new SelectItem[3];

		for (int i = 0; i < 3; i++) {
			SelectItem item = new SelectItem();
			item.setLabel("Item " + (i + 1));
			item.setValue(i + 1);
			selectItems[i] = item;
		}

		return selectItems;
	}

	public void setSelectOneModelBean(SelectOneModelBean selectOneModelBean) {
		this.selectOneModelBean = selectOneModelBean;
	}

	public void submit() {
		PhaseId phaseId = FacesContext.getCurrentInstance().getCurrentPhaseId();
		logger.info("submit: phaseId=[{0}] favoriteId=[{1}]", phaseId.toString(), selectOneModelBean.getFavoriteId());
	}

	public void submitAnswer() {

		Date selectedDate = selectOneModelBean.getDate();

		TimeZone gmtTimeZone = TimeZone.getTimeZone("GMT");
		Calendar calendar = new GregorianCalendar(gmtTimeZone);

		if (selectedDate != null) {
			calendar.setTime(selectedDate);
		}

		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage facesMessage;

		if ((selectedDate != null) && (calendar.get(Calendar.MONTH) == 6) && (calendar.get(Calendar.DATE) == 4) &&
				(calendar.get(Calendar.YEAR) == 1776)) {
			facesMessage = new FacesMessage("Correct!");
			facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
		}
		else {
			facesMessage = new FacesMessage("Incorrect!");
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		}

		facesContext.addMessage(null, facesMessage);
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
