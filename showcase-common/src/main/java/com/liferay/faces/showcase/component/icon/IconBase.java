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
package com.liferay.faces.showcase.component.icon;
//J-

import javax.annotation.Generated;
import javax.faces.component.UIComponentBase;

import com.liferay.faces.util.component.Styleable;


/**
 * @author	Bruno Basto
 * @author	Kyle Stiemann
 */
@Generated(value = "com.liferay.alloy.tools.builder.FacesBuilder")
public abstract class IconBase extends UIComponentBase implements Styleable {

	// Public Constants
	public static final String COMPONENT_FAMILY = "com.liferay.faces.showcase.component.icon";
	public static final String COMPONENT_TYPE = "com.liferay.faces.showcase.component.icon.Icon";
	public static final String RENDERER_TYPE = "com.liferay.faces.showcase.component.icon.IconRenderer";

	// Protected Enumerations
	protected enum IconPropertyKeys {
		color,
		name,
		style,
		styleClass
	}

	public IconBase() {
		super();
		setRendererType(RENDERER_TYPE);
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	/**
	 * <code>color</code> attribute description:
	 * <br /><br />
	 * The color of the icon which this component will render. Valid values include black (the default) and white.
	 */
	public String getColor() {
		return (String) getStateHelper().eval(IconPropertyKeys.color, null);
	}

	/**
	 * <code>color</code> attribute description:
	 * <br /><br />
	 * The color of the icon which this component will render. Valid values include black (the default) and white.
	 */
	public void setColor(String color) {
		getStateHelper().put(IconPropertyKeys.color, color);
	}

	/**
	 * <code>name</code> attribute description:
	 * <br /><br />
	 * The icon which this component will render. A list of valid icons can be found at <a href="http://getbootstrap.com/components/#glyphicons" target="_blank">Bootstrap Base CSS</a>. <b>Note:</b> icon names must be specified without the <code>glyphicon glyphicon-</code> prefix.
	 */
	public String getName() {
		return (String) getStateHelper().eval(IconPropertyKeys.name, null);
	}

	/**
	 * <code>name</code> attribute description:
	 * <br /><br />
	 * The icon which this component will render. A list of valid icons can be found at <a href="http://getbootstrap.com/components/#glyphicons" target="_blank">Bootstrap Base CSS</a>. <b>Note:</b> icon names must be specified without the <code>glyphicon glyphicon-</code> prefix.
	 */
	public void setName(String name) {
		getStateHelper().put(IconPropertyKeys.name, name);
	}

	/**
	 * <code>style</code> attribute description:
	 * <br /><br />
	 * HTML passthrough attribute specifying the css style of the element.
	 */
	@Override
	public String getStyle() {
		return (String) getStateHelper().eval(IconPropertyKeys.style, null);
	}

	/**
	 * <code>style</code> attribute description:
	 * <br /><br />
	 * HTML passthrough attribute specifying the css style of the element.
	 */
	@Override
	public void setStyle(String style) {
		getStateHelper().put(IconPropertyKeys.style, style);
	}

	/**
	 * <code>styleClass</code> attribute description:
	 * <br /><br />
	 * List of CSS class names (separated by spaces) that are to be rendered within the class attribute.
	 */
	@Override
	public String getStyleClass() {

		// getStateHelper().eval(IconPropertyKeys.styleClass, null) is called because
		// super.getStyleClass() may return the styleClass name of the super class.
		String styleClass = (String) getStateHelper().eval(IconPropertyKeys.styleClass, null);

		return com.liferay.faces.util.component.ComponentUtil.concatCssClasses(styleClass, "showcase-icon");
	}

	/**
	 * <code>styleClass</code> attribute description:
	 * <br /><br />
	 * List of CSS class names (separated by spaces) that are to be rendered within the class attribute.
	 */
	@Override
	public void setStyleClass(String styleClass) {
		getStateHelper().put(IconPropertyKeys.styleClass, styleClass);
	}
}
//J+
