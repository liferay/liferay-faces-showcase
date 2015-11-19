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
package com.liferay.faces.showcase.component.icon.internal;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import com.liferay.faces.showcase.component.icon.Icon;
import com.liferay.faces.util.render.RendererUtil;

import java.io.IOException;


/**
 * @author	Kyle Stiemann
 */
//J-
@FacesRenderer(componentFamily = Icon.COMPONENT_FAMILY, rendererType = Icon.RENDERER_TYPE)
@ResourceDependencies(
	{
		@ResourceDependency(library = "bootstrap", name = "css/bootstrap.min.css"),
		@ResourceDependency(library = "bootstrap", name = "css/bootstrap-responsive.min.css"),
	}
)
//J+
public class IconRenderer extends IconRendererBase {

	@Override
	public void encodeBegin(FacesContext facesContext, UIComponent uiComponent) throws IOException {

		ResponseWriter responseWriter = facesContext.getResponseWriter();
		responseWriter.startElement("i", uiComponent);

		String clientId = uiComponent.getClientId(facesContext);
		responseWriter.writeAttribute("id", clientId, "id");

		Icon icon = (Icon) uiComponent;

		String name = icon.getName();

		if (name != null) {

			name = "icon-".concat(name);
		}

		RendererUtil.encodeStyleable(responseWriter, icon, name);
	}

	@Override
	public void encodeEnd(FacesContext facesContext, UIComponent uiComponent) throws IOException {

		ResponseWriter responseWriter = facesContext.getResponseWriter();
		responseWriter.endElement("i");
	}
}
