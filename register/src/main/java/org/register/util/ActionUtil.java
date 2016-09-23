package org.register.util;

import org.register.entity.UserInfo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sunshiwang on 16/6/5.
 */
public class ActionUtil {
    public static final String SESSION_LOGIN_KEY="USER";
    public static final String SESSION_RAND_KEY="rand";

    public static UserInfo getSessionUser(){
        HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (UserInfo) request.getSession().getAttribute(SESSION_LOGIN_KEY);
    }

    public static void setSessionUser(UserInfo userInfo){
        HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
       request.getSession().setAttribute(SESSION_LOGIN_KEY,SESSION_LOGIN_KEY);
    }
    public static String getUserName(){
        return getSessionUser()==null?null:getSessionUser().getUserName();
    }


    public static boolean isRandRight(String rand){
        HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String sessionRand=(String)request.getSession().getAttribute(SESSION_RAND_KEY);
        return sessionRand!=null&&rand!=null&&sessionRand.equalsIgnoreCase(rand);
    }

}
