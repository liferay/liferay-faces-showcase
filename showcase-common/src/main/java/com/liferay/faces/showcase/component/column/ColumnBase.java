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
package com.liferay.faces.showcase.component.column;
//J-

import javax.annotation.Generated;
import javax.faces.component.html.HtmlColumn;

import com.liferay.faces.util.component.Styleable;


/**
 * @author	Bruno Basto
 * @author	Kyle Stiemann
 */
@Generated(value = "com.liferay.alloy.tools.builder.FacesBuilder")
public abstract class ColumnBase extends HtmlColumn implements Styleable {

	// Public Constants
	public static final String COMPONENT_TYPE = "com.liferay.faces.showcase.component.column.Column";
	public static final String RENDERER_TYPE = "com.liferay.faces.showcase.component.column.ColumnRenderer";

	// Protected Enumerations
	protected enum ColumnPropertyKeys {
		execute,
		filterBy,
		headerText,
		offsetWidth,
		render,
		sortBy,
		sortOrder,
		span,
		style,
		styleClass,
		width
	}

	public ColumnBase() {
		super();
		setRendererType(RENDERER_TYPE);
	}

	/**
	 * <code>execute</code> attribute description:
	 * <br /><br />
	 * Space-delimited list of component ids that are to participate in the APPLY_REQUEST_VALUES, PROCESS_VALIDATIONS, UPDATE_MODEL_VALUES, and INVOKE_APPLICATION phases of the JSF lifecycle. The following keywords may also appear in the list: <code>@this</code>, <code>@form</code>, <code>@all</code>, <code>@none</code>, <code>@parent</code>. The default value is <code>@parent</code> (meaning, the parent showcase:dataTable).
	 */
	public String getExecute() {
		return (String) getStateHelper().eval(ColumnPropertyKeys.execute, "@parent");
	}

	/**
	 * <code>execute</code> attribute description:
	 * <br /><br />
	 * Space-delimited list of component ids that are to participate in the APPLY_REQUEST_VALUES, PROCESS_VALIDATIONS, UPDATE_MODEL_VALUES, and INVOKE_APPLICATION phases of the JSF lifecycle. The following keywords may also appear in the list: <code>@this</code>, <code>@form</code>, <code>@all</code>, <code>@none</code>, <code>@parent</code>. The default value is <code>@parent</code> (meaning, the parent showcase:dataTable).
	 */
	public void setExecute(String execute) {
		getStateHelper().put(ColumnPropertyKeys.execute, execute);
	}

	/**
	 * <code>filterBy</code> attribute description:
	 * <br /><br />
	 * When showcase:column is a child of showcase:dataTabe, this is the JavaBean property that is to be used for filtering.
	 */
	public Object getFilterBy() {
		return (Object) getStateHelper().eval(ColumnPropertyKeys.filterBy, null);
	}

	/**
	 * <code>filterBy</code> attribute description:
	 * <br /><br />
	 * When showcase:column is a child of showcase:dataTabe, this is the JavaBean property that is to be used for filtering.
	 */
	public void setFilterBy(Object filterBy) {
		getStateHelper().put(ColumnPropertyKeys.filterBy, filterBy);
	}

	/**
	 * <code>headerText</code> attribute description:
	 * <br /><br />
	 * When showcase:column is a child of showcase:dataTable, this is the text for the column header. This attribute can be used as a convenience instead of specifying a child <code>&lt;f:facet name="header" /&gt;</code> tag.
	 */
	public String getHeaderText() {
		return (String) getStateHelper().eval(ColumnPropertyKeys.headerText, null);
	}

	/**
	 * <code>headerText</code> attribute description:
	 * <br /><br />
	 * When showcase:column is a child of showcase:dataTable, this is the text for the column header. This attribute can be used as a convenience instead of specifying a child <code>&lt;f:facet name="header" /&gt;</code> tag.
	 */
	public void setHeaderText(String headerText) {
		getStateHelper().put(ColumnPropertyKeys.headerText, headerText);
	}

	/**
	 * <code>offsetWidth</code> attribute description:
	 * <br /><br />
	 * When showcase:column is a child of showcase:row, this is the width that the column will be offset by as a percent. This value selects the offset via the formula <code>offset = (offsetWidth/100)*12 (rounded to the nearest whole number)</code>. If both the offsetWidth and offset attributes are present, the offsetWidth attribute determines the value of the offset.
	 */
	public Integer getOffsetWidth() {
		return (Integer) getStateHelper().eval(ColumnPropertyKeys.offsetWidth, null);
	}

	/**
	 * <code>offsetWidth</code> attribute description:
	 * <br /><br />
	 * When showcase:column is a child of showcase:row, this is the width that the column will be offset by as a percent. This value selects the offset via the formula <code>offset = (offsetWidth/100)*12 (rounded to the nearest whole number)</code>. If both the offsetWidth and offset attributes are present, the offsetWidth attribute determines the value of the offset.
	 */
	public void setOffsetWidth(Integer offsetWidth) {
		getStateHelper().put(ColumnPropertyKeys.offsetWidth, offsetWidth);
	}

	/**
	 * <code>render</code> attribute description:
	 * <br /><br />
	 * Space-delimited list of component ids that are to participate in the RENDER_RESPONSE phase of the JSF lifecycle and be re-rendered in the DOM after the response is received from the XmlHttpRequest. The following keywords may also appear in the list: <code>@this</code>, <code>@form</code>, <code>@all</code>, <code>@none</code>, <code>@parent</code>. The default value is <code>@parent</code> (meaning, the parent showcase:dataTable).
	 */
	public String getRender() {
		return (String) getStateHelper().eval(ColumnPropertyKeys.render, "@parent");
	}

	/**
	 * <code>render</code> attribute description:
	 * <br /><br />
	 * Space-delimited list of component ids that are to participate in the RENDER_RESPONSE phase of the JSF lifecycle and be re-rendered in the DOM after the response is received from the XmlHttpRequest. The following keywords may also appear in the list: <code>@this</code>, <code>@form</code>, <code>@all</code>, <code>@none</code>, <code>@parent</code>. The default value is <code>@parent</code> (meaning, the parent showcase:dataTable).
	 */
	public void setRender(String render) {
		getStateHelper().put(ColumnPropertyKeys.render, render);
	}

	/**
	 * <code>sortBy</code> attribute description:
	 * <br /><br />
	 * When showcase:column is a child of showcase:dataTabe, this is the JavaBean property that is to be used for sorting.
	 */
	public Object getSortBy() {
		return (Object) getStateHelper().eval(ColumnPropertyKeys.sortBy, null);
	}

	/**
	 * <code>sortBy</code> attribute description:
	 * <br /><br />
	 * When showcase:column is a child of showcase:dataTabe, this is the JavaBean property that is to be used for sorting.
	 */
	public void setSortBy(Object sortBy) {
		getStateHelper().put(ColumnPropertyKeys.sortBy, sortBy);
	}

	/**
	 * <code>sortOrder</code> attribute description:
	 * <br /><br />
	 * Indicates the sort order for the column. Valid values are <code>ascending</code>, <code>descending</code>, and <code>none</code>.
	 */
	public String getSortOrder() {
		return (String) getStateHelper().eval(ColumnPropertyKeys.sortOrder, null);
	}

	/**
	 * <code>sortOrder</code> attribute description:
	 * <br /><br />
	 * Indicates the sort order for the column. Valid values are <code>ascending</code>, <code>descending</code>, and <code>none</code>.
	 */
	public void setSortOrder(String sortOrder) {
		getStateHelper().put(ColumnPropertyKeys.sortOrder, sortOrder);
	}

	/**
	 * <code>span</code> attribute description:
	 * <br /><br />
	 * When showcase:column is a child of showcase:row, this is the width of this column as a unit from 1 to 12. This value is rendered as a CSS class as <code>col-#{size}-#{span}</code> (for example if span="4", the CSS class rendered will be <code>col-#{size}-4</code>). If both the width and span attributes are present, the width attribute determines the value of the span number. Please consider using width.
	 */
	public Integer getSpan() {
		return (Integer) getStateHelper().eval(ColumnPropertyKeys.span, null);
	}

	/**
	 * <code>span</code> attribute description:
	 * <br /><br />
	 * When showcase:column is a child of showcase:row, this is the width of this column as a unit from 1 to 12. This value is rendered as a CSS class as <code>col-#{size}-#{span}</code> (for example if span="4", the CSS class rendered will be <code>col-#{size}-4</code>). If both the width and span attributes are present, the width attribute determines the value of the span number. Please consider using width.
	 */
	public void setSpan(Integer span) {
		getStateHelper().put(ColumnPropertyKeys.span, span);
	}

	/**
	 * <code>style</code> attribute description:
	 * <br /><br />
	 * HTML passthrough attribute specifying the css style of the element.
	 */
	@Override
	public String getStyle() {
		return (String) getStateHelper().eval(ColumnPropertyKeys.style, null);
	}

	/**
	 * <code>style</code> attribute description:
	 * <br /><br />
	 * HTML passthrough attribute specifying the css style of the element.
	 */
	@Override
	public void setStyle(String style) {
		getStateHelper().put(ColumnPropertyKeys.style, style);
	}

	/**
	 * <code>styleClass</code> attribute description:
	 * <br /><br />
	 * List of CSS class names (separated by spaces) that are to be rendered within the class attribute.
	 */
	@Override
	public String getStyleClass() {

		// getStateHelper().eval(ColumnPropertyKeys.styleClass, null) is called because
		// super.getStyleClass() may return the styleClass name of the super class.
		String styleClass = (String) getStateHelper().eval(ColumnPropertyKeys.styleClass, null);

		return com.liferay.faces.util.component.ComponentUtil.concatCssClasses(styleClass, "showcase-column");
	}

	/**
	 * <code>styleClass</code> attribute description:
	 * <br /><br />
	 * List of CSS class names (separated by spaces) that are to be rendered within the class attribute.
	 */
	@Override
	public void setStyleClass(String styleClass) {
		getStateHelper().put(ColumnPropertyKeys.styleClass, styleClass);
	}

	/**
	 * <code>width</code> attribute description:
	 * <br /><br />
	 * When showcase:column is a child of showcase:row, this is the width of the column as a percent. This value selects the span number via the formula <code>span = (width/100)*12 (rounded to the nearest whole number)</code>. If both the width and span attributes are present, the width attribute determines the value of the span number.
	 */
	public Integer getWidth() {
		return (Integer) getStateHelper().eval(ColumnPropertyKeys.width, null);
	}

	/**
	 * <code>width</code> attribute description:
	 * <br /><br />
	 * When showcase:column is a child of showcase:row, this is the width of the column as a percent. This value selects the span number via the formula <code>span = (width/100)*12 (rounded to the nearest whole number)</code>. If both the width and span attributes are present, the width attribute determines the value of the span number.
	 */
	public void setWidth(Integer width) {
		getStateHelper().put(ColumnPropertyKeys.width, width);
	}
}
//J+
