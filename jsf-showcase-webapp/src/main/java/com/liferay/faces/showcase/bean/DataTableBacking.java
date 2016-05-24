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

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;

import com.liferay.faces.showcase.dto.Customer;
import com.liferay.faces.showcase.model.CustomerOnDemandDataModel;
import com.liferay.faces.showcase.service.CustomerService;


/**
 * @author  Neil Griffin
 */
@ManagedBean
@ViewScoped
public class DataTableBacking implements Serializable {

	// serialVersionUID
	private static final long serialVersionUID = 1715081848553221866L;

	// Injections
	@ManagedProperty(value = "#{customerService}")
	private transient CustomerService customerService;

	// Private Data Members
	private List<Customer> customerDataModel;
	private DataModel<Customer> customerOnDemandDataModel;
	private int rowsPerPage = 10;
	private String selectionMode = "checkbox";
	private String summaryPosition = "bottom";

	public List<Customer> getCustomerDataModel() {

		if (customerDataModel == null) {
			customerDataModel = customerService.getAllCustomers();
		}

		return customerDataModel;
	}

	public DataModel getCustomerOnDemandDataModel() {

		if (customerOnDemandDataModel == null) {
			customerOnDemandDataModel = new CustomerOnDemandDataModel(getRowsPerPage());
		}

		return customerOnDemandDataModel;
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public String getSelectionMode() {
		return selectionMode;
	}

	public String getSummaryPosition() {
		return summaryPosition;
	}

	public void setCustomerService(CustomerService customerService) {

		// Injected via @ManagedProperty annotation.
		this.customerService = customerService;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public void setSelectionMode(String selectionMode) {
		this.selectionMode = selectionMode;
	}

	public void setSummaryPosition(String summaryPosition) {
		this.summaryPosition = summaryPosition;
	}
}
