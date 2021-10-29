package com.naichuan.seckill.controller;

import com.naichuan.seckill.pojo.User;
import com.naichuan.seckill.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IGoodsService goodsService;

    /**
     * 商品列表页面 
     * @author 张乃川
     * @date 2021/10/28 10:22
     * @param
     */
    @RequestMapping("/toList")
    public String toList(Model model, User user) {
        model.addAttribute("user", user);
        model.addAttribute("goodsList", goodsService.findGoodsVo());
        return "goodsList";
    }

//    @RequestMapping("/toDetail")
//    public String toDetail(Model model, User user) {
//        model.addAttribute("user", user);
//        return "goodsList";
//    }
}
