package com.brook.ups.model;

import java.util.Date;

public class Resource {
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
     * 资源名称
     */
    private String resName;

    /**
     * 资源编码
     */
    private String resCode;

    /**
     * 资源类型，1目录 2模块 3按钮
     */
    private String resType;

    /**
     * 资源地址
     */
    private String resUrl;

    /**
     * 资源图标
     */
    private String resIcon;

    /**
     * 资源在同级目录内的排序序号，1开始
     */
    private Integer resOrder;

    /**
     * 资源描述
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
     * 资源名称
     * @return resName 资源名称
     */
    public String getResName() {
        return resName;
    }

    /**
     * 资源名称
     * @param resName 资源名称
     */
    public void setResName(String resName) {
        this.resName = resName == null ? null : resName.trim();
    }

    /**
     * 资源编码
     * @return resCode 资源编码
     */
    public String getResCode() {
        return resCode;
    }

    /**
     * 资源编码
     * @param resCode 资源编码
     */
    public void setResCode(String resCode) {
        this.resCode = resCode == null ? null : resCode.trim();
    }

    /**
     * 资源类型，1目录 2模块 3按钮
     * @return resType 资源类型，1目录 2模块 3按钮
     */
    public String getResType() {
        return resType;
    }

    /**
     * 资源类型，1目录 2模块 3按钮
     * @param resType 资源类型，1目录 2模块 3按钮
     */
    public void setResType(String resType) {
        this.resType = resType == null ? null : resType.trim();
    }

    /**
     * 资源地址
     * @return resUrl 资源地址
     */
    public String getResUrl() {
        return resUrl;
    }

    /**
     * 资源地址
     * @param resUrl 资源地址
     */
    public void setResUrl(String resUrl) {
        this.resUrl = resUrl == null ? null : resUrl.trim();
    }

    /**
     * 资源图标
     * @return resIcon 资源图标
     */
    public String getResIcon() {
        return resIcon;
    }

    /**
     * 资源图标
     * @param resIcon 资源图标
     */
    public void setResIcon(String resIcon) {
        this.resIcon = resIcon == null ? null : resIcon.trim();
    }

    /**
     * 资源在同级目录内的排序序号，1开始
     * @return resOrder 资源在同级目录内的排序序号，1开始
     */
    public Integer getResOrder() {
        return resOrder;
    }

    /**
     * 资源在同级目录内的排序序号，1开始
     * @param resOrder 资源在同级目录内的排序序号，1开始
     */
    public void setResOrder(Integer resOrder) {
        this.resOrder = resOrder;
    }

    /**
     * 资源描述
     * @return remark 资源描述
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 资源描述
     * @param remark 资源描述
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