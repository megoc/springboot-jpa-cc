package com.flymegoc.cc.service;

import com.flymegoc.cc.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User,Long>{
    public User findByUserName(String userName);
}
