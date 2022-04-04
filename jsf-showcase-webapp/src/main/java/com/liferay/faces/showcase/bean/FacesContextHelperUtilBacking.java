/**
 * Copyright (c) 2000-2022 Liferay, Inc. All rights reserved.
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
import javax.faces.bean.ViewScoped;


/**
 * @author  Kyle Stiemann
 */
@ManagedBean
@ViewScoped
public class FacesContextHelperUtilBacking implements Serializable {

	// serialVersionUID
	private static final long serialVersionUID = 5123157520254209271L;

	private String email;

	public void closeDialog() {

		if (email != null) {
			com.liferay.faces.util.context.FacesContextHelperUtil.addScript("$('.modal').modal('hide');");
		}
	}

	public String getEmail() {
		return email;
	}

	public void openDialog() {
		com.liferay.faces.util.context.FacesContextHelperUtil.addScript("$('.modal').modal('show');");
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
