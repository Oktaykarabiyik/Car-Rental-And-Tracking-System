package com.example.vehicles.repos;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.vehicles.entities.Vehicle;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle,Long> {
	
	@Query("{'vehicleId' : ?0 , 'date' : { $gte: ?1, $lte: ?2 } }")
	public List<Vehicle> findByVehicleIdAndDateBetween(Long vehicleId,LocalDateTime from,LocalDateTime to);

	@Query("{'vehicleId' : ?0 , 'date' : {$gte: ?1, $lte: ?2  } }")      
	public List<Vehicle> findByLast30Minutes(Long vehicleId,LocalDateTime from,LocalDateTime to);
	 
	
}
