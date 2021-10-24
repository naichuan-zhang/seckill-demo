package com.naichuan.seckill.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Naichuan Zhang
 */
@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {

    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }
}
