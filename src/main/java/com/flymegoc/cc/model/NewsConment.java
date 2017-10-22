package com.flymegoc.cc.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 *新闻评论
 */
@Entity
public class NewsConment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String conment;//评论

    @ManyToOne(optional = false)
    private News news;

    @ManyToOne(optional = false)
    private User user;

    @Column(nullable = false)
    private long supportCount;//支持数

    @Column(nullable = false)
    private long againstCount;//反对数

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @Column(nullable = false,updatable = false)
    private Date conmentCreateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @Column(nullable = false)
    private Date conmentUpdateTime;

    public NewsConment() {
    }

    public NewsConment(String conment, News news, User user, long supportCount, long againstCount, Date conmentCreateTime, Date conmentUpdateTime) {
        this.conment = conment;
        this.news = news;
        this.user = user;
        this.supportCount = supportCount;
        this.againstCount = againstCount;
        this.conmentCreateTime = conmentCreateTime;
        this.conmentUpdateTime = conmentUpdateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConment() {
        return conment;
    }

    public void setConment(String conment) {
        this.conment = conment;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getSupportCount() {
        return supportCount;
    }

    public void setSupportCount(long supportCount) {
        this.supportCount = supportCount;
    }

    public long getAgainstCount() {
        return againstCount;
    }

    public void setAgainstCount(long againstCount) {
        this.againstCount = againstCount;
    }

    public Date getConmentCreateTime() {
        return conmentCreateTime;
    }

    public void setConmentCreateTime(Date conmentCreateTime) {
        this.conmentCreateTime = conmentCreateTime;
    }

    public Date getConmentUpdateTime() {
        return conmentUpdateTime;
    }

    public void setConmentUpdateTime(Date conmentUpdateTime) {
        this.conmentUpdateTime = conmentUpdateTime;
    }

    @Override
    public String toString() {
        return "NewsConment{" +
                "id=" + id +
                ", conment='" + conment + '\'' +
                ", news=" + news +
                ", user=" + user +
                ", supportCount=" + supportCount +
                ", againstCount=" + againstCount +
                ", conmentCreateTime=" + conmentCreateTime +
                ", conmentUpdateTime=" + conmentUpdateTime +
                '}';
    }
}
