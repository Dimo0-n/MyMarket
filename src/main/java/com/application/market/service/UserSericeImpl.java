package com.application.market.service;

import com.application.market.entity.User;
import com.application.market.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

@Service
public class UserSericeImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        User newUser = new User();

        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setRegisterDate(LocalDateTime.now());
        newUser.setLastLoginDate(LocalDateTime.now());

        userRepository.save(newUser);

    }

    @Override
    public void updateLastLoginDate(String email) {
        userRepository.findByEmail(email).
                ifPresent(user -> {user.setLastLoginDate(LocalDateTime.now());
                userRepository.save(user);
                });
    }
}
