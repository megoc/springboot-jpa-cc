package com.flymegoc.cc.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Picture {

    @Id
    private long id;

    @Column(nullable = false)
    private String picturePinId;

    @Column(nullable = false)
    private String pictureBoardId;

    @Column(nullable = false)
    private String pictureBucket;

    @Column(nullable = false)
    private String pictureKey;

    @Column(nullable = false)
    private String pictureType;

    @Column(nullable = false)
    private Integer pictureWidth;

    @Column(nullable = false)
    private Integer pictureHeight;

    @Column(nullable = false)
    private Double pictureRatio;

    @Column(nullable = false)
    private Integer pictureLikes;

    @ManyToOne(optional = false)
    private Category category;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @Column(nullable = false,updatable = false)
    private Date pictureCreateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @Column(nullable = false)
    private Date pictureUpdateTime;

    public Picture() {
    }

    public Picture(String picturePinId, String pictureBoardId, String pictureBucket, String pictureKey, String pictureType, Integer pictureWidth, Integer pictureHeight, Double pictureRatio, Integer pictureLikes, Category category, Date pictureCreateTime, Date pictureUpdateTime) {
        this.picturePinId = picturePinId;
        this.pictureBoardId = pictureBoardId;
        this.pictureBucket = pictureBucket;
        this.pictureKey = pictureKey;
        this.pictureType = pictureType;
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
        this.pictureRatio = pictureRatio;
        this.pictureLikes = pictureLikes;
        this.category = category;
        this.pictureCreateTime = pictureCreateTime;
        this.pictureUpdateTime = pictureUpdateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPicturePinId() {
        return picturePinId;
    }

    public void setPicturePinId(String picturePinId) {
        this.picturePinId = picturePinId;
    }

    public String getPictureBoardId() {
        return pictureBoardId;
    }

    public void setPictureBoardId(String pictureBoardId) {
        this.pictureBoardId = pictureBoardId;
    }

    public String getPictureBucket() {
        return pictureBucket;
    }

    public void setPictureBucket(String pictureBucket) {
        this.pictureBucket = pictureBucket;
    }

    public String getPictureKey() {
        return pictureKey;
    }

    public void setPictureKey(String pictureKey) {
        this.pictureKey = pictureKey;
    }

    public String getPictureType() {
        return pictureType;
    }

    public void setPictureType(String pictureType) {
        this.pictureType = pictureType;
    }

    public Integer getPictureWidth() {
        return pictureWidth;
    }

    public void setPictureWidth(Integer pictureWidth) {
        this.pictureWidth = pictureWidth;
    }

    public Integer getPictureHeight() {
        return pictureHeight;
    }

    public void setPictureHeight(Integer pictureHeight) {
        this.pictureHeight = pictureHeight;
    }

    public Double getPictureRatio() {
        return pictureRatio;
    }

    public void setPictureRatio(Double pictureRatio) {
        this.pictureRatio = pictureRatio;
    }

    public Integer getPictureLikes() {
        return pictureLikes;
    }

    public void setPictureLikes(Integer pictureLikes) {
        this.pictureLikes = pictureLikes;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getPictureCreateTime() {
        return pictureCreateTime;
    }

    public void setPictureCreateTime(Date pictureCreateTime) {
        this.pictureCreateTime = pictureCreateTime;
    }

    public Date getPictureUpdateTime() {
        return pictureUpdateTime;
    }

    public void setPictureUpdateTime(Date pictureUpdateTime) {
        this.pictureUpdateTime = pictureUpdateTime;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", picturePinId='" + picturePinId + '\'' +
                ", pictureBoardId='" + pictureBoardId + '\'' +
                ", pictureBucket='" + pictureBucket + '\'' +
                ", pictureKey='" + pictureKey + '\'' +
                ", pictureType='" + pictureType + '\'' +
                ", pictureWidth=" + pictureWidth +
                ", pictureHeight=" + pictureHeight +
                ", pictureRatio=" + pictureRatio +
                ", pictureLikes=" + pictureLikes +
                ", category=" + category +
                ", pictureCreateTime=" + pictureCreateTime +
                ", pictureUpdateTime=" + pictureUpdateTime +
                '}';
    }
}
