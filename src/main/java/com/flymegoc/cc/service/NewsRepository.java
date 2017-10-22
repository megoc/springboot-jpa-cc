package com.flymegoc.cc.service;

import com.flymegoc.cc.model.News;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NewsRepository extends PagingAndSortingRepository<News,Long>{

}
