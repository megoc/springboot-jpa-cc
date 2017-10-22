package com.flymegoc.cc.service.impl;

import com.flymegoc.cc.model.Category;
import com.flymegoc.cc.model.Picture;
import com.flymegoc.cc.service.PictureRepository;
import com.flymegoc.cc.service.PictureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("pictureService")
@Transactional
public class PictureServiceImpl implements PictureService{

    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public void save(Picture picture) {
        pictureRepository.save(picture);
    }

    @Override
    public Picture findByPictureKey(String pictureKey) {
        return pictureRepository.findByPictureKey(pictureKey);
    }

    @Override
    public Page<Picture> findPictures(Category category, Pageable pageable) {
        return pictureRepository.findAll(pageable);
    }

    @Override
    public Page<Picture> findByCategory(Category category, Pageable pageable) {
        return pictureRepository.findByCategory(category,pageable);
    }


}
