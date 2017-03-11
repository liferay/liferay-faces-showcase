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
package com.liferay.faces.showcase.model;

import java.io.Serializable;
import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.liferay.faces.showcase.comparator.CustomerComparator;
import com.liferay.faces.showcase.dto.Customer;
import com.liferay.faces.showcase.service.CustomerService;
import com.liferay.faces.util.model.OnDemandDataModel;
import com.liferay.faces.util.model.SortCriterion;


/**
 * @author  Neil Griffin
 */
public class CustomerOnDemandDataModel extends OnDemandDataModel<Customer> implements Serializable {

	// serialVersionUID
	private static final long serialVersionUID = 1715725381634521866L;

	// Transient Data members
	private transient CustomerService customerService;
	private int rowsPerPage;

	public CustomerOnDemandDataModel(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	@Override
	public int countRows() {
		return getCustomerService().getCustomerCount();
	}

	@Override
	public List<Customer> findRows(int startRow, int finishRow, List<SortCriterion> sortCriteria) {

		CustomerComparator customerComparator = new CustomerComparator(sortCriteria);
		List<Customer> customers = getCustomerService().getCustomers(startRow, finishRow, customerComparator);

		FacesMessage facesMessage = new FacesMessage("OnDemandDataModel: Fetched row index range " + startRow + "-" +
				finishRow);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, facesMessage);

		return customers;
	}

	@Override
	public int getRowsPerPage() {
		return rowsPerPage;
	}

	protected CustomerService getCustomerService() {

		if (customerService == null) {

			// In order to accommodate clustered environments, the CustomerService is transient and therefore must be
			// self-injected.
			FacesContext facesContext = FacesContext.getCurrentInstance();
			Application application = facesContext.getApplication();
			ELResolver elResolver = application.getELResolver();
			ELContext elContext = facesContext.getELContext();
			customerService = (CustomerService) elResolver.getValue(elContext, null, "customerService");
		}

		return customerService;
	}
}
