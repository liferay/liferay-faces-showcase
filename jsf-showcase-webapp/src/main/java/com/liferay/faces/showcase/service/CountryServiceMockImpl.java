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
package com.liferay.faces.showcase.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.liferay.faces.showcase.dto.Country;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
@ManagedBean(name = "countryService")
public class CountryServiceMockImpl implements CountryService, Serializable {

	// serialVersionUID
	private static final long serialVersionUID = 4289537697479875863L;

	// Private Data Members
	private List<Country> countryList;
	private Map<Long, Country> countryMap;

	@Override
	public List<Country> getAllCountries() {
		return countryList;
	}

	@Override
	public Country getCountryByCode(String countryCode) {

		Country country = null;

		for (Country curCountry : countryList) {

			if (curCountry.getCountryCode().equals(countryCode)) {
				country = curCountry;

				break;
			}
		}

		return country;
	}

	@Override
	public Map<Long, Country> getCountryMap() {
		return countryMap;
	}

	@PostConstruct
	public void postConstruct() {
		countryMap = new HashMap<Long, Country>();

		Country country = new Country(1, "CN", "China");
		countryMap.put(country.getCountryId(), country);
		country = new Country(2, "CH", "Switzerland");
		countryMap.put(country.getCountryId(), country);
		country = new Country(3, "US", "United States");
		countryMap.put(country.getCountryId(), country);
		country = new Country(4, "UK", "United Kingdom");
		countryMap.put(country.getCountryId(), country);
		country = new Country(5, "VN", "Vietnam");
		countryMap.put(country.getCountryId(), country);
		countryList = new ArrayList<Country>(countryMap.values());
	}
}
