package com.grocery.on.wheels.model;

public class InventoryPurchaseTransactionItem {
	private String itemId;
	private int itemCount;
	private String mrp;
	private String cp;
	private String sp;
	private String expDate;
	private String itemPriceId;
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	public String getMrp() {
		return mrp;
	}
	public void setMrp(String mrp) {
		this.mrp = mrp;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getSp() {
		return sp;
	}
	public void setSp(String sp) {
		this.sp = sp;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public String getItemPriceId() {
		return itemPriceId;
	}
	public void setItemPriceId(String itemPriceId) {
		this.itemPriceId = itemPriceId;
	}
}
