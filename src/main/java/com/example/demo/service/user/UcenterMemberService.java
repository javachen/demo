package com.example.demo.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.RegisterVo;
import com.example.demo.entity.user.UcenterMember;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author chenzz
 * @since 2021-03-31
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    void register(RegisterVo registerVo);
}
