package com.grocery.on.wheels.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.on.wheels.model.Van;

@RestController
@RequestMapping("/reports")

public class ReportsController {
	
	
	
	@GetMapping("/inventory")
	public List<Van> getInventoryReports(@RequestParam("inventory_id") String inventoryId) {
		//return vanService.getVans(inventoryId);
		return null;
	}
}
