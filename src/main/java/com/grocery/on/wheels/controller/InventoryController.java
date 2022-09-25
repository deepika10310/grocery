package com.grocery.on.wheels.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.on.wheels.model.Inventory;
import com.grocery.on.wheels.model.Item;
import com.grocery.on.wheels.service.InventoryService;

@RestController
@RequestMapping("/grocery/inventory")
public class InventoryController {
	
	@Autowired
	InventoryService inventoryService;
	
	@GetMapping("/list")
	public List<Inventory> getInventories() {
		return inventoryService.listInventory();
	}
	
	@GetMapping("/items")
	public List<Item> getItems(@RequestParam("inventory_id") String inventoryId) {
		return inventoryService.getItems(inventoryId);
	}
	
	@GetMapping("/items/{searchText}")
	public List<Item> getItemsByName(@RequestParam("inventory_id") String inventoryId,
			@PathVariable("searchText") String searchText) {
		return inventoryService.getItemsByName(inventoryId, "%"+searchText+"%");
	}
	
}
