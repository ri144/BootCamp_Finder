package com.example.demo.controllers;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import javax.mail.internet.InternetAddress;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.google.common.collect.Lists;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AymenController {
	
	@Autowired
	UserRepository userRepository;

    @Autowired
    public EmailService emailService;
    
    public void sendEmailWithoutTemplating(String username, String email2) throws UnsupportedEncodingException {
        final Email email = DefaultEmail.builder()
                .from(new InternetAddress("aymehai@gmail.com", "Admin"))
                .to(Lists.newArrayList(new InternetAddress(email2, username)))
                .subject("goat")
                .body("lel")
                .encoding("UTF-8").build();
        emailService.send(email);
    }
    
    @PostMapping("/moreinfo")
    public String creatememe(Model model, Principal principal) throws UnsupportedEncodingException {
        User newUser = userRepository.findByUsername(principal.getName());
        sendEmailWithoutTemplating(newUser.getFullName(), newUser.getEmail());
        return "gallery";
    }
}