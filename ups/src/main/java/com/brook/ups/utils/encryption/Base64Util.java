package com.brook.ups.utils.encryption;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Base64编码和解码
 */
public class Base64Util {
	
	private final static String CODE_METHOD = "UTF-8";
	
	
    /**
     * 编码
     * @param text 待编码数据
     * @author		姜宝俊
     * @date 		2018年9月26日 上午11:28:28
     */
    public static String encode(String text) {
		Base64.Encoder encoder = Base64.getEncoder();
		byte[] textByte = null;
		try {
			textByte = text.getBytes(CODE_METHOD);
		} catch (UnsupportedEncodingException e) {
            throw new RuntimeException("BASE64正向运算出现错误");
		}
		//编码
		String encodedText = encoder.encodeToString(textByte);
		return encodedText;
	}
 
    /**
     * 解码
     * @param encodedText 待解码数据
     * @author		姜宝俊
     * @date 		2018年9月26日 上午11:28:47
     */
    public static String decode(String encodedText) {
    	Base64.Decoder decoder = Base64.getDecoder();
		String decodeText = null;
		try {
			decodeText = new String(decoder.decode(encodedText), CODE_METHOD);
		} catch (UnsupportedEncodingException e) {
            throw new RuntimeException("BASE64逆向运算出现错误");
		}
		return decodeText;
    }
}