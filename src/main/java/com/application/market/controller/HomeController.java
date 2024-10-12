package com.application.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String homeController() {
        return "index";
    }

    @GetMapping("/not-found")
    public String error404(){
        return "error404";
    }

}