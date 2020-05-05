package com.brook.ups.model;

public class UserAttrValue {
    /**
     * id
     */
    private String id;

    /**
     * 用户数据id
     */
    private String userId;

    /**
     * 扩展属性字段id
     */
    private String attrId;

    /**
     * 扩展属性值
     */
    private String value;

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
     * 用户数据id
     * @return userId 用户数据id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户数据id
     * @param userId 用户数据id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 扩展属性字段id
     * @return attrId 扩展属性字段id
     */
    public String getAttrId() {
        return attrId;
    }

    /**
     * 扩展属性字段id
     * @param attrId 扩展属性字段id
     */
    public void setAttrId(String attrId) {
        this.attrId = attrId == null ? null : attrId.trim();
    }

    /**
     * 扩展属性值
     * @return value 扩展属性值
     */
    public String getValue() {
        return value;
    }

    /**
     * 扩展属性值
     * @param value 扩展属性值
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}