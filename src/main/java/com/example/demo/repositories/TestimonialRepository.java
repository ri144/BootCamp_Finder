package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.example.demo.models.Testimonial;

public interface TestimonialRepository extends CrudRepository<Testimonial, Long> {

	List<Testimonial> findByCamp_CampId(long campId);

}
