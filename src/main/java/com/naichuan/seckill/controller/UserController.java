package com.naichuan.seckill.controller;


import com.naichuan.seckill.pojo.User;
import com.naichuan.seckill.vo.RespBean;
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
}
