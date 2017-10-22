package com.flymegoc.cc.service;

import com.flymegoc.cc.model.Category;
import com.flymegoc.cc.model.Picture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PictureRepository extends PagingAndSortingRepository<Picture,Long> {
    public Picture findByPictureKey(String pictureKey);
    public Page<Picture> findByCategory(Category category, Pageable pageable);
}
