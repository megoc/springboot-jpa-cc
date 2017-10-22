package com.flymegoc.cc.service;

import com.flymegoc.cc.model.User;
import com.flymegoc.cc.model.UserRole;

import java.util.List;

public interface UserRoleService {
    public List<UserRole> findByUser(User user);
}
