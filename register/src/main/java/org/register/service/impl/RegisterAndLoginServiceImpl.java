package org.register.service.impl;

import org.register.dao.RegisterDao;
import org.register.dto.RegisterExecution;
import org.register.entity.UserInfo;
import org.register.enums.RegisterStateEnum;
import org.register.service.RegisterAndLoginService;
import org.register.util.ActionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

/**
 * command+alt+o 优化import自动去除无用的import语句，蛮不错的一个功能
 * Created by sunshiwang on 16/6/5.
 */
@Service
public class RegisterAndLoginServiceImpl implements RegisterAndLoginService {

    //md5盐值字符串,混淆md5
    private final String slat = "hdfahfjahifffdfkfjifDGG$$%^HHJJ";

    @Autowired
    RegisterDao registerDao;


    public RegisterExecution register(String userName, String password) {

        UserInfo userInfo=registerDao.queryByUserName(userName);
        if(userInfo!=null){
            return new RegisterExecution(userName,RegisterStateEnum.REGISTER_REPEAT);
        }
        int rtn= registerDao.register(userName,getMd5(password));
        if(rtn==1){
           return new RegisterExecution(userName,RegisterStateEnum.REGISTER_SUCCESS);
        }
        return new RegisterExecution(userName,RegisterStateEnum.REGISTER_REPEAT);

    }

    public UserInfo queryByUserName(String userName) {
        return null;
    }

    public RegisterExecution login(String userName, String pwd) {

        if(!StringUtils.isEmpty(ActionUtil.getUserName())){
          return new RegisterExecution(userName,RegisterStateEnum.LOGIN_ALREADY_SUCCESS);
        }
        UserInfo userInfo=registerDao.queryByUserName(userName);
        if(userInfo==null){
            return new RegisterExecution(userName,RegisterStateEnum.LOGIN_ACCOUNT_NOT_EXISTS);
        }else if(!getMd5(pwd).equals(userInfo.getPassword())){
            return new RegisterExecution(userName,RegisterStateEnum.LOGIN_PASSWORD_ERROR);
        }
        ActionUtil.setSessionUser(userInfo);
        return new RegisterExecution(userName,RegisterStateEnum.LOGIN_SUCCESS);

    }

    private String getMd5(String pwd ) {
        String base = pwd + "/" + slat;
        return DigestUtils.md5DigestAsHex(base.getBytes());

    }
}
