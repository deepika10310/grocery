package com.grocery.on.wheels.dao;

import org.apache.ibatis.annotations.Mapper;

import com.grocery.on.wheels.model.InventoryPurchaseTransaction;
import com.grocery.on.wheels.model.Invoice;
import com.grocery.on.wheels.model.VanPurchaseTransaction;

@Mapper
public interface GroceryTransactionMapper {

	void createInvoice(Invoice invoice);

	void saveInventoryTransactionInfo(InventoryPurchaseTransaction inventoryPurchaseTransaction);

	void updateInventoryStock(InventoryPurchaseTransaction inventoryPurchaseTransaction);

	void saveVanTransactionInfo(VanPurchaseTransaction vanPurchaseTransaction);

	void updateVanStockPurchase(VanPurchaseTransaction vanPurchaseTransaction);

	void updateVanStockSale(VanPurchaseTransaction vanPurchaseTransaction);

}
