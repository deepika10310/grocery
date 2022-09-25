package com.grocery.on.wheels.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.grocery.on.wheels.config.GroceryPropertyConfig;

@Component
@PropertySource("classpath:application.properties")
public class Item {
	
	@Autowired
	GroceryPropertyConfig config;

	@Value( "${logging.level.root}" )
	public String s3Domain;
	
	public String getS3Domain() {
		return s3Domain;
	}
	public void setS3Domain(String s3Domain) {
		this.s3Domain = s3Domain;
	}
	public String domain;
	private String inventoryId;
	private String itemId;
	private String itemName;
	private String itemCode;
	private String itemUnit;
	private int itemUnitCount;
	private String itemIcon;
	private String itemQrBarCode;
	private List<ItemPrice> itemPrice;
	public String getDomain() {
		System.out.println(s3Domain + "  " + config);
		return domain;
	}
	public void setDomain(String domain) {
		System.out.println("set " + domain);
		this.domain = domain;
	}
	public String getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemUnit() {
		return itemUnit;
	}
	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}
	public int getItemUnitCount() {
		return itemUnitCount;
	}
	public void setItemUnitCount(int itemUnitCount) {
		this.itemUnitCount = itemUnitCount;
	}
	public String getItemIcon() {
		return itemIcon;
	}
	public void setItemIcon(String itemIcon) {
		this.itemIcon = itemIcon;
	}
	public String getItemQrBarCode() {
		return itemQrBarCode;
	}
	public void setItemQrBarCode(String itemQrBarCode) {
		this.itemQrBarCode = itemQrBarCode;
	}
	public List<ItemPrice> getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(List<ItemPrice> itemPrice) {
		this.itemPrice = itemPrice;
	}
	
}
