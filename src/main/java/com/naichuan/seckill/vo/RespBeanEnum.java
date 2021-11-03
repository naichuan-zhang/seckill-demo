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
    MOBILE_ERROR(500211, "手机号格式不正确"),
    BINDING_ERROR(500212, "参数校验异常"),
    MOBILE_NOT_EXISTS(500213, "手机号不存在"),
    PASSWORD_UPDATE_FAIL(500214, "密码更新失败"),
    SESSION_ERROR(5000215, "用户不存在"),
    // 秒杀模块
    EMPTY_STOCK(500500, "库存不足"),
    REPEATE_ERROR(500501, "该商品每人限购一件");

    private final Integer code;
    private final String message;
}
