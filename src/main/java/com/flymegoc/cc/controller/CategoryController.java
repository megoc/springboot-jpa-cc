package com.flymegoc.cc.controller;

import com.flymegoc.cc.model.Category;
import com.flymegoc.cc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/category")
    public List<Category> findAllCategory(){
        return categoryService.findAll();
    }

    @RequestMapping("/category/save")
    public Category saveCategory(){
        Category category=new Category();
        category.setId(1);
        category.setCategoryName("造型美妆");
        category.setCategoryUrl("http://huaban.com/favorite/modeling_hair/");
        category.setCategoryStatus(0);
        category.setCategoryCreateTime(new Date());
        category.setCategoryUpdateTime(new Date());
        return categoryService.save(category);
    }
}
