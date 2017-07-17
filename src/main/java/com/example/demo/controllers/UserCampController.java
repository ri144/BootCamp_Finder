package com.example.demo.controllers;

import java.security.Principal;

import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.User;
import com.example.demo.models.UserCamp;
import com.example.demo.repositories.UserCampRepository;
import com.example.demo.repositories.UserRepository;

@Controller
public class UserCampController {
	
	@Autowired
	UserCampRepository userCampRepository;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/myApp")
	public String listUserStatus(Principal principal, Model model){
		User user = userService.findByEmail(principal.getName()) ;
		UserCamp usercamp = userCampRepository.findByUser_Id(user.getId());
		model.addAttribute("usercamp", usercamp);
		return "userStatus";
		
	}
	
	
}
