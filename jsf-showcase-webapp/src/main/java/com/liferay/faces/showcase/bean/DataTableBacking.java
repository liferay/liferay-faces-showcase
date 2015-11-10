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
			customerOnDemandDataModel = new CustomerOnDemandDataModel();
		}

		return customerOnDemandDataModel;
	}

	public void setCustomerService(CustomerService customerService) {

		// Injected via @ManagedProperty annotation.
		this.customerService = customerService;
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public String getSelectionMode() {
		return selectionMode;
	}

	public void setSelectionMode(String selectionMode) {
		this.selectionMode = selectionMode;
	}

	public String getSummaryPosition() {
		return summaryPosition;
	}

	public void setSummaryPosition(String summaryPosition) {
		this.summaryPosition = summaryPosition;
	}
}
