/**
 * Copyright (c) 2000-2015 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
package com.liferay.faces.showcase.component.column;

import javax.faces.component.FacesComponent;


/**
 * @author  Kyle Stiemann
 */
@FacesComponent(value = Column.COMPONENT_TYPE)
public class Column extends ColumnBase {

	// Public Constants
	public static final int COLUMNS = 12;

	@Override
	public Integer getSpan() {
		return (Integer) getStateHelper().eval(ColumnPropertyKeys.span, COLUMNS);
	}
}
