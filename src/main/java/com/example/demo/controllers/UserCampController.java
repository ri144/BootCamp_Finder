package com.example.demo.controllers;

import java.security.Principal;
import java.util.List;

import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.models.Camp;
import com.example.demo.models.User;
import com.example.demo.models.UserCamp;
import com.example.demo.repositories.CampRepository;
import com.example.demo.repositories.UserCampRepository;
import com.example.demo.repositories.UserRepository;

@Controller
public class UserCampController {
	
	@Autowired
	UserCampRepository userCampRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CampRepository campRepo;
	
	@RequestMapping("/myApp")
	public String listUserStatus(Principal principal, Model model){
		User user = userService.findByEmail(principal.getName()) ;
		UserCamp usercamp = userCampRepository.findByUser_Id(user.getId());
		model.addAttribute("usercamp", usercamp);
		return "userStatus";
		
	}
	
	@RequestMapping("/viewmycamps")
	public String allAdminCamps(Principal principal, Model model){
		
		User user = userRepo.findByEmail(principal.getName());
		List<Camp> campList = (List<Camp>) campRepo.findAllByAdminIdAndEnabled(user.getId(), true);
		
		model.addAttribute("allCamps", campList);
		return "allCamps";
	}
	
	@RequestMapping("/applicant/{id}")
	public String userApplicationDetail(@PathVariable("id") Long id,  Principal principal, Model model){
		
		
		UserCamp usercamp = userCampRepository.findByUser_Id(id);
		model.addAttribute("usercamp", usercamp);
		return "userApplicationDetail";
		
	}
	
	@RequestMapping("/allApplicants/{campId}")
	public String appliedUsers(@PathVariable("campId") Long campId, Principal principal, Model model){
		
		User user = userService.findbyUsername(principal.getName()) ;
		Camp camp = campRepo.findByCampIdAndAdminId(campId, user.getId());
		
		if(camp!=null){
			List<UserCamp> applicants = userCampRepository.findByCamp_CampId(campId);
			model.addAttribute("applicants", applicants);
			return "applicants";
		}else{
			return "index";
		}
	}
	
	@RequestMapping(path="/accept/{id}", method=RequestMethod.POST)
	public String acceptUser(@PathVariable("id") Long id, String status, Principal principal, Model model){
		
		
		UserCamp usercamp = userCampRepository.findByUser_Id(id);
		usercamp.setStatus(status);
		Camp camp = usercamp.getCamp();
		List<UserCamp> applicants = userCampRepository.findByCamp_CampId(camp.getCampId());
		
		model.addAttribute("applicants", applicants);
		return "applicants";
		
	}
	
}
