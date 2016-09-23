package org.register.web;

import org.register.dto.CommonResult;
import org.register.dto.RegisterExecution;
import org.register.enums.RegisterStateEnum;
import org.register.service.RegisterAndLoginService;
import org.register.util.ActionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sunshiwang on 16/6/5.
 */
@Controller
@RequestMapping("/user")//url :模块/ 资源 /id /细分
public class RegisterAndLoginController {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());


    @Autowired
    RegisterAndLoginService registerAndLoginService;


    @RequestMapping(value = "toRegister",method = RequestMethod.GET)
    public String toRegister(Model model){

        //list.jsp+model=ModelAndView
        return "register";
    }

    @RequestMapping(value = "toLogin",method = RequestMethod.GET)
    public String toLogin(Model model){

        //list.jsp+model=ModelAndView
        return "login";
    }

    @RequestMapping(value = "test",method = RequestMethod.GET)
    public String toTest(Model model){

        //list.jsp+model=ModelAndView
        return "index";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CommonResult<RegisterExecution> execute(String userName, String password, String rand){



        try {
            //如果多参数 用 springmvc valid  验证信息
            if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(password)||StringUtils.isEmpty(rand)){
                RegisterExecution execution=new RegisterExecution(userName, RegisterStateEnum.REGISTER_INFO_NOT_COMPLETED);
                return new CommonResult<RegisterExecution>(true,execution);
            }

            //验证验证码正确性
            if(!ActionUtil.isRandRight(rand)){
                RegisterExecution execution=new RegisterExecution(userName, RegisterStateEnum.REGISTER_RAND_ERROR);
                return new CommonResult<RegisterExecution>(true,execution);
            }

            //注册逻辑
            RegisterExecution registerExecution=registerAndLoginService.register(userName,password);
            return new CommonResult<RegisterExecution>(true,registerExecution);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            RegisterExecution execution=new RegisterExecution(userName,RegisterStateEnum.INNER_ERROR);
            return new CommonResult<RegisterExecution>(true,execution);
        }

    }

    @RequestMapping(value = "/login",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CommonResult<RegisterExecution> signIn(String userName, String password, String rand){



        try {
            //如果多参数 用 springmvc valid  验证信息
            if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(password)||StringUtils.isEmpty(rand)){
                RegisterExecution execution=new RegisterExecution(userName, RegisterStateEnum.REGISTER_INFO_NOT_COMPLETED);
                return new CommonResult<RegisterExecution>(true,execution);
            }

            //验证验证码正确性
            if(!ActionUtil.isRandRight(rand)){
                RegisterExecution execution=new RegisterExecution(userName, RegisterStateEnum.REGISTER_RAND_ERROR);
                return new CommonResult<RegisterExecution>(true,execution);
            }

            //登录逻辑
            RegisterExecution registerExecution=registerAndLoginService.login(userName,password);
            return new CommonResult<RegisterExecution>(true,registerExecution);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            RegisterExecution execution=new RegisterExecution(userName,RegisterStateEnum.INNER_ERROR);
            return new CommonResult<RegisterExecution>(true,execution);
        }

    }
}
