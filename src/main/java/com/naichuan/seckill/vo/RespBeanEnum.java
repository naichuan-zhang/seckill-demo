package com.naichuan.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author 张乃川
 * @date 2021/10/26 17:02
 */
@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {
    // 通用
    SUCCESS(200, "成功"),
    ERROR(500, "服务端异常"),
    // 登录模块
    LOGIN_ERROR(500210, "用户名或密码不正确"),
    MOBILE_ERROR(500211, "手机号格式不正确");

    private final Integer code;
    private final String message;
}
