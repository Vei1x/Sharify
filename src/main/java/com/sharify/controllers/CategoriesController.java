package com.sharify.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String transport(Model model){
        return "list";
    }

    @GetMapping("/electronics")
    public String electronics(Model model){
        return "list";
    }

        @GetMapping("/entertainment")
    public String entertainment(Model model){
        return "list";
    }

        @GetMapping("/construction")
    public String construction(Model model){
        return "list";
    }

        @GetMapping("/home-stuff")
    public String homeStuff(Model model){
        return "list";
    }

        @GetMapping("/other")
    public String other(Model model){
        return "list";
    }
}
