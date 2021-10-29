package com.naichuan.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.naichuan.seckill.pojo.Order;
import com.naichuan.seckill.pojo.User;
import com.naichuan.seckill.vo.GoodsVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author naichuan
 * @since 2021-10-29
 */
public interface IOrderService extends IService<Order> {

    /**
     * 秒杀 
     * @author 张乃川
     * @date 2021/10/29 16:46
     * @param 
     */
    Order seckill(User user, GoodsVo goodsVo);
}
