package com.grocery.on.wheels.service;

import java.util.List;

import com.grocery.on.wheels.model.InventoryPurchaseTransaction;
import com.grocery.on.wheels.model.ItemValidate;
import com.grocery.on.wheels.model.VanPurchaseTransaction;

public interface GroceryTransactionService {

	InventoryPurchaseTransaction purchaseInventory(InventoryPurchaseTransaction inventoryPurchaseTransaction);
	
	VanPurchaseTransaction purchaseVan(VanPurchaseTransaction vanPurchaseTransaction);

	VanPurchaseTransaction sellVan(VanPurchaseTransaction vanPurchaseTransaction);

	void validateInventoryPurchase(List<ItemValidate> itemValidateList);

	void validateInventorySale(List<ItemValidate> itemValidateList);

	void validateVanSale(List<ItemValidate> itemValidateList);

	void validateVanPurchase(List<ItemValidate> itemValidateList);

	void clearTransaction();

}
