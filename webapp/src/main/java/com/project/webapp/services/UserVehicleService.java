package com.project.webapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.webapp.entities.UserVehicle;
import com.project.webapp.repos.UserVehicleRepository;

@Service
public class UserVehicleService {
	
	private UserVehicleRepository userVehicleRepository;
	

	public UserVehicleService(UserVehicleRepository userVehicleRepository) {
		this.userVehicleRepository = userVehicleRepository;
	}


	public List<UserVehicle> getAllUserVehicles(Optional<Long> userId) {
		if(userId.isPresent())
		{
			return userVehicleRepository.findByUserId(userId);
		}
		return userVehicleRepository.findAll();
	}


	public UserVehicle getOneUserVehicleId(Long userVehicleId) {
		return userVehicleRepository.findById(userVehicleId).orElse(null);
	}




	

}
