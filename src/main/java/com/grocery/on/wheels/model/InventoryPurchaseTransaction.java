package com.grocery.on.wheels.model;

import java.util.Date;
import java.util.List;

public class InventoryPurchaseTransaction extends Invoice {
	private String inventoryId;
	private List<InventoryPurchaseTransactionItem> purchaseTransItem;
	private String supplierId;
	private String discount;
	private String otherCharges;
	private String transactionType;//INVOICE/CANCEL/REFUND
	private String transactionStatus;//SUCCESS/FAIL
	private Date transactionDate;
	public String getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}
	public List<InventoryPurchaseTransactionItem> getPurchaseTransItem() {
		return purchaseTransItem;
	}
	public void setPurchaseTransItem(List<InventoryPurchaseTransactionItem> purchaseTransItem) {
		this.purchaseTransItem = purchaseTransItem;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getOtherCharges() {
		return otherCharges;
	}
	public void setOtherCharges(String otherCharges) {
		this.otherCharges = otherCharges;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
}
