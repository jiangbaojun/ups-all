package com.brook.ups.common.enums;

/**
 * 控件类型
 */
public enum PlugType {
    /** 单行文本 */
    TEXT("单行文本","1"),
    /** 多行文本 */
    TEXTAREA("多行文本","2"),
    /** 日期框 */
    DATE("日期框","3"),
    /** 单选框 */
    RADIO("单选框","4"),
    /** 复选框 */
    CHECKBOX("复选框","5"),
    /** 下拉框 */
    COMBOBOX("下拉框","6");

    private final String name;
    private final String value;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    PlugType(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
