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
package com.liferay.faces.showcase.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.liferay.faces.showcase.dto.Country;
import com.liferay.faces.showcase.service.CountryService;


/**
 * @author  Vernon Singleton
 */
@ManagedBean
@RequestScoped
public class OutputLinkModelBean {

	@ManagedProperty(value = "#{countryService}")
	private CountryService countryService;

	// Private properties
	private Country country;

	public Country getCountry() {
		return country;
	}

	public CountryService getCountryService() {
		return countryService;
	}

	@PostConstruct
	public void postConstruct() {
		this.country = countryService.getAllCountries().get(0);
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}
}
