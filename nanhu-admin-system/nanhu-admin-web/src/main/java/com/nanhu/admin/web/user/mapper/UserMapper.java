package com.nanhu.admin.web.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nanhu.admin.web.user.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zfliu
 * @date 2022/8/27
 * @desc:
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
