package com.flymegoc.cc.service;

import com.flymegoc.cc.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {
    public News save(News news);
    Page<News> findNews(Pageable pageable);
    public News findById(Long newsId);
    List<News> findTop6ByFigureImgUrlNotNullOrderByIdDesc();
}
