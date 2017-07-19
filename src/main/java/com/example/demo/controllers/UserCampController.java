package com.example.demo.controllers;

import java.security.Principal;
import java.util.List;

import com.example.demo.repositories.RoleRepository;
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
import org.springframework.web.bind.annotation.RequestParam;

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

	@Autowired
    RoleRepository roleRepository;
	@RequestMapping("/myapplication")
	public String listUserStatus(Principal principal, Model model){
		User user = userService.findbyUsername(principal.getName()) ;
		Iterable<UserCamp> usercamp = userCampRepository.findByUser_Id(user.getId());
		model.addAttribute("usercamp", usercamp);
		return "userStatus";
		
	}
	
	@RequestMapping("/viewmycamps")
	public String allAdminCamps(Principal principal, Model model){
		
		User user = userRepo.findByUsername(principal.getName());
		List<Camp> campList = (List<Camp>) campRepo.findAllByAdminIdAndEnabled(user.getId(), true);
		
		model.addAttribute("allCamps", campList);
		return "allCamps";
	}
	
	@RequestMapping("/applicant/{id}/{id2}")
	public String userApplicationDetail(@PathVariable("id") Long id, @PathVariable("id2") Long id2, Principal principal, Model model){
		UserCamp usercamp = userCampRepository.findByUser_IdAndCamp_CampId(id, id2);
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
		}else if(user.getRoles().contains(roleRepository.findByRole("SUPER"))){
            List<UserCamp> applicants = userCampRepository.findByCamp_CampId(campId);
            model.addAttribute("applicants", applicants);
            return "applicants";
        }
		else{
			return "redirect:/camp/" + String.valueOf(campId);
		}
	}
	
	@RequestMapping(path="/accept/{id}/{id2}", method=RequestMethod.POST)
	public String acceptUser(@PathVariable("id") Long id, @PathVariable("id2") Long id2, @RequestParam("status") String status, Principal principal, Model model){
		UserCamp usercamp = userCampRepository.findByUser_IdAndCamp_CampId(id, id2);
		usercamp.setStatus(status);
		Camp camp = usercamp.getCamp();
		List<UserCamp> applicants = userCampRepository.findByCamp_CampId(camp.getCampId());
		userCampRepository.save(usercamp);
		model.addAttribute("applicants", applicants);
		return "applicants";
		
	}

	@RequestMapping("submitApp/{id}")
	public String submit(Model model, Principal principal, @PathVariable("id") Long id){ //id is campid
		User u = userService.findbyUsername(principal.getName());
		boolean check= userCampRepository.existsByUser_IdAndCamp_CampId(u.getId(),id);
		
		if(check==true){
		    return "redirect:/myapplication";
        }else {
            UserCamp userCamp1= new UserCamp();
		    Camp c = campRepo.findByCampId(id);
            userCamp1.setCamp(c);
            userCamp1.setStatus("pending");
            userCamp1.setUser(u);

            userCampRepository.save(userCamp1);
            return "redirect:/myapplication";
        }


	}
	
}
