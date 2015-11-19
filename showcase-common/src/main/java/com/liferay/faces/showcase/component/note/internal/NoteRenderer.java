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
package com.liferay.faces.showcase.component.note.internal;

import java.io.IOException;

import javax.faces.application.Application;
import javax.faces.application.Resource;
import javax.faces.application.ResourceDependency;
import javax.faces.application.ResourceHandler;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import com.liferay.faces.showcase.component.note.Note;


/**
 * @author  Neil Griffin
 */
//J-
@FacesRenderer(componentFamily = Note.COMPONENT_FAMILY, rendererType = Note.RENDERER_TYPE)
@ResourceDependency(library = "showcase", name = "showcase-components.css")
//J+
public class NoteRenderer extends NoteRendererBase {

	@Override
	public void encodeChildren(FacesContext facesContext, UIComponent uiComponent) throws IOException {

		Note note = (Note) uiComponent;
		ResponseWriter responseWriter = facesContext.getResponseWriter();
		responseWriter.startElement("table", null);
		responseWriter.startElement("tr", null);
		responseWriter.startElement("td", null);
		responseWriter.startElement("img", null);

		if (note.isSmallIcon()) {
			responseWriter.writeAttribute("class", "showcase-note-small", null);
		}

		Application application = facesContext.getApplication();
		ResourceHandler resourceHandler = application.getResourceHandler();
		Resource imageResource = resourceHandler.createResource("note.png", "showcase");
		String requestPath = imageResource.getRequestPath();
		responseWriter.writeAttribute("src", requestPath, null);
		responseWriter.endElement("img");
		responseWriter.endElement("td");
		responseWriter.startElement("td", null);
		super.encodeChildren(facesContext, uiComponent);
		responseWriter.endElement("td");
		responseWriter.endElement("tr");
		responseWriter.endElement("table");
	}
}
