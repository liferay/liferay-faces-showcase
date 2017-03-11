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

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


/**
 * @author  Juan Gonzalez
 */
@FacesValidator("com.liferay.faces.showcase.validator.EmailValidator")
public class EmailValidator implements Validator {

	private static final String EMAIL_REGEX = ".+[@].+[.].+";

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		String message = "";

		try {
			Pattern pattern = Pattern.compile(EMAIL_REGEX);
			Matcher matcher = pattern.matcher((String) value);

			if (!matcher.matches()) {
				message = ValidatorHelper.getMessage(context, "email-is-not-valid");

				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, message, message);
				throw new ValidatorException(facesMessage);
			}
		}
		catch (PatternSyntaxException pse) {
			message = ValidatorHelper.getMessage(context, "unexpected-validation-error-ocurred");

			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, message, message);
			throw new ValidatorException(facesMessage);
		}
	}
}
