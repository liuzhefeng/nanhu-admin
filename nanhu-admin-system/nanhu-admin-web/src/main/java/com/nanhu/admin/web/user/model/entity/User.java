package com.nanhu.admin.web.user.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.security.core.GrantedAuthority;

import java.util.Date;

/**
 * @author zfliu
 * @date 2022/8/27
 * @desc:
 */
@Getter
@Setter
@TableName("sys_user")
public class User {

    //主键自动增长
    @TableId(type = IdType.AUTO)
    private Long id;
    //登录名
    private String username;
    //用户名
    private String loginName;
    @JsonIgnore
    private String password;
    //帐户是否过期(1 未过期，0已过期)
    private boolean isAccountNonExpired = true;
    //帐户是否被锁定(1 未锁定，0已锁定)
    private boolean isAccountNonLocked = true;
    //密码是否过期(1 未过期，0已过期)
    private boolean isCredentialsNonExpired = true;
    //帐户是否可用(1 可用，0 删除用户)
    private boolean isEnabled = true;
    //昵称
    private String nickName;
    //手机号
    private String mobile;
    //邮箱
    private String email;
    //部门id
    private Long deptId;
    //部门名称
    private String deptName;
    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //更新时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    //是否是管理员 1：是 0 ：不是
    private String isAdmin;
    //0:男 1：女
    private String sex;
    //岗位id
    private Integer postId;
    //岗位名称
    private String postName;


    public User() {
    }
}
