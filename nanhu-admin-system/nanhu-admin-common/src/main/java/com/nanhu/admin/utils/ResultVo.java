package com.nanhu.admin.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zfliu
 * @date 2022/8/27
 * @desc: 通用结果类
 */
@Getter
@Setter
@AllArgsConstructor
public class ResultVo<T> {

    private String msg;
    private int code;
    private T data;

}
