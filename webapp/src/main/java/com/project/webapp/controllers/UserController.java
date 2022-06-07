package com.project.webapp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.webapp.dto.UserDTO;
import com.project.webapp.entities.User;
import com.project.webapp.repos.UserRepository;
import com.project.webapp.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
 private UserService userService;
 
 public UserController(UserService userService) {
	 this.userService=userService;
	 	 
 }
 
 
 @GetMapping
 public List<User> getAllUsers(){
	return userService.getAllUsers();
 }
 
  @PostMapping
  public User createUser(@RequestBody User newUser) {
	  return userService.saveOneUser(newUser);
  }
  
  @GetMapping ("/{userId}")
  public User getOneUser(@PathVariable Long userId) {
	  return userService.getOneUser(userId);
  }
  
  /*@PutMapping("/{userId}")
  public User updateOneUser(@PathVariable Long userId,@RequestBody User newUser) {
	  Optional<User> user=userRepository.findById(userId);
	  if(user.isPresent()) {
		  User foundUser=user.get();
		 foundUser.setUserName(newUser.getUserName());
		 foundUser.setPassword(newUser.getPassword());
		 userRepository.save(foundUser);
		 return foundUser; 
	  }
	  else 
		  return null;
	  
  }*/
 
  
 
 
}
