package com.grocery.on.wheels.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.grocery.on.wheels.dao.InventoryMapper;
import com.grocery.on.wheels.dao.ItemMapper;
import com.grocery.on.wheels.model.Item;
import com.grocery.on.wheels.s3.configuration.S3Configuration;
import com.grocery.on.wheels.s3.service.AmazonClient;
import com.grocery.on.wheels.service.ItemService;

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
		item.setItemIcon(iconPath);
		item.setItemQrBarCode(qrPath);
		itemMapper.addItem(item);
		if(item != null && item.getItemPrice().size() > 0) {
			itemMapper.addItemPrice(item);
			itemMapper.addInvItemMap(inventoryId, item);
		}
	}
	
	private String uploadFile(MultipartFile file) {
		//get file metadata
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        String path = String.format("%s/%s", s3Config.getBucketName(), UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename());
        System.out.println(s3Config.getEndpointUrl() + "/" + path + "/" + fileName);
        String savedFilePath = String.format("%s/%s", path, fileName);
        System.out.println(savedFilePath);
        try {
        	s3Client.upload(path, fileName, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
        return savedFilePath;
	}

}
