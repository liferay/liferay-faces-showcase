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
package com.liferay.faces.showcase.component.tab;
//J-

import javax.annotation.Generated;
import javax.faces.component.UIColumn;

import com.liferay.faces.util.component.Styleable;


/**
 * @author	Bruno Basto
 * @author	Kyle Stiemann
 */
@Generated(value = "com.liferay.alloy.tools.builder.FacesBuilder")
public abstract class TabBase extends UIColumn implements Styleable {

	// Public Constants
	public static final String COMPONENT_TYPE = "com.liferay.faces.showcase.component.tab.Tab";
	public static final String RENDERER_TYPE = "com.liferay.faces.showcase.component.tab.TabRenderer";

	// Protected Enumerations
	protected enum TabPropertyKeys {
		contentClass,
		disabled,
		headerClass,
		headerText,
		style,
		styleClass
	}

	public TabBase() {
		super();
		setRendererType(RENDERER_TYPE);
	}

	/**
	 * <code>contentClass</code> attribute description:
	 * <br /><br />
	 * When showcase:tab is a child of showcase:accordion, this is the name of the CSS class that is to be rendered within the class attribute of the content &lt;div&gt;
	 */
	public String getContentClass() {
		return (String) getStateHelper().eval(TabPropertyKeys.contentClass, null);
	}

	/**
	 * <code>contentClass</code> attribute description:
	 * <br /><br />
	 * When showcase:tab is a child of showcase:accordion, this is the name of the CSS class that is to be rendered within the class attribute of the content &lt;div&gt;
	 */
	public void setContentClass(String contentClass) {
		getStateHelper().put(TabPropertyKeys.contentClass, contentClass);
	}

	/**
	 * <code>disabled</code> attribute description:
	 * <br /><br />
	 * When this flag is true, the component will be disabled, and the user will be unable to intercat with the component.
	 */
	public boolean isDisabled() {
		return (Boolean) getStateHelper().eval(TabPropertyKeys.disabled, false);
	}

	/**
	 * <code>disabled</code> attribute description:
	 * <br /><br />
	 * When this flag is true, the component will be disabled, and the user will be unable to intercat with the component.
	 */
	public void setDisabled(boolean disabled) {
		getStateHelper().put(TabPropertyKeys.disabled, disabled);
	}

	/**
	 * <code>headerClass</code> attribute description:
	 * <br /><br />
	 * When showcase:tab is a child of showcase:accordion, this is the name of the CSS class that is to be rendered within the class attribute of the header &lt;div&gt;
	 */
	public String getHeaderClass() {
		return (String) getStateHelper().eval(TabPropertyKeys.headerClass, null);
	}

	/**
	 * <code>headerClass</code> attribute description:
	 * <br /><br />
	 * When showcase:tab is a child of showcase:accordion, this is the name of the CSS class that is to be rendered within the class attribute of the header &lt;div&gt;
	 */
	public void setHeaderClass(String headerClass) {
		getStateHelper().put(TabPropertyKeys.headerClass, headerClass);
	}

	/**
	 * <code>headerText</code> attribute description:
	 * <br /><br />
	 * The text to place in the header of the component.
	 */
	public String getHeaderText() {
		return (String) getStateHelper().eval(TabPropertyKeys.headerText, null);
	}

	/**
	 * <code>headerText</code> attribute description:
	 * <br /><br />
	 * The text to place in the header of the component.
	 */
	public void setHeaderText(String headerText) {
		getStateHelper().put(TabPropertyKeys.headerText, headerText);
	}

	/**
	 * <code>style</code> attribute description:
	 * <br /><br />
	 * HTML passthrough attribute specifying the css style of the element.
	 */
	@Override
	public String getStyle() {
		return (String) getStateHelper().eval(TabPropertyKeys.style, null);
	}

	/**
	 * <code>style</code> attribute description:
	 * <br /><br />
	 * HTML passthrough attribute specifying the css style of the element.
	 */
	@Override
	public void setStyle(String style) {
		getStateHelper().put(TabPropertyKeys.style, style);
	}

	/**
	 * <code>styleClass</code> attribute description:
	 * <br /><br />
	 * List of CSS class names (separated by spaces) that are to be rendered within the class attribute.
	 */
	@Override
	public String getStyleClass() {

		// getStateHelper().eval(TabPropertyKeys.styleClass, null) is called because
		// super.getStyleClass() may return the styleClass name of the super class.
		String styleClass = (String) getStateHelper().eval(TabPropertyKeys.styleClass, null);

		return com.liferay.faces.util.component.ComponentUtil.concatCssClasses(styleClass, "showcase-tab");
	}

	/**
	 * <code>styleClass</code> attribute description:
	 * <br /><br />
	 * List of CSS class names (separated by spaces) that are to be rendered within the class attribute.
	 */
	@Override
	public void setStyleClass(String styleClass) {
		getStateHelper().put(TabPropertyKeys.styleClass, styleClass);
	}
}
//J+
