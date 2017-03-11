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
package com.liferay.faces.showcase.component.accordion;
//J-

import javax.annotation.Generated;
import javax.faces.component.UIData;

import com.liferay.faces.util.component.Styleable;


/**
 * @author	Bruno Basto
 * @author	Kyle Stiemann
 */
@Generated(value = "com.liferay.alloy.tools.builder.FacesBuilder")
public abstract class AccordionBase extends UIData implements Styleable {

	// Public Constants
	public static final String COMPONENT_TYPE = "com.liferay.faces.showcase.component.accordion.Accordion";
	public static final String RENDERER_TYPE = "com.liferay.faces.showcase.component.accordion.AccordionRenderer";

	// Protected Enumerations
	protected enum AccordionPropertyKeys {
		selectedIndex,
		style,
		styleClass
	}

	public AccordionBase() {
		super();
		setRendererType(RENDERER_TYPE);
	}

	/**
	 * <code>selectedIndex</code> attribute description:
	 * <br /><br />
	 * Integer representing the zero-based index representing the selected tab.
	 */
	public Integer getSelectedIndex() {
		return (Integer) getStateHelper().eval(AccordionPropertyKeys.selectedIndex, null);
	}

	/**
	 * <code>selectedIndex</code> attribute description:
	 * <br /><br />
	 * Integer representing the zero-based index representing the selected tab.
	 */
	public void setSelectedIndex(Integer selectedIndex) {
		getStateHelper().put(AccordionPropertyKeys.selectedIndex, selectedIndex);
	}

	/**
	 * <code>style</code> attribute description:
	 * <br /><br />
	 * HTML passthrough attribute specifying the css style of the element.
	 */
	@Override
	public String getStyle() {
		return (String) getStateHelper().eval(AccordionPropertyKeys.style, null);
	}

	/**
	 * <code>style</code> attribute description:
	 * <br /><br />
	 * HTML passthrough attribute specifying the css style of the element.
	 */
	@Override
	public void setStyle(String style) {
		getStateHelper().put(AccordionPropertyKeys.style, style);
	}

	/**
	 * <code>styleClass</code> attribute description:
	 * <br /><br />
	 * List of CSS class names (separated by spaces) that are to be rendered within the class attribute.
	 */
	@Override
	public String getStyleClass() {

		// getStateHelper().eval(AccordionPropertyKeys.styleClass, null) is called because
		// super.getStyleClass() may return the styleClass name of the super class.
		String styleClass = (String) getStateHelper().eval(AccordionPropertyKeys.styleClass, null);

		return com.liferay.faces.util.component.ComponentUtil.concatCssClasses(styleClass, "showcase-accordion");
	}

	/**
	 * <code>styleClass</code> attribute description:
	 * <br /><br />
	 * List of CSS class names (separated by spaces) that are to be rendered within the class attribute.
	 */
	@Override
	public void setStyleClass(String styleClass) {
		getStateHelper().put(AccordionPropertyKeys.styleClass, styleClass);
	}
}
//J+
