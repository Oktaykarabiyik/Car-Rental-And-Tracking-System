package com.project.webapp.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.webapp.entities.User;
import com.project.webapp.repos.UserRepository;

@Service
public class UserService {
	UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getOneUser(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}
	public User getOneUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
	public User saveOneUser(User newUser) {
		return userRepository.save(newUser);
	}
	
	public void createUser(Long id, String userName, String password) {
		User user = new User();
		user.setId(id);
		user.setUserName(userName);
		user.setPassword(passwordEncoder.encode(password));
		userRepository.save(user);
	}
}
