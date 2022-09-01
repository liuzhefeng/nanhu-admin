package com.nanhu.admin.config.security;

import com.nanhu.admin.config.security.context.AuthenticationContextHolder;
import com.nanhu.admin.exception.user.UserException;
import com.nanhu.admin.exception.user.UserPasswordNotMatchException;
import com.nanhu.admin.web.user.service.UserService;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LoggingSystemFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zfliu
 * @date 2022/8/29
 * @desc:
 */
@Component("customerUserDetailsService")
public class CustomerUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(CustomerUserDetailsService.class);

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.查询用户是否存在
        UserDetails user = userService.getUserByUserName(username);
        if (user == null) {
            throw new UserException("500", "用户名或密码错误!");
        }
        validate(user);
        //2.授权
        return user;
    }

    private void validate(UserDetails user) {
        Authentication usernamePasswordAuthenticationToken = AuthenticationContextHolder.getContext();
        String password = usernamePasswordAuthenticationToken.getCredentials().toString();
        if (!matches(user, password)) {
            throw new BadCredentialsException("密码错误");
        }
    }

    private boolean matches(UserDetails user, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, user.getPassword());
    }
}
