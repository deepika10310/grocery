package com.grocery.on.wheels.model;

import java.util.Date;

public class Invoice {
	private String invoiceId;
	private String invoiceName;
	private Date date;
	
	public Invoice() {
		
	}
	
	public Invoice(String invoiceId, String invoiceName) {
		this.invoiceId = invoiceId;
		this.invoiceName = invoiceName;
	}
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getInvoiceName() {
		return invoiceName;
	}
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
