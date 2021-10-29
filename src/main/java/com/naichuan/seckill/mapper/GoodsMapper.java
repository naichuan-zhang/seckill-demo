package com.naichuan.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.naichuan.seckill.pojo.Goods;
import com.naichuan.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author naichuan
 * @since 2021-10-29
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 获取商品列表
     * @author 张乃川
     * @date 2021/10/29 15:19
     * @param 
     */
    List<GoodsVo> findGoodsVo();
}
