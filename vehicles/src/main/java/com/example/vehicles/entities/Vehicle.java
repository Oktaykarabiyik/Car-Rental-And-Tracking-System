package com.example.vehicles.entities;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Vehicle {
	@Id
	private ObjectId id;
	private Long vehicleId;
	private LocalDateTime date;
	private String latitude;
	private String longitude;
	

	public Vehicle() {

	}

	public Vehicle( LocalDateTime date, String latitude, String longitude, Long vehicleId) {
		super();
		
		this.date = date;
		this.latitude = latitude;
		this.longitude = longitude;
		this.vehicleId = vehicleId;
	}


	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}



}
