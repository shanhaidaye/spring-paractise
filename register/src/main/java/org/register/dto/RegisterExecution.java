package org.register.dto;

import org.register.enums.RegisterStateEnum;

/**
 * Created by sunshiwang on 16/6/4.
 */
public class RegisterExecution {

    private String  userName;

    //秒杀执行结果状态
    private int state;

    //状态说明
    private String stateInfo;


    public RegisterExecution(String userName, int state, String stateInfo) {
        this.userName = userName;
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public RegisterExecution(String userName, RegisterStateEnum registerStateEnum) {
        this.userName = userName;
        this.state = registerStateEnum.getState();
        this.stateInfo=registerStateEnum.getStateInfo();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    @Override
    public String toString() {
        return "RegisterExecution{" +
                "userName='" + userName + '\'' +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                '}';
    }
}
