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
package com.liferay.faces.showcase.component.accordion.internal;

import java.io.IOException;
import java.util.List;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import com.liferay.faces.showcase.component.accordion.Accordion;
import com.liferay.faces.showcase.component.tab.Tab;
import com.liferay.faces.showcase.component.tab.TabUtil;
import com.liferay.faces.showcase.util.ShowcaseUtil;
import com.liferay.faces.util.component.Styleable;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.faces.util.product.Product;
import com.liferay.faces.util.product.ProductFactory;
import com.liferay.faces.util.render.RendererUtil;


/**
 * @author  Vernon Singleton
 */

//J-
@FacesRenderer(componentFamily = Accordion.COMPONENT_FAMILY, rendererType = Accordion.RENDERER_TYPE)
@ResourceDependencies(
	{
		@ResourceDependency(library = "bootstrap", name = "css/bootstrap.min.css"),
		@ResourceDependency(library = "bootstrap", name = "js/jquery.min.js"),
		@ResourceDependency(library = "bootstrap", name = "js/bootstrap.min.js")
	}
)
//J+
public class AccordionRenderer extends AccordionRendererBase {

	// Private Constants
	private static final boolean LIFERAY_PORTAL_DETECTED = ProductFactory.getProduct(Product.Name.LIFERAY_PORTAL)
		.isDetected();

	// Logger
	private static final Logger logger = LoggerFactory.getLogger(AccordionRenderer.class);

	@Override
	public void encodeBegin(FacesContext facesContext, UIComponent uiComponent) throws IOException {

		// Encode the starting <div> element that represents the accordion.
		ResponseWriter responseWriter = facesContext.getResponseWriter();
		responseWriter.startElement("div", uiComponent);
		responseWriter.writeAttribute("id", uiComponent.getClientId(facesContext), "id");
		RendererUtil.encodeStyleable(responseWriter, (Styleable) uiComponent, "accordion", "panel-group");
	}

	@Override
	public void encodeChildren(FacesContext facesContext, UIComponent uiComponent) throws IOException {

		// If iteration should take place over a data-model, then
		Accordion accordion = (Accordion) uiComponent;
		String accordionClientId = accordion.getClientId();
		Integer selectedIndex = accordion.getSelectedIndex();
		Object value = accordion.getValue();
		String var = accordion.getVar();
		boolean iterateOverDataModel = ((value != null) && (var != null));
		ResponseWriter responseWriter = facesContext.getResponseWriter();

		if (iterateOverDataModel) {

			// Get the first child tab and use it as a prototype tab.
			Tab prototypeChildTab = TabUtil.getFirstChildTab(accordion);

			if (prototypeChildTab != null) {

				// Encode a header <div> and content <div> for each row in the data-model.
				int rowCount = accordion.getRowCount();

				for (int i = 0; i < rowCount; i++) {
					accordion.setRowIndex(i);

					String accordionIteratedClientId = accordion.getClientId();

					boolean selected = ((selectedIndex != null) && (i == selectedIndex));
					responseWriter.startElement("div", null);
					responseWriter.writeAttribute("class", "accordion-group panel panel-default", null);
					encodeHeader(facesContext, responseWriter, accordionClientId, accordionIteratedClientId,
						prototypeChildTab);
					encodeContent(facesContext, responseWriter, accordionIteratedClientId, prototypeChildTab, selected);
					responseWriter.endElement("div");
				}

				accordion.setRowIndex(-1);
			}
			else {
				logger.warn("Unable to iterate because alloy:accordion does not have an alloy:tab child element.");
			}
		}

		// Otherwise, encode a header <div> and content <div> for each child tab of the specified accordion.
		else {
			List<UIComponent> children = uiComponent.getChildren();
			int childCount = children.size();

			for (int i = 0; i < childCount; i++) {

				UIComponent child = children.get(i);

				if ((child instanceof Tab) && child.isRendered()) {
					Tab childTab = (Tab) child;
					String accordionIteratedClientId = accordion.getClientId().concat("_").concat(Integer.toString(i));
					boolean selected = ((selectedIndex != null) && (i == selectedIndex));
					encodeHeader(facesContext, responseWriter, accordionClientId, accordionIteratedClientId, childTab);
					encodeContent(facesContext, responseWriter, accordionIteratedClientId, childTab, selected);
				}
				else {
					logger.warn("Unable to render child element of alloy:accordion since it is not alloy:tab");
				}
			}
		}

		accordion.setRowIndex(-1);
	}

	@Override
	public void encodeEnd(FacesContext facesContext, UIComponent uiComponent) throws IOException {

		// Encode the closing </div> element for the accordion.
		ResponseWriter responseWriter = facesContext.getResponseWriter();
		responseWriter.endElement("div");

		String escapedClientId;

		if (LIFERAY_PORTAL_DETECTED) {
			escapedClientId = ShowcaseUtil.doubleEscapeClientId(uiComponent.getClientId());
		}
		else {
			escapedClientId = ShowcaseUtil.singleEscapeClientId(uiComponent.getClientId());
		}
	}

	@Override
	public boolean getRendersChildren() {
		return true;
	}

	protected void encodeContent(FacesContext facesContext, ResponseWriter responseWriter,
		String accordionIteratedClientId, Tab tab, boolean selected) throws IOException {

		// Encode the starting <div> element that represents the specified tab's content.
		responseWriter.startElement("div", tab);
		responseWriter.writeAttribute("id", accordionIteratedClientId, null);

		// Encode the div's class attribute according to the specified tab's collapsed/expanded state.
		String contentClass = "accordion-body panel-body panel-collapse collapse";

		if (selected) {
			contentClass = contentClass.concat(" in");
		}

		// If the specified tab has a contentClass, then append it to the class attribute before encoding.
		String tabContentClass = tab.getContentClass();

		if (tabContentClass != null) {
			contentClass = contentClass.concat(" ").concat(tabContentClass);
		}

		responseWriter.writeAttribute("class", contentClass, Styleable.STYLE_CLASS);

		responseWriter.startElement("div", null);
		responseWriter.writeAttribute("class", "accordion-inner", null);

		// Encode the children of the specified tab as the actual content.
		tab.encodeAll(facesContext);

		responseWriter.endElement("div");

		// Encode the closing </div> element for the specified tab.
		responseWriter.endElement("div");
	}

	protected void encodeHeader(FacesContext facesContext, ResponseWriter responseWriter, String accordionClientId,
		String accordionIteratedClientId, Tab tab) throws IOException {

		// Encode the starting <div> element that represents the specified tab's header.
		responseWriter.startElement("div", tab);

		// Encode the div's class attribute according to the specified tab's collapsed/expanded state.
		String headerClass = "accordion-heading panel-heading";

		// If the specified tab has a headerClass, then append it to the class attribute before encoding.
		String tabHeaderClass = tab.getHeaderClass();

		if (tabHeaderClass != null) {
			headerClass += " " + tabHeaderClass;
		}

		responseWriter.writeAttribute("class", headerClass, Styleable.STYLE_CLASS);

		responseWriter.startElement("a", null);
		responseWriter.writeAttribute("class", "accordion-toggle panel-title collapsed", null);

		String escapedAccordionClientId = "#".concat(ShowcaseUtil.singleEscapeClientId(accordionClientId));
		responseWriter.writeAttribute("data-parent", escapedAccordionClientId, null);
		responseWriter.writeAttribute("data-toggle", "collapse", null);

		String escapedTabClientId = "#".concat(ShowcaseUtil.singleEscapeClientId(accordionIteratedClientId));
		responseWriter.writeAttribute("href", escapedTabClientId, null);

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

		// Encode the closing </div> element for the specified tab.
		responseWriter.endElement("div");
	}
}
