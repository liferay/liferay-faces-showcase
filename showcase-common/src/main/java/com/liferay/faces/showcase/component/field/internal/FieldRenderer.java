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
package com.liferay.faces.showcase.component.field.internal;

import java.io.IOException;
import java.util.List;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import com.liferay.faces.showcase.component.field.Field;


/**
 * @author  Kyle Stiemann
 */
//J-
@FacesRenderer(componentFamily = Field.COMPONENT_FAMILY, rendererType = Field.RENDERER_TYPE)
@ResourceDependencies(
	{
		@ResourceDependency(library = "bootstrap", name = "css/bootstrap.min.css"),
		@ResourceDependency(library = "bootstrap", name = "css/bootstrap-responsive.min.css"),
	}
)
//J+
public class FieldRenderer extends FieldRendererBase {

	@Override
	public void encodeChildren(FacesContext facesContext, UIComponent uiComponent) throws IOException {

		Field field = (Field) uiComponent;
		String label = field.getLabel();
		UIComponent labelFacet = field.getFacet("label");
		boolean hasLabel = ((label != null) && !label.equals("")) || (labelFacet != null);
		boolean labelFirst = field.isLabelFirst();

		if (hasLabel && labelFirst) {
			encodeLabel(facesContext, field, labelFacet, label);
		}

		super.encodeChildren(facesContext, uiComponent);

		if (hasLabel && !labelFirst) {
			encodeLabel(facesContext, field, labelFacet, label);
		}
	}

	private void encodeLabel(FacesContext facesContext, Field field, UIComponent labelFacet, String label)
		throws IOException {

		ResponseWriter responseWriter = facesContext.getResponseWriter();
		responseWriter.startElement(LABEL, field);
		responseWriter.writeAttribute("class", "control-label", null);

		if (labelFacet != null) {

			UIComponent checkboxLabelFacetChild = getSelectBooleanCheckboxLabelFacetChild(labelFacet);

			if (checkboxLabelFacetChild != null) {

				String checkboxClientId = checkboxLabelFacetChild.getClientId(facesContext);
				responseWriter.writeAttribute("for", checkboxClientId, null);
			}

			labelFacet.encodeAll(facesContext);
		}

		if ((label != null) && !label.equals("")) {
			responseWriter.writeText(label, LABEL);
		}

		responseWriter.endElement(LABEL);
	}

	private UIComponent getSelectBooleanCheckboxLabelFacetChild(UIComponent uiComponent) {

		UIComponent labelFacet = uiComponent.getFacet("label");
		UIComponent selectBooleanCheckboxChild = null;

		if (labelFacet != null) {

			if (labelFacet instanceof HtmlSelectBooleanCheckbox) {
				selectBooleanCheckboxChild = labelFacet;
			}
			else if (labelFacet.getChildCount() > 0) {

				List<UIComponent> children = labelFacet.getChildren();

				for (UIComponent child : children) {

					selectBooleanCheckboxChild = child;

					break;
				}
			}
		}

		return selectBooleanCheckboxChild;
	}
}
