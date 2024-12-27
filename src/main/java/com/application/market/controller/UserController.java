package com.application.market.controller;

import com.application.market.entity.User;
import com.application.market.repository.UserRepository;
import com.application.market.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    //verificare daca utilizatorul este logat sau nu
    @GetMapping("/verif")
    public String verif(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        System.out.println(email);
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
                                              Model model) {

        Optional<User> existingUserByEmail = userRepository.findByEmail(user.getEmail());

        if (existingUserByEmail.isPresent()) {
            bindingResult.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "redirect:/authentication?error";
        }

        userService.saveUser(user);

        return "redirect:/authentication?success";
    }

    @GetMapping("/login")
    public String login() {
        return "redirect:/index";
    }
}
