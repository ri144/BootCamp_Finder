package com.example.demo.validators;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by daylinhenry on 7/17/17.
 */
@Component
public class UserValidator implements Validator {
    @Autowired
    UserRepository userRepository;
    public boolean supports(Class<?> clazz){
        return User.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors){
        User user = (User) target;
        String email = user.getEmail();
        String username = user.getUsername();
        String password = user.getPassword();
        Collection<Role> roles = user.getRoles();
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "user.name.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "user.username.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "user.email.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.empty");
        if(userRepository.countByEmail(email)>0){
            errors.rejectValue("email","user.email.duplicate");
        }
        if(userRepository.countByUsername(username)>0){
            errors.rejectValue("username","user.username.duplicate");
        }
        if(password.length() < 5){
            errors.rejectValue("password","user.password.tooShort");
        }
        /*if(authority == null || authority.isEmpty()){
            errors.rejectValue("authority","user.authority.notChoose");
        }*/
    }
}