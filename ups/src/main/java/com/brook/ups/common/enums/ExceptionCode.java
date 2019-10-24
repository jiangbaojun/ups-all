package com.brook.ups.common.enums;

/**
 * 异常代码
 */
public enum ExceptionCode {
    /** 严重的系统错误 */
    S99999("Error系统异常",ExceptionType.SYSTEM),
    /** 未知ups异常*/
    C99999("未知异常",ExceptionType.COMMON),

    /** 解析token错误*/
    AU0001("token处理失败",ExceptionType.AUTHENTICATION),
    /** token无效*/
    AU0002("不是有效的token",ExceptionType.AUTHENTICATION),
    /** token失效*/
    AU0003("您的token已失效",ExceptionType.AUTHENTICATION),

    /** 添加应用异常*/
    A00101("添加应用失败异常",ExceptionType.APPLICATION),
    /** 修改应用异常*/
    A00201("修改应用失败异常",ExceptionType.APPLICATION),
    /** 删除应用异常*/
    A00301("删除应用失败异常",ExceptionType.APPLICATION),

    /** 添加插件异常*/
    P00101("添加插件失败异常",ExceptionType.PLUGIN),
    /** 修改插件异常*/
    P00201("修改插件失败异常",ExceptionType.PLUGIN),
    /** 删除插件异常*/
    P00301("删除插件失败异常",ExceptionType.PLUGIN),
    /** 保存插件数据异常*/
    PD0101("保存插件数据失败异常",ExceptionType.PLUGIN),
    /** 删除插件数据异常*/
    PD0201("删除插件数据失败异常",ExceptionType.PLUGIN),

    /** 添加账户异常*/
    AC0101("添加账户失败异常",ExceptionType.ACCOUNT),
    /** 修改账户异常*/
    AC0201("修改账户失败异常",ExceptionType.ACCOUNT),
    /** 删除账户异常*/
    AC0301("删除账户失败异常",ExceptionType.ACCOUNT);

    private final ExceptionType type;
    private final String description;
    ExceptionCode(String description, ExceptionType type){
        this.type = type;
        this.description = description;
    }

    public ExceptionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
