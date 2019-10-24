package com.brook.ups.model;

public class UserOrganization {
    /**
     * id
     */
    private String id;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 组织id
     */
    private String orgId;

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
     * 用户id
     * @return userId 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户id
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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
}