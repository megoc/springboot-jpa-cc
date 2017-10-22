package com.flymegoc.cc.service;

import com.flymegoc.cc.model.User;

public interface UserService {
    public User findByUserName(String userName);
}
