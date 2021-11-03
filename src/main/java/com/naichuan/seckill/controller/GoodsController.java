package com.naichuan.seckill.controller;

import com.naichuan.seckill.pojo.User;
import com.naichuan.seckill.service.IGoodsService;
import com.naichuan.seckill.vo.DetailVo;
import com.naichuan.seckill.vo.GoodsVo;
import com.naichuan.seckill.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author 张乃川
 * @date 2021/10/28 10:19
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ThymeleafViewResolver viewResolver;

    /**
     * 商品列表页面 
     * @author 张乃川
     * @date 2021/10/28 10:22
     * @param
     */
    @RequestMapping(value = "/toList", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String toList(Model model, User user, HttpServletRequest request, HttpServletResponse response) {
        // Redis中获取页面，如果不为空直接返回页面
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String html = (String) valueOperations.get("goodsList");
        if (!StringUtils.isEmpty(html)) {
            return html;
        }
        model.addAttribute("user", user);
        model.addAttribute("goodsList", goodsService.findGoodsVo());
        // 如果为空，手动渲染，并存入Redis中
        WebContext webContext = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        html = viewResolver.getTemplateEngine().process("goodsList", webContext);
        if (!StringUtils.isEmpty(html)) {
            valueOperations.set("goodsList", html, 60, TimeUnit.MINUTES);
        }
        return html;
//        return "goodsList";
    }

    @RequestMapping("/detail/{goodsId}")
    @ResponseBody
    public RespBean toDetail(@PathVariable("goodsId") Long goodsId, Model model, User user) {
        model.addAttribute("user", user);
        GoodsVo goodsVo = goodsService.findGoodsByGoodsId(goodsId);
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date nowDate = new Date();
        // 秒杀状态
        int secKillStatus;
        // 秒杀倒计时
        int remainSeconds;
        if (nowDate.before(startDate)) {
            // 秒杀未开始
            secKillStatus = 0;
            remainSeconds = ((int) ((startDate.getTime() - nowDate.getTime()) / 1000));
        } else if (nowDate.after(endDate)) {
            // 秒杀已结束
            secKillStatus = 2;
            remainSeconds = -1;
        } else {
            secKillStatus = 1;
            remainSeconds = 0;
        }
        DetailVo detailVo = new DetailVo();
        detailVo.setUser(user);
        detailVo.setGoodsVo(goodsVo);
        detailVo.setSecKillStatus(secKillStatus);
        detailVo.setRemainSeconds(remainSeconds);
        return RespBean.success(detailVo);
    }

//    @RequestMapping(value = "/toDetail/{goodsId}", produces = "text/html;charset=utf-8")
//    @ResponseBody
//    public String toDetail(@PathVariable("goodsId") Long goodsId, Model model, User user, HttpServletRequest request, HttpServletResponse response) {
//        ValueOperations valueOptions = redisTemplate.opsForValue();
//        String html = (String) valueOptions.get("goodsDetail:" + goodsId);
//        if (!StringUtils.isEmpty(html)) {
//            return html;
//        }
//        model.addAttribute("user", user);
//        GoodsVo goodsVo = goodsService.findGoodsByGoodsId(goodsId);
//        Date startDate = goodsVo.getStartDate();
//        Date endDate = goodsVo.getEndDate();
//        Date nowDate = new Date();
//        // 秒杀状态
//        int secKillStatus;
//        // 秒杀倒计时
//        int remainSeconds;
//        if (nowDate.before(startDate)) {
//            // 秒杀未开始
//            secKillStatus = 0;
//            remainSeconds = ((int) ((startDate.getTime() - nowDate.getTime()) / 1000));
//        } else if (nowDate.after(endDate)) {
//            // 秒杀已结束
//            secKillStatus = 2;
//            remainSeconds = -1;
//        } else {
//            secKillStatus = 1;
//            remainSeconds = 0;
//        }
//        model.addAttribute("remainSeconds", remainSeconds);
//        model.addAttribute("secKillStatus", secKillStatus);
//        model.addAttribute("goods", goodsVo);
//        WebContext webContext = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
//        html = viewResolver.getTemplateEngine().process("goodsDetail", webContext);
//        if (!StringUtils.isEmpty(html)) {
//            valueOptions.set("goodsDetail:" + goodsId, html, 60, TimeUnit.SECONDS);
//        }
//        return html;
//        //        return "goodsDetail";
//    }
}
