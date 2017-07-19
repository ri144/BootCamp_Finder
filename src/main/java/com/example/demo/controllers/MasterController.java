package com.example.demo.controllers;

import com.example.demo.models.Camp;
import com.example.demo.models.City;
import com.example.demo.models.Testimonial;
import com.example.demo.models.User;
import com.example.demo.repositories.CampRepository;
import com.example.demo.repositories.CityRepository;
import com.example.demo.repositories.TestimonialRepository;
import com.example.demo.repositories.UserRepository;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by student on 7/17/17.
 */
@Controller
public class MasterController {

    @Autowired
    CampRepository campRepo;

    @Autowired
    CityRepository cityRepo;
    
    @Autowired
    UserRepository userRepo;

    @Autowired
    TestimonialRepository testimonialRepository;

    @RequestMapping("/camp/{id}")
    public String setupCamp(Model model, @PathVariable("id") Long id, Principal principal){
        Camp c = campRepo.findByCampId(id);
        List<Testimonial> testimonials = testimonialRepository.findByCamp_CampId(c.getCampId());
        model.addAttribute("camp", c);
        model.addAttribute("testimonials", testimonials);
        model.addAttribute("user", userRepo.findByUsername(principal.getName()));
        return "camp_page";
    }
   
    @RequestMapping("/campLists")
    public String allCamps(Model model){
    	
    	Iterable<Camp> campList = campRepo.findAllByEnabled(true);
    	model.addAttribute("allCamps", campList);
    	
    	return "allCamps";
    }
    
    @RequestMapping("/camp/byCity")
    public String allCampsByCity(Model model, Principal principal){
    	
    	User user = userRepo.findByEmail(principal.getName());
    	List<Camp> campList = campRepo.findByCity_Id(user.getCity().getId());
    	model.addAttribute("allCamps", campList);
    	
    	return "allCamps";
    }

    @RequestMapping("/admincamps")
    public String viewMyCamps(Principal principal, Model model){
        User user = userRepo.findByUsername(principal.getName());
        Iterable<Camp> campList = campRepo.findAllByAdminId(user.getId());
        model.addAttribute("allCamps", campList);
        model.addAttribute("seeEnabled", true);
        return "allCamps";
    }

    @RequestMapping("/searchCamps")
    public String searchCamps(Model model){
        Iterable<City> citylist = cityRepo.findAll();
        model.addAttribute("cities", citylist);
        return "search";
    }

    @RequestMapping("/findCampsByCity/{id}")
    public String displayCampsByCity(Model model, @PathVariable("id") Long id){
        Iterable<Camp> campList = campRepo.findAllByCity_IdAndEnabled(id, true);
        model.addAttribute("allCamps", campList);
        return "allCamps";
    }
}
