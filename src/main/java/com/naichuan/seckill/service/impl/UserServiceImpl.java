package com.naichuan.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.naichuan.seckill.mapper.UserMapper;
import com.naichuan.seckill.pojo.User;
import com.naichuan.seckill.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author naichuan
 * @since 2021-10-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
