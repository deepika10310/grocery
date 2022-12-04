package com.grocery.on.wheels.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.on.wheels.dao.UserRepository;
import com.grocery.on.wheels.model.User;

@RestController
@RequestMapping("/grocery/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		if (userRepository.findById(user.getId()) == null) {
			userRepository.insert(user);
			return userRepository.findById(user.getId());
		}
		return null;
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		User user = userRepository.findById(id);

		return ResponseEntity.ok(user);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {

		return ResponseEntity.ok(userRepository.findById(id));
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
		userRepository.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}