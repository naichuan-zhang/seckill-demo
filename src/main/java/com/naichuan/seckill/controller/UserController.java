package com.naichuan.seckill.controller;


import com.naichuan.seckill.pojo.User;
import com.naichuan.seckill.rabbitmq.MqSender;
import com.naichuan.seckill.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author naichuan
 * @since 2021-10-24
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MqSender sender;

    /**
     * 用户信息（用于测试）
     * @author 张乃川
     * @date 2021/11/3 14:38
     * @param 
     */
    @RequestMapping("/info")
    @ResponseBody
    public RespBean info(User user) {
        return RespBean.success(user);
    }

//    /**
//     * 发送rabbitmq消息（用于测试）
//     * @author 张乃川
//     * @date 2021/11/4 9:37
//     * @param
//     */
//    @RequestMapping("/mq")
//    @ResponseBody
//    public void mq() {
//        sender.send("Hello, RabbitMQ!");
//    }
}
