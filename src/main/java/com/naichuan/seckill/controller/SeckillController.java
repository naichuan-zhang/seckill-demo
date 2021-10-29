package com.naichuan.seckill.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.naichuan.seckill.pojo.Order;
import com.naichuan.seckill.pojo.SeckillOrder;
import com.naichuan.seckill.pojo.User;
import com.naichuan.seckill.service.IGoodsService;
import com.naichuan.seckill.service.IOrderService;
import com.naichuan.seckill.service.ISeckillOrderService;
import com.naichuan.seckill.vo.GoodsVo;
import com.naichuan.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 张乃川
 * @date 2021/10/29 16:31
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private ISeckillOrderService seckillOrderService;

    @Autowired
    private IOrderService orderService;

    /**
     * 秒杀
     * @author 张乃川
     * @date 2021/10/29 16:33
     * @param 
     */
    @RequestMapping("/doSeckill")
    public String doSeckill(Model model, User user, Long goodsId) {
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        GoodsVo goodsVo = goodsService.findGoodsByGoodsId(goodsId);
        // 判断库存
        if (goodsVo.getStockCount() < 1) {
            model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK.getMessage());
            return "secKillFail";
        }
        // 判断是否重复抢购
        SeckillOrder seckillOrder = seckillOrderService.getOne(new QueryWrapper<SeckillOrder>()
                .eq("user_id", user.getId()).eq("goods_id", goodsId));
        if (seckillOrder != null) {
            model.addAttribute("errmsg", RespBeanEnum.REPEATE_ERROR.getMessage());
            return "secKillFail";
        }
        Order order = orderService.seckill(user, goodsVo);
        model.addAttribute("order", order);
        model.addAttribute("goods", goodsVo);
        return "orderDetail";
    }
}
