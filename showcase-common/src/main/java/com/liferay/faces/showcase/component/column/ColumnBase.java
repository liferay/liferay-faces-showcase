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
		offset,
		offsetWidth,
		render,
		size,
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
	 * <p><code>execute</code> attribute description:</p>
	 *
	 * <div>Space-delimited list of component ids that are to participate in the APPLY_REQUEST_VALUES, PROCESS_VALIDATIONS, UPDATE_MODEL_VALUES, and INVOKE_APPLICATION phases of the JSF lifecycle. The following keywords may also appear in the list: <code>@this</code>, <code>@form</code>, <code>@all</code>, <code>@none</code>, <code>@parent</code>. The default value is <code>@parent</code> (meaning, the parent showcase:dataTable).</div>
	 */
	public String getExecute() {
		return (String) getStateHelper().eval(ColumnPropertyKeys.execute, "@parent");
	}

	/**
	 * <p><code>execute</code> attribute description:</p>
	 *
	 * <div>Space-delimited list of component ids that are to participate in the APPLY_REQUEST_VALUES, PROCESS_VALIDATIONS, UPDATE_MODEL_VALUES, and INVOKE_APPLICATION phases of the JSF lifecycle. The following keywords may also appear in the list: <code>@this</code>, <code>@form</code>, <code>@all</code>, <code>@none</code>, <code>@parent</code>. The default value is <code>@parent</code> (meaning, the parent showcase:dataTable).</div>
	 */
	public void setExecute(String execute) {
		getStateHelper().put(ColumnPropertyKeys.execute, execute);
	}

	/**
	 * <p><code>filterBy</code> attribute description:</p>
	 *
	 * <div>When showcase:column is a child of showcase:dataTabe, this is the JavaBean property that is to be used for filtering.</div>
	 */
	public Object getFilterBy() {
		return (Object) getStateHelper().eval(ColumnPropertyKeys.filterBy, null);
	}

	/**
	 * <p><code>filterBy</code> attribute description:</p>
	 *
	 * <div>When showcase:column is a child of showcase:dataTabe, this is the JavaBean property that is to be used for filtering.</div>
	 */
	public void setFilterBy(Object filterBy) {
		getStateHelper().put(ColumnPropertyKeys.filterBy, filterBy);
	}

	/**
	 * <p><code>headerText</code> attribute description:</p>
	 *
	 * <div>When showcase:column is a child of showcase:dataTable, this is the text for the column header. This attribute can be used as a convenience instead of specifying a child <code>&lt;f:facet name="header" /&gt;</code> tag.</div>
	 */
	public String getHeaderText() {
		return (String) getStateHelper().eval(ColumnPropertyKeys.headerText, null);
	}

	/**
	 * <p><code>headerText</code> attribute description:</p>
	 *
	 * <div>When showcase:column is a child of showcase:dataTable, this is the text for the column header. This attribute can be used as a convenience instead of specifying a child <code>&lt;f:facet name="header" /&gt;</code> tag.</div>
	 */
	public void setHeaderText(String headerText) {
		getStateHelper().put(ColumnPropertyKeys.headerText, headerText);
	}

	/**
	 * <p><code>offset</code> attribute description:</p>
	 *
	 * <div>When alloy:column is a child of alloy:row, this is the width that the column will be offset by as a unit from 1 to 12. This value is rendered as a CSS class as <code>col-#{size}-offset-#{offset}</code> (for example if offset="4", the CSS class rendered will be <code>col-#{size}-offset-4</code>). If both the offsetWidth and offset attributes are present, the offsetWidth attribute determines the value of the offset. Please consider using offsetWidth.</div>
	 */
	public Integer getOffset() {
		return (Integer) getStateHelper().eval(ColumnPropertyKeys.offset, null);
	}

	/**
	 * <p><code>offset</code> attribute description:</p>
	 *
	 * <div>When alloy:column is a child of alloy:row, this is the width that the column will be offset by as a unit from 1 to 12. This value is rendered as a CSS class as <code>col-#{size}-offset-#{offset}</code> (for example if offset="4", the CSS class rendered will be <code>col-#{size}-offset-4</code>). If both the offsetWidth and offset attributes are present, the offsetWidth attribute determines the value of the offset. Please consider using offsetWidth.</div>
	 */
	public void setOffset(Integer offset) {
		getStateHelper().put(ColumnPropertyKeys.offset, offset);
	}

	/**
	 * <p><code>offsetWidth</code> attribute description:</p>
	 *
	 * <div>When showcase:column is a child of showcase:row, this is the width that the column will be offset by as a percent. This value selects the offset via the formula <code>offset = (offsetWidth/100)*12 (rounded to the nearest whole number)</code>. If both the offsetWidth and offset attributes are present, the offsetWidth attribute determines the value of the offset.</div>
	 */
	public Integer getOffsetWidth() {
		return (Integer) getStateHelper().eval(ColumnPropertyKeys.offsetWidth, null);
	}

	/**
	 * <p><code>offsetWidth</code> attribute description:</p>
	 *
	 * <div>When showcase:column is a child of showcase:row, this is the width that the column will be offset by as a percent. This value selects the offset via the formula <code>offset = (offsetWidth/100)*12 (rounded to the nearest whole number)</code>. If both the offsetWidth and offset attributes are present, the offsetWidth attribute determines the value of the offset.</div>
	 */
	public void setOffsetWidth(Integer offsetWidth) {
		getStateHelper().put(ColumnPropertyKeys.offsetWidth, offsetWidth);
	}

	/**
	 * <p><code>render</code> attribute description:</p>
	 *
	 * <div>Space-delimited list of component ids that are to participate in the RENDER_RESPONSE phase of the JSF lifecycle and be re-rendered in the DOM after the response is received from the XmlHttpRequest. The following keywords may also appear in the list: <code>@this</code>, <code>@form</code>, <code>@all</code>, <code>@none</code>, <code>@parent</code>. The default value is <code>@parent</code> (meaning, the parent showcase:dataTable).</div>
	 */
	public String getRender() {
		return (String) getStateHelper().eval(ColumnPropertyKeys.render, "@parent");
	}

	/**
	 * <p><code>render</code> attribute description:</p>
	 *
	 * <div>Space-delimited list of component ids that are to participate in the RENDER_RESPONSE phase of the JSF lifecycle and be re-rendered in the DOM after the response is received from the XmlHttpRequest. The following keywords may also appear in the list: <code>@this</code>, <code>@form</code>, <code>@all</code>, <code>@none</code>, <code>@parent</code>. The default value is <code>@parent</code> (meaning, the parent showcase:dataTable).</div>
	 */
	public void setRender(String render) {
		getStateHelper().put(ColumnPropertyKeys.render, render);
	}

	/**
	 * <p><code>size</code> attribute description:</p>
	 *
	 * <div>When alloy:column is a child of alloy:row, this is the size of the column. This value is rendered as a CSS class as <code>col-#{size}-#{span}</code> (for example if size="medium", the CSS class rendered will be <code>col-md-#{span}</code>). Valid values include <code>"extra-small"</code> (or <code>"xs"</code>), <code>"small"</code> (or <code>"sm"</code>), <code>"medium"</code> (or <code>"md"</code>), and <code>"large"</code> (or <code>"lg"</code>).</div>
	 */
	public String getSize() {
		return (String) getStateHelper().eval(ColumnPropertyKeys.size, "medium");
	}

	/**
	 * <p><code>size</code> attribute description:</p>
	 *
	 * <div>When alloy:column is a child of alloy:row, this is the size of the column. This value is rendered as a CSS class as <code>col-#{size}-#{span}</code> (for example if size="medium", the CSS class rendered will be <code>col-md-#{span}</code>). Valid values include <code>"extra-small"</code> (or <code>"xs"</code>), <code>"small"</code> (or <code>"sm"</code>), <code>"medium"</code> (or <code>"md"</code>), and <code>"large"</code> (or <code>"lg"</code>).</div>
	 */
	public void setSize(String size) {
		getStateHelper().put(ColumnPropertyKeys.size, size);
	}

	/**
	 * <p><code>sortBy</code> attribute description:</p>
	 *
	 * <div>When showcase:column is a child of showcase:dataTabe, this is the JavaBean property that is to be used for sorting.</div>
	 */
	public Object getSortBy() {
		return (Object) getStateHelper().eval(ColumnPropertyKeys.sortBy, null);
	}

	/**
	 * <p><code>sortBy</code> attribute description:</p>
	 *
	 * <div>When showcase:column is a child of showcase:dataTabe, this is the JavaBean property that is to be used for sorting.</div>
	 */
	public void setSortBy(Object sortBy) {
		getStateHelper().put(ColumnPropertyKeys.sortBy, sortBy);
	}

	/**
	 * <p><code>sortOrder</code> attribute description:</p>
	 *
	 * <div>Indicates the sort order for the column. Valid values are <code>ascending</code>, <code>descending</code>, and <code>none</code>.</div>
	 */
	public String getSortOrder() {
		return (String) getStateHelper().eval(ColumnPropertyKeys.sortOrder, null);
	}

	/**
	 * <p><code>sortOrder</code> attribute description:</p>
	 *
	 * <div>Indicates the sort order for the column. Valid values are <code>ascending</code>, <code>descending</code>, and <code>none</code>.</div>
	 */
	public void setSortOrder(String sortOrder) {
		getStateHelper().put(ColumnPropertyKeys.sortOrder, sortOrder);
	}

	/**
	 * <p><code>span</code> attribute description:</p>
	 *
	 * <div>When showcase:column is a child of showcase:row, this is the width of this column as a unit from 1 to 12. This value is rendered as a CSS class as <code>col-#{size}-#{span}</code> (for example if span="4", the CSS class rendered will be <code>col-#{size}-4</code>). If both the width and span attributes are present, the width attribute determines the value of the span number. Please consider using width.</div>
	 */
	public Integer getSpan() {
		return (Integer) getStateHelper().eval(ColumnPropertyKeys.span, null);
	}

	/**
	 * <p><code>span</code> attribute description:</p>
	 *
	 * <div>When showcase:column is a child of showcase:row, this is the width of this column as a unit from 1 to 12. This value is rendered as a CSS class as <code>col-#{size}-#{span}</code> (for example if span="4", the CSS class rendered will be <code>col-#{size}-4</code>). If both the width and span attributes are present, the width attribute determines the value of the span number. Please consider using width.</div>
	 */
	public void setSpan(Integer span) {
		getStateHelper().put(ColumnPropertyKeys.span, span);
	}

	/**
	 * <p><code>style</code> attribute description:</p>
	 *
	 * <div>HTML passthrough attribute specifying the css style of the element.</div>
	 */
	@Override
	public String getStyle() {
		return (String) getStateHelper().eval(ColumnPropertyKeys.style, null);
	}

	/**
	 * <p><code>style</code> attribute description:</p>
	 *
	 * <div>HTML passthrough attribute specifying the css style of the element.</div>
	 */
	@Override
	public void setStyle(String style) {
		getStateHelper().put(ColumnPropertyKeys.style, style);
	}

	/**
	 * <p><code>styleClass</code> attribute description:</p>
	 *
	 * <div>List of CSS class names (separated by spaces) that are to be rendered within the class attribute.</div>
	 */
	@Override
	public String getStyleClass() {

		// getStateHelper().eval(ColumnPropertyKeys.styleClass, null) is called because
		// super.getStyleClass() may return the styleClass name of the super class.
		String styleClass = (String) getStateHelper().eval(ColumnPropertyKeys.styleClass, null);

		return com.liferay.faces.util.component.ComponentUtil.concatCssClasses(styleClass, "showcase-column");
	}

	/**
	 * <p><code>styleClass</code> attribute description:</p>
	 *
	 * <div>List of CSS class names (separated by spaces) that are to be rendered within the class attribute.</div>
	 */
	@Override
	public void setStyleClass(String styleClass) {
		getStateHelper().put(ColumnPropertyKeys.styleClass, styleClass);
	}

	/**
	 * <p><code>width</code> attribute description:</p>
	 *
	 * <div>When showcase:column is a child of showcase:row, this is the width of the column as a percent. This value selects the span number via the formula <code>span = (width/100)*12 (rounded to the nearest whole number)</code>. If both the width and span attributes are present, the width attribute determines the value of the span number.</div>
	 */
	public Integer getWidth() {
		return (Integer) getStateHelper().eval(ColumnPropertyKeys.width, null);
	}

	/**
	 * <p><code>width</code> attribute description:</p>
	 *
	 * <div>When showcase:column is a child of showcase:row, this is the width of the column as a percent. This value selects the span number via the formula <code>span = (width/100)*12 (rounded to the nearest whole number)</code>. If both the width and span attributes are present, the width attribute determines the value of the span number.</div>
	 */
	public void setWidth(Integer width) {
		getStateHelper().put(ColumnPropertyKeys.width, width);
	}
}
//J+
