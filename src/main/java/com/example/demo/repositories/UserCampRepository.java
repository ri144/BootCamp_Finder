package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.UserCamp;

public interface UserCampRepository extends CrudRepository<UserCamp, Long>{

	Iterable<UserCamp>
	findByUser_Id(long id);

	List<UserCamp> findByCamp_CampId(Long campId);

	UserCamp findByUser_IdAndCamp_CampId(Long userid, long campid);

	boolean existsByUser_IdAndCamp_CampId(long id, Long id2);

}
