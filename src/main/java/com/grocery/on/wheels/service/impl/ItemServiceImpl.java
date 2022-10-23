package com.grocery.on.wheels.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.grocery.on.wheels.dao.ItemMapper;
import com.grocery.on.wheels.model.Item;
import com.grocery.on.wheels.s3.configuration.S3Configuration;
import com.grocery.on.wheels.s3.service.AmazonClient;
import com.grocery.on.wheels.service.ItemService;
import com.grocery.on.wheels.util.GroceryUtil;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	AmazonClient s3Client;

	@Autowired
	S3Configuration s3Config;
	
	@Autowired
	ItemMapper itemMapper;
	
	@Override
	public void addItem(Item item,String inventoryId, MultipartFile iconFile, MultipartFile qrFile) {
		String iconPath = uploadFile(iconFile);
		String qrPath = uploadFile(qrFile);
		item.setItemId("ITM_" + GroceryUtil.getFormatDate(new Date()));;
		item.setItemIcon(iconPath);
		item.setItemQrBarCode(qrPath);
		itemMapper.addItem(item);
		if(item != null && item.getItemPrice().size() > 0) {
			itemMapper.addItemPrice(item);
			itemMapper.addInvItemMap(inventoryId, item);
		}
	}
	
	private String uploadFile(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        String path = String.format("%s/%s", s3Config.getBucketName(), UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename());
        String savedFilePath = String.format("%s/%s", path, fileName);
        try {
        	s3Client.upload(path, fileName, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
        return savedFilePath;
	}

	@Override
	public void editItem(Item item, String inventoryId, MultipartFile iconFile, MultipartFile qrFile) {
		boolean iconFileUpdate = false;
		boolean qrFileUpdate = false;
		String iconPath = null;
		String qrPath = null;
		if(iconFile != null) {
			iconFileUpdate = true;
			iconPath = uploadFile(iconFile);
		}
		if(qrFile != null) {
			qrFileUpdate = true;
			qrPath = uploadFile(qrFile);
		}
		item.setItemIcon(iconPath);
		item.setItemQrBarCode(qrPath);
		itemMapper.editItem(item, iconFileUpdate, qrFileUpdate);
		if(item != null && item.getItemPrice().size() > 0) {
			itemMapper.deleteItemPrice(item);
			itemMapper.deleteInvItemMap(inventoryId, item);
			itemMapper.addItemPrice(item);
			itemMapper.addInvItemMap(inventoryId, item);
		}
	}

}
