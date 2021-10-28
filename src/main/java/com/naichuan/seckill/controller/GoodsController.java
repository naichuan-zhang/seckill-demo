package com.naichuan.seckill.controller;

import com.naichuan.seckill.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 张乃川
 * @date 2021/10/28 10:19
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    /**
     * 商品列表页面 
     * @author 张乃川
     * @date 2021/10/28 10:22
     * @param
     */
    @RequestMapping("/toList")
    public String toList(Model model, User user) {
        model.addAttribute("user", user);
        return "goodsList";
    }

//    @RequestMapping("/toDetail")
//    public String toDetail(Model model, User user) {
//        model.addAttribute("user", user);
//        return "goodsList";
//    }
}
