package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Camp;

public interface CampRepository extends CrudRepository<Camp, Long> {
    Camp findByCampId(Long campid);
    Iterable<Camp> findAllByAdminIdAndEnabled(Long adminId, boolean enabled);
    Iterable<Camp> findAllByEnabled(boolean enabled);
	List<Camp> findByCity_Id(Long id);
	Camp findByCampIdAndAdminId(Long campId, long id);
	Iterable<Camp> findAllByAdminId(Long adminid);
	Iterable<Camp> findAllByCity_IdAndEnabled(Long id, boolean enabled);
}
