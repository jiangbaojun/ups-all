package com.brook.ups.common.enums;

/**
 * 异常类型
 */
public enum ExceptionType {

    COMMON("通用异常"),
    SYSTEM("系统异常"),
    ACCOUNT("账户异常"),
    APPLICATION("应用异常"),
    USER("用户异常"),
    RESOURCE("资源异常"),
    ORGANIZATION("组织异常"),
    ROLE("角色异常"),
    PLUGIN("插件异常"),
    AUTHENTICATION("鉴权异常");

    private final String value;

    ExceptionType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
