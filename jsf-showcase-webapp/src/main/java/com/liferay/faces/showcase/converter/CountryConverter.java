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
package com.liferay.faces.showcase.converter;

import java.util.Map;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.liferay.faces.showcase.dto.Country;
import com.liferay.faces.showcase.service.CountryService;


/**
 * @author  Neil Griffin
 * @author  Juan Gonzalez
 */
@FacesConverter(value = "com.liferay.faces.showcase.converter.CountryConverter")
public class CountryConverter implements Converter {

	// Private Data Members
	private static Map<Long, Country> countryMap;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		Object countryObject = null;

		if ((value != null) && !"".equals(value)) {
			Long countryId = Long.parseLong(value);

			countryObject = getCountryMap(facesContext).get(countryId);
		}

		return countryObject;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		String strValue = "";

		if (value != null) {
			Country country = (Country) value;
			strValue = Long.toString(country.getCountryId());
		}

		return strValue;
	}

	protected Map<Long, Country> getCountryMap(FacesContext facesContext) {

		if (countryMap == null) {

			synchronized (this) {

				ELResolver elResolver = facesContext.getApplication().getELResolver();
				ELContext elContext = facesContext.getELContext();
				CountryService countryService = (CountryService) elResolver.getValue(elContext, null, "countryService");
				countryMap = countryService.getCountryMap();
			}
		}

		return countryMap;
	}
}
