package com.grocery.on.wheels.model;

import java.util.Date;
import java.util.List;

public class VanPurchaseTransaction {
	private String inventoryId;
	private String invoiceId;
	private String invoiceName;
	private String vanId;
	private List<VanPurchaseTransactionItem> purchaseTransItem;
	private String customerId;
	private String discount;
	private String otherCharges;
	private String transactionType;//CREDIT/DEBIT
	private String transactionStatus;//INVOICE/CANCEL/REFUND
	private Date transactionDate;
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getVanId() {
		return vanId;
	}
	public void setVanId(String vanId) {
		this.vanId = vanId;
	}
	public List<VanPurchaseTransactionItem> getPurchaseTransItem() {
		return purchaseTransItem;
	}
	public void setPurchaseTransItem(List<VanPurchaseTransactionItem> purchaseTransItem) {
		this.purchaseTransItem = purchaseTransItem;
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
	public String getInvoiceName() {
		return invoiceName;
	}
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	public String getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
