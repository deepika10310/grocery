package com.grocery.on.wheels.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.grocery.on.wheels.model.Customer;

@Mapper
public interface CustomerMapper {

	List<Customer> getCustomers();

	void addCustomer(Customer customer);
	void addCustomerAddress(Customer customer);

	List<Customer> findCustomers(String string);

	
}
