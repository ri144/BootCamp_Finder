package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.City;

public interface CityRepository extends CrudRepository<City, Long> {
    City findByCity(String city);
}
