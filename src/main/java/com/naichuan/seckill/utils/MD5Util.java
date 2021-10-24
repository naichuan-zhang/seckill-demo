package com.naichuan.seckill.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

/**
 * @author Naichuan Zhang
 */
@Component
public class MD5Util {

    private static final String SALT = "1a2b3c4d";

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    public static String inputPassToFromPass(String inputPass) {
        String str = SALT.charAt(0) + SALT.charAt(2) + inputPass + SALT.charAt(5) + SALT.charAt(4);
        return md5(str);
    }

    public static String fromPassToDBPass(String fromPass, String salt) {
        String str = SALT.charAt(0) + SALT.charAt(2) + fromPass + SALT.charAt(5) + SALT.charAt(4);
        return md5(str);
    }

    public static String inputPassToDBPass(String inputPass, String salt) {
        String fromPass = inputPassToFromPass(inputPass);
        return fromPassToDBPass(fromPass, salt);
    }

    public static void main(String[] args) {
        System.out.println(inputPassToFromPass("123456"));
        System.out.println(fromPassToDBPass("ce21b747de5af71ab5c2e20ff0a60eea", SALT));
        System.out.println(inputPassToDBPass("123456", SALT));
    }
}
