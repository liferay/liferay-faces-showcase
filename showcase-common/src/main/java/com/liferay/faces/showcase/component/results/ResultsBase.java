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
package com.liferay.faces.showcase.component.results;
//J-

import javax.annotation.Generated;
import com.liferay.faces.showcase.component.panelgroup.PanelGroupBlockLayout;

import com.liferay.faces.util.component.Styleable;


/**
 * @author	Bruno Basto
 * @author	Kyle Stiemann
 */
@Generated(value = "com.liferay.alloy.tools.builder.FacesBuilder")
public abstract class ResultsBase extends PanelGroupBlockLayout implements Styleable {

	// Public Constants
	public static final String COMPONENT_TYPE = "com.liferay.faces.showcase.component.results.Results";
	public static final String RENDERER_TYPE = "com.liferay.faces.showcase.component.results.ResultsRenderer";

	// Protected Enumerations
	protected enum ResultsPropertyKeys {
		label
	}

	public ResultsBase() {
		super();
		setRendererType(RENDERER_TYPE);
	}

	/**
	 * <code>label</code> attribute description:
	 * <br /><br />
	 * The text value for the rendered &lt;label&gt; element.
	 */
	public String getLabel() {
		return (String) getStateHelper().eval(ResultsPropertyKeys.label, null);
	}

	/**
	 * <code>label</code> attribute description:
	 * <br /><br />
	 * The text value for the rendered &lt;label&gt; element.
	 */
	public void setLabel(String label) {
		getStateHelper().put(ResultsPropertyKeys.label, label);
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

		return com.liferay.faces.util.component.ComponentUtil.concatCssClasses(styleClass, "showcase-results");
	}
}
//J+
