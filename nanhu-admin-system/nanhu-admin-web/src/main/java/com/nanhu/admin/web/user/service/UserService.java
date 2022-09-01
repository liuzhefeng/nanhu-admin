package com.nanhu.admin.web.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nanhu.admin.web.user.model.entity.User;
import com.nanhu.admin.web.user.model.req.LoginReq;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author zfliu
 * @date 2022/8/27
 * @desc:
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名查询用户信息
     */
    UserDetails getUserByUserName(String username);

    String login(LoginReq req);
}
