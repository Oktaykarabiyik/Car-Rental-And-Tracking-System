package com.project.webapp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.webapp.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {

	User findByUserName(String userName);

}
