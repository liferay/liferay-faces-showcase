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
package com.liferay.faces.showcase.component.example.internal;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.ProjectStage;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ComponentSystemEventListener;
import javax.faces.event.ListenerFor;
import javax.faces.event.PostAddToViewEvent;
import javax.faces.render.FacesRenderer;

import com.liferay.faces.showcase.component.example.Example;
import com.liferay.faces.util.context.FacesContextHelperUtil;


/**
 * @author  Neil Griffin
 */
//J-
@FacesRenderer(componentFamily = Example.COMPONENT_FAMILY, rendererType = Example.RENDERER_TYPE)
@ListenerFor(systemEventClass = PostAddToViewEvent.class)
@ResourceDependency(library = "showcase", name = "showcase-components.css")
//J+
public class ExampleRenderer extends ExampleRendererBase implements ComponentSystemEventListener {

	@Override
	public void encodeChildren(FacesContext facesContext, UIComponent uiComponent) throws IOException {

		ResponseWriter responseWriter = facesContext.getResponseWriter();
		responseWriter.startElement("label", null);
		responseWriter.writeAttribute("class", "showcase-example-label", null);

		String label = FacesContextHelperUtil.getMessage("example");

		if (label != null) {
			responseWriter.startElement("span", null);
			responseWriter.writeAttribute("class", "showcase-example-number", null);
			responseWriter.write(label);
			responseWriter.endElement("span");
		}

		Example example = (Example) uiComponent;
		int number = example.getNumber();

		if (number > 0) {
			responseWriter.startElement("span", null);
			responseWriter.writeAttribute("class", "showcase-example-number", null);
			responseWriter.write(" ");
			responseWriter.write(Integer.toString(number));
			responseWriter.endElement("span");
		}

		String description = example.getDescription();

		if (description != null) {
			responseWriter.startElement("span", null);
			responseWriter.writeAttribute("class", "showcase-example-description", null);
			responseWriter.write(": ");
			responseWriter.write(description);
			responseWriter.endElement("span");
		}

		responseWriter.endElement("label");

		responseWriter.startElement("div", null);
		responseWriter.writeAttribute("class", "showcase-example-content", null);

		HtmlSelectBooleanCheckbox renderedCheckbox = findRenderedCheckbox(example);

		if (renderedCheckbox != null) {

			responseWriter.startElement("span", null);
			responseWriter.startElement("label", null);
			responseWriter.writeAttribute("class", "component-rendered-label", null);
			renderedCheckbox.encodeAll(facesContext);
			renderedCheckbox.setRendered(false);

			String rendered = FacesContextHelperUtil.getMessage("rendered");
			responseWriter.write(rendered);
			responseWriter.endElement("label");
			responseWriter.endElement("span");
		}

		HtmlSelectBooleanCheckbox requiredCheckbox = findRequiredCheckbox(example);

		if (requiredCheckbox != null) {

			responseWriter.startElement("span", null);
			responseWriter.startElement("label", null);
			responseWriter.writeAttribute("class", "component-required-label", null);
			requiredCheckbox.encodeAll(facesContext);
			requiredCheckbox.setRendered(false);

			String rendered = FacesContextHelperUtil.getMessage("required");
			responseWriter.write(rendered);
			responseWriter.endElement("label");
			responseWriter.endElement("span");
		}

		responseWriter.startElement("div", null);
		responseWriter.writeAttribute("class", "showcase-example-usage", null);
		super.encodeChildren(facesContext, uiComponent);

		if (renderedCheckbox != null) {
			renderedCheckbox.setRendered(true);
		}

		if (requiredCheckbox != null) {
			requiredCheckbox.setRendered(true);
		}

		responseWriter.endElement("div");

		responseWriter.endElement("div");
	}

	@Override
	public void processEvent(ComponentSystemEvent postAddToViewEvent) throws AbortProcessingException {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		Example example = (Example) postAddToViewEvent.getComponent();
		List<UIComponent> exampleChildren = example.getChildren();
		Boolean renderedCheckbox = example.isRenderedCheckbox();

		if ((renderedCheckbox != null) && renderedCheckbox && !facesContext.isProjectStage(ProjectStage.Production)) {

			HtmlSelectBooleanCheckbox htmlSelectBooleanCheckbox = createHtmlBooleanCheckbox(facesContext,
					"#{showcaseModelBean.selectedComponent.rendered}");
			htmlSelectBooleanCheckbox.setId("renderedCheckbox");
			exampleChildren.add(htmlSelectBooleanCheckbox);
		}

		Boolean requiredCheckbox = example.isRequiredCheckbox();

		if ((requiredCheckbox != null) && requiredCheckbox) {

			HtmlSelectBooleanCheckbox htmlSelectBooleanCheckbox = createHtmlBooleanCheckbox(facesContext,
					"#{showcaseModelBean.selectedComponent.required}");
			htmlSelectBooleanCheckbox.setId("requiredCheckbox");
			exampleChildren.add(htmlSelectBooleanCheckbox);
		}
	}

	private HtmlSelectBooleanCheckbox createHtmlBooleanCheckbox(FacesContext facesContext, String elExpression) {

		HtmlSelectBooleanCheckbox htmlSelectBooleanCheckbox = new HtmlSelectBooleanCheckbox();
		htmlSelectBooleanCheckbox.setImmediate(true);

		Application application = facesContext.getApplication();
		ExpressionFactory expressionFactory = application.getExpressionFactory();
		ELContext elContext = facesContext.getELContext();
		ValueExpression valueExpression = expressionFactory.createValueExpression(elContext, elExpression,
				boolean.class);
		htmlSelectBooleanCheckbox.setValueExpression("value", valueExpression);

		AjaxBehavior ajaxBehavior = (AjaxBehavior) application.createBehavior(AjaxBehavior.BEHAVIOR_ID);
		List<String> renderIds = Arrays.asList(new String[] { "@form" });
		ajaxBehavior.setRender(renderIds);
		htmlSelectBooleanCheckbox.addClientBehavior("valueChange", ajaxBehavior);

		return htmlSelectBooleanCheckbox;
	}

	private HtmlSelectBooleanCheckbox findChildCheckbox(Example example, String id) {

		List<UIComponent> children = example.getChildren();

		for (UIComponent child : children) {

			if (id.equals(child.getId())) {
				return (HtmlSelectBooleanCheckbox) child;
			}
		}

		return null;
	}

	private HtmlSelectBooleanCheckbox findRenderedCheckbox(Example example) {
		return findChildCheckbox(example, "renderedCheckbox");
	}

	private HtmlSelectBooleanCheckbox findRequiredCheckbox(Example example) {
		return findChildCheckbox(example, "requiredCheckbox");
	}
}
