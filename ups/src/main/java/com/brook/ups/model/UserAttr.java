package com.brook.ups.model;

import java.util.Date;

public class UserAttr {
    /**
     * id
     */
    private String id;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 扩展属性名称
     */
    private String userAttrName;

    /**
     * 扩展属性编码
     */
    private String userAttrCode;

    /**
     * 扩展属性类型，1字符，2日期
     */
    private String userAttrType;

    /**
     * 扩展属性控件类型，1单行文本，2多行文本，3日期框，4单选框，5复选框，6下拉框
     */
    private String userAttrPlugType;

    /**
     * 扩展属性使用的控件id
     */
    private String userAttrPlugId;

    /**
     * 扩展属性值是否可以为空
     */
    private String isNull;

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
     * 扩展属性名称
     * @return userAttrName 扩展属性名称
     */
    public String getUserAttrName() {
        return userAttrName;
    }

    /**
     * 扩展属性名称
     * @param userAttrName 扩展属性名称
     */
    public void setUserAttrName(String userAttrName) {
        this.userAttrName = userAttrName == null ? null : userAttrName.trim();
    }

    /**
     * 扩展属性编码
     * @return userAttrCode 扩展属性编码
     */
    public String getUserAttrCode() {
        return userAttrCode;
    }

    /**
     * 扩展属性编码
     * @param userAttrCode 扩展属性编码
     */
    public void setUserAttrCode(String userAttrCode) {
        this.userAttrCode = userAttrCode == null ? null : userAttrCode.trim();
    }

    /**
     * 扩展属性类型，1字符，2日期
     * @return userAttrType 扩展属性类型，1字符，2日期
     */
    public String getUserAttrType() {
        return userAttrType;
    }

    /**
     * 扩展属性类型，1字符，2日期
     * @param userAttrType 扩展属性类型，1字符，2日期
     */
    public void setUserAttrType(String userAttrType) {
        this.userAttrType = userAttrType == null ? null : userAttrType.trim();
    }

    /**
     * 扩展属性控件类型，1单行文本，2多行文本，3日期框，4单选框，5复选框，6下拉框
     * @return userAttrPlugType 扩展属性控件类型，1单行文本，2多行文本，3日期框，4单选框，5复选框，6下拉框
     */
    public String getUserAttrPlugType() {
        return userAttrPlugType;
    }

    /**
     * 扩展属性控件类型，1单行文本，2多行文本，3日期框，4单选框，5复选框，6下拉框
     * @param userAttrPlugType 扩展属性控件类型，1单行文本，2多行文本，3日期框，4单选框，5复选框，6下拉框
     */
    public void setUserAttrPlugType(String userAttrPlugType) {
        this.userAttrPlugType = userAttrPlugType == null ? null : userAttrPlugType.trim();
    }

    /**
     * 扩展属性使用的控件id
     * @return userAttrPlugId 扩展属性使用的控件id
     */
    public String getUserAttrPlugId() {
        return userAttrPlugId;
    }

    /**
     * 扩展属性使用的控件id
     * @param userAttrPlugId 扩展属性使用的控件id
     */
    public void setUserAttrPlugId(String userAttrPlugId) {
        this.userAttrPlugId = userAttrPlugId == null ? null : userAttrPlugId.trim();
    }

    /**
     * 扩展属性值是否可以为空
     * @return isNull 扩展属性值是否可以为空
     */
    public String getIsNull() {
        return isNull;
    }

    /**
     * 扩展属性值是否可以为空
     * @param isNull 扩展属性值是否可以为空
     */
    public void setIsNull(String isNull) {
        this.isNull = isNull == null ? null : isNull.trim();
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