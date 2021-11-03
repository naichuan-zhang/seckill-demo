package com.naichuan.seckill.vo;

import com.naichuan.seckill.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 张乃川
 * @date 2021/11/3 16:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailVo {

    private User user;
    private GoodsVo goodsVo;
    private int secKillStatus;
    private int remainSeconds;
}
