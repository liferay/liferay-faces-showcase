/**
 * Copyright (c) 2000-2015 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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

	public static Integer columnUnitSize(Integer width) {
		return (int) Math.round(12 * ((double) width / 100));
	}
}
