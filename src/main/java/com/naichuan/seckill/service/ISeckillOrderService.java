package com.naichuan.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.naichuan.seckill.pojo.SeckillOrder;
import com.naichuan.seckill.pojo.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author naichuan
 * @since 2021-10-29
 */
public interface ISeckillOrderService extends IService<SeckillOrder> {

    /**
     * 获取秒杀结果
     * @author 张乃川
     * @date 2021/11/4 10:37
     * @param
     * @return orderId：成功；-1：秒杀失败，0：排队中
     */
    Long getResult(User user, Long goodsId);
}
