package com.grocery.on.wheels.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.grocery.on.wheels.model.Item;

@Mapper
public interface ItemMapper {

	void addItem(Item item);

	void addItemPrice(Item item);

	void addInvItemMap(@Param("inventoryId") String inventoryId, @Param("item") Item item);

	void editItem(@Param("item") Item item, @Param("iconFileUpdate") boolean iconFileUpdate, @Param("qrFileUpdate") boolean qrFileUpdate);

	void editItemPrice(Item item);

	void deleteItemPrice(Item item);

	void deleteInvItemMap(@Param("inventoryId") String inventoryId, @Param("item") Item item);

}
