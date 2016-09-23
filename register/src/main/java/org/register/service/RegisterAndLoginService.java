package org.register.service;

import org.apache.ibatis.annotations.Param;
import org.register.dto.RegisterExecution;
import org.register.entity.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sunshiwang on 16/6/4.
 */
public interface RegisterAndLoginService {
    /**
     * 注册保存
     * @param userName
     * @param password
     * @return
     */
    RegisterExecution register( String userName, String password);

    /**
     * 查询用户信息
     * @param userName
     * @return
     */
    UserInfo queryByUserName(String userName);

    /**
     * 登录逻辑判断
     * @param userName
     * @param pwd
     * @return
     */
    RegisterExecution login(String userName, String pwd);
}
