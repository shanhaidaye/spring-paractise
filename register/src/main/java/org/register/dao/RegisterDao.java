package org.register.dao;

import org.apache.ibatis.annotations.Param;
import org.register.entity.UserInfo;

/**
 * Created by sunshiwang on 16/6/4.
 */
public interface RegisterDao {

    /**
     * 注册保存
     * @param userName
     * @param password
     * @return
     */
    int register(@Param("userName") String userName, @Param("password") String password);

    /**
     * 查询用户信息
     * @param userName
     * @return
     */
    UserInfo queryByUserName(@Param("userName") String userName);
}

