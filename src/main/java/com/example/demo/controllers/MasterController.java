package com.example.demo.controllers;

import com.example.demo.models.Camp;
import com.example.demo.models.City;
import com.example.demo.repositories.CampRepository;
import com.example.demo.repositories.CityRepository;
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

    @RequestMapping("/camp/{id}")
    public String setupCamp(Model model, @PathVariable("id") Long id){
        Camp c = campRepo.findByCampId(id);
        City city =  c.getCity();
        model.addAttribute("camp", c);
        model.addAttribute("city", city.getCity());
        return "camp_page";
    }

}
