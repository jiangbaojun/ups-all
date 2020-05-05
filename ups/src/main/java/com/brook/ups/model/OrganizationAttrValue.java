package com.brook.ups.model;

public class OrganizationAttrValue {
    /**
     * id
     */
    private String id;

    /**
     * 组织数据id
     */
    private String orgId;

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
     * 组织数据id
     * @return orgId 组织数据id
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 组织数据id
     * @param orgId 组织数据id
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
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