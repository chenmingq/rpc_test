package com.github.chenmingq.rpc.admin.controller;

import com.github.chenmingq.rpc.admin.core.consts.ResultCode;
import com.github.chenmingq.rpc.admin.core.result.ResultService;
import com.github.chenmingq.rpc.admin.entity.user.User;
import com.github.chenmingq.rpc.admin.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : cmq
 * date : 2021/01
 * description : 用户相关
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;

    /**
     * 登录
     *
     * @param data
     * @return
     */
    @CrossOrigin
    @PostMapping("/login")
    public ResultService login(@RequestBody User data) {
        User user = iUserService.login(data);
        if (null == user) {
            return new ResultService(ResultCode.FAILURE.getCode(), "账号密码不正确", "");
        }
        return new ResultService(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), user);
    }
}
