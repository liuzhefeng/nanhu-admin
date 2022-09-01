package com.nanhu.admin.exception.user;

import com.nanhu.admin.exception.base.BaseException;

/**
 * @author zfliu
 * @date 2022/8/29
 * @desc:
 */
public class UserException extends BaseException {

    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args) {
        super("user", code, args);
    }
}
