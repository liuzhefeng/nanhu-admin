package com.nanhu.admin.web.user.controller;

import com.nanhu.admin.web.user.entity.User;
import com.nanhu.admin.web.user.service.UserService;
import com.nanhu.admin.utils.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zfliu
 * @date 2022/8/27
 * @desc:
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUser")
    public ResultVo getAllUser() {
        List<User> list = userService.list();
        return new ResultVo("成功", 200, list);
    }
}
