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
package com.liferay.faces.showcase.util;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import com.liferay.faces.showcase.bean.ShowcaseModelBean;
import com.liferay.faces.showcase.bean.ShowcaseModelBean.ViewParameters;


/**
 * @author  Neil Griffin
 */
public class ViewParamUtil {

	public static final String getUsage() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		Application application = facesContext.getApplication();
		ELResolver elResolver = application.getELResolver();
		ELContext elContext = facesContext.getELContext();
		ShowcaseModelBean showcaseModelBean = (ShowcaseModelBean) elResolver.getValue(elContext, null,
				"showcaseModelBean");
		ViewParameters viewParameters = showcaseModelBean.getViewParameters();
		String usage = viewParameters.getComponentUseCase();

		return usage;
	}
}
