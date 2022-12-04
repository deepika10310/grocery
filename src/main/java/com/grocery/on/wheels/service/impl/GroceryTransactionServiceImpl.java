package com.grocery.on.wheels.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grocery.on.wheels.dao.GroceryTransactionMapper;
import com.grocery.on.wheels.model.InventoryPurchaseTransaction;
import com.grocery.on.wheels.model.InventoryPurchaseTransactionItem;
import com.grocery.on.wheels.model.Invoice;
import com.grocery.on.wheels.model.ItemValidate;
import com.grocery.on.wheels.model.VanPurchaseTransaction;
import com.grocery.on.wheels.model.VanPurchaseTransactionItem;
import com.grocery.on.wheels.s3.configuration.S3Configuration;
import com.grocery.on.wheels.s3.service.AmazonClient;
import com.grocery.on.wheels.service.GroceryTransactionService;
import com.grocery.on.wheels.util.GroceryUtil;

@Service
@Transactional
public class GroceryTransactionServiceImpl implements GroceryTransactionService {

	private static final String DEBIT = "DEBIT";
	private static final String CREDIT = "CREDIT";
	

	@Autowired
	AmazonClient s3Client;

	@Autowired
	S3Configuration s3Config;

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
	public InventoryPurchaseTransaction purchaseInventory(InventoryPurchaseTransaction inventoryPurchaseTransaction) {
		String invoiceId = generateInvoiceId("inventory");
		saveInvoice(invoiceId, inventoryPurchaseTransaction.getInvoiceName());
		inventoryPurchaseTransaction.setInvoiceId(invoiceId);
		inventoryPurchaseTransaction.setTransactionDate(new Date());
		switch (inventoryPurchaseTransaction.getTransactionStatus()) {
			case "INVOICE":
				inventoryPurchaseTransaction.setTransactionType(CREDIT);
				break;
			case "REFUND":
				inventoryPurchaseTransaction.setTransactionType(DEBIT);
				break;
			default:
				break;
		}
//		inventoryPurchaseTransaction.setTransactionStatus("INVOICE");
		String invoiceUrl = createAndUploadInvoice(inventoryPurchaseTransaction);
		inventoryPurchaseTransaction.setInvoiceUrl(invoiceUrl);
		transactionMapper.saveInventoryTransactionInfo(inventoryPurchaseTransaction);
		transactionMapper.updateInventoryStockPurchase(inventoryPurchaseTransaction);
		return inventoryPurchaseTransaction;
	}

	private String createAndUploadInvoice(Invoice invoice) {
		List<String> pdfTextList = new ArrayList<>();
		pdfTextList.add("******************************  GROCERY ON WHEELS *************************");
		if(invoice != null) {
			if(invoice instanceof InventoryPurchaseTransaction) {
				InventoryPurchaseTransaction transactionInvoice = (InventoryPurchaseTransaction) invoice;
				List<InventoryPurchaseTransactionItem> purchaseTransItems = transactionInvoice.getPurchaseTransItem();
				if(purchaseTransItems != null && purchaseTransItems.size() > 0) {
					for(InventoryPurchaseTransactionItem purchaseTransItem: purchaseTransItems) {
						pdfTextList.add("ITEMID: " + purchaseTransItem.getItemId());
						pdfTextList.add("MRP: " + purchaseTransItem.getMrp());
						pdfTextList.add("EXPIRY: " + purchaseTransItem.getExpDate());
					}
				}
			} else if(invoice instanceof VanPurchaseTransaction) {
				VanPurchaseTransaction transactionInvoice = (VanPurchaseTransaction) invoice;
				List<VanPurchaseTransactionItem> purchaseTransItems = transactionInvoice.getPurchaseTransItem();
				if(purchaseTransItems != null && purchaseTransItems.size() > 0) {
					for(VanPurchaseTransactionItem purchaseTransItem: purchaseTransItems) {
						pdfTextList.add("ITEMID: " + purchaseTransItem.getItemId());
						pdfTextList.add("MRP: " + purchaseTransItem.getMrp());
						pdfTextList.add("EXPIRY: " + purchaseTransItem.getExpDate());
					}
				}
			}
		}
		
		File tempPdfFile = GroceryUtil.createAndUpload(pdfTextList, invoice.getInvoiceId());
		String invoiceUrl = GroceryUtil.uploadFile(tempPdfFile, s3Config, s3Client);
		if(tempPdfFile != null) {
			tempPdfFile.delete();
		}
		return invoiceUrl;
	}
	
	@Override
	public VanPurchaseTransaction purchaseVan(VanPurchaseTransaction vanPurchaseTransaction) {
		String invoiceId = generateInvoiceId("van");
		vanPurchaseTransaction.setInvoiceId(invoiceId);
//		vanPurchaseTransaction.setTransactionType(CREDIT);
//		vanPurchaseTransaction.setTransactionStatus("INVOICE");
		switch (vanPurchaseTransaction.getTransactionStatus()) {
			case "INVOICE":
				vanPurchaseTransaction.setTransactionType(CREDIT);
				break;
			case "REFUND":
				vanPurchaseTransaction.setTransactionType(DEBIT);
				break;
			default:
				break;
		}

		vanPurchaseTransaction.setTransactionDate(new Date());
		saveInvoice(invoiceId, vanPurchaseTransaction.getInvoiceName());
		String invoiceUrl = createAndUploadInvoice(vanPurchaseTransaction);
		vanPurchaseTransaction.setInvoiceUrl(invoiceUrl);
		transactionMapper.saveVanTransactionInfo(vanPurchaseTransaction);
		transactionMapper.addVanItemMap(vanPurchaseTransaction);
		transactionMapper.updateVanStockPurchase(vanPurchaseTransaction);
		switch (vanPurchaseTransaction.getTransactionStatus()) {
			case "INVOICE":
				vanPurchaseTransaction.setTransactionType(DEBIT);
				break;
			case "REFUND":
				vanPurchaseTransaction.setTransactionType(CREDIT);
				break;
			default:
				break;
		}
//		vanPurchaseTransaction.setTransactionType(DEBIT);
		transactionMapper.updateInventoryStockSale(vanPurchaseTransaction);
		return vanPurchaseTransaction;
	}

	@Override
	public VanPurchaseTransaction sellVan(VanPurchaseTransaction vanPurchaseTransaction) {
		String invoiceId = generateInvoiceId("van");
		vanPurchaseTransaction.setInvoiceId(invoiceId);
		switch (vanPurchaseTransaction.getTransactionStatus()) {
			case "INVOICE":
				vanPurchaseTransaction.setTransactionType(DEBIT);
				break;
			case "REFUND":
				vanPurchaseTransaction.setTransactionType(CREDIT);
				break;
			default:
				break;
		}
//		vanPurchaseTransaction.setTransactionType(DEBIT);
//		vanPurchaseTransaction.setTransactionStatus("INVOICE");
		vanPurchaseTransaction.setTransactionDate(new Date());
		saveInvoice(invoiceId, vanPurchaseTransaction.getInvoiceName());
		String invoiceUrl = createAndUploadInvoice(vanPurchaseTransaction);
		vanPurchaseTransaction.setInvoiceUrl(invoiceUrl);
		transactionMapper.saveVanTransactionInfo(vanPurchaseTransaction);
		transactionMapper.updateVanStockSale(vanPurchaseTransaction);
		return vanPurchaseTransaction;
	}

	@Override
	public void validateInventoryPurchase(List<ItemValidate> itemValidateList) {
		transactionMapper.validateInventoryPurchase(itemValidateList);
	}

	@Override
	public void validateInventorySale(List<ItemValidate> itemValidateList) {
		transactionMapper.validateInventorySale(itemValidateList);
	}

	@Override
	public void validateVanSale(List<ItemValidate> itemValidateList) {
		transactionMapper.validateVanSale(itemValidateList);
	}

	@Override
	public void validateVanPurchase(List<ItemValidate> itemValidateList) {
		transactionMapper.validateVanPurchase(itemValidateList);
	}

	@Override
	public void clearTransaction() {
		transactionMapper.clearTransaction();
	}
}
