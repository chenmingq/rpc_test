package com.github.chenmingq.rpc.admin.entity.user;

import io.protostuff.Tag;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : cmq
 * date : 2021/01
 * description : 用户信息
 */

@Setter
@Getter
public class User {

    /**
     * 用户名
     */
    @Tag(1)
    private String userName;

    /**
     * 密码
     */
    @Tag(2)
    private String password;

}
