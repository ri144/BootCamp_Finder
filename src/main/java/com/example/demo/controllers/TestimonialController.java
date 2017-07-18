package com.example.demo.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.models.Camp;
import com.example.demo.models.Testimonial;
import com.example.demo.models.User;
import com.example.demo.repositories.CampRepository;
import com.example.demo.repositories.TestimonialRepository;
import com.example.demo.repositories.UserRepository;

@Controller
public class TestimonialController {
	
	@Autowired
	private TestimonialRepository testimonialRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CampRepository campRepository;
	
	
	@RequestMapping(path= "/test/{campId}")
	public String addTestimonial(@PathVariable("campId") Long campId, Model model, Principal principal){
		Camp camp = campRepository.findByCampId(campId);
		model.addAttribute("test", new Testimonial());
		List<Testimonial> testimonials = testimonialRepository.findByCamp_CampId(campId);
		model.addAttribute("camp", camp);
		model.addAttribute("testimonials", testimonials);
		return "camp_page";
	}

	
	@RequestMapping(path= "/test/save", method=RequestMethod.POST)
	public String saveTestimonial(@ModelAttribute("testimonial") Testimonial testimonial, Long campId,  Model model, Principal principal){
		
		User user = userService.findbyUsername(principal.getName());
		
		Camp camp = campRepository.findByCampId(campId);
		
	
		System.out.println(campId);
		testimonial.setDate(new Date());
		testimonial.setCamp(camp);
		testimonial.setUser(user);
		testimonialRepository.save(testimonial);
		
		List<Testimonial> testimonials = testimonialRepository.findByCamp_CampId(camp.getCampId());
		
		model.addAttribute("camp", camp);
		model.addAttribute("testimonials", testimonials);
		
		return "camp_page";
		
	}

}
