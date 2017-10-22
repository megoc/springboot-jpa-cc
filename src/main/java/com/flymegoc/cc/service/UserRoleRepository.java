package com.flymegoc.cc.service;

import com.flymegoc.cc.model.User;
import com.flymegoc.cc.model.UserRole;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRoleRepository extends PagingAndSortingRepository<UserRole,Long>{
    public List<UserRole> findByUser(User user);
}
