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
		super.encodeChildren(facesContext, uiComponent);
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
