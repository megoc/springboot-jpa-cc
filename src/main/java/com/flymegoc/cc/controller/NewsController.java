package com.flymegoc.cc.controller;

import com.flymegoc.cc.model.News;
import com.flymegoc.cc.service.NewsService;
import com.flymegoc.cc.utils.BaseResult;
import com.flymegoc.cc.utils.CnbateUtils;
import com.flymegoc.cc.utils.ResultUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

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

    @RequestMapping("/getTopFigureNews")
    public BaseResult getTopFigureNews(){
        return ResultUtils.getSuccBaseResult(newsService.findTop6ByFigureImgUrlNotNullOrderByIdDesc());
    }

    @RequestMapping("/getNewsContent")
    public BaseResult getNewsContent(@RequestParam("newsId") long newsId) throws UnsupportedEncodingException {
        News news=newsService.findById(newsId);
        String content="";
        if (news.getContent()==null||news.getContent().equals("")){
            content=CnbateUtils.getNewsContent(news.getContentUrl());
            news.setContent(content);
            newsService.save(news);
            System.out.println("解析网页");
        }else {
            content= news.getContent();
            System.out.println("获取缓存");
        }

        return ResultUtils.getSuccBaseResult(content);
    }
}
