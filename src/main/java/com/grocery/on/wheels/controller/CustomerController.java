package com.grocery.on.wheels.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.on.wheels.model.Customer;
import com.grocery.on.wheels.service.CustomerService;

@RestController
@RequestMapping("/grocery/customer")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/list")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}
	
	@PostMapping("/add")
	public void addCustomer(@RequestBody Customer customer) {
		customerService.addCustomer(customer);
	}
	
	
	@GetMapping("/list/{searchText}")
	public List<Customer> findCustomers(@PathVariable("searchText") String searchText) {
		return customerService.findCustomers(searchText);
	}
}
