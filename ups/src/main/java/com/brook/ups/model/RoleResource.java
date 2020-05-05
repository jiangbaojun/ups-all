package com.brook.ups.model;

public class RoleResource {
    /**
     * id
     */
    private String id;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 资源id
     */
    private String resourceId;

    /**
     * 角色id
     */
    private String roleId;

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

    /**
     * 角色id
     * @return roleId 角色id
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 角色id
     * @param roleId 角色id
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
}