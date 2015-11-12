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
package com.liferay.faces.showcase.util;

import com.liferay.faces.util.component.ComponentUtil;


/**
 * @author  Neil Griffin
 */
public class BootstrapUtil {

	// Private Constants
	private static final String SINGLE_BACKSLASH_COLON = "\\\\:";
	private static final String REGEX_COLON = "[:]";

	public static Integer columnUnitSize(Integer width) {
		return (int) Math.round(12 * ((double) width / 100));
	}

	public static String doubleEscapeClientId(String clientId) {
		return ComponentUtil.escapeClientId(clientId);
	}

	public static String singleEscapeClientId(String clientId) {
		String escapedClientId = clientId;

		if (escapedClientId != null) {

			// JSF clientId values contain colons, which must be preceeded by single backslashes in order to have them
			// work with Bootstrap+jQuery functions.
			escapedClientId = escapedClientId.replaceAll(REGEX_COLON, SINGLE_BACKSLASH_COLON);
		}

		return escapedClientId;
	}
}
