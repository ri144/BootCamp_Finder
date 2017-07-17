package com.example.demo.controllers;


import com.example.demo.models.*;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * Created by student on 7/10/17.
 */
@Controller
public class KCController {

    @Autowired
    CampRepository campRepository;

    @Autowired
    CityRepository cityRepository;



    @GetMapping("/a")//change later
    public String registerCamp(Model model){
        model.addAttribute("camp", new Camp());
        Iterable<City> cityList = cityRepository.findAll();
        model.addAttribute("cityList", cityList);
        return "createcamp";
    }


    @PostMapping("/createcamp")//change later
    public String registerCampSave(@RequestParam("cityId") long cityId, @RequestParam("sDate") String sDateString,
                                   @RequestParam("eDate") String eDateString, @ModelAttribute Camp camp, Model model){
        model.addAttribute("camp", camp);
        //convert date from Strings to sql dates
        Date start = stringToDate(sDateString);
        Date end = stringToDate(eDateString);
        //convert find the city from thesubmitted city ID
        City city = cityRepository.findOne(cityId);
        //Saves the information
        camp.setStartDate(start);
        camp.setEndDate(end);
        camp.setCity(city);
        camp.setEnabled(false);
        camp.setAdminId(1);//change to user id later
        campRepository.save(camp);
        return "index";
    }

    @RequestMapping("/b")//change later
    public String enableCamps(Model model){
        Iterable<Camp> campList = campRepository.findAll();
        model.addAttribute("campList", campList);
        return "campenable";
    }

    @RequestMapping("/campenable/{id}")//change later
    public String enableCampsClick(@PathVariable("id") long id, Model model){
       Camp camp = campRepository.findOne(id);
       camp.setEnabled(!camp.isEnabled());
       campRepository.save(camp);
       return "redirect:/b";
    }


    public Date stringToDate(String dateString){
        //Converts a string to SQL date
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
            java.util.Date date = df.parse(dateString);
            return new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
