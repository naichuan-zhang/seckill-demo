package com.naichuan.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.naichuan.seckill.pojo.Goods;
import com.naichuan.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author naichuan
 * @since 2021-10-29
 */
public interface IGoodsService extends IService<Goods> {

    /**
     * 获取商品列表
     * @author 张乃川
     * @date 2021/10/29 15:18
     * @param 
     */
    List<GoodsVo> findGoodsVo();
}
