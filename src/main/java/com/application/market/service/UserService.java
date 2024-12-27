package com.application.market.service;

import com.application.market.entity.User;

public interface UserService {

    void saveUser(User user);

    void updateLastLoginDate(String email);

}
