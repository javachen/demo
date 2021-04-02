package com.example.demo.service.acl.impl;

import com.example.demo.entity.acl.User;
import com.example.demo.mapper.acl.UserMapper;
import com.example.demo.service.acl.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author chenzz
 * @since 2021-04-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
