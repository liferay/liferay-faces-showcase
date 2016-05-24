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

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Pattern;


/**
 * @author  Juan Gonzalez
 */
@ManagedBean
@RequestScoped
public class ValidationModelBean {

	@Pattern(regexp = ".+[@].+[.].+")
	private String email;
	private Double doubleNumber;
	private Long longNumber;
	private String otherText;

	public Double getDoubleNumber() {
		return doubleNumber;
	}

	public String getEmail() {
		return email;
	}

	public Long getLongNumber() {
		return longNumber;
	}

	public String getOtherText() {
		return otherText;
	}

	public void setDoubleNumber(Double doubleNumber) {
		this.doubleNumber = doubleNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLongNumber(Long longNumber) {
		this.longNumber = longNumber;
	}

	public void setOtherText(String otherText) {
		this.otherText = otherText;
	}
}
