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
package com.liferay.faces.showcase.validator;

import java.util.Locale;

import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.liferay.faces.util.i18n.I18n;
import com.liferay.faces.util.i18n.I18nFactory;


/**
 * @author  Juan Gonzalez
 */
public class ValidatorHelper {

	public static String getMessage(FacesContext facesContext, String messageId) {

		ExternalContext externalContext = facesContext.getExternalContext();
		I18n i18n = I18nFactory.getI18nInstance(externalContext);
		UIViewRoot viewRoot = facesContext.getViewRoot();
		Locale locale = viewRoot.getLocale();

		return i18n.getMessage(facesContext, locale, messageId);
	}
}
