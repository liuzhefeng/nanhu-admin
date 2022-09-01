package com.nanhu.admin.web.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.nanhu.admin.config.security.context.AuthenticationContextHolder;
import com.nanhu.admin.exception.user.UserPasswordNotMatchException;
import com.nanhu.admin.utils.ResultVo;
import com.nanhu.admin.web.user.model.entity.User;
import com.nanhu.admin.web.user.mapper.UserMapper;
import com.nanhu.admin.web.user.model.req.LoginReq;
import com.nanhu.admin.web.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author zfliu
 * @date 2022/8/27
 * @desc:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails getUserByUserName(String username) {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.eq(User::getUsername, username);
        User user = this.baseMapper.selectOne(query);
        if (Objects.isNull(user)) {
            return null;
        }
        UserDetails userDetails = new com.nanhu.admin.web.user.model.aggregates.UserDetails();
        BeanUtils.copyProperties(user, userDetails);
        return userDetails;
    }

    @Override
    public String login(LoginReq req) {
        Authentication authentication = null;

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword());
        AuthenticationContextHolder.setContext(authenticationToken);
        // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername

        authentication = authenticationManager.authenticate(authenticationToken);


        return "token_1234562";


    }
}
