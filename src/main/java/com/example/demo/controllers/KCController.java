package com.example.demo.controllers;


import com.example.demo.models.*;
import com.example.demo.repositories.*;
import com.example.demo.services.*;
import com.example.demo.validators.UserValidator;
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
import java.util.Date;
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

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserService userService;



    @GetMapping("/createcamp")//change later
    //Show page that admins can register a camp
    public String registerCamp(Model model, Principal principal){
        Camp temp = new Camp();
        temp.setAdminId((userService.findbyUsername(principal.getName())).getId());
        temp.setStartDate(new Date());
        temp.setEndDate(new Date());
        temp.setCity(cityRepository.findOne((long)1));
        temp.setEnabled(false);
        model.addAttribute("camp", temp);
        Iterable<City> cityList = cityRepository.findAll();
        model.addAttribute("cityList", cityList);
        return "createcamp";
    }


    @PostMapping("/createcamp")
    //registercamp clicks submit button
    public String registerCampSave(@RequestParam("cityId") long cityId, @RequestParam("sDate") String sDateString,
                                   @RequestParam("eDate") String eDateString, @ModelAttribute Camp camp, Model model){
        model.addAttribute("camp", camp);

        //convert date from Strings to sql dates
        Date start = stringToDate(sDateString);
        Date end = stringToDate(eDateString);
        //find the city from the submitted city ID
        City city = cityRepository.findOne(cityId);
        //Saves the information
        camp.setStartDate(start);
        camp.setEndDate(end);
        camp.setCity(city);
        //camp.setEnabled(false);
        campRepository.save(camp);
        return "redirect:/";
    }

    @RequestMapping("/enable")//change later
    //superadmin sees all camps and can enable and disable
    public String enableCamps(Model model){
        Iterable<Camp> campList = campRepository.findAll();
        model.addAttribute("campList", campList);
        return "campenable";
    }

    @RequestMapping("/campenable/{id}")//change later
    //Click on the camp to enable/disable it
    public String enableCampsClick(@PathVariable("id") long id, Model model){
       Camp camp = campRepository.findOne(id);
       camp.setEnabled(!camp.isEnabled());
       campRepository.save(camp);
       return "redirect:/enable";
    }


    @GetMapping("/editcamp/{id}")//change later
    //edit this specific camp
    public String editCampSubmit(@PathVariable("id") long id, Model model, Principal principal){
        Camp camp = campRepository.findOne(id);
        User user = userService.findbyUsername(principal.getName());
        if(user.getId() != camp.getAdminId()){
            return "redirect:/camp/" + String.valueOf(id);
        }
        model.addAttribute("camp", camp);
        Iterable<City> cityList = cityRepository.findAll();
        model.addAttribute("cityList", cityList);
        model.addAttribute("edit", true);
        model.addAttribute("id", id);
        return "createcamp";
    }
    @PostMapping("/editcamp/{id}")//change later
    //edit this specific camp-click submit button
    public String editCamp(@PathVariable("id") long campId, @RequestParam("cityId") long cityId, @RequestParam("sDate") String sDateString,
                           @RequestParam("eDate") String eDateString, @ModelAttribute Camp camp, Model model){
        model.addAttribute("camp", camp);
        Camp fromdb = campRepository.findByCampId(campId);//retrieve current camp values from database
        //convert date from Strings to sql dates
        Date start = stringToDate(sDateString);
        Date end = stringToDate(eDateString);
        //find the city from the submitted city ID
        City city = cityRepository.findOne(cityId);
        //Saves the information
        camp.setStartDate(start);
        camp.setEndDate(end);
        camp.setCity(city);
        campRepository.save(camp);
        return "redirect:/";
    }

    @GetMapping("/registeruser")
    //Show registration page
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        Iterable<City> cityList = cityRepository.findAll();
        model.addAttribute("cityList", cityList);
        return "registeruser";
    }

    @PostMapping("/registeruser")
    //saves the account into the database
    public String saveAccount(@RequestParam("cityId") long cityId, @RequestParam("role") String role,  @Valid User user, BindingResult result, Model model){
        model.addAttribute("user", user);
        userValidator.validate(user,result);
        if (result.hasErrors()){
            Iterable<City> cityList = cityRepository.findAll();
            model.addAttribute("cityList", cityList);
            return "registeruser";
        }

        user.setCity(cityRepository.findOne(cityId));

        userService.saveAccount(user,role);
        return "redirect:/login";
    }


    public Date stringToDate(String dateString){
        //Converts a string to java date
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        try {
            return df.parse(dateString);
            //return new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
