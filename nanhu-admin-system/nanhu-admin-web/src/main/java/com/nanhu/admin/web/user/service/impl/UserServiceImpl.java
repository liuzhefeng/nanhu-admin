package com.nanhu.admin.web.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nanhu.admin.web.user.entity.User;
import com.nanhu.admin.web.user.mapper.UserMapper;
import com.nanhu.admin.web.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author zfliu
 * @date 2022/8/27
 * @desc:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
