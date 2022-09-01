package com.nanhu.admin.web.user.model.aggregates;

import com.nanhu.admin.web.user.model.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author zfliu
 * @date 2022/8/29
 * @desc:
 */
public class UserDetails extends User implements org.springframework.security.core.userdetails.UserDetails {
    //由于authorities不是数据库里面的字段，所以要排除他，不然mybatis-plus找不到该字段会报错
    //@TableField(exist = false)
    Collection<? extends GrantedAuthority> authorities;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
