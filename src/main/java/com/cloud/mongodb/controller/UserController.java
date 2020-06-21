package com.cloud.mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.mongodb.entity.User;
import com.cloud.mongodb.service.IUserService;

@RestController
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	//************
	
	@GetMapping(path = "/settings/{userId}")
	public Object getAllUserSettings(@PathVariable String userId) {
		
		User user = userService.geUserById(userId);
		
		return user.getUserSettings();
	}
	
	@PostMapping(path = "/new")
	public User addNewUser(@RequestBody User user) {
		
		return userService.addNewUser(user);
	}
	
	@DeleteMapping(path = "/delete/{userId}")
	public String deleteUser(@PathVariable String userId) {
		
		return userService.deleteById(userId);
	}

	@GetMapping(path = "/user/{userId}")
	public Object getUser(@PathVariable String userId) {
		
		return userService.geUserById(userId);
	}
	
	@PutMapping(path = "/update")
	public String updateUser(@RequestBody User user) {
		
		return userService.updateUser(user);
	}
}
