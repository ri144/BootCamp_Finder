package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.UserCamp;

public interface UserCampRepository extends CrudRepository<UserCamp, Long>{

	UserCamp findByUser_Id(long id);

}
