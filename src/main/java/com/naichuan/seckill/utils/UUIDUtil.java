package com.naichuan.seckill.utils;

import java.util.UUID;

/**
 * @author 张乃川
 * @date 2021/10/27 15:57
 */
public class UUIDUtil {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
