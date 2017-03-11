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
package com.liferay.faces.showcase.dto;

import java.io.Serializable;
import java.util.Date;


/**
 * @author  Neil Griffin
 */
public class Applicant implements Serializable {

	private static final long serialVersionUID = 4661552363081858711L;

	private String city;
	private String comments;
	private Date dateOfBirth;
	private String emailAddress;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String postalCode;
	private Long provinceId;

	public String getCity() {
		return city;
	}

	public String getComments() {
		return comments;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
}
