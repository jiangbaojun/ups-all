package com.brook.ups;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author 姜宝俊
 * @date 2019/3/25 9:34
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replaceAll("-","").length());
        System.out.println(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()).length());
    }
}
