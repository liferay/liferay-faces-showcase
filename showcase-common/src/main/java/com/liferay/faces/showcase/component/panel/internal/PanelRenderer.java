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
package com.liferay.faces.showcase.component.panel.internal;

import java.io.IOException;

import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import com.liferay.faces.showcase.component.panel.Panel;


/**
 * @author  Bruno Basto
 * @author  Kyle Stiemann
 */

//J-
@FacesRenderer(componentFamily = Panel.COMPONENT_FAMILY, rendererType = Panel.RENDERER_TYPE)
@ResourceDependency(library = "showcase", name = "showcase-components.css")
//J+
public class PanelRenderer extends PanelRendererBase {

	@Override
	public void encodeBegin(FacesContext facesContext, UIComponent uiComponent) throws IOException {

		// Delegate to the JSF runtime renderer in order to start encoding the outermost <div> of the panel.
		super.encodeBegin(facesContext, uiComponent);

		// If necessary, encode the <div>...</div> for the panel header.
		Panel panel = (Panel) uiComponent;
		String headerText = panel.getHeaderText();
		UIComponent headerFacet = uiComponent.getFacet("header");
		ResponseWriter responseWriter = facesContext.getResponseWriter();

		if ((headerFacet != null) || (headerText != null)) {
			responseWriter.startElement("div", uiComponent);
			responseWriter.writeAttribute("class", "showcase-panel-heading", null);

			if (headerFacet != null) {
				headerFacet.encodeAll(facesContext);
			}
			else {
				responseWriter.startElement("span", null);
				responseWriter.writeAttribute("class", "showcase-panel-title", null);
				responseWriter.writeText(headerText, null);
				responseWriter.endElement("span");
			}

			responseWriter.endElement("div");
		}

		// Encode a starting <div> for the panel body.
		responseWriter.startElement("div", uiComponent);
		responseWriter.writeAttribute("class", "showcase-panel-body", null);
	}

	@Override
	public void encodeEnd(FacesContext facesContext, UIComponent uiComponent) throws IOException {

		// Encode the ending </div> for the panel body.
		ResponseWriter responseWriter = facesContext.getResponseWriter();
		responseWriter.endElement("div");

		// If necessary, encode the <div>...</div> for the panel header.
		Panel panel = (Panel) uiComponent;
		String footerText = panel.getFooterText();
		UIComponent footerFacet = uiComponent.getFacet("footer");

		if ((footerFacet != null) || (footerText != null)) {
			responseWriter.startElement("div", uiComponent);
			responseWriter.writeAttribute("class", "showcase-panel-footer", null);

			if (footerFacet != null) {
				footerFacet.encodeAll(facesContext);
			}
			else {
				responseWriter.startElement("span", null);
				responseWriter.writeText(footerText, null);
				responseWriter.endElement("span");
			}

			responseWriter.endElement("div");
		}

		// Delegate to the JSF runtime renderer in order to finish encoding the outermost </div> of the panel.
		super.encodeEnd(facesContext, uiComponent);
	}
}
