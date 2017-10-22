package com.flymegoc.cc.service.impl;

import com.flymegoc.cc.model.User;
import com.flymegoc.cc.model.UserRole;
import com.flymegoc.cc.service.UserRoleRepository;
import com.flymegoc.cc.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("userRoleService")
@Transactional
public class UserRoleServiceImpl implements UserRoleService{

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public List<UserRole> findByUser(User user) {
        return userRoleRepository.findByUser(user);
    }
}
