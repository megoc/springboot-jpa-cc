package com.flymegoc.cc.service;

import com.flymegoc.cc.model.User;

public interface UserService {
    User findByUserName(String userName);
    User saveUser(User user);
}
