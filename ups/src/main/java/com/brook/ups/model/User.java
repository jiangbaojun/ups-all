package com.brook.ups.model;

import java.util.Date;

public class User {
    /**
     * id
     */
    private String id;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 用户名称（代码）
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名称（姓名）
     */
    private String name;

    /**
     * 是否有效，1有效，0无效
     */
    private Integer isAvailable;

    /**
     * 操作人
     */
    private String optName;

    /**
     * 操作时间
     */
    private Date optDate;

    /**
     * id
     * @return id id
     */
    public String getId() {
        return id;
    }

    /**
     * id
     * @param id id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 应用id
     * @return appId 应用id
     */
    public String getAppId() {
        return appId;
    }

    /**
     * 应用id
     * @param appId 应用id
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * 用户名称（代码）
     * @return userName 用户名称（代码）
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名称（代码）
     * @param userName 用户名称（代码）
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 密码
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 用户名称（姓名）
     * @return name 用户名称（姓名）
     */
    public String getName() {
        return name;
    }

    /**
     * 用户名称（姓名）
     * @param name 用户名称（姓名）
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 是否有效，1有效，0无效
     * @return isAvailable 是否有效，1有效，0无效
     */
    public Integer getIsAvailable() {
        return isAvailable;
    }

    /**
     * 是否有效，1有效，0无效
     * @param isAvailable 是否有效，1有效，0无效
     */
    public void setIsAvailable(Integer isAvailable) {
        this.isAvailable = isAvailable;
    }

    /**
     * 操作人
     * @return optName 操作人
     */
    public String getOptName() {
        return optName;
    }

    /**
     * 操作人
     * @param optName 操作人
     */
    public void setOptName(String optName) {
        this.optName = optName == null ? null : optName.trim();
    }

    /**
     * 操作时间
     * @return optDate 操作时间
     */
    public Date getOptDate() {
        return optDate;
    }

    /**
     * 操作时间
     * @param optDate 操作时间
     */
    public void setOptDate(Date optDate) {
        this.optDate = optDate;
    }
}