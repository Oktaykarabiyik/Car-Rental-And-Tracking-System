package com.example.vehicles;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.vehicles.entities.Vehicle;
import com.example.vehicles.repos.VehicleRepository;

@SpringBootApplication
public class VehiclesApplication implements CommandLineRunner {
	@Autowired
	private VehicleRepository vehicleRepository;

	public static void main(String[] args) {
		SpringApplication.run(VehiclesApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	//List<Vehicle>vehicles=vehicleRepository.findAll();
	//vehicles.forEach(vehicle->System.out.println(vehicle.getLatitude()));
		
	System.out.println("Vehicle 7");
	//List<Vehicle>vehicle7=vehicleRepository.findByLast30Minutes(5,LocalDateTime.of(2018, 11, 30, 23, 29) ,LocalDateTime.of(2018, 11, 30, 23, 59));
	//vehicle7.forEach(vehicle->System.out.println(vehicle.getLatitude()));
	//System.out.println("Vehicle 12");
	//List<Vehicle>vehicle12=vehicleRepository.findByLast30Minutes(12,LocalDateTime.of(2018, 11, 30, 16, 30) ,LocalDateTime.of(2018, 11, 30, 17, 04));
	//vehicle12.forEach(vehicle->System.out.println(vehicle.getLatitude()));
	}

}
