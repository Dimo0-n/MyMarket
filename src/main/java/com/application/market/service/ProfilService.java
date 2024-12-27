package com.application.market.service;

import com.application.market.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface ProfilService {

    User updateUserData(User user, String email);

}
