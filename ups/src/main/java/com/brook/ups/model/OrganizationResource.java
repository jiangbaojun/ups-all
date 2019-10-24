package com.brook.ups.model;

public class OrganizationResource {
    /**
     * id
     */
    private String id;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 组织id
     */
    private String orgId;

    /**
     * 资源id
     */
    private String resourceId;

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
     * 组织id
     * @return orgId 组织id
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 组织id
     * @param orgId 组织id
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    /**
     * 资源id
     * @return resourceId 资源id
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * 资源id
     * @param resourceId 资源id
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId == null ? null : resourceId.trim();
    }
}