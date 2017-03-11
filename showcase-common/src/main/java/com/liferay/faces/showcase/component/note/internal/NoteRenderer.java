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
