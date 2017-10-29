package com.flymegoc.cc.service;

import com.flymegoc.cc.model.News;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface NewsRepository extends PagingAndSortingRepository<News,Long>{
    List<News> findTop6ByFigureImgUrlNotNullOrderByIdDesc();
}
