package com.nanhu.admin.web.user.model.req;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfliu
 * @date 2022/8/29
 * @desc:
 */
@Setter
@Getter
public class LoginReq {
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

}
