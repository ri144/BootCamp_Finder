package com.example.demo.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import it.ozimov.springboot.mail.configuration.EnableEmailTools;

@SpringBootApplication
@EnableEmailTools
public class AymenController {
	public static void main(final String... args) {
		SpringApplication.run(AymenController.class, args);
	}
}