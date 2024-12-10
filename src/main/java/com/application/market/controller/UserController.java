package com.application.market.controller;

import com.application.market.entity.User;
import com.application.market.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //verificare daca utilizatorul este logat sau nu
    @GetMapping("/verif")
    public String verif(){
        return "verif";
    }

    @GetMapping("/authentication")
    public String auth(Model model) {
        model.addAttribute("user", new User());
        return "auth";
    }

    @PostMapping("/register")
    public String registerAndLoginRedirection(@Valid @ModelAttribute("user") User user,
                                              BindingResult bindingResult,
                                              @RequestParam("formType") String formType,
                                              Model model) {

            if (bindingResult.hasErrors()) {
                model.addAttribute("user", user);
                return "auth";

            }
            userService.saveUser(user);
            return "redirect:/authentication?success=true";

    }

    @GetMapping("/login")
    public String login() {
        return "redirect:/index";
    }
}
