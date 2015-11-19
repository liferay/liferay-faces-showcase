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
package com.liferay.faces.showcase.component.panel;
//J-

import javax.annotation.Generated;
import com.liferay.faces.showcase.component.panelgroup.PanelGroupBlockLayout;

import com.liferay.faces.util.component.Styleable;


/**
 * @author	Bruno Basto
 * @author	Kyle Stiemann
 */
@Generated(value = "com.liferay.alloy.tools.builder.FacesBuilder")
public abstract class PanelBase extends PanelGroupBlockLayout implements Styleable {

	// Public Constants
	public static final String COMPONENT_TYPE = "com.liferay.faces.showcase.component.panel.Panel";
	public static final String RENDERER_TYPE = "com.liferay.faces.showcase.component.panel.PanelRenderer";

	// Protected Enumerations
	protected enum PanelPropertyKeys {
		footerText,
		headerText
	}

	public PanelBase() {
		super();
		setRendererType(RENDERER_TYPE);
	}

	/**
	 * <code>footerText</code> attribute description:
	 * <br /><br />
	 * The text to place in the footer of the component.
	 */
	public String getFooterText() {
		return (String) getStateHelper().eval(PanelPropertyKeys.footerText, null);
	}

	/**
	 * <code>footerText</code> attribute description:
	 * <br /><br />
	 * The text to place in the footer of the component.
	 */
	public void setFooterText(String footerText) {
		getStateHelper().put(PanelPropertyKeys.footerText, footerText);
	}

	/**
	 * <code>headerText</code> attribute description:
	 * <br /><br />
	 * The text to place in the header of the component.
	 */
	public String getHeaderText() {
		return (String) getStateHelper().eval(PanelPropertyKeys.headerText, null);
	}

	/**
	 * <code>headerText</code> attribute description:
	 * <br /><br />
	 * The text to place in the header of the component.
	 */
	public void setHeaderText(String headerText) {
		getStateHelper().put(PanelPropertyKeys.headerText, headerText);
	}

	/**
	 * <code>styleClass</code> attribute description:
	 * <br /><br />
	 * List of CSS class names (separated by spaces) that are to be rendered within the class attribute.
	 */
	@Override
	public String getStyleClass() {

		// getStateHelper().eval(PropertyKeys.styleClass, null) is called because
		// super.getStyleClass() may return the styleClass name of the super class.
		String styleClass = (String) getStateHelper().eval(PropertyKeys.styleClass, null);

		return com.liferay.faces.util.component.ComponentUtil.concatCssClasses(styleClass, "showcase-panel", "showcase-panel-default");
	}
}
//J+
