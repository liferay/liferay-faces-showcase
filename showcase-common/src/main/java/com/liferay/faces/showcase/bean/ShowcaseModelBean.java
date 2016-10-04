/**
 * Copyright (c) 2000-2016 Liferay, Inc. All rights reserved.
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

import javax.faces.application.ProjectStage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.liferay.faces.showcase.dto.SelectedComponent;
import com.liferay.faces.showcase.dto.SelectedComponentImpl;
import com.liferay.faces.showcase.dto.ShowcaseComponent;
import com.liferay.faces.util.product.Product;
import com.liferay.faces.util.product.ProductFactory;


/**
 * @author  Neil Griffin
 */
@ManagedBean
@ViewScoped
public class ShowcaseModelBean implements Serializable {

	// Public Constants
	public static final boolean BOOTSTRAP_2;

	static {

		final Product LIFERAY_PORTAL = ProductFactory.getProduct(Product.Name.LIFERAY_PORTAL);
		final Product ALLOY = ProductFactory.getProduct(Product.Name.LIFERAY_FACES_ALLOY);
		final boolean LIFERAY_PORTAL_6_2_DETECTED = LIFERAY_PORTAL.isDetected() &&
			((LIFERAY_PORTAL.getMajorVersion() == 6) && (LIFERAY_PORTAL.getMinorVersion() == 2));
		final boolean ALLOY_2_DETECTED = ALLOY.isDetected() && (ALLOY.getMajorVersion() == 2);
		BOOTSTRAP_2 = (ALLOY_2_DETECTED || LIFERAY_PORTAL_6_2_DETECTED);
	}

	// serialVersionUID
	private static final long serialVersionUID = 3339667513222866249L;

	// Private Constants
	private static final boolean LIFERAY_FACES_BRIDGE_DETECTED = ProductFactory.getProduct(
			Product.Name.LIFERAY_FACES_BRIDGE).isDetected();

	// Injections
	@ManagedProperty(name = "listModelBean", value = "#{listModelBean}")
	private transient ListModelBean listModelBean;

	// Private Data Members
	private String deploymentType;
	private SelectedComponent selectedComponent;
	private String sourceControlURL;
	private ViewParameters viewParameters;

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

	public ProjectStage getProjectStageProduction() {
		return ProjectStage.Production;
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

	public String getSourceControlURL() {

		if (sourceControlURL == null) {

			StringBuilder urlBuilder = new StringBuilder();
			urlBuilder.append("https://github.com/liferay/liferay-faces-");

			String selectedComponentPrefix = selectedComponent.getPrefix();
			String componentRepoSuffix = selectedComponentPrefix;
			String componentShowcaseFolderPrefix = selectedComponentPrefix;
			String componentShowcaseContainer = "webapp";

			if ("c".equals(selectedComponentPrefix) || "f".equals(selectedComponentPrefix) ||
					"h".equals(selectedComponentPrefix) || "util".equals(selectedComponentPrefix) ||
					"ui".equals(selectedComponentPrefix)) {

				componentRepoSuffix = "showcase";
				componentShowcaseFolderPrefix = "jsf";
			}
			else if ("bridge".equals(selectedComponentPrefix) || "portlet".equals(selectedComponentPrefix)) {

				componentRepoSuffix = "bridge-impl";
				componentShowcaseFolderPrefix = "jsf";
				componentShowcaseContainer = "portlet";
			}
			else if ("portal".equals(selectedComponentPrefix)) {
				componentShowcaseContainer = "portlet";
			}

			urlBuilder.append(componentRepoSuffix);
			urlBuilder.append("/edit/master/");

			if (!"showcase".equals(componentRepoSuffix)) {
				urlBuilder.append("demo/");
			}

			urlBuilder.append(componentShowcaseFolderPrefix);
			urlBuilder.append("-showcase-");
			urlBuilder.append(componentShowcaseContainer);
			urlBuilder.append("/src/main/webapp/WEB-INF/component/");
			urlBuilder.append(selectedComponentPrefix);
			urlBuilder.append("/");
			urlBuilder.append(selectedComponent.getLowerCaseName());
			urlBuilder.append("/");
			urlBuilder.append(selectedComponent.getUseCaseName());
			urlBuilder.append("/");
			urlBuilder.append(selectedComponent.getCamelCaseName());
			urlBuilder.append(".xhtml");

			sourceControlURL = urlBuilder.toString();

		}

		return sourceControlURL;
	}

	public ViewParameters getViewParameters() {

		if (viewParameters == null) {
			viewParameters = new ViewParameters();
		}

		return viewParameters;
	}

	public boolean isBootstrap2() {
		return BOOTSTRAP_2;
	}

	public void setListModelBean(ListModelBean listModelBean) {
		this.listModelBean = listModelBean;
	}

	public static class ViewParameters implements Serializable {

		// serialVersionUID
		private static final long serialVersionUID = 1629675419430845173L;

		// Private Data Members
		private String componentPrefix;
		private String componentName;
		private String componentUseCase;

		public String getComponentName() {
			return componentName;
		}

		public String getComponentPrefix() {
			return componentPrefix;
		}

		public String getComponentUseCase() {
			return componentUseCase;
		}

		public boolean isValid() {
			return ((componentPrefix != null) && (componentName != null));
		}

		public void setComponentName(String componentName) {
			this.componentName = componentName;
		}

		public void setComponentPrefix(String componentPrefix) {
			this.componentPrefix = componentPrefix;
		}

		public void setComponentUseCase(String componentUseCase) {
			this.componentUseCase = componentUseCase;
		}
	}
}
