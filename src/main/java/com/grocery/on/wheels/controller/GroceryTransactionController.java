package com.grocery.on.wheels.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.on.wheels.model.BaseRsp;
import com.grocery.on.wheels.model.InventoryPurchaseTransaction;
import com.grocery.on.wheels.model.ItemValidate;
import com.grocery.on.wheels.model.VanPurchaseTransaction;
import com.grocery.on.wheels.service.GroceryTransactionService;

@RestController
@RequestMapping("/grocery/transaction")
public class GroceryTransactionController {
	
	@Autowired
	GroceryTransactionService transactionService;
	
	@PostMapping("/purchase/inventory")
	public BaseRsp purchaseInventory(@RequestBody InventoryPurchaseTransaction inventoryPurchaseTransaction) {
		return new BaseRsp("success", transactionService.purchaseInventory(inventoryPurchaseTransaction));
	}
	
	@PostMapping("/purchase/van")
	public BaseRsp purchaseVan(@RequestBody VanPurchaseTransaction vanPurchaseTransaction) {
		return new BaseRsp("success", transactionService.purchaseVan(vanPurchaseTransaction));
	}
	
	@PostMapping("/sell/van")
	public BaseRsp sellVan(@RequestBody VanPurchaseTransaction vanPurchaseTransaction) {
		return new BaseRsp("success", transactionService.sellVan(vanPurchaseTransaction));
	}
	
	@PostMapping("validate/inventory/purchase")
	public BaseRsp validateInventoryPurchase(@RequestBody List<ItemValidate> itemValidateList) {
		transactionService.validateInventoryPurchase(itemValidateList);
		return new BaseRsp("success", "saved");
	}
	
	@PostMapping("validate/inventory/sale")
	public BaseRsp validateInventorySale(@RequestBody List<ItemValidate> itemValidateList) {
		transactionService.validateInventorySale(itemValidateList);
		return new BaseRsp("success", "saved");
	}

	@PostMapping("validate/van/purchase")
	public BaseRsp validateVanPurchase(@RequestBody List<ItemValidate> itemValidateList) {
		transactionService.validateVanPurchase(itemValidateList);
		return new BaseRsp("success", "saved");
	}
	
	@PostMapping("validate/van/sale")
	public BaseRsp validateVanSale(@RequestBody List<ItemValidate> itemValidateList) {
		transactionService.validateVanSale(itemValidateList);
		return new BaseRsp("success", "saved");
	}
	
	@PostMapping("/clear_transaction/{inv_id}")
	public BaseRsp clearTransaction(@PathVariable("inv_id") String inventoryId) {
		transactionService.clearTransaction();
		return new BaseRsp("success", "delete");
	}
}
