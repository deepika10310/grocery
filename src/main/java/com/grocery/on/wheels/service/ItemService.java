package com.grocery.on.wheels.service;

import org.springframework.web.multipart.MultipartFile;

import com.grocery.on.wheels.model.Item;

public interface ItemService {

	public void addItem(Item item, String inventoryId, MultipartFile iconFile, MultipartFile qrFile);
}
