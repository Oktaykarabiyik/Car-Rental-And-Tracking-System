package com.example.vehicles.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vehicles.dto.VehicleDTO;
import com.example.vehicles.entities.Vehicle;
import com.example.vehicles.repos.VehicleRepository;

@RestController
@RequestMapping("/last")
public class Last30MinutesController {

	private VehicleRepository vehicleRepository;
    
	public Last30MinutesController(VehicleRepository vehicleRepository) {
	this.vehicleRepository=vehicleRepository;
	}
		
		@GetMapping("/Last30Minutes")
		public List<VehicleDTO> getByLast30Minutes(@RequestParam("vehicleId") Long vehicleId){

			List<Vehicle> vehicleList = vehicleRepository.findByLast30Minutes(vehicleId,LocalDateTime.of(2018, 11, 30, 23, 29),LocalDateTime.of(2018, 11, 30, 23,59) );
			List<VehicleDTO> vehicleDTOList = new ArrayList<>();
			for(Vehicle vehicle:vehicleList) {
				VehicleDTO vehicleDTO = new VehicleDTO();
				vehicleDTO.setVehicleId(vehicle.getVehicleId());
				vehicleDTO.setDate(vehicle.getDate());
				vehicleDTO.setLatitude(vehicle.getLatitude());
				vehicleDTO.setLongitude(vehicle.getLongitude());
				vehicleDTOList.add(vehicleDTO);

			}

			return vehicleDTOList;		
		}


	}