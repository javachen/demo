package com.example.demo.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.RegisterVo;
import com.example.demo.entity.user.UcenterMember;
import com.example.demo.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.user.UcenterMemberMapper;
import com.example.demo.service.user.UcenterMemberService;
import com.example.demo.utli.JwtUtils;
import com.example.demo.utli.MD5;
import com.example.demo.utli.ResultCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author chenzz
 * @since 2021-03-30
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    //@Autowired
   // private RedisTemplate<String,String> redisTempl;
    @Override
    public String login(UcenterMember member) {
        //获取登录手机号和密码
        String mobile = member.getMobile();
        String password = member.getPassword();

        //手机号和密码非空判断
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new GuliException(ResultCode.ERROR,"登录失败");
        }

        //判断手机号是否正确
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember mobileMember = baseMapper.selectOne(wrapper);
        //判断查询对象是否为空
        if(mobileMember == null) {//没有这个手机号
            throw new GuliException(ResultCode.ERROR,"登录失败");
        }

        //判断密码
        //因为存储到数据库密码肯定加密的
        //把输入的密码进行加密，再和数据库密码进行比较
        //加密方式 MD5
        if(!MD5.encrypt(password).equals(mobileMember.getPassword())) {
            throw new GuliException(ResultCode.ERROR,"登录失败");
        }

        //判断用户是否禁用
        if(mobileMember.getIsDisabled()) {
            throw new GuliException(ResultCode.ERROR,"登录失败");
        }

        //登录成功
        //生成token字符串，使用jwt工具类
        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
        return jwtToken;

    }

    @Override
    public void register(RegisterVo registerVo) {

    }
}
