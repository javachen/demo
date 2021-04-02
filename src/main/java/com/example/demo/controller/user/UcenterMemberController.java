package com.example.demo.controller.user;


import com.example.demo.entity.RegisterVo;
import com.example.demo.entity.user.UcenterMember;
import com.example.demo.service.user.UcenterMemberService;
import com.example.demo.utli.JwtUtils;
import com.example.demo.utli.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author chenzz
 * @since 2021-03-30
 */
@Api(description = "用户操作接口")
@RestController
@RequestMapping("/ucenter-member")
public class UcenterMemberController   {

    @Autowired
    private UcenterMemberService memberService;

    //登录
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember member) {
        //member对象封装手机号和密码
        //调用service方法实现登录
        //返回token值，使用jwt生成
        String token = memberService.login(member);
        return R.ok().data("token",token);
    }

    //注册
    @PostMapping("register")
    public R registerUser(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }

    //根据token获取用户信息
    @PostMapping("getMemberInfo")
    public R getMemberInfo(@RequestHeader("token") String token ,HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);
        return R.ok().data("userInfo",member);
    }
}
