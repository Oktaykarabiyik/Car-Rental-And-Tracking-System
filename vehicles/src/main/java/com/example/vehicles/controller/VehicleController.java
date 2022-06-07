package com.example.vehicles.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vehicles.dto.VehicleDTO;
import com.example.vehicles.entities.Vehicle;
import com.example.vehicles.repos.VehicleRepository;



@RestController
@RequestMapping("/vehicle")
public class VehicleController {

	private VehicleRepository vehicleRepository;

	public VehicleController(VehicleRepository vehicleRepository) {
		this.vehicleRepository=vehicleRepository;

	}

	@GetMapping("/getAllVehicle")
	public List<VehicleDTO> getAllVehicle(){
		List<Vehicle> vehicleList = vehicleRepository.findAll();
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

	@GetMapping("/getByDate")
	public List<VehicleDTO> getByDate(@RequestParam("vehicleId") Long vehicleId,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@RequestParam 
	LocalDateTime startDate,
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@RequestParam 
	LocalDateTime endDate){
		
		List<Vehicle> vehicleList = vehicleRepository.findByVehicleIdAndDateBetween(vehicleId, startDate, endDate);
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
