package com.grocery.on.wheels.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.grocery.on.wheels.dao.CustomerMapper;
import com.grocery.on.wheels.model.Customer;
import com.grocery.on.wheels.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerMapper customerMapper;
	
	@Override
	public List<Customer> getCustomers() {
		return customerMapper.getCustomers();
	}

	@Override
	public void addCustomer(Customer customer) {
		customerMapper.addCustomer(customer);
		customerMapper.addCustomerAddress(customer);
	}

	@Override
	public List<Customer> findCustomers(String searchText) {
		return customerMapper.findCustomers("%" + searchText + "%");
	}

}
