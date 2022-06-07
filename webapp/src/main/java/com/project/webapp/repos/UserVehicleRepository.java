package com.project.webapp.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.webapp.entities.UserVehicle;

public interface UserVehicleRepository extends JpaRepository<UserVehicle, Long> {

	List<UserVehicle> findByUserId(Optional<Long> userId);

}
