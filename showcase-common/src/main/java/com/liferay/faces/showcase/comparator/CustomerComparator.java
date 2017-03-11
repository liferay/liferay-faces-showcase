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
package com.liferay.faces.showcase.comparator;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.liferay.faces.showcase.dto.Customer;
import com.liferay.faces.util.model.SortCriterion;


/**
 * @author  Neil Griffin
 */
public class CustomerComparator implements Comparator<Customer> {

	// Private Data Members
	private List<SortCriterion> sortCriteria;

	public CustomerComparator(List<SortCriterion> sortCriteria) {
		this.sortCriteria = sortCriteria;
	}

	@Override
	public int compare(Customer customer1, Customer customer2) {

		int result = 0;

		if (sortCriteria != null) {

			try {
				BeanInfo beanInfo = Introspector.getBeanInfo(Customer.class);
				PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

				for (SortCriterion sortCriterion : sortCriteria) {

					String sortCriterionColumnId = sortCriterion.getColumnId();
					SortCriterion.Order sortCriterionOrder = sortCriterion.getOrder();

					for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {

						String propertyDescriptorName = propertyDescriptor.getName();

						if ((sortCriterionColumnId != null) && sortCriterionColumnId.equals(propertyDescriptorName)) {

							Method readMethod = propertyDescriptor.getReadMethod();
							Object customer1Value = readMethod.invoke(customer1);
							Object customer2Value = readMethod.invoke(customer2);

							if (sortCriterionOrder == SortCriterion.Order.DESCENDING) {
								Object tempValue = customer1Value;
								customer1Value = customer2Value;
								customer2Value = tempValue;
							}

							if ((customer1Value != null) && (customer1Value instanceof Comparable) &&
									(customer2Value != null) && (customer2Value instanceof Comparable)) {

								if (customer1Value instanceof String) {
									String s1 = (String) customer1Value;
									String s2 = (String) customer2Value;
									result = s1.compareTo(s2);
								}
								else if (customer1Value instanceof Date) {
									Date d1 = (Date) customer1Value;
									Date d2 = (Date) customer2Value;
									result = d1.compareTo(d2);
								}
							}
						}

						if (result != 0) {
							break;
						}
					}
				}
			}
			catch (IntrospectionException e) {
				e.printStackTrace();
			}
			catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}
