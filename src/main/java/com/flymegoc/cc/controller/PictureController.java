package com.flymegoc.cc.controller;

import com.flymegoc.cc.model.Category;
import com.flymegoc.cc.model.Picture;
import com.flymegoc.cc.service.PictureService;
import com.flymegoc.cc.utils.BaseResult;
import com.flymegoc.cc.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/findPictures")
    public BaseResult findPictures(@RequestParam("categoryId") int categoryId, @RequestParam("page") int page,@RequestParam("pageSize") int pageSize){
        return queryPicture(categoryId,page,pageSize);
    }

    @RequestMapping("/findPictures/categoryId/{categoryId}/page/{page}/pageSize/{pageSize}")
    public BaseResult findPicturesRest(@PathVariable("categoryId") int categoryId, @PathVariable("page") int page,@PathVariable("pageSize") int pageSize){
        return queryPicture(categoryId,page,pageSize);
    }

    //查询图片数据，页数从0开始
    private BaseResult<Picture> queryPicture(int categoryId,int page,int pageSize){

        //默认categoryId为1
        if (categoryId<=0){
            categoryId=1;
        }
        if (page>=1){
            page=page-1;
        }else {
            page=0;//第一页
        }
        //默认分页10
        if (pageSize<=0){
            pageSize=10;
        }

        Category category=new Category();
        category.setId(categoryId);

        PageRequest pageRequest=new PageRequest(page,pageSize, Sort.Direction.DESC,"pictureCreateTime");

        Page<Picture> picturePage=pictureService.findByCategory(category,pageRequest);

        return ResultUtils.getSuccBaseResult(picturePage.getContent());
    }
}
