/**
 * Copyright (c) 2000-2016 Liferay, Inc. All rights reserved.
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
		@ResourceDependency(library = "prettify", name = "prettify.min.js")
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
