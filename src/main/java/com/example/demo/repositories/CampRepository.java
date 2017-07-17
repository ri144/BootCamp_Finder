package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Camp;

public interface CampRepository extends CrudRepository<Camp, Long> {

}
