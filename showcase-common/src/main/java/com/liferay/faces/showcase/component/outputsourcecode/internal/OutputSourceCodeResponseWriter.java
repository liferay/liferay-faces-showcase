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
package com.liferay.faces.showcase.component.outputsourcecode.internal;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.ResponseWriter;
import javax.faces.context.ResponseWriterWrapper;


/**
 * @author  Neil Griffin
 */
public class OutputSourceCodeResponseWriter extends ResponseWriterWrapper {

	// Private Data Members
	private String mode;
	private ResponseWriter wrappedResponseWriter;

	public OutputSourceCodeResponseWriter(ResponseWriter responseWriter, String mode) {
		this.wrappedResponseWriter = responseWriter;
		this.mode = mode;
	}

	@Override
	public void endElement(String name) throws IOException {

		if ("span".equals(name)) {
			name = "pre";
		}

		super.endElement(name);
	}

	@Override
	public void startElement(String name, UIComponent component) throws IOException {

		if ("span".equals(name)) {
			name = "pre";
		}

		super.startElement(name, component);
	}

	@Override
	public void writeAttribute(String name, Object value, String property) throws IOException {

		if ("class".equals("name")) {

			if (mode != null) {

				if (value == null) {
					value = "lang-".concat(mode);
				}
				else {
					value = value.toString().concat("  lang-".concat(mode));
				}
			}
		}

		super.writeAttribute(name, value, property);
	}

	@Override
	public ResponseWriter getWrapped() {
		return wrappedResponseWriter;
	}
}
