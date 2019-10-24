package com.brook.ups.utils.encryption;

import com.brook.ups.common.enums.TokenType;
import com.brook.ups.model.Account;
import com.brook.ups.model.token.AccountToken;

import java.util.Date;

/**
 * 生成账户加密token工具
 */
public class TokenEncryptUtil {

	/**
	 * 生成内部token
     * 格式：appid-账户ID-时间Long-密码
	 */
	public static String generateInnerToken(Account ac) {
        return generate(ac,TokenType.INNER);
	}
	/**
	 * 生成API token
     * 格式：appid-账户ID-时间Long-密码
	 */
	public static String generateApiToken(Account ac) {
        return generate(ac,TokenType.API);
	}

    /**
     * 生成token
     * @param ac
     * @param type
     * @return
     */
	private static String generate(Account ac , TokenType type){
        if(ac==null ||ac.getId()==null || ac.getPassword()==null){
            return null;
        }
        String token = null;
        try {
            StringBuffer bufr = new StringBuffer();
            bufr.append(ac.getAppId()).append("-");
            bufr.append(ac.getId()).append("-");
            bufr.append(new Date().getTime()).append("-");
            bufr.append(ac.getPassword()).append("-");
            bufr.append(type.getValue());
            token = Base64Util.encode(DESUtil.encrypt(bufr.toString()));
        } catch (Exception e) {
            throw new RuntimeException("生成token失败");
        }
        return token;
    }
	/**
	 * 解析token
     * token格式：appid-账户ID-时间Long-密码
	 */
	public static AccountToken parse(String token) {
	    if(token==null){
	        return  null;
        }
        AccountToken at = new AccountToken();
		try {
		    String str = DESUtil.decrypt(Base64Util.decode(token));
		    String[] tokenArr = str.split("-");
		    if(tokenArr!=null && tokenArr.length==5){
		        at.setAppId(tokenArr[0]);
		        at.setAcId(tokenArr[1]);
		        at.setDate(new Date(Long.parseLong(tokenArr[2])));
		        at.setPassword(tokenArr[3]);
		        at.setType(TokenType.getTypeByValue(tokenArr[4]));
            }else{
		        return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("解析token失败");
		}
		return at;
	}

}