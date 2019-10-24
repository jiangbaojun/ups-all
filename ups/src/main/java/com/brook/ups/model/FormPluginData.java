package com.brook.ups.model;

public class FormPluginData {
    /**
     * id
     */
    private String id;

    /**
     * 插件id
     */
    private String plugId;

    /**
     * 插件数据key
     */
    private String plugDataKey;

    /**
     * 插件数据value
     */
    private String plugDataValue;

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
     * @return plugId 应用id
     */
    public String getPlugId() {
        return plugId;
    }

    /**
     * 应用id
     * @param plugId 应用id
     */
    public void setPlugId(String plugId) {
        this.plugId = plugId == null ? null : plugId.trim();
    }

    /**
     * 插件类型
     * @return plugDataKey 插件类型
     */
    public String getPlugDataKey() {
        return plugDataKey;
    }

    /**
     * 插件类型
     * @param plugDataKey 插件类型
     */
    public void setPlugDataKey(String plugDataKey) {
        this.plugDataKey = plugDataKey == null ? null : plugDataKey.trim();
    }

    /**
     * 插件名称
     * @return plugDataValue 插件名称
     */
    public String getPlugDataValue() {
        return plugDataValue;
    }

    /**
     * 插件名称
     * @param plugDataValue 插件名称
     */
    public void setPlugDataValue(String plugDataValue) {
        this.plugDataValue = plugDataValue == null ? null : plugDataValue.trim();
    }
}