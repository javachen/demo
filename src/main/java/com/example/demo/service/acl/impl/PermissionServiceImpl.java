package com.example.demo.service.acl.impl;

import com.example.demo.entity.acl.Permission;
import com.example.demo.mapper.acl.PermissionMapper;
import com.example.demo.service.acl.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author chenzz
 * @since 2021-04-01
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
