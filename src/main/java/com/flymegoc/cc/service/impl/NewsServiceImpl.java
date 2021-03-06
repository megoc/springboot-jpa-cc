package com.flymegoc.cc.service.impl;

import com.flymegoc.cc.model.News;
import com.flymegoc.cc.service.NewsRepository;
import com.flymegoc.cc.service.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("newsService")
@Transactional
public class NewsServiceImpl implements NewsService{

    private final NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public News save(News news) {
        return newsRepository.save(news);
    }

    @Override
    public Page<News> findNews(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    @Override
    public News findById(Long newsId) {
        return newsRepository.findOne(newsId);
    }

    @Override
    public List<News> findTop6ByFigureImgUrlNotNullOrderByIdDesc() {
        return newsRepository.findTop6ByFigureImgUrlNotNullOrderByIdDesc();
    }

    @Override
    public void addNewsReadCounts(Long newsId) {
        News news=newsRepository.findOne(newsId);
        if (news!=null){
            news.setViewCount(news.getViewCount()+1);
        }
    }
}
