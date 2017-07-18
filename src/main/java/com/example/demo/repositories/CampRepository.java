package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Camp;

public interface CampRepository extends CrudRepository<Camp, Long> {
    Camp findByCampId(Long campid);
    Iterable<Camp> findAllByAdminId(Long adminId);

	List<Camp> findByCity_Id(Long id);
}
