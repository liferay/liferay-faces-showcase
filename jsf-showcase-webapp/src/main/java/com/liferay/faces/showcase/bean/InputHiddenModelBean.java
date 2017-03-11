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

import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;


/**
 * @author  Vernon Singleton
 */
@ManagedBean
@RequestScoped
public class InputHiddenModelBean {

	private Date date = new GregorianCalendar().getTime();
	private Date testDate = new GregorianCalendar(33, 3, 5).getTime();
	private String text;

	public Date getDate() {
		return date;
	}

	public String getTestDate1() {

		// Example 1 requires a date formatted by the user's locale.
		DateTimeConverter dateTimeConverter = new DateTimeConverter();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		dateTimeConverter.setLocale(externalContext.getRequestLocale());

		return dateTimeConverter.getAsString(facesContext, facesContext.getViewRoot(), testDate);
	}

	public String getTestDate2() {

		// Example 1 requires a date formatted by a specific date pattern.
		DateTimeConverter dateTimeConverter = new DateTimeConverter();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		dateTimeConverter.setLocale(externalContext.getRequestLocale());
		dateTimeConverter.setPattern("MM/dd/yyyy");

		return dateTimeConverter.getAsString(facesContext, facesContext.getViewRoot(), testDate);
	}

	public String getText() {
		return text;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setText(String text) {
		this.text = text;
	}
}
