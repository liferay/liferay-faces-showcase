/**
 * Copyright (c) 2000-2015 Liferay, Inc. All rights reserved.
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
package com.liferay.faces.showcase.context;

import java.util.Locale;

import com.liferay.faces.showcase.util.ShowcaseUtil;
import com.liferay.faces.util.context.MessageContext;
import com.liferay.faces.util.context.MessageContextWrapper;


/**
 * @author  Neil Griffin
 */
public class ShowcaseMessageContext extends MessageContextWrapper {

	// Private Data Members
	private MessageContext wrappedMessageContext;

	public ShowcaseMessageContext(MessageContext messageContext) {
		this.wrappedMessageContext = messageContext;
	}

	@Override
	public String getMessage(Locale locale, String messageId) {

		String message = super.getMessage(locale, messageId);

		if (message != null) {
			message = ShowcaseUtil.encodeDescription(message);
		}

		return message;
	}

	@Override
	public MessageContext getWrapped() {
		return wrappedMessageContext;
	}
}
