package com.example.demo.configs;


import com.example.demo.models.City;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.CityRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner{

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CityRepository cityRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Loading data . . .");                         // ...here
        if(cityRepo.findByCity("Chicago, IL") == null) {
            roleRepository.save(new Role("SUPER"));
            roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("USER"));
            cityRepo.save(new City("Chicago, IL"));
            cityRepo.save(new City("Philadelphia, PA"));
            cityRepo.save(new City("New York, NY"));
            cityRepo.save(new City("Columbus, OH"));
            cityRepo.save(new City("Los Angeles, CA"));
            cityRepo.save(new City("Austin, TX"));
            cityRepo.save(new City("Miami, FL"));
            cityRepo.save(new City("Boston, MA"));
            cityRepo.save(new City("Detroit, MI"));
            cityRepo.save(new City("Seattle, WA"));
            //roleRepository.save(new Role("ADMIN"));
            Role superRole = roleRepository.findByRole("SUPER");
            //Role userRole = roleRepository.findByRole("USER");
            User user = new User("BootCampFinderInfo@gmail.com", "JavaGuess", "superman");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList(superRole));
            userRepository.save(user);
            /*user = new User("jim@jim.com","jim","Jim","Jimmerson", true, "jim");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList(userRole));
            userRepository.save(user);
            user = new User("admin@secure.com","password","Admin","User", true, "admin");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList(adminRole));
            userRepository.save(user);
            user = new User("sam@every.com","password","Sam","Everyman", true, "everyman");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList(userRole, adminRole));
            userRepository.save(user);*/
        }
    }
}