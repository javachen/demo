package com.example.demo.service.acl.impl;

import com.example.demo.entity.acl.Role;
import com.example.demo.mapper.acl.RoleMapper;
import com.example.demo.service.acl.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenzz
 * @since 2021-04-01
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
