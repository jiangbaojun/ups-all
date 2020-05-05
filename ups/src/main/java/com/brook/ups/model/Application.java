package com.brook.ups.model;

import java.sql.Timestamp;

public class Application {
    /**
     * id
     */
    private String id;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 描述
     */
    private String remark;

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
     * 应用名称
     * @return appName 应用名称
     */
    public String getAppName() {
        return appName;
    }

    /**
     * 应用名称
     * @param appName 应用名称
     */
    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    /**
     * 描述
     * @return remark 描述
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 描述
     * @param remark 描述
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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