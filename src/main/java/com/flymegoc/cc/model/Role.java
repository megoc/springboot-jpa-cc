package com.flymegoc.cc.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String roleName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @Column(nullable = false,updatable = false)
    private Date roleCreateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @Column(nullable = false)
    private Date roleUpdateTime;

    public Role() {
    }

    public Role(String roleName, Date roleCreateTime, Date roleUpdateTime) {
        this.roleName = roleName;
        this.roleCreateTime = roleCreateTime;
        this.roleUpdateTime = roleUpdateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getRoleCreateTime() {
        return roleCreateTime;
    }

    public void setRoleCreateTime(Date roleCreateTime) {
        this.roleCreateTime = roleCreateTime;
    }

    public Date getRoleUpdateTime() {
        return roleUpdateTime;
    }

    public void setRoleUpdateTime(Date roleUpdateTime) {
        this.roleUpdateTime = roleUpdateTime;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleCreateTime=" + roleCreateTime +
                ", roleUpdateTime=" + roleUpdateTime +
                '}';
    }
}
