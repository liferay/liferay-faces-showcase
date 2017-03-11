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
package com.liferay.faces.showcase.component.field;
//J-

import javax.annotation.Generated;
import com.liferay.faces.showcase.component.panelgroup.PanelGroupBlockLayout;

import com.liferay.faces.util.component.Styleable;


/**
 * @author	Bruno Basto
 * @author	Kyle Stiemann
 */
@Generated(value = "com.liferay.alloy.tools.builder.FacesBuilder")
public abstract class FieldBase extends PanelGroupBlockLayout implements Styleable {

	// Public Constants
	public static final String COMPONENT_TYPE = "com.liferay.faces.showcase.component.field.Field";
	public static final String RENDERER_TYPE = "com.liferay.faces.showcase.component.field.FieldRenderer";

	// Protected Enumerations
	protected enum FieldPropertyKeys {
		label,
		labelFirst
	}

	public FieldBase() {
		super();
		setRendererType(RENDERER_TYPE);
	}

	/**
	 * <code>label</code> attribute description:
	 * <br /><br />
	 * The text value for the rendered &lt;label&gt; element.
	 */
	public String getLabel() {
		return (String) getStateHelper().eval(FieldPropertyKeys.label, null);
	}

	/**
	 * <code>label</code> attribute description:
	 * <br /><br />
	 * The text value for the rendered &lt;label&gt; element.
	 */
	public void setLabel(String label) {
		getStateHelper().put(FieldPropertyKeys.label, label);
	}

	/**
	 * <code>labelFirst</code> attribute description:
	 * <br /><br />
	 * When this flag is true, the label will be rendered before the rendered children.
	 */
	public boolean isLabelFirst() {
		return (Boolean) getStateHelper().eval(FieldPropertyKeys.labelFirst, true);
	}

	/**
	 * <code>labelFirst</code> attribute description:
	 * <br /><br />
	 * When this flag is true, the label will be rendered before the rendered children.
	 */
	public void setLabelFirst(boolean labelFirst) {
		getStateHelper().put(FieldPropertyKeys.labelFirst, labelFirst);
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

		return com.liferay.faces.util.component.ComponentUtil.concatCssClasses(styleClass, "showcase-field");
	}
}
//J+
