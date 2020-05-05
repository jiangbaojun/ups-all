package com.brook.ups.model;

import java.util.Date;

public class Organization {
    /**
     * id
     */
    private String id;

    /**
     * 父级id
     */
    private String pid;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 组织名称
     */
    private String orgName;

    /**
     * 组织编码
     */
    private String orgCode;

    /**
     * 是否有效，1有效，0无效
     */
    private String isAvailable;

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
     * 父级id
     * @return pid 父级id
     */
    public String getPid() {
        return pid;
    }

    /**
     * 父级id
     * @param pid 父级id
     */
    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
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
     * 组织名称
     * @return orgName 组织名称
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 组织名称
     * @param orgName 组织名称
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    /**
     * 组织编码
     * @return orgCode 组织编码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 组织编码
     * @param orgCode 组织编码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
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