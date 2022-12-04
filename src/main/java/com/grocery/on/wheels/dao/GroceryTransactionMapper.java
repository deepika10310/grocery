package com.grocery.on.wheels.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.grocery.on.wheels.model.InventoryPurchaseTransaction;
import com.grocery.on.wheels.model.Invoice;
import com.grocery.on.wheels.model.ItemValidate;
import com.grocery.on.wheels.model.VanPurchaseTransaction;

@Mapper
public interface GroceryTransactionMapper {

	void createInvoice(Invoice invoice);

	void saveInventoryTransactionInfo(InventoryPurchaseTransaction inventoryPurchaseTransaction);

	void updateInventoryStockPurchase(InventoryPurchaseTransaction inventoryPurchaseTransaction);

	void saveVanTransactionInfo(VanPurchaseTransaction vanPurchaseTransaction);

	void updateVanStockPurchase(VanPurchaseTransaction vanPurchaseTransaction);

	void updateVanStockSale(VanPurchaseTransaction vanPurchaseTransaction);

	void updateInventoryStockSale(VanPurchaseTransaction vanPurchaseTransaction);

	void addVanItemMap(VanPurchaseTransaction vanPurchaseTransaction);

	void validateVanPurchase(@Param("itemValidateList") List<ItemValidate> itemValidateList);

	void validateVanSale(@Param("itemValidateList") List<ItemValidate> itemValidateList);

	void validateInventorySale(@Param("itemValidateList") List<ItemValidate> itemValidateList);

	void validateInventoryPurchase(@Param("itemValidateList") List<ItemValidate> itemValidateList);
	
	
	void clearTransaction();

}
