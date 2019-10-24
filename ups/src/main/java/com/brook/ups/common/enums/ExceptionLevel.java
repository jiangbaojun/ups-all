package com.brook.ups.common.enums;

/**
 * 异常级别
 */
public enum ExceptionLevel {
    /** 错误级别，说明业务错误已经失败*/
    ERROR(1),
    /** 警告级别，说明业务可以继续执行*/
    WARN(2);

    private final Integer value;

    ExceptionLevel(Integer value) {
        this.value = value;
    }

    /**
     * 获得异常级别编码
     */
    public Integer getValue() {
        return value;
    }
}
