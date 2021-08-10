package com.sharify.controllers;

import com.sharify.models.Role;
import com.sharify.models.User;
import com.sharify.repos.UserRepo;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class SharifyController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String index(){
        return "home";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String registration(){
        return "register";
    }

    @PostMapping("/register")
    public String addUser(User user, Model model){
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if(userFromDb != null){
            model.addAttribute("message", "User exists!");
            return "register";
        }
        user.setActive(true);
        user.setPassword(user.getPassword());
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }

}
