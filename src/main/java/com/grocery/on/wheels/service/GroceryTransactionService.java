package com.grocery.on.wheels.service;

import com.grocery.on.wheels.model.InventoryPurchaseTransaction;
import com.grocery.on.wheels.model.VanPurchaseTransaction;

public interface GroceryTransactionService {

	String purchaseInventory(InventoryPurchaseTransaction inventoryPurchaseTransaction);

	String purchaseVan(VanPurchaseTransaction vanPurchaseTransaction);

	String sellVan(VanPurchaseTransaction vanPurchaseTransaction);

}
