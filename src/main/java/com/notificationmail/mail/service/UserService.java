package com.notificationmail.mail.service;

import com.notificationmail.mail.model.entity.User;

public interface UserService {
    User saveUser(User user);
    Boolean verifyToken(String token);
}
