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
public class Customer implements Serializable {

	// serialVersionUID
	private static final long serialVersionUID = 6155011527137371447L;

	// Private Data Members
	private long customerId;
	private Country country;
	private Date dateOfBirth;
	private String firstName;
	private String lastName;

	public Customer(long customerId, Country country, String firstName, String lastName, Date dateOfBirth) {
		this.customerId = customerId;
		this.country = country;
		this.dateOfBirth = dateOfBirth;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Country getCountry() {
		return country;
	}

	public long getCustomerId() {
		return customerId;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
