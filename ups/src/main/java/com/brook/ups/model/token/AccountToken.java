package com.brook.ups.model.token;

import com.brook.ups.common.enums.TokenType;

import java.util.Date;

/**
 * 账户token
 */
public class AccountToken {
    private String acId;
    private String appId;
    private Date date;
    private String password;
    private TokenType type;

    public String getAcId() {
        return acId;
    }

    public void setAcId(String acId) {
        this.acId = acId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }
}
