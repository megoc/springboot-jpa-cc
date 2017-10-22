package com.flymegoc.cc.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Role role;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @Column(nullable = false,updatable = false)
    private Date userRoleCreateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @Column(nullable = false)
    private Date userRoleUpdateTime;

    public UserRole() {
    }

    public UserRole(User user, Role role, Date userRoleCreateTime, Date userRoleUpdateTime) {
        this.user = user;
        this.role = role;
        this.userRoleCreateTime = userRoleCreateTime;
        this.userRoleUpdateTime = userRoleUpdateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getUserRoleCreateTime() {
        return userRoleCreateTime;
    }

    public void setUserRoleCreateTime(Date userRoleCreateTime) {
        this.userRoleCreateTime = userRoleCreateTime;
    }

    public Date getUserRoleUpdateTime() {
        return userRoleUpdateTime;
    }

    public void setUserRoleUpdateTime(Date userRoleUpdateTime) {
        this.userRoleUpdateTime = userRoleUpdateTime;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", user=" + user +
                ", role=" + role +
                ", userRoleCreateTime=" + userRoleCreateTime +
                ", userRoleUpdateTime=" + userRoleUpdateTime +
                '}';
    }
}
