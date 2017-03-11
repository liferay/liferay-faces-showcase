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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIMessage;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;

import com.liferay.faces.util.component.ComponentUtil;


/**
 * @author  Kyle Stiemann
 */
@FacesComponent(value = Field.COMPONENT_TYPE)
public class Field extends FieldBase {

	// Private Constants
	private static final String FORM_GROUP = "form-group";
	private static final String HAS_ERROR = "has-error";
	private static final String HAS_WARNING = "has-warning";
	private static final String HAS_SUCCESS = "has-success";

	@Override
	public String getStyleClass() {

		String controlGroupCssClass = FORM_GROUP;

		boolean editableValueHoldersExist = false;
		boolean editableValueHoldersValid = false;

		FacesMessage.Severity severity = null;

		List<EditableValueHolder> editableValueHolders = getEditableValueHoldersRecurse(this);

		if (editableValueHolders != null) {

			editableValueHoldersExist = true;

			for (EditableValueHolder editableValueHolder : editableValueHolders) {

				if (editableValueHolder.isValid()) {

					if (editableValueHolder.isRequired()) {

						if (editableValueHolder.isLocalValueSet()) {
							editableValueHoldersValid = true;
						}
						else {

							if (editableValueHolder instanceof UIInput) {
								UIInput uiInput = (UIInput) editableValueHolder;

								if (!UIInput.isEmpty(uiInput.getValue())) {
									editableValueHoldersValid = true;
								}
							}
						}
					}
					else {
						editableValueHoldersValid = true;
					}
				}
				else {
					editableValueHoldersValid = false;
					severity = FacesMessage.SEVERITY_FATAL;

					break;
				}
			}
		}

		FacesContext facesContext = FacesContext.getCurrentInstance();

		if (severity == null) {
			severity = getHighestMessageSeverityRecurse(facesContext, this, null);
		}

		if (severity == null) {

			PartialViewContext partialViewContext = facesContext.getPartialViewContext();

			if (editableValueHoldersExist && editableValueHoldersValid && partialViewContext.isAjaxRequest()) {
				controlGroupCssClass = controlGroupCssClass + " " + HAS_SUCCESS;
			}
		}
		else {

			if ((severity == FacesMessage.SEVERITY_FATAL) || (severity == FacesMessage.SEVERITY_ERROR)) {
				controlGroupCssClass = controlGroupCssClass + " " + HAS_ERROR;
			}
			else if (severity == FacesMessage.SEVERITY_WARN) {
				controlGroupCssClass = controlGroupCssClass + " " + HAS_WARNING;
			}
			else if (severity == FacesMessage.SEVERITY_INFO) {
				// no-op Bootstrap 3 does not contain a has-info CSS class
			}
		}

		if (hasSelectBooleanCheckboxChild()) {
			controlGroupCssClass = controlGroupCssClass + " checkbox";
		}

		// getStateHelper().eval(PropertyKeys.styleClass, null) is called because super.getStyleClass() may return the
		// STYLE_CLASS_NAME of the super class.
		String styleClass = (String) getStateHelper().eval(PropertyKeys.styleClass, null);

		return ComponentUtil.concatCssClasses(styleClass, "showcase-field", controlGroupCssClass);
	}

	private List<EditableValueHolder> getEditableValueHoldersRecurse(UIComponent uiComponent) {

		List<EditableValueHolder> editableValueHolders = null;

		if (uiComponent instanceof EditableValueHolder) {
			editableValueHolders = new ArrayList<EditableValueHolder>();
			editableValueHolders.add((EditableValueHolder) uiComponent);
		}
		else if (uiComponent instanceof UIMessage) {
			UIMessage uiMessage = (UIMessage) uiComponent;
			String forId = uiMessage.getFor();

			if (forId != null) {
				UIComponent forComponent = uiComponent.findComponent(forId);

				if ((forComponent != null) && (forComponent instanceof EditableValueHolder)) {
					editableValueHolders = new ArrayList<EditableValueHolder>();
					editableValueHolders.add((EditableValueHolder) forComponent);
				}
			}
		}
		else {
			List<UIComponent> children = uiComponent.getChildren();

			for (UIComponent child : children) {

				List<EditableValueHolder> childEditableValueHolders = getEditableValueHoldersRecurse(child);

				if (childEditableValueHolders != null) {

					if (editableValueHolders == null) {
						editableValueHolders = childEditableValueHolders;
					}
					else {
						editableValueHolders.addAll(childEditableValueHolders);
					}
				}
			}
		}

		return editableValueHolders;
	}

	private FacesMessage.Severity getHighestMessageSeverityRecurse(FacesContext facesContext, UIComponent uiComponent,
		FacesMessage.Severity severity) {

		List<UIComponent> children = uiComponent.getChildren();

		if (children != null) {

			// For each child component:
			for (UIComponent child : children) {

				// Otherwise, determine the highest severity of the FacesMessages associated with the current child
				// component.
				Iterator<FacesMessage> messages = facesContext.getMessages(child.getClientId());

				while (messages.hasNext()) {

					FacesMessage facesMessage = messages.next();
					FacesMessage.Severity currentSeverity = facesMessage.getSeverity();

					if ((currentSeverity == FacesMessage.SEVERITY_ERROR) ||
							(currentSeverity == FacesMessage.SEVERITY_FATAL)) {

						severity = FacesMessage.SEVERITY_FATAL;

						break;
					}
					else if ((severity == null) || (severity.getOrdinal() < currentSeverity.getOrdinal())) {
						severity = currentSeverity;
					}
				}

				if ((severity != null) && (severity == FacesMessage.SEVERITY_FATAL)) {
					break;
				}
				else {
					severity = getHighestMessageSeverityRecurse(facesContext, child, severity);
				}
			}
		}

		return severity;
	}

	private boolean hasSelectBooleanCheckboxChild() {

		boolean hasSelectBooleanCheckboxChild = hasSelectBooleanCheckboxChild(this);

		if (!hasSelectBooleanCheckboxChild) {

			Map<String, UIComponent> facets = getFacets();
			Set<Map.Entry<String, UIComponent>> entrySet = facets.entrySet();

			for (Map.Entry<String, UIComponent> facetEntry : entrySet) {

				UIComponent uiComponent = facetEntry.getValue();

				if (uiComponent instanceof HtmlSelectBooleanCheckbox) {

					hasSelectBooleanCheckboxChild = true;

					break;
				}
				else if (uiComponent.getChildCount() > 0) {

					hasSelectBooleanCheckboxChild = hasSelectBooleanCheckboxChild(uiComponent);

					break;
				}
			}
		}

		return hasSelectBooleanCheckboxChild;
	}

	private boolean hasSelectBooleanCheckboxChild(UIComponent uiComponent) {

		boolean hasSelectBooleanCheckboxChild = false;
		List<UIComponent> children = uiComponent.getChildren();

		for (UIComponent child : children) {

			if (child instanceof HtmlSelectBooleanCheckbox) {

				hasSelectBooleanCheckboxChild = true;

				break;
			}
		}

		return hasSelectBooleanCheckboxChild;
	}
}
