package com.project.webapp.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.project.webapp.dto.VehicleDTO;

@CrossOrigin(origins="http://localhost:3000",maxAge = 3600)
@RestController
@RequestMapping("/locations")
public class LocationController {

	@GetMapping("/car")
	public VehicleDTO[] Test() {
		RestTemplate restTemplate = new RestTemplate();
		VehicleDTO[] forObject = restTemplate.getForObject("http://localhost:8080/vehicle", VehicleDTO[].class);
		
		return forObject;
	}
	
	@GetMapping("/getByDate")
	public VehicleDTO[] getLocationsByVehicle(@RequestParam("vehicleId") Long vehicleId,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
			@RequestParam 
			LocalDateTime startDate,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
			@RequestParam 
			LocalDateTime endDate) {
		
		
		RestTemplate restTemplate = new RestTemplate();
		VehicleDTO[] forObject = restTemplate.getForObject("http://localhost:8080/vehicle/getByDate?vehicleId="+vehicleId+"&startDate="+startDate+"&endDate="+endDate, VehicleDTO[].class);
		return forObject;
	}
	
	
	
	

}
