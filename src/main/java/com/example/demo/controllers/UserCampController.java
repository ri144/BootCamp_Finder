package com.example.demo.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	UserRepository userRepository;
	
	@RequestMapping("/myApp")
	public String listUserStatus(Principal principal, Model model){
		
		User user = userRepository.findByEmail( principal.getName()) ;
		UserCamp usercamp = userCampRepository.findOne(user.getId());
		
		return null;
		
	}
	
	
}
