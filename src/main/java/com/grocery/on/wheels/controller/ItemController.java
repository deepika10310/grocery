package com.grocery.on.wheels.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grocery.on.wheels.model.Item;
import com.grocery.on.wheels.service.ItemService;

@RestController
@RequestMapping("/grocery/item")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@PostMapping(
            path = "/add",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
	public void addItem(@RequestParam("item") String itemJson,
						@RequestParam("inventoryId") String inventoryId,
                                         @RequestParam("icon") MultipartFile iconFile,
                                         @RequestParam("qrCode") MultipartFile qrFile
                                         ) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Item item = mapper.readValue(itemJson, Item.class);
			itemService.addItem(item, inventoryId, iconFile, qrFile);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
}
