package com.flymegoc.cc.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String categoryName;

    @Column(nullable = false)
    private String categoryUrl;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @Column(nullable = false,updatable = false)
    private Date categoryCreateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @Column(nullable = false)
    private Date categoryUpdateTime;

    @Column(nullable = false)
    private Integer categoryStatus;

    public Category() {
    }

    public Category(String categoryName, String categoryUrl, Date categoryCreateTime, Date categoryUpdateTime, Integer categoryStatus) {
        this.categoryName = categoryName;
        this.categoryUrl = categoryUrl;
        this.categoryCreateTime = categoryCreateTime;
        this.categoryUpdateTime = categoryUpdateTime;
        this.categoryStatus = categoryStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryUrl() {
        return categoryUrl;
    }

    public void setCategoryUrl(String categoryUrl) {
        this.categoryUrl = categoryUrl;
    }

    public Date getCategoryCreateTime() {
        return categoryCreateTime;
    }

    public void setCategoryCreateTime(Date categoryCreateTime) {
        this.categoryCreateTime = categoryCreateTime;
    }

    public Date getCategoryUpdateTime() {
        return categoryUpdateTime;
    }

    public void setCategoryUpdateTime(Date categoryUpdateTime) {
        this.categoryUpdateTime = categoryUpdateTime;
    }

    public Integer getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(Integer categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", categoryUrl='" + categoryUrl + '\'' +
                ", categoryCreateTime=" + categoryCreateTime +
                ", categoryUpdateTime=" + categoryUpdateTime +
                ", categoryStatus=" + categoryStatus +
                '}';
    }
}
