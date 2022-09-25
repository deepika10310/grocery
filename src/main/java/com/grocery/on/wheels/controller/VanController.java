package com.grocery.on.wheels.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.on.wheels.model.Van;
import com.grocery.on.wheels.service.VanService;

@RestController
@RequestMapping("/grocery/van")
public class VanController {
	@Autowired
	VanService vanService;
	
	@GetMapping("/list")
	public List<Van> getVans() {
		return vanService.getVans();
	}
}
