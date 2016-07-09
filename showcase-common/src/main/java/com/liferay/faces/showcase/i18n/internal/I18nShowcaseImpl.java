/**
 * Copyright (c) 2000-2016 Liferay, Inc. All rights reserved.
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

import java.util.Locale;

import com.liferay.faces.showcase.util.ShowcaseUtil;
import com.liferay.faces.util.i18n.I18n;
import com.liferay.faces.util.i18n.I18nWrapper;


/**
 * @author  Neil Griffin
 */
public class I18nShowcaseImpl extends I18nWrapper {

	// Private Data Members
	private I18n wrappedI18n;

	public I18nShowcaseImpl(I18n i18n) {
		this.wrappedI18n = i18n;
	}

	// TODO: Uncomment! @Override
	public String getMessage(Locale locale, String messageId) {

		// TODO: Uncomment!
		// String message = super.getMessage(locale, messageId);
		String message = null;

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
