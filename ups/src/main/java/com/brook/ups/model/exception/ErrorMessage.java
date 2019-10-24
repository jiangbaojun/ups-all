package com.brook.ups.model.exception;

/**
 * 前端展示异常
 */
public class ErrorMessage {
    /** 异常代码 */
    private String code;
    /** 异常级别 */
    private Integer level;
    /** 是否需要退出跳转到登录页，1是，0否 */
    private Integer redirectLogin;
    /** 异常描述 */
    private String description;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getRedirectLogin() {
        return redirectLogin;
    }

    public void setRedirectLogin(Integer redirectLogin) {
        this.redirectLogin = redirectLogin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
