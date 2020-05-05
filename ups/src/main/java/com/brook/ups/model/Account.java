package com.brook.ups.model;

import java.sql.Timestamp;

public class Account {
    /**
     * id
     */
    private String id;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 账户名称
     */
    private String acName;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否为管理员，1是，0否。管理员可以修改数据，否则只能查看
     */
    private String isAdmin;

    /**
     * 是否有效，1有效，0无效
     */
    private String isAvailable;

    /**
     * 操作人
     */
    private String optName;
    private String optName_name;

    /**
     * 操作时间
     */
    private Timestamp optDate;

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
     * 账户名称
     * @return acName 账户名称
     */
    public String getAcName() {
        return acName;
    }

    /**
     * 账户名称
     * @param acName 账户名称
     */
    public void setAcName(String acName) {
        this.acName = acName == null ? null : acName.trim();
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
     * 是否为管理员，1是，0否。管理员可以修改数据，否则只能查看
     * @return isAdmin 是否为管理员，1是，0否。管理员可以修改数据，否则只能查看
     */
    public String getIsAdmin() {
        return isAdmin;
    }

    /**
     * 是否为管理员，1是，0否。管理员可以修改数据，否则只能查看
     * @param isAdmin 是否为管理员，1是，0否。管理员可以修改数据，否则只能查看
     */
    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin == null ? null : isAdmin.trim();
    }

    /**
     * 是否有效，1有效，0无效
     * @return isAvailable 是否有效，1有效，0无效
     */
    public String getIsAvailable() {
        return isAvailable;
    }

    /**
     * 是否有效，1有效，0无效
     * @param isAvailable 是否有效，1有效，0无效
     */
    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable == null ? null : isAvailable.trim();
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
    public Timestamp getOptDate() {
        return optDate;
    }

    /**
     * 操作时间
     * @param optDate 操作时间
     */
    public void setOptDate(Timestamp optDate) {
        this.optDate = optDate;
    }

    public String getOptName_name() {
        return optName_name;
    }

    public void setOptName_name(String optName_name) {
        this.optName_name = optName_name;
    }
}