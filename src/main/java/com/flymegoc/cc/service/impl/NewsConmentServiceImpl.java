package com.flymegoc.cc.service.impl;

import com.flymegoc.cc.service.NewsConmentRepository;
import com.flymegoc.cc.service.NewsConmentService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("newsConmentService")
@Transactional
public class NewsConmentServiceImpl implements NewsConmentService{

    private final NewsConmentRepository newsConmentRepository;

    public NewsConmentServiceImpl(NewsConmentRepository newsConmentRepository) {
        this.newsConmentRepository = newsConmentRepository;
    }
}
