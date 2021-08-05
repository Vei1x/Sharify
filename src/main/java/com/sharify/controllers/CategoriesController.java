package com.sharify.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoriesController {

    @GetMapping("/")
    public String home(){
        return "redirect:/";
    }

    @GetMapping("/transport")
    public String transport(){
        return "";
    }

    @GetMapping("/electronics")
    public String electronics(){
        return "";
    }

        @GetMapping("/entertainment")
    public String entertainment(){
        return "";
    }

        @GetMapping("/construction")
    public String construction(){
        return "";
    }

        @GetMapping("/home-stuff")
    public String homeStuff(){
        return "";
    }

        @GetMapping("/other")
    public String other(){
        return "";
    }
}
