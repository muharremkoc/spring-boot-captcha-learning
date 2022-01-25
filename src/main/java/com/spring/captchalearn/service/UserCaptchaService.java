package com.spring.captchalearn.service;

import com.spring.captchalearn.model.User;

import java.util.List;

public interface UserCaptchaService {

    void createUser(User user);
    List<User> getUsers();
    User getUser(int id);

}
