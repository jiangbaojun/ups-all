package com.brook.ups.utils.encryption;
 
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.util.Date;
 
/**
 * DES(CBC)加解密，密文采用HEX输出
 * @author		姜宝俊
 */
public class DESUtil {
 
    /** 密钥,注意和vsta统一 */
	private static String sKey = "87654321";
	/** 字符串编码方式 */
    private static String charset = "UTF-8";
 
    public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "dajiahao大家好123";
		System.out.println("加密前："+str);
		
		String encodeStr = DESUtil.encrypt(str);
		System.out.println("加密后："+encodeStr);
		
		String decodeStr = DESUtil.decrypt(encodeStr);
		System.out.println("解密后："+decodeStr);
		
		String u = "xiaoqiang";
		String desU = DESUtil.encrypt(u);
		System.out.println(Base64Util.encode(new Date().getTime()+"-"+desU));
		System.out.println(DESUtil.decrypt(desU));
	}
    
    /**
     * 对字符串进行des加密
     * @param str 待加密字符串
     * @author		姜宝俊
     * @date 		2018年9月27日 上午9:47:16
     */
	public static String encrypt(String str) {
		byte[] resultByte = null;
		try {
			resultByte = encrypt(str.getBytes(charset));
		} catch (UnsupportedEncodingException e) {
            throw new RuntimeException("字符串解析为byte出现错误");
		}
		return Hex.encodeHexString(resultByte);
	}

	/**
	 * 对字符串密文进行des解密
	 * @param encodeStr 待解密字符串密文
	 * @author		姜宝俊
	 * @date 		2018年9月27日 上午9:48:07
	 */
	public static String decrypt(String encodeStr) {
		String resultStr = null;
		try {
			byte[] resultByte = decrypt(Hex.decodeHex(encodeStr));
			resultStr = new String(resultByte,charset);
		} catch (DecoderException e) {
            throw new RuntimeException("对密文HEX解码出现错误");
		} catch (UnsupportedEncodingException e) {
            throw new RuntimeException("byte解析为字符串出现错误");
		}
		return resultStr;
	}


	/**
	 * des加密
	 * @author		姜宝俊
	 * @date 		2018年9月27日 上午9:49:14
	 */
    private static byte[] encrypt(byte[] data) {
        try {
            byte[] key = sKey.getBytes(charset);
            // 初始化向量
            DESKeySpec desKey = new DESKeySpec(key);
            IvParameterSpec iv = new IvParameterSpec(key);
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成securekey
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);
            // 执行加密操作
            byte[] encodeByte = cipher.doFinal(data);
            return encodeByte;
        } catch (Exception e) {
            throw new RuntimeException("对byte加密失败！");
        }
    }

    /**
     * des解密
     * @author		姜宝俊
     * @date 		2018年9月27日 上午9:49:25
     */
    private static byte[] decrypt(byte[] src) {
        try {
			byte[] key = sKey.getBytes();
			// 初始化向量
			IvParameterSpec iv = new IvParameterSpec(key);
			// 创建一个DESKeySpec对象
			DESKeySpec desKey = new DESKeySpec(key);
			// 创建一个密匙工厂
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// 将DESKeySpec对象转换成SecretKey对象
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, iv);
			// 执行解密操作
			byte[] encodeByte = cipher.doFinal(src);
			return encodeByte;
		} catch (Exception e) {
            throw new RuntimeException("对byte解密失败！");
		}
    }
}