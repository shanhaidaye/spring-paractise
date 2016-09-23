package org.register.enums;

/**
 * Created by sunshiwang on 16/6/5.
 */
public enum RegisterStateEnum {


    REGISTER_SUCCESS(1,"注册成功"),
    REGISTER_REPEAT(0,"账号已经注册过"),
    REGISTER_RAND_ERROR(0,"验证码错误"),
    REGISTER_INFO_NOT_COMPLETED(0,"请输入必要信息"),
    LOGIN_SUCCESS(1,"登录成功"),
    LOGIN_ALREADY_SUCCESS(1,"已经登录成功"),
    LOGIN_ACCOUNT_NOT_EXISTS(-1,"账号不存在"),
    LOGIN_PASSWORD_ERROR(-2,"密码错误"),
    INNER_ERROR(-100,"系统异常");

    private int state;

    private String stateInfo;

    RegisterStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public static RegisterStateEnum stateOf(int index){
        for(RegisterStateEnum stateEnum:values()){
            if(stateEnum.getState()==index){
                return stateEnum;
            }
        }
        return null;
    }
}
