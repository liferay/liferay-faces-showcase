/**
 * Copyright (c) 2000-2022 Liferay, Inc. All rights reserved.
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
package com.liferay.faces.showcase.component.tabview.internal;

import java.io.IOException;
import java.util.List;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import com.liferay.faces.showcase.component.tab.Tab;
import com.liferay.faces.showcase.component.tab.TabUtil;
import com.liferay.faces.showcase.component.tabview.TabView;
import com.liferay.faces.util.component.Styleable;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.faces.util.render.RendererUtil;


/**
 * @author  Neil Griffin
 * @author  Vernon Singleton
 */

//J-
@FacesRenderer(componentFamily = TabView.COMPONENT_FAMILY, rendererType = TabView.RENDERER_TYPE)
@ResourceDependencies(
	{
		@ResourceDependency(library = "bootstrap", name = "css/bootstrap.min.css"),
		@ResourceDependency(library = "bootstrap", name = "js/jquery.min.js"),
		@ResourceDependency(library = "bootstrap", name = "js/bootstrap.min.js")
	}
)
//J+
public class TabViewRenderer extends TabViewRendererBase {

	// Private Constants
	private static final String SELECTED_TAB_CLASSES = "active";
	private static final String UNSELECTED_TAB_CLASSES = "";

	// Logger
	private static final Logger logger = LoggerFactory.getLogger(TabViewRenderer.class);

	private static void encodeTab(ResponseWriter responseWriter, UIComponent uiComponent, FacesContext facesContext,
		Integer selectedIndex, int currentIndex) throws IOException {

		responseWriter.startElement("div", null);

		String jQuerySafeTabClientId = getJQuerySafeTabClientId(uiComponent, facesContext);
		responseWriter.writeAttribute("id", jQuerySafeTabClientId, null);

		// Encode the div's class attribute according to the specified tab's selected/un-selected state.
		String selectionClass = UNSELECTED_TAB_CLASSES;

		if (isSelected(selectedIndex, currentIndex)) {
			selectionClass = SELECTED_TAB_CLASSES;
		}

		responseWriter.writeAttribute("class", "tab-pane " + selectionClass, null);
		uiComponent.encodeAll(facesContext);
		responseWriter.endElement("div");
	}

	private static String getJQuerySafeTabClientId(UIComponent uiComponent, FacesContext facesContext) {

		String clientId = uiComponent.getClientId(facesContext);

		return clientId.replace(":", "_") + "_tab";
	}

	private static boolean isSelected(Integer selectedIndex, int currentIndex) {
		return (((selectedIndex == null) && (currentIndex == 0)) ||
				((selectedIndex != null) && (currentIndex == selectedIndex)));
	}

	@Override
	public void encodeBegin(FacesContext facesContext, UIComponent uiComponent) throws IOException {

		// Encode the starting <div> element that represents the component.
		ResponseWriter responseWriter = facesContext.getResponseWriter();
		responseWriter.startElement("div", uiComponent);
		responseWriter.writeAttribute("id", uiComponent.getClientId(facesContext), "id");
		RendererUtil.encodeStyleable(responseWriter, (Styleable) uiComponent);
	}

	@Override
	public void encodeChildren(FacesContext facesContext, UIComponent uiComponent) throws IOException {

		// Get the "value" and "var" attributes of the TabView component and determine if iteration should take place
		// using a prototype child tab.
		TabView tabView = (TabView) uiComponent;
		Integer selectedIndex = tabView.getSelectedIndex();
		Object value = tabView.getValue();
		String var = tabView.getVar();
		boolean iterateOverDataModel = ((value != null) && (var != null));
		Tab prototypeChildTab = TabUtil.getFirstChildTab(tabView);
		String clientId = uiComponent.getClientId(facesContext);

		// Encode the starting <ul> unordered list element that represents the list of clickable tabs.
		ResponseWriter responseWriter = facesContext.getResponseWriter();
		responseWriter.startElement("ul", tabView);
		responseWriter.writeAttribute("class", "nav nav-tabs", null);

		if (iterateOverDataModel) {

			if (prototypeChildTab != null) {

				int rowCount = tabView.getRowCount();

				for (int i = 0; i < rowCount; i++) {

					tabView.setRowIndex(i);
					encodeTabListItem(facesContext, responseWriter, prototypeChildTab, isSelected(selectedIndex, i),
						clientId);
				}

				tabView.setRowIndex(-1);

			}
			else {
				logger.warn("Unable to iterate because alloy:tabView does not have an alloy:tab child element.");
			}
		}
		else {
			List<UIComponent> children = uiComponent.getChildren();
			int childCount = children.size();

			for (int i = 0; i < childCount; i++) {
				UIComponent child = children.get(i);

				if ((child instanceof Tab) && child.isRendered()) {

					Tab childTab = (Tab) child;
					encodeTabListItem(facesContext, responseWriter, childTab, isSelected(selectedIndex, i), clientId);
				}
				else {
					logger.warn("Unable to render child element of alloy:tabView since it is not alloy:tab");
				}
			}
		}

		responseWriter.endElement("ul");

		// Encode the starting <div> element that represents the content for the selected tab.
		responseWriter.startElement("div", uiComponent);
		responseWriter.writeAttribute("class", "tab-content", null);

		// Encode the content for each tab.
		if ((iterateOverDataModel) && (prototypeChildTab != null)) {

			int rowCount = tabView.getRowCount();

			for (int i = 0; i < rowCount; i++) {
				tabView.setRowIndex(i);
				encodeTab(responseWriter, prototypeChildTab, facesContext, selectedIndex, i);
			}
		}
		else {
			List<UIComponent> children = tabView.getChildren();

			for (int i = 0; i < children.size(); i++) {
				UIComponent child = children.get(i);

				if (child.isRendered()) {
					encodeTab(responseWriter, child, facesContext, selectedIndex, i);
				}
			}
		}

		tabView.setRowIndex(-1);

		// Encode the closing </div> element for the content.
		responseWriter.endElement("div");
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

	protected void encodeTabListItem(FacesContext facesContext, ResponseWriter responseWriter, Tab tab,
		boolean selected, String tabViewClientId) throws IOException {

		responseWriter.startElement("li", tab);

		// Encode the div's class attribute according to the specified tab's selected/un-selected state.
		String selectionClass = UNSELECTED_TAB_CLASSES;

		if (selected) {
			selectionClass = SELECTED_TAB_CLASSES;
		}

		// If the specified tab has a headerClass, then append it to the class attribute before encoding.
		String tabHeaderClass = tab.getHeaderClass();

		if (tabHeaderClass == null) {
			tabHeaderClass = "";
		}

		responseWriter.writeAttribute("class", "nav-item " + tabHeaderClass, Styleable.STYLE_CLASS);
		responseWriter.startElement("a", tab);

		responseWriter.writeAttribute("class", selectionClass + " nav-link", null);
		responseWriter.writeAttribute("data-toggle", "tab", null);

		if (tab.isDisabled()) {
			responseWriter.writeAttribute("disabled", "disabled", null);
		}

		responseWriter.writeAttribute("href", "#" + getJQuerySafeTabClientId(tab, facesContext), null);

		// If the header facet exists for the specified tab, then encode the header facet.
		UIComponent headerFacet = tab.getFacet("header");

		if (headerFacet != null) {
			headerFacet.encodeAll(facesContext);
		}

		// Otherwise, render the label of the specified tab.
		else {
			String headerText = tab.getHeaderText();

			if (headerText != null) {
				responseWriter.write(headerText);
			}
		}

		responseWriter.endElement("a");
		responseWriter.endElement("li");
	}
}
