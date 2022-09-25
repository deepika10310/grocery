package com.grocery.on.wheels.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.on.wheels.model.InventoryPurchaseTransaction;
import com.grocery.on.wheels.model.VanPurchaseTransaction;
import com.grocery.on.wheels.service.GroceryTransactionService;

@RestController
@RequestMapping("/grocery/transaction")
public class GroceryTransactionController {
	
	
	@Autowired
	GroceryTransactionService transactionService;
	
	@PostMapping("/purchase/inventory")
	public String purchaseInventory(@RequestBody InventoryPurchaseTransaction inventoryPurchaseTransaction) {
		return transactionService.purchaseInventory(inventoryPurchaseTransaction);
	}
	
	@PostMapping("/purchase/van")
	public String purchaseVan(@RequestBody VanPurchaseTransaction vanPurchaseTransaction) {
		return transactionService.purchaseVan(vanPurchaseTransaction);
	}
	
	@PostMapping("/sell/van")
	public String sellVan(@RequestBody VanPurchaseTransaction vanPurchaseTransaction) {
		return transactionService.sellVan(vanPurchaseTransaction);
	}
}
