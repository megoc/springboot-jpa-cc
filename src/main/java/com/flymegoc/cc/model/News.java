package com.flymegoc.cc.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class News {

    @Id
    private long id;

    @Column(nullable = false)
    private String title;//标题

    @Column(nullable = false)
    private String contentUrl;//正文连接

    @Column(nullable = false)
    private String summary;//简介

    @Column(nullable = false)
    private String imgUrl;//图片连接

    @Column(nullable = false)
    private long conmentCount;//评论数

    @Column(nullable = false)//阅读数
    private long viewCount;

    private String content;//正文

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @Column(nullable = false,updatable = false)
    private Date newCreateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @Column(nullable = false)
    private Date newUpdateTime;

    public News() {
    }

    public News(long id, String title, String contentUrl, String summary, String imgUrl, long conmentCount, long viewCount, String content, Date newCreateTime, Date newUpdateTime) {
        this.id = id;
        this.title = title;
        this.contentUrl = contentUrl;
        this.summary = summary;
        this.imgUrl = imgUrl;
        this.conmentCount = conmentCount;
        this.viewCount = viewCount;
        this.content = content;
        this.newCreateTime = newCreateTime;
        this.newUpdateTime = newUpdateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public long getConmentCount() {
        return conmentCount;
    }

    public void setConmentCount(long conmentCount) {
        this.conmentCount = conmentCount;
    }

    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getNewCreateTime() {
        return newCreateTime;
    }

    public void setNewCreateTime(Date newCreateTime) {
        this.newCreateTime = newCreateTime;
    }

    public Date getNewUpdateTime() {
        return newUpdateTime;
    }

    public void setNewUpdateTime(Date newUpdateTime) {
        this.newUpdateTime = newUpdateTime;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contentUrl='" + contentUrl + '\'' +
                ", summary='" + summary + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", conmentCount=" + conmentCount +
                ", viewCount=" + viewCount +
                ", content='" + content + '\'' +
                ", newCreateTime=" + newCreateTime +
                ", newUpdateTime=" + newUpdateTime +
                '}';
    }
}
