package com.flymegoc.cc.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String permissionName;

    @ManyToOne(optional = false)
    private Role role;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @Column(nullable = false,updatable = false)
    private Date permissionCreateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @Column(nullable = false)
    private Date permissionUpdateTime;

    public Permission() {
    }

    public Permission(String permissionName, Role role, Date permissionCreateTime, Date permissionUpdateTime) {
        this.permissionName = permissionName;
        this.role = role;
        this.permissionCreateTime = permissionCreateTime;
        this.permissionUpdateTime = permissionUpdateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getPermissionCreateTime() {
        return permissionCreateTime;
    }

    public void setPermissionCreateTime(Date permissionCreateTime) {
        this.permissionCreateTime = permissionCreateTime;
    }

    public Date getPermissionUpdateTime() {
        return permissionUpdateTime;
    }

    public void setPermissionUpdateTime(Date permissionUpdateTime) {
        this.permissionUpdateTime = permissionUpdateTime;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permissionName='" + permissionName + '\'' +
                ", role=" + role +
                ", permissionCreateTime=" + permissionCreateTime +
                ", permissionUpdateTime=" + permissionUpdateTime +
                '}';
    }
}
