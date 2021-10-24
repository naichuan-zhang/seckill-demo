package com.naichuan.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Naichuan Zhang
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    /**
     * 测试页面跳转
     * @param model
     * @return
     */
    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "naichuan");
        return "hello";
    }
}
