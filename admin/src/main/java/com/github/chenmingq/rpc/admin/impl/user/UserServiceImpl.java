package com.github.chenmingq.rpc.admin.impl.user;

import com.github.chenmingq.rpc.admin.core.config.ConfigValue;
import com.github.chenmingq.rpc.admin.entity.user.User;
import com.github.chenmingq.rpc.admin.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : cmq
 * date : 2021/01
 * description : 用户相关
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private ConfigValue configValue;

    @Override
    public User login(User user) {
        if (null == configValue) {
            throw new RuntimeException("加载异常,检查配置");
        }
        if (configValue.password.equals(user.getPassword()) && configValue.userName.equals(user.getUserName())) {
            User result = new User();
            result.setUserName(user.getUserName());
            return user;
        }
        return null;
    }
}
