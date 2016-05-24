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

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.liferay.faces.showcase.dto.LiferayBenefit;
import com.liferay.faces.showcase.service.LiferayBenefitService;


/**
 * @author  Vernon Singleton
 */
@ManagedBean
@RequestScoped
public class SelectManyModelBean {

	private List<Long> favoriteIds;
	private List<Long> benefitIds = Arrays.asList(2L, 4L);
	private List<Date> dates;
	private String phase;

	@ManagedProperty(name = "liferayBenefitService", value = "#{liferayBenefitService}")
	private LiferayBenefitService liferayBenefitService;

	public List<Long> getBenefitIds() {
		return benefitIds;
	}

	public List<Date> getDates() {
		return dates;
	}

	public List<Long> getFavoriteIds() {
		return favoriteIds;
	}

	public List<LiferayBenefit> getLiferayBenefits() {
		return liferayBenefitService.getLiferayBenefits();
	}

	public String getPhase() {
		return phase;
	}

	public void setBenefitIds(List<Long> benefitIds) {
		this.benefitIds = benefitIds;
	}

	public void setDates(List<Date> dates) {
		this.dates = dates;
	}

	public void setFavoriteIds(List<Long> favoriteIds) {
		this.favoriteIds = favoriteIds;
	}

	public void setLiferayBenefitService(LiferayBenefitService liferayBenefitService) {
		this.liferayBenefitService = liferayBenefitService;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}
}
