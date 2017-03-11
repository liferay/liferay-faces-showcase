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

import javax.faces.component.FacesComponent;
import javax.faces.component.UIColumn;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.model.DataModel;
import javax.faces.render.Renderer;


/**
 * This is the {@link UIComponent} class associated with the alloy:tab component tag. The intended usage is for the
 * developer to specify alloy:tab as a child element of alloy:tabView. For example:
 *
 * <pre>
 {@code
 <showcase:tabView value="#{modelBean.items}" var="item">
 <showcase:tab label="#{item.label}" />
 </alloy:tabView>
 }
 * </pre>
 *
 * Note that this class ultimately extends {@link UIColumn} because the {@link TabView} class ultimately extends {@link
 * UIData} (which handles children of type {@link UIColumn} in a special manner). In fact, the JavaDoc description for
 * the {@link UIData} class states that _ONLY_ children of type {@link UIColumn} should be processed by associated
 * {@link Renderer} classes. One of the most important benefits of extending {@link UIColumn} is that the {@link
 * UIData#getClientId()} method will append the rowIndex during iteration over a {@link DataModel}, ensuring that each
 * rendered {@link Tab} will have a unique clientId.
 *
 * @author  Neil Griffin
 */
@FacesComponent(value = Tab.COMPONENT_TYPE)
public class Tab extends TabBase {
	// Initial Generation
}
