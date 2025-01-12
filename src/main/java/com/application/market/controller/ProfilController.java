package com.application.market.controller;

import com.application.market.entity.User;
import com.application.market.repository.UserRepository;
import com.application.market.service.ProfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class ProfilController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ProfilService profilService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/profil")
    public String profil(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Optional<User> userProfile = userRepository.findByEmail(email);

        User user = userProfile.get();
        model.addAttribute("user", user);

        return "profil";
    }

    @PostMapping("/updateProfile")
    public String updateUserData(@ModelAttribute("user") User user, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String oldEmail = authentication.getName();

        try {
            User updatedUser = profilService.updateUserData(user, oldEmail);

            Authentication newAuth = new UsernamePasswordAuthenticationToken(
                    updatedUser.getEmail(),
                    authentication.getCredentials(),
                    authentication.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(newAuth);

            model.addAttribute("user", updatedUser);
            model.addAttribute("successMessage", "Profile updated successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while updating your profile.");
            return "profil";
        }

        return "redirect:/profil";
    }

}
