package com.example.demo.entity.user;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalTime;
import java.util.Date;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author chenzz
 * @since 2021-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UcenterMember implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会员id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别 1 女，2 男
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户签名
     */
    private String sign;

    /**
     * 是否禁用 1（true）已禁用，  0（false）未禁用
     */
    private Boolean isDisabled;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableLogic
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    @ApiModelProperty(required = false,example = "2018-10-01 12:18:48")
    @JsonFormat(locale = "zh",pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @ApiModelProperty(required = false,example = "2018-10-01 12:18:48")
    @JsonFormat(locale = "zh",pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.UPDATE )
    private Date gmtModified;


}
