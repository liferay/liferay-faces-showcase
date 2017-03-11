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
package com.liferay.faces.showcase.component.results.internal;

import java.io.IOException;

import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import com.liferay.faces.showcase.component.results.Results;


/**
 * @author  Bruno Basto
 * @author  Kyle Stiemann
 */

//J-
@FacesRenderer(componentFamily = Results.COMPONENT_FAMILY, rendererType = Results.RENDERER_TYPE)
@ResourceDependency(library = "showcase", name = "showcase-components.css")
//J+
public class ResultsRenderer extends ResultsRendererBase {

	@Override
	public void encodeChildren(FacesContext facesContext, UIComponent uiComponent) throws IOException {

		Results results = (Results) uiComponent;
		String label = results.getLabel();
		UIComponent labelFacet = results.getFacet("label");
		boolean hasLabel = ((label != null) && !label.equals("")) || (labelFacet != null);

		if (hasLabel) {
			encodeLabel(facesContext, results, labelFacet, label);
		}

		ResponseWriter responseWriter = facesContext.getResponseWriter();
		responseWriter.startElement("div", null);
		responseWriter.writeAttribute("class", "results-content", null);
		responseWriter.startElement("pre", null);
		super.encodeChildren(facesContext, uiComponent);
		responseWriter.endElement("pre");
		responseWriter.endElement("div");
	}

	private void encodeLabel(FacesContext facesContext, Results results, UIComponent labelFacet, String label)
		throws IOException {

		ResponseWriter responseWriter = facesContext.getResponseWriter();
		responseWriter.startElement(LABEL, results);

		if (labelFacet != null) {
			labelFacet.encodeAll(facesContext);
		}

		if ((label != null) && !label.equals("")) {
			responseWriter.writeText(label, LABEL);
		}

		responseWriter.endElement(LABEL);
	}
}
