package com.example.demo.controllers;

import com.example.demo.models.Camp;
import com.example.demo.models.City;
import com.example.demo.repositories.CampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by daylinhenry on 7/17/17.
 */

@Controller
public class DaylinController {

    @Autowired
    CampRepository campRepo;

    @RequestMapping("/")
    public String index(Model model){
        Camp c = campRepo.findByCampId(1l);
        City city =  c.getCity();
        model.addAttribute("camp", c);
        model.addAttribute("city", city.getCity());
        return "camp_page";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

}
