package com.grocery.on.wheels.model;

public class ItemPrice {
	private double mrp;
	private double cp;
	private double sp;
	private int itemCount;
	public double getMrp() {
		return mrp;
	}
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}
	public double getCp() {
		return cp;
	}
	public void setCp(double cp) {
		this.cp = cp;
	}
	public double getSp() {
		return sp;
	}
	public void setSp(double sp) {
		this.sp = sp;
	}
	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
}
