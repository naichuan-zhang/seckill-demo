package com.naichuan.seckill.rabbitmq;

import com.naichuan.seckill.pojo.SeckillMessage;
import com.naichuan.seckill.pojo.SeckillOrder;
import com.naichuan.seckill.pojo.User;
import com.naichuan.seckill.service.IGoodsService;
import com.naichuan.seckill.service.IOrderService;
import com.naichuan.seckill.utils.JsonUtil;
import com.naichuan.seckill.vo.GoodsVo;
import com.naichuan.seckill.vo.RespBean;
import com.naichuan.seckill.vo.RespBeanEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author 张乃川
 * @date 2021/11/4 9:34
 */
@Service
@Slf4j
public class MqReceiver {

//    @RabbitListener(queues = "queue")
//    public void receive(Object msg) {
//        log.info("接收消息：" + msg);
//    }
//
//    @RabbitListener(queues = "queue_fanout01")
//    public void receive01(Object msg) {
//        log.info("QUEUE01接收消息：" + msg);
//    }
//
//    @RabbitListener(queues = "queue_fanout02")
//    public void receive02(Object msg) {
//        log.info("QUEUE02接收消息：" + msg);
//    }

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 下单操作
     * @author 张乃川
     * @date 2021/11/4 10:20
     * @param 
     */
    @RabbitListener(queues = "seckillQueue")
    public void receive(String message) {
        log.info("接收的消息：" + message);
        SeckillMessage seckillMessage = JsonUtil.jsonStr2Object(message, SeckillMessage.class);
        Long goodsId = seckillMessage.getGoodsId();
        User user = seckillMessage.getUser();
        // 判断库存
        GoodsVo goodsVo = goodsService.findGoodsByGoodsId(goodsId);
        if (goodsVo.getStockCount() < 1) {
            return;
        }
        // 判断是否重复抢购
        SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goodsId);
        if (seckillOrder != null) {
            return;
        }
        // 下单操作
        orderService.seckill(user, goodsVo);
    }
}
