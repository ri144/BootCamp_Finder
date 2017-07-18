package com.example.demo.controllers;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import com.example.demo.models.Camp;
import com.example.demo.models.User;
import com.example.demo.repositories.CampRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import com.google.common.collect.Lists;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AymenController {
	
	@Autowired
	UserService userService;

	@Autowired
	CampRepository campRepository;
	
	@Autowired
	UserRepository userRepository;
	
    @Autowired
    public EmailService emailService;
    
    //This method sends an email to the Camp Admin
    public void sendEmailWithoutTemplating(String currentUserName, String currentUserEmail, String adminName, String adminEmail, String body, String subject) throws UnsupportedEncodingException {
        final Email email = DefaultEmail.builder()
                .from(new InternetAddress(currentUserEmail, currentUserName))
                .to(Lists.newArrayList(new InternetAddress(adminEmail, adminName)))
                .subject(subject)
                .body(body)
                .encoding("UTF-8").build();
        emailService.send(email);
    }
    
    //This Mapping Receives the Camp
    @GetMapping("/email/{id}")
    public String RequestMoreInfo(Model model, @PathVariable("id") Long id){ //path id is for camp
    	model.addAttribute("id", id);
    	return "moreInfo";
    }
    
    @PostMapping("/moreInfo/{id}")
    public String moreInfoPage(@PathVariable("id") Long id, Model model, Principal principal, HttpServletRequest getSubject, HttpServletRequest getBody) throws UnsupportedEncodingException{
    	
    	//Gets logged in Users information
    	User newUser = userRepository.findByUsername(principal.getName());
    	String currentUserEmail = newUser.getEmail();
    	String currentUserName = newUser.getFullName();
    	
    	System.out.println(currentUserEmail);
    	//uses camp.camp_id and assigns it to Camp currentCamp
    	Camp currentCamp = campRepository.findByCampId(id);
    	
    	//gets currentCamp's admin ID and assigns it to User campInfo
    	User campInfo = userService.findById(currentCamp.getAdminId());
    	
    	//Gets HttpServletRequest and assigns it to String subject & body
    	String subject = getSubject.getParameter("subject");
    	String body = getBody.getParameter("body") + "\n \n To respond to this user please email: " + currentUserEmail;
    	System.out.println(body);
    	//Uses method sendEmailWithoutTemplating
    	sendEmailWithoutTemplating(currentUserName, currentUserEmail, campInfo.getFullName(), campInfo.getEmail(), body, subject);
    	return "redirect:/";
    }
}