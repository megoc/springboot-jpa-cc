package com.flymegoc.cc.controller;

import com.flymegoc.cc.model.Category;
import com.flymegoc.cc.service.CategoryService;
import com.flymegoc.cc.utils.BaseResult;
import com.flymegoc.cc.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/findCategory")
    public BaseResult findAllCategory(){
        return ResultUtils.getSuccBaseResult(categoryService.findAll());
    }

}
