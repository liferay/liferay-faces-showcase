/**
 * Copyright (c) 2000-2019 Liferay, Inc. All rights reserved.
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
package com.liferay.faces.showcase.component.example;
//J-

import javax.annotation.Generated;
import com.liferay.faces.showcase.component.panelgroup.PanelGroupBlockLayout;

import com.liferay.faces.util.component.Styleable;


/**
 * @author	Bruno Basto
 * @author	Kyle Stiemann
 */
@Generated(value = "com.liferay.alloy.tools.builder.FacesBuilder")
public abstract class ExampleBase extends PanelGroupBlockLayout implements Styleable {

	// Public Constants
	public static final String COMPONENT_TYPE = "com.liferay.faces.showcase.component.example.Example";
	public static final String RENDERER_TYPE = "com.liferay.faces.showcase.component.example.ExampleRenderer";

	// Protected Enumerations
	protected enum ExamplePropertyKeys {
		description,
		number,
		renderedCheckbox,
		requiredCheckbox
	}

	public ExampleBase() {
		super();
		setRendererType(RENDERER_TYPE);
	}

	/**
	 * <p><code>description</code> attribute description:</p>
	 *
	 * <div>The description the component.</div>
	 */
	public String getDescription() {
		return (String) getStateHelper().eval(ExamplePropertyKeys.description, null);
	}

	/**
	 * <p><code>description</code> attribute description:</p>
	 *
	 * <div>The description the component.</div>
	 */
	public void setDescription(String description) {
		getStateHelper().put(ExamplePropertyKeys.description, description);
	}

	/**
	 * <p><code>number</code> attribute description:</p>
	 *
	 * <div>The number of the example.</div>
	 */
	public int getNumber() {
		return (Integer) getStateHelper().eval(ExamplePropertyKeys.number, 0);
	}

	/**
	 * <p><code>number</code> attribute description:</p>
	 *
	 * <div>The number of the example.</div>
	 */
	public void setNumber(int number) {
		getStateHelper().put(ExamplePropertyKeys.number, number);
	}

	/**
	 * <p><code>renderedCheckbox</code> attribute description:</p>
	 *
	 * <div>Flag indicating whether or not a "Rendered" checkbox should be included.</div>
	 */
	public boolean isRenderedCheckbox() {
		return (Boolean) getStateHelper().eval(ExamplePropertyKeys.renderedCheckbox, false);
	}

	/**
	 * <p><code>renderedCheckbox</code> attribute description:</p>
	 *
	 * <div>Flag indicating whether or not a "Rendered" checkbox should be included.</div>
	 */
	public void setRenderedCheckbox(boolean renderedCheckbox) {
		getStateHelper().put(ExamplePropertyKeys.renderedCheckbox, renderedCheckbox);
	}

	/**
	 * <p><code>requiredCheckbox</code> attribute description:</p>
	 *
	 * <div>Flag indicating whether or not a "Required" checkbox should be included.</div>
	 */
	public boolean isRequiredCheckbox() {
		return (Boolean) getStateHelper().eval(ExamplePropertyKeys.requiredCheckbox, false);
	}

	/**
	 * <p><code>requiredCheckbox</code> attribute description:</p>
	 *
	 * <div>Flag indicating whether or not a "Required" checkbox should be included.</div>
	 */
	public void setRequiredCheckbox(boolean requiredCheckbox) {
		getStateHelper().put(ExamplePropertyKeys.requiredCheckbox, requiredCheckbox);
	}

	/**
	 * <p><code>styleClass</code> attribute description:</p>
	 *
	 * <div>List of CSS class names (separated by spaces) that are to be rendered within the class attribute.</div>
	 */
	@Override
	public String getStyleClass() {

		// getStateHelper().eval(PropertyKeys.styleClass, null) is called because
		// super.getStyleClass() may return the styleClass name of the super class.
		String styleClass = (String) getStateHelper().eval(PropertyKeys.styleClass, null);

		return com.liferay.faces.util.component.ComponentUtil.concatCssClasses(styleClass, "showcase-example");
	}
}
//J+
