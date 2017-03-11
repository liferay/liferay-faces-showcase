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


/**
 * @author  Neil Griffin
 */
public class LiferayBenefit implements Serializable {

	// serialVersionUID
	private static final long serialVersionUID = 6398796698902377951L;

	// Private Data Members
	private long id;
	private String description;
	private String imageName;
	private String label;
	private String title;

	public LiferayBenefit(long id, String label, String title, String description, String imageName) {
		this.id = id;
		this.label = label;
		this.title = title;
		this.description = description;
		this.imageName = imageName;
	}

	public String getDescription() {
		return description;
	}

	public long getId() {
		return id;
	}

	public String getImageName() {
		return imageName;
	}

	public String getLabel() {
		return label;
	}

	public String getTitle() {
		return title;
	}
}
