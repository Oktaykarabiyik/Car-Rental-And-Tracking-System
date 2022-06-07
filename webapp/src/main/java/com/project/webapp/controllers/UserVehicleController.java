package com.project.webapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.webapp.entities.UserVehicle;
import com.project.webapp.services.UserVehicleService;

@CrossOrigin(origins="http://localhost:3000",maxAge = 3600)
@RestController
@RequestMapping("/userVehicle")
public class UserVehicleController {

	private UserVehicleService userVehicleService;

	public UserVehicleController(UserVehicleService userVehicleService) {
		this.userVehicleService = userVehicleService;
	}

	@GetMapping
	public List<UserVehicle> getAllUserVehicles(@RequestParam Optional<Long> userId){
		return userVehicleService.getAllUserVehicles(userId);

	}
	@GetMapping("/{userVehicleId}")
	public UserVehicle getOneUserVehicle(@PathVariable Long userVehicleId) {
		return userVehicleService.getOneUserVehicleId(userVehicleId);
	}

}
