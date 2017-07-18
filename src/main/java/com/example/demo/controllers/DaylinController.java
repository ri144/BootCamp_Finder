package com.example.demo.controllers;

import com.example.demo.models.Camp;
import com.example.demo.models.City;
import com.example.demo.models.Testimonial;
import com.example.demo.repositories.CampRepository;
import com.example.demo.repositories.TestimonialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by daylinhenry on 7/17/17.
 */

@Controller
public class DaylinController {

    @Autowired
    CampRepository campRepo;

    @Autowired
    TestimonialRepository testimonialRepository;

    @RequestMapping("/ccamp")
    public String index(Model model){
        Camp c = campRepo.findByCampId(2l);
        City city =  c.getCity();
        List<Testimonial> testimonials = testimonialRepository.findByCamp_CampId(c.getCampId());
        model.addAttribute("camp", c);
        model.addAttribute("testimonials", testimonials);
        model.addAttribute("city", city.getCity());
        return "camp_page";
    }


    @RequestMapping("/")
    public String home(Model model){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

}
