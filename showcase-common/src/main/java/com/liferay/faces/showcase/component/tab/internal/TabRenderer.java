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
package com.liferay.faces.showcase.component.tab.internal;

import java.io.IOException;

import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import com.liferay.faces.showcase.component.tab.Tab;
import com.liferay.faces.showcase.component.tabview.TabView;
import com.liferay.faces.util.component.Styleable;
import com.liferay.faces.util.render.RendererUtil;


/**
 * @author  Neil Griffin
 */
@FacesRenderer(componentFamily = Tab.COMPONENT_FAMILY, rendererType = Tab.RENDERER_TYPE)
@ResourceDependency(library = "bootstrap", name = "css/bootstrap.min.css")
public class TabRenderer extends TabRendererBase {

	@Override
	public void encodeBegin(FacesContext facesContext, UIComponent uiComponent) throws IOException {

		// Encode the starting <div> element that represents the component.
		ResponseWriter responseWriter = facesContext.getResponseWriter();
		responseWriter.startElement("div", uiComponent);
		responseWriter.writeAttribute("id", uiComponent.getClientId(facesContext), "id");

		boolean selected = false;

		UIComponent uiComponentParent = uiComponent.getParent();

		if (uiComponentParent instanceof TabView) {
			TabView tabView = (TabView) uiComponentParent;
			Integer selectedIndex = tabView.getSelectedIndex();
			int rowIndex = tabView.getRowIndex();
			selected = (((selectedIndex == null) && (rowIndex == 0)) ||
					((selectedIndex != null) && (rowIndex == selectedIndex)));
		}

		if (selected) {
			RendererUtil.encodeStyleable(responseWriter, (Styleable) uiComponent, "tab-pane active");
		}
		else {
			RendererUtil.encodeStyleable(responseWriter, (Styleable) uiComponent, "tab-pane");
		}
	}

	@Override
	public void encodeChildren(FacesContext facesContext, UIComponent uiComponent) throws IOException {

		UIComponent implicitPanelGroup = uiComponent.getFacet(TabHandler.IMPLICIT_FACET_NAME);

		// For more information, see TabHandler.
		if (implicitPanelGroup != null) {
			implicitPanelGroup.encodeAll(facesContext);
		}

		super.encodeChildren(facesContext, uiComponent);
	}

	@Override
	public void encodeEnd(FacesContext facesContext, UIComponent uiComponent) throws IOException {

		// Encode the closing </div> element.
		ResponseWriter responseWriter = facesContext.getResponseWriter();
		responseWriter.endElement("div");
	}

	@Override
	public boolean getRendersChildren() {
		return true;
	}
}
