package com.naichuan.seckill.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张乃川
 * @date 2021/11/4 9:33
 */
@Service
@Slf4j
public class MqSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

//    public void send(Object msg) {
//        log.info("发送消息：" + msg);
////        rabbitTemplate.convertAndSend("queue", msg);
//        rabbitTemplate.convertAndSend("fanoutExchange", msg);
//    }

    /**
     * 发送秒杀信息
     * @author 张乃川
     * @date 2021/11/4 10:06
     * @param 
     */
    public void sendSeckillMessage(String message) {
        log.info("发送消息：" + message);
        rabbitTemplate.convertAndSend("seckillExchange", "seckill.message", message);
    }
}
