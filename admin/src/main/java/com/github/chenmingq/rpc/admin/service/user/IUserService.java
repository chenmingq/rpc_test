package com.github.chenmingq.rpc.admin.service.user;

import com.github.chenmingq.rpc.admin.entity.user.User;

/**
 * @author : cmq
 * date : 2021/01
 * description :用户相关
 */

public interface IUserService {


    /**
     * 登录
     *
     * @param user
     * @return
     */
    User login(User user);
}
