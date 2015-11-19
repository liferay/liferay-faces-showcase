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

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import com.liferay.faces.showcase.component.outputsourcecode.OutputSourceCode;


/**
 * @author  Bruno Basto
 * @author  Kyle Stiemann
 */
//J-
@FacesRenderer(componentFamily = OutputSourceCode.COMPONENT_FAMILY, rendererType = OutputSourceCode.RENDERER_TYPE)
@ResourceDependencies(
	{
		@ResourceDependency(library = "prettify", name = "prettify.css"),
		@ResourceDependency(library = "prettify", name = "prettify-ext.css"),
		@ResourceDependency(library = "prettify", name = "run_prettify.js")
	}
)
//J+
public class OutputSourceCodeRenderer extends OutputSourceCodeRendererBase {

	@Override
	public void encodeBegin(FacesContext facesContext, UIComponent uiComponent) throws IOException {

		OutputSourceCode outputSourceCode = (OutputSourceCode) uiComponent;
		String mode = outputSourceCode.getMode();
		ResponseWriter originalResponseWriter = facesContext.getResponseWriter();
		ResponseWriter responseWriter = new OutputSourceCodeResponseWriter(originalResponseWriter, mode);
		facesContext.setResponseWriter(responseWriter);
		super.encodeBegin(facesContext, uiComponent);
		facesContext.setResponseWriter(originalResponseWriter);
	}

	@Override
	public void encodeEnd(FacesContext facesContext, UIComponent uiComponent) throws IOException {

		OutputSourceCode outputSourceCode = (OutputSourceCode) uiComponent;
		String mode = outputSourceCode.getMode();
		ResponseWriter originalResponseWriter = facesContext.getResponseWriter();
		ResponseWriter responseWriter = new OutputSourceCodeResponseWriter(originalResponseWriter, mode);
		facesContext.setResponseWriter(responseWriter);
		super.encodeEnd(facesContext, uiComponent);
		facesContext.setResponseWriter(originalResponseWriter);
	}
}
