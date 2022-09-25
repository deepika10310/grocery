package com.grocery.on.wheels.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.on.wheels.model.Supplier;
import com.grocery.on.wheels.service.SupplierService;

@RestController
@RequestMapping("/grocery/supplier")
public class SupplierController {

	@Autowired
	SupplierService supplierService;
	
	@GetMapping("/list")
	public List<Supplier> getSuppliers() {
		return supplierService.getSuppliers();
	}
	
	@PostMapping("/add")
	public void addSupplier(@RequestBody Supplier supplier) {
		supplierService.addSupplier(supplier);
	}
	
	
	@GetMapping("/list/{searchText}")
	public List<Supplier> findSuppliers(@PathVariable("searchText") String searchText) {
		return supplierService.findSuppliers(searchText);
	}
	
}
