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
package com.liferay.faces.showcase.service;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.liferay.faces.showcase.dto.LiferayBenefit;


/**
 * @author  Neil Griffin
 */
@ManagedBean(name = "liferayBenefitService", eager = true)
@ApplicationScoped
public class LiferayBenefitServiceMockImpl implements LiferayBenefitService {

	private static final List<LiferayBenefit> LIFERAY_BENEFITS;

	static {
		LIFERAY_BENEFITS = new ArrayList<LiferayBenefit>();
		LIFERAY_BENEFITS.add(new LiferayBenefit(1, "Compatible", "Compatible With Your IT",
				"Liferay lets you reuse the enterprise software and skills you already have in-house.",
				"compatible.png"));
		LIFERAY_BENEFITS.add(new LiferayBenefit(2, "Enterprise Ready", "Enterprise Ready",
				"Liferay is designed for scalability, reliablity, and high performance both on-premise and in the cloud.",
				"enterprise.png"));
		LIFERAY_BENEFITS.add(new LiferayBenefit(3, "Powerful Integration", "Powerful Integration",
				"Liferay is designed for integrating with both enterprise systems and web-based resources.",
				"integration.png"));
		LIFERAY_BENEFITS.add(new LiferayBenefit(4, "Lightweight", "Lightweight and Agile",
				"With Liferay, projects are completed faster and with smaller budgets so you can see immediate results.",
				"lightweight.png"));
		LIFERAY_BENEFITS.add(new LiferayBenefit(5, "Open Source", "Built With Open Source",
				"Our open source community fosters innovation, increase security, and improves quality of the software.",
				"open-source.png"));
	}

	@Override
	public List<LiferayBenefit> getLiferayBenefits() {
		return LIFERAY_BENEFITS;
	}
}
