package com.brook.ups.model;

import java.sql.Timestamp;

public class FormPlugin {
    /**
     * id
     */
    private String id;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 插件类型,1单行文本，2多行文本，3日期框，4单选框，5复选框，6下拉框
     */
    private String plugType;

    /**
     * 插件名称
     */
    private String plugName;

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
     * 插件类型
     * @return plugType 插件类型
     */
    public String getPlugType() {
        return plugType;
    }

    /**
     * 插件类型
     * @param plugType 插件类型
     */
    public void setPlugType(String plugType) {
        this.plugType = plugType == null ? null : plugType.trim();
    }

    /**
     * 插件名称
     * @return plugName 插件名称
     */
    public String getPlugName() {
        return plugName;
    }

    /**
     * 插件名称
     * @param plugName 插件名称
     */
    public void setPlugName(String plugName) {
        this.plugName = plugName == null ? null : plugName.trim();
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