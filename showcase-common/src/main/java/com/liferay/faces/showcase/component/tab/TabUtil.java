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
package com.liferay.faces.showcase.component.tab;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIData;


/**
 * @author  Neil Griffin
 */
public class TabUtil {

	public static List<Tab> getChildTabs(UIData uiData) {

		List<Tab> childTabs = new ArrayList<Tab>();

		List<UIComponent> children = uiData.getChildren();

		for (UIComponent child : children) {

			if (child instanceof Tab) {
				childTabs.add((Tab) child);
			}
		}

		return childTabs;
	}

	public static Tab getFirstChildTab(UIData uiData) {

		Tab prototypeChildType = null;

		List<Tab> childTabs = getChildTabs(uiData);

		if (childTabs.size() > 0) {
			prototypeChildType = childTabs.get(0);
		}

		return prototypeChildType;
	}
}
