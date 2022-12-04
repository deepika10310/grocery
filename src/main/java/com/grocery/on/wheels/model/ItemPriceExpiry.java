package com.grocery.on.wheels.model;

public class ItemPriceExpiry {
	String expDate;
	int itemCount;
	int purchasePending;
	int salePending;
	int selCount;
	
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	public int getSelCount() {
		return selCount;
	}
	public void setSelCount(int selCount) {
		this.selCount = selCount;
	}
	public int getPurchasePending() {
		return purchasePending;
	}
	public void setPurchasePending(int purchasePending) {
		this.purchasePending = purchasePending;
	}
	public int getSalePending() {
		return salePending;
	}
	public void setSalePending(int salePending) {
		this.salePending = salePending;
	}
}
