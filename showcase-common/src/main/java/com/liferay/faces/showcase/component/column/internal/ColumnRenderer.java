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
package com.liferay.faces.showcase.component.column.internal;

import java.io.IOException;

import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.render.FacesRenderer;

import com.liferay.faces.showcase.component.column.Column;
import com.liferay.faces.util.render.RendererUtil;


/**
 * @author  Kyle Stiemann
 */
@FacesRenderer(componentFamily = Column.COMPONENT_FAMILY, rendererType = Column.RENDERER_TYPE)
@ResourceDependency(library = "bootstrap", name = "css/bootstrap.min.css")
public class ColumnRenderer extends ColumnRendererBase {

	protected static Integer getColumnUnitSize(Integer width) {
		return (int) Math.round(Column.COLUMNS * ((double) width / 100));
	}

	@Override
	public void encodeBegin(FacesContext facesContext, UIComponent uiComponent) throws IOException {

		ResponseWriter responseWriter = facesContext.getResponseWriter();

		UIComponent parent = uiComponent.getParent();

		if (!((parent instanceof HtmlDataTable) || (parent instanceof HtmlPanelGrid))) {

			responseWriter.startElement("div", uiComponent);

			String clientId = uiComponent.getClientId(facesContext);
			responseWriter.writeAttribute("id", clientId, null);

			Column column = (Column) uiComponent;
			StringBuilder classNames = new StringBuilder();

			String size = column.getSize();
			size = getColumnCSSClassSize(size);

			Integer span = column.getSpan();

			if (span != null) {

				if ((span < 1) || (span > Column.COLUMNS)) {
					throw new IOException("span number must be between 1 and " + Column.COLUMNS);
				}
			}

			Integer width = column.getWidth();

			if (width != null) {

				if ((width < 1) || (width > 100)) {
					throw new IOException("width must be between 1 and 100");
				}

				span = getColumnUnitSize(width);
			}

			classNames.append("col-");
			classNames.append(size);
			classNames.append("-");
			classNames.append(span);

			classNames.append(" ");
			classNames.append("span");
			classNames.append(span);

			Integer offset = column.getOffset();

			if (offset != null) {

				if ((offset < 1) || (offset > Column.COLUMNS)) {
					throw new IOException("offset must be between 1 and " + Column.COLUMNS);
				}
			}

			Integer offsetWidth = column.getOffsetWidth();

			if (offsetWidth != null) {

				if ((offsetWidth < 1) || (offsetWidth > 100)) {
					throw new IOException("offsetWidth must be between 1 and 100");
				}

				offset = getColumnUnitSize(offsetWidth);
			}

			if (offset != null) {
				classNames.append(" ");
				classNames.append("col-");
				classNames.append(size);
				classNames.append("-offset-");
				classNames.append(offset);

				classNames.append(" ");
				classNames.append("offset");
				classNames.append(span);
			}

			RendererUtil.encodeStyleable(responseWriter, column, classNames.toString());
		}
	}

	@Override
	public void encodeEnd(FacesContext facesContext, UIComponent uiComponent) throws IOException {

		ResponseWriter responseWriter = facesContext.getResponseWriter();

		UIComponent parent = uiComponent.getParent();

		if (!((parent instanceof HtmlDataTable) || (parent instanceof HtmlPanelGrid))) {
			responseWriter.endElement("div");
		}
	}

	private String getColumnCSSClassSize(String size) throws IOException {

		if ("extra-small".equals(size) || "xs".equals(size)) {
			size = "xs";
		}
		else if ("small".equals(size) || "sm".equals(size)) {
			size = "sm";
		}
		else if ("medium".equals(size) || "md".equals(size)) {
			size = "md";
		}
		else if ("large".equals(size) || "lg".equals(size)) {
			size = "lg";
		}
		else {
			throw new IOException(size + " is not a valid value for size.");
		}

		return size;
	}
}
