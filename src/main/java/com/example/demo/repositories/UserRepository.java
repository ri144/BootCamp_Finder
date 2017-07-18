package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.User;

public interface UserRepository extends CrudRepository<User,Long>{

	User findByEmail(String name);

	User findByUsername(String name);
	
	Long countByEmail(String email);

	int countByUsername(String name);

	

}
