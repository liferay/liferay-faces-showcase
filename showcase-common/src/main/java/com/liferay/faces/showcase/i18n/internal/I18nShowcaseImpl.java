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
package com.liferay.faces.showcase.i18n.internal;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.liferay.faces.showcase.util.ShowcaseUtil;
import com.liferay.faces.util.i18n.I18n;
import com.liferay.faces.util.i18n.I18nUtil;
import com.liferay.faces.util.i18n.I18nWrapper;


/**
 * @author  Neil Griffin
 */
public class I18nShowcaseImpl extends I18nWrapper implements Serializable {

	// serialVersionUID
	private static final long serialVersionUID = 37063121784542610L;

	// Private Data Members
	private I18n wrappedI18n;

	public I18nShowcaseImpl(I18n i18n) {
		this.wrappedI18n = i18n;
	}

	@Override
	public FacesMessage getFacesMessage(FacesContext facesContext, Locale locale, FacesMessage.Severity severity,
		String messageId) {
		return I18nUtil.getFacesMessage(this, facesContext, locale, severity, messageId);
	}

	@Override
	public FacesMessage getFacesMessage(FacesContext facesContext, Locale locale, FacesMessage.Severity severity,
		String messageId, Object... arguments) {
		return I18nUtil.getFacesMessage(this, facesContext, locale, severity, messageId, arguments);
	}

	@Override
	public String getMessage(FacesContext facesContext, Locale locale, String messageId) {

		String message = super.getMessage(facesContext, locale, messageId);

		if (message != null) {
			message = ShowcaseUtil.encodeDescription(message);
		}

		return message;
	}

	@Override
	public I18n getWrapped() {
		return wrappedI18n;
	}
}
