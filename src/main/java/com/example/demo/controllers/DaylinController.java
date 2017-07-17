package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by daylinhenry on 7/17/17.
 */

@Controller
public class DaylinController {


    @RequestMapping("/")
    public String index(){
        return "login";
    }



}
