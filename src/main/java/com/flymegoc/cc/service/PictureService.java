package com.flymegoc.cc.service;

import com.flymegoc.cc.model.Category;
import com.flymegoc.cc.model.Picture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PictureService {
    public void save(Picture picture);
    public Picture findByPictureKey(String pictureKey);
    public Page<Picture> findPictures(Category category,Pageable pageable);
    public Page<Picture> findByCategory(Category category, Pageable pageable);
}
