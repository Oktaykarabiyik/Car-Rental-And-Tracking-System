package com.project.webapp.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.project.webapp.dto.VehicleDTO;


@RestController
@RequestMapping("/lastMinutes")
public class LastMinutesController {
	
	@GetMapping("/Last30Minutes")
	public VehicleDTO[] getLocationsLast30MinutesByVehicle(@RequestParam("vehicleId") Long vehicleId) {
		
		
		RestTemplate restTemplate = new RestTemplate();
		VehicleDTO[] forObject = restTemplate.getForObject("http://localhost:8080/last/Last30Minutes?vehicleId="+vehicleId, VehicleDTO[].class);
		return forObject;
	}
	
	


}
