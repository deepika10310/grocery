package com.grocery.on.wheels.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grocery.on.wheels.dao.GroceryTransactionMapper;
import com.grocery.on.wheels.model.InventoryPurchaseTransaction;
import com.grocery.on.wheels.model.Invoice;
import com.grocery.on.wheels.model.VanPurchaseTransaction;
import com.grocery.on.wheels.service.GroceryTransactionService;
import com.grocery.on.wheels.util.GroceryUtil;

@Service
@Transactional
public class GroceryTransactionServiceImpl implements GroceryTransactionService {

	@Autowired
	GroceryTransactionMapper transactionMapper;
	
	private String generateInvoiceId(String invoceType) {
		String prfeix = "";
		Date date = new Date();
		switch (invoceType) {
			case "inventory":
				prfeix = "INVENTORY_";
				break;
			case "van":
				prfeix = "VAN_";
				break;
			default:
				prfeix = "INV_";
				break;
		}
		return prfeix + "" + GroceryUtil.getFormatDate(date);
	}
	
	private void saveInvoice(String invoiceId, String invoiceName) {
		Invoice invoice = new Invoice(invoiceId, invoiceName);
		transactionMapper.createInvoice(invoice);
	}
	
	
	@Override
	public String purchaseInventory(InventoryPurchaseTransaction inventoryPurchaseTransaction) {
		String invoiceId = generateInvoiceId("inventory");
		saveInvoice(invoiceId, inventoryPurchaseTransaction.getInvoiceName());
		inventoryPurchaseTransaction.setInvoiceId(invoiceId);
		transactionMapper.saveInventoryTransactionInfo(inventoryPurchaseTransaction);
		transactionMapper.updateInventoryStock(inventoryPurchaseTransaction);
		return invoiceId;
	}
	


	@Override
	public String purchaseVan(VanPurchaseTransaction vanPurchaseTransaction) {
		String invoiceId = generateInvoiceId("van");
		vanPurchaseTransaction.setInvoiceId(invoiceId);
		saveInvoice(invoiceId, vanPurchaseTransaction.getInvoiceName());
		transactionMapper.saveVanTransactionInfo(vanPurchaseTransaction);
		transactionMapper.addVanItemMap(vanPurchaseTransaction);
		transactionMapper.updateInventoryStockSale(vanPurchaseTransaction);
		transactionMapper.updateVanStockPurchase(vanPurchaseTransaction);
		return invoiceId;
	}

	@Override
	public String sellVan(VanPurchaseTransaction vanPurchaseTransaction) {
		String invoiceId = generateInvoiceId("van");
		vanPurchaseTransaction.setInvoiceId(invoiceId);
		saveInvoice(invoiceId, vanPurchaseTransaction.getInvoiceName());
		transactionMapper.saveVanTransactionInfo(vanPurchaseTransaction);
		transactionMapper.updateVanStockSale(vanPurchaseTransaction);
		return invoiceId;
	}

}
