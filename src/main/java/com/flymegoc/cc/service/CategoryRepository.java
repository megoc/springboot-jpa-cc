package com.flymegoc.cc.service;

import com.flymegoc.cc.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category,Long>{
}
