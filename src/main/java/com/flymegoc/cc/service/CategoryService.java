package com.flymegoc.cc.service;

import com.flymegoc.cc.model.Category;

import java.util.List;

public interface CategoryService {
    public Category save(Category category);
    public List<Category> findAll();
}
