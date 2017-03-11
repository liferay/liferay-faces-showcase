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
package com.liferay.faces.showcase.component.tabview;
//J-

import javax.annotation.Generated;
import javax.faces.component.UIData;

import com.liferay.faces.util.component.Styleable;


/**
 * @author	Bruno Basto
 * @author	Kyle Stiemann
 */
@Generated(value = "com.liferay.alloy.tools.builder.FacesBuilder")
public abstract class TabViewBase extends UIData implements Styleable {

	// Public Constants
	public static final String COMPONENT_TYPE = "com.liferay.faces.showcase.component.tabview.TabView";
	public static final String RENDERER_TYPE = "com.liferay.faces.showcase.component.tabview.TabViewRenderer";

	// Protected Enumerations
	protected enum TabViewPropertyKeys {
		height,
		selectedIndex,
		style,
		styleClass
	}

	public TabViewBase() {
		super();
		setRendererType(RENDERER_TYPE);
	}

	/**
	 * <code>height</code> attribute description:
	 * <br /><br />
	 * The the height (in pixels) of the rendered <code>&lt;div&gt;</code>.
	 */
	public String getHeight() {
		return (String) getStateHelper().eval(TabViewPropertyKeys.height, null);
	}

	/**
	 * <code>height</code> attribute description:
	 * <br /><br />
	 * The the height (in pixels) of the rendered <code>&lt;div&gt;</code>.
	 */
	public void setHeight(String height) {
		getStateHelper().put(TabViewPropertyKeys.height, height);
	}

	/**
	 * <code>selectedIndex</code> attribute description:
	 * <br /><br />
	 * Integer representing the zero-based index representing the selected tab.
	 */
	public Integer getSelectedIndex() {
		return (Integer) getStateHelper().eval(TabViewPropertyKeys.selectedIndex, null);
	}

	/**
	 * <code>selectedIndex</code> attribute description:
	 * <br /><br />
	 * Integer representing the zero-based index representing the selected tab.
	 */
	public void setSelectedIndex(Integer selectedIndex) {
		getStateHelper().put(TabViewPropertyKeys.selectedIndex, selectedIndex);
	}

	/**
	 * <code>style</code> attribute description:
	 * <br /><br />
	 * HTML passthrough attribute specifying the css style of the element.
	 */
	@Override
	public String getStyle() {
		return (String) getStateHelper().eval(TabViewPropertyKeys.style, null);
	}

	/**
	 * <code>style</code> attribute description:
	 * <br /><br />
	 * HTML passthrough attribute specifying the css style of the element.
	 */
	@Override
	public void setStyle(String style) {
		getStateHelper().put(TabViewPropertyKeys.style, style);
	}

	/**
	 * <code>styleClass</code> attribute description:
	 * <br /><br />
	 * List of CSS class names (separated by spaces) that are to be rendered within the class attribute.
	 */
	@Override
	public String getStyleClass() {

		// getStateHelper().eval(TabViewPropertyKeys.styleClass, null) is called because
		// super.getStyleClass() may return the styleClass name of the super class.
		String styleClass = (String) getStateHelper().eval(TabViewPropertyKeys.styleClass, null);

		return com.liferay.faces.util.component.ComponentUtil.concatCssClasses(styleClass, "showcase-tab-view");
	}

	/**
	 * <code>styleClass</code> attribute description:
	 * <br /><br />
	 * List of CSS class names (separated by spaces) that are to be rendered within the class attribute.
	 */
	@Override
	public void setStyleClass(String styleClass) {
		getStateHelper().put(TabViewPropertyKeys.styleClass, styleClass);
	}
}
//J+
