/**
 * Copyright (c) 2000-2015 Liferay, Inc. All rights reserved.
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
package com.liferay.faces.showcase.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.liferay.faces.showcase.dto.SelectedComponent;
import com.liferay.faces.showcase.dto.SelectedComponentImpl;
import com.liferay.faces.showcase.dto.ShowcaseComponent;
import com.liferay.faces.util.product.Product;
import com.liferay.faces.util.product.ProductConstants;
import com.liferay.faces.util.product.ProductMap;


/**
 * @author  Neil Griffin
 */
@ManagedBean
@ViewScoped
public class ShowcaseModelBean implements Serializable {

	// Public Constants
	public static final boolean BOOTSTRAP_2;

	static {

		final Product LIFERAY_PORTAL = ProductMap.getInstance().get(ProductConstants.LIFERAY_PORTAL);
		final Product ALLOY = ProductMap.getInstance().get(ProductConstants.LIFERAY_FACES_ALLOY);
		final boolean LIFERAY_PORTAL_6_2_DETECTED = LIFERAY_PORTAL.isDetected() &&
			((LIFERAY_PORTAL.getMajorVersion() == 6) && (LIFERAY_PORTAL.getMinorVersion() == 2));
		final boolean ALLOY_2_DETECTED = ALLOY.isDetected() && (ALLOY.getMajorVersion() == 2);
		BOOTSTRAP_2 = (ALLOY_2_DETECTED || LIFERAY_PORTAL_6_2_DETECTED);
	}

	// serialVersionUID
	private static final long serialVersionUID = 3339667513222866249L;

	// Private Constants
	private static final boolean LIFERAY_FACES_BRIDGE_DETECTED = ProductMap.getInstance().get(
			ProductConstants.LIFERAY_FACES_BRIDGE).isDetected();

	// Injections
	@ManagedProperty(name = "listModelBean", value = "#{listModelBean}")
	private transient ListModelBean listModelBean;

	// Private Data Members
	private String deploymentType;
	private SelectedComponent selectedComponent;
	private ViewParameters viewParameters;

	public boolean isBootstrap2() {
		return BOOTSTRAP_2;
	}

	public String getDeploymentType() {

		if (deploymentType == null) {

			if (LIFERAY_FACES_BRIDGE_DETECTED) {
				deploymentType = "portlet";
			}
			else {
				deploymentType = "webapp";
			}
		}

		return deploymentType;
	}

	public void setListModelBean(ListModelBean listModelBean) {
		this.listModelBean = listModelBean;
	}

	public SelectedComponent getSelectedComponent() {

		if (selectedComponent == null) {

			ViewParameters viewParameters = getViewParameters();

			if (viewParameters.isValid()) {

				ShowcaseComponent showcaseComponent = listModelBean.findShowcaseComponent(
						viewParameters.getComponentPrefix(), viewParameters.getComponentName());

				selectedComponent = new SelectedComponentImpl(showcaseComponent, viewParameters.getComponentUseCase());
			}
		}

		return selectedComponent;
	}

	public ViewParameters getViewParameters() {

		if (viewParameters == null) {
			viewParameters = new ViewParameters();
		}

		return viewParameters;
	}

	public class ViewParameters implements Serializable {

		// serialVersionUID
		private static final long serialVersionUID = 1629675419430845173L;

		// Private Data Members
		private String componentPrefix;
		private String componentName;
		private String componentUseCase;

		public String getComponentName() {
			return componentName;
		}

		public void setComponentName(String componentName) {
			this.componentName = componentName;
		}

		public String getComponentPrefix() {
			return componentPrefix;
		}

		public void setComponentPrefix(String componentPrefix) {
			this.componentPrefix = componentPrefix;
		}

		public String getComponentUseCase() {
			return componentUseCase;
		}

		public void setComponentUseCase(String componentUseCase) {
			this.componentUseCase = componentUseCase;
		}

		public boolean isValid() {
			return ((componentPrefix != null) && (componentName != null));
		}
	}
}
