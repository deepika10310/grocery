package com.grocery.on.wheels.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.grocery.on.wheels.model.Van;

@Mapper
public interface VanMapper {

	List<Van> getVans();

}
