package com.nanhu.admin.web.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.nanhu.admin.config.security.CustomerUserDetailsService;
import com.nanhu.admin.config.security.context.AuthenticationContextHolder;
import com.nanhu.admin.exception.user.UserPasswordNotMatchException;
import com.nanhu.admin.web.user.model.entity.User;
import com.nanhu.admin.web.user.model.req.LoginReq;
import com.nanhu.admin.web.user.service.UserService;
import com.nanhu.admin.utils.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zfliu
 * @date 2022/8/27
 * @desc:
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;


    @PostMapping("/login")
    public ResultVo login(LoginReq req) {
        try {
            return new ResultVo(userService.login(req), 200, null);
        } catch (Exception e) {
            String str = e.getMessage();
            return new ResultVo(str, 500, null);
        }
    }


    @GetMapping("/getAllUser")
    public ResultVo getAllUser() {
        List<User> list = userService.list();
        return new ResultVo("成功", 200, list);
    }
}
