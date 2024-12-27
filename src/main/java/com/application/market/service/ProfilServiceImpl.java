package com.application.market.service;

import com.application.market.entity.User;
import com.application.market.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ProfilServiceImpl implements ProfilService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User updateUserData(User newUserData, String email) {

        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        User user = userOptional.get();

        if (newUserData.getName() != null) user.setName(newUserData.getName());
        if (newUserData.getSurname() != null) user.setSurname(newUserData.getSurname());
        if (newUserData.getEmail() != null) user.setEmail(newUserData.getEmail());

        if (newUserData.getNotificationFrequency() != null) {
            user.setNotificationFrequency(newUserData.getNotificationFrequency());
        }
        user.setNotifyDiscounts(newUserData.isNotifyDiscounts());
        user.setNotifyNewProducts(newUserData.isNotifyNewProducts());
        user.setNotifyNews(newUserData.isNotifyNews());
        user.setNotifyWeeklyUpdates(newUserData.isNotifyWeeklyUpdates());

        return userRepository.save(user);
    }

}
