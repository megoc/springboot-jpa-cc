package com.flymegoc.cc.service.impl;

import com.flymegoc.cc.model.User;
import com.flymegoc.cc.service.UserRepository;
import com.flymegoc.cc.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component("userService")
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
