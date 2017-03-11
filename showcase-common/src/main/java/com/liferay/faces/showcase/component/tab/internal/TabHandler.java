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
package com.liferay.faces.showcase.component.tab.internal;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.component.UIPanel;
import javax.faces.component.UniqueIdVendor;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.FaceletContext;


/**
 * This class provides alloy:tab with the ability to implicitly wrap the tab's children inside of a {@link UIPanel}
 * facet. This is necessary because {@link UIData#visitTree(javax.faces.component.visit.VisitContext,
 * javax.faces.component.visit.VisitCallback)} will visit facets but not children when it is not iterating over data
 * (i.e. when the value and var attributes are unused).
 *
 * @author  Neil Griffin
 */
public class TabHandler extends ComponentHandler {

	// Public Constants
	public static final String IMPLICIT_FACET_NAME = "implicit";

	// Private Constants
	private static final String MOJARRA_IMPLICIT_PANEL = "com.sun.faces.facelets.IMPLICIT_PANEL";
	private static final String MYFACES_ADDED_BY_HANDLER = "oam.vf.addedByHandler";
	private static final String MYFACES_CREATED_UIPANEL = "oam.vf.createdUIPanel";

	public TabHandler(ComponentConfig config) {
		super(config);
	}

	@Override
	public void onComponentPopulated(FaceletContext faceletContext, UIComponent uiComponent, UIComponent parent) {

		super.onComponentPopulated(faceletContext, uiComponent, parent);

		Map<String, Object> attributes = parent.getAttributes();
		Object value = attributes.get("value");
		Object var = attributes.get("var");

		// If not iterating over a DataModel, then
		if ((value == null) || (var == null)) {

			// If an implicit panel group doesn't yet exist, then create one.
			FacesContext facesContext = faceletContext.getFacesContext();
			UIComponent implicitPanelGroup = uiComponent.getFacet(IMPLICIT_FACET_NAME);

			if (implicitPanelGroup == null) {
				implicitPanelGroup = createImplicitPanelGroup(facesContext, uiComponent.getId());
				uiComponent.getFacets().put(IMPLICIT_FACET_NAME, implicitPanelGroup);
			}

			// Re-parent each child of the tab by adding it as a child of the implicit panel group.
			List<UIComponent> implicitPanelGroupChildren = implicitPanelGroup.getChildren();
			Iterator<UIComponent> childIterator = uiComponent.getChildren().iterator();

			while (childIterator.hasNext()) {
				UIComponent child = childIterator.next();
				childIterator.remove();
				implicitPanelGroupChildren.add(child);
			}
		}
	}

	protected UIPanel createImplicitPanelGroup(FacesContext facesContext, String parentId) {

		Application application = facesContext.getApplication();
		UIPanel implicitPanelGroup = (UIPanel) application.createComponent(UIPanel.COMPONENT_TYPE);
		Map<String, Object> implicitPanelGroupAttributes = implicitPanelGroup.getAttributes();
		implicitPanelGroupAttributes.put(MOJARRA_IMPLICIT_PANEL, Boolean.TRUE);
		implicitPanelGroupAttributes.put(MYFACES_CREATED_UIPANEL, Boolean.TRUE);
		implicitPanelGroupAttributes.put(MYFACES_ADDED_BY_HANDLER, Boolean.TRUE);

		UniqueIdVendor uniqueIdVendor = facesContext.getViewRoot();
		String seed = parentId.concat("_").concat(IMPLICIT_FACET_NAME);
		String implicitPanelGroupId = uniqueIdVendor.createUniqueId(facesContext, seed);
		implicitPanelGroup.setId(implicitPanelGroupId);

		return implicitPanelGroup;
	}
}
