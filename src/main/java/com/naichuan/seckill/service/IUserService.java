package com.naichuan.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.naichuan.seckill.pojo.User;
import com.naichuan.seckill.vo.LoginVo;
import com.naichuan.seckill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     * @param request
     * @param response
     */
    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据cookie获取用户 
     * @author 张乃川
     * @date 2021/10/28 10:46
     * @param
     * @param request
     * @param response
     */
    User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response);
}
