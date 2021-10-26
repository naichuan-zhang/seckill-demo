package com.naichuan.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.naichuan.seckill.pojo.User;
import com.naichuan.seckill.vo.LoginVo;
import com.naichuan.seckill.vo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author naichuan
 * @since 2021-10-24
 */
public interface IUserService extends IService<User> {

    /**
     * 登录
     * @author 张乃川
     * @date 2021/10/26 17:14
     * @param 
     */
    RespBean doLogin(LoginVo loginVo);
}
