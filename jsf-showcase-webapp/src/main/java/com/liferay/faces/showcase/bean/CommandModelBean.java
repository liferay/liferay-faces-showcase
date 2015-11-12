/**
 * Copyright (c) 2000-2015 Liferay, Inc. All rights reserved.
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

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.liferay.faces.showcase.dto.Customer;
import com.liferay.faces.showcase.service.CustomerService;


/**
 * @author  Vernon Singleton
 * @author  Kyle Stiemann
 */
@ManagedBean
@ViewScoped
public class CommandModelBean {

	// Injections
	@ManagedProperty(value = "#{customerService}")
	private CustomerService customerService;

	// Private Data Members
	private boolean ajax;
	private Customer selectedCustomer;
	private List<Customer> customers;

	@PostConstruct
	public void postConstruct() {
		customers = customerService.getCustomers(0, 5);
	}

	public void setAjax(boolean ajax) {
		this.ajax = ajax;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}

	public boolean isAjax() {
		return ajax;
	}
}
