package org.register.dto;

/**
 * Created by sunshiwang on 16/5/22.
*所有json的返回类型 封装json
 */
public class CommonResult<T> {
    private boolean success;
    private T data;
    private String error;

    public CommonResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public CommonResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
