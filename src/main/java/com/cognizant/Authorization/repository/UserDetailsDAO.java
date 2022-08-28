package com.cognizant.Authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.Authorization.model.User;


@Repository
public interface UserDetailsDAO extends JpaRepository<User, Integer>  {
	
	User findByUsername(String username);
}
