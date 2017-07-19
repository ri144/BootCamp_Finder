package com.example.demo.configs;


import com.example.demo.models.Role;
import com.example.demo.models.User;
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
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception {
        /*System.out.println("Loading data . . .");                         // ...here
        roleRepository.save(new Role("SUPER"));
        //roleRepository.save(new Role("ADMIN"));
        Role adminRole = roleRepository.findByRole("SUPER");
        //Role userRole = roleRepository.findByRole("USER");
        User user = new User("BootCampFinderInfo@gmail.com","JavaGuess","superman");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(adminRole));
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