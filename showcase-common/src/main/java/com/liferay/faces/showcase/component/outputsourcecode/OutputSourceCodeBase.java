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
package com.liferay.faces.showcase.component.outputsourcecode;
//J-

import javax.annotation.Generated;
import javax.faces.component.html.HtmlOutputText;

import com.liferay.faces.util.component.Styleable;


/**
 * @author	Bruno Basto
 * @author	Kyle Stiemann
 */
@Generated(value = "com.liferay.alloy.tools.builder.FacesBuilder")
public abstract class OutputSourceCodeBase extends HtmlOutputText implements Styleable {

	// Public Constants
	public static final String COMPONENT_TYPE = "com.liferay.faces.showcase.component.outputsourcecode.OutputSourceCode";
	public static final String RENDERER_TYPE = "com.liferay.faces.showcase.component.outputsourcecode.OutputSourceCodeRenderer";

	// Protected Enumerations
	protected enum OutputSourceCodePropertyKeys {
		mode
	}

	public OutputSourceCodeBase() {
		super();
		setRendererType(RENDERER_TYPE);
	}

	/**
	 * <code>mode</code> attribute description:
	 * <br /><br />
	 * The name of the programming language that the editor is editing or displaying.
	 */
	public String getMode() {
		return (String) getStateHelper().eval(OutputSourceCodePropertyKeys.mode, null);
	}

	/**
	 * <code>mode</code> attribute description:
	 * <br /><br />
	 * The name of the programming language that the editor is editing or displaying.
	 */
	public void setMode(String mode) {
		getStateHelper().put(OutputSourceCodePropertyKeys.mode, mode);
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

		return com.liferay.faces.util.component.ComponentUtil.concatCssClasses(styleClass, "showcase-output-source-code", "prettyprint linenums");
	}
}
//J+
