package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Testimonial;

public interface TestimonialRepository extends CrudRepository<Testimonial, Long> {

}
