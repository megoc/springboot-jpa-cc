package com.flymegoc.cc.controller;

import com.flymegoc.cc.service.NewsService;
import com.flymegoc.cc.utils.BaseResult;
import com.flymegoc.cc.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;

@RestController
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("/findNews")
    public BaseResult findNews(@RequestParam("page") int page,@RequestParam("pageSize") int pageSize){

        if (page>=1){
            page=page-1;
        }else {
            page=0;
        }

        if (pageSize<10){
            pageSize=10;
        }
        PageRequest pageRequest=new PageRequest(page,pageSize, Sort.Direction.DESC,"id");

        return ResultUtils.getSuccBaseResult(newsService.findNews(pageRequest).getContent());
    }

}
