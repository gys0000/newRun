package com.gystry.common.net;

public class BaseCommonBean<T> {
    private boolean success;//提示信息
    private String error;
    private int code;//状态码
    private T data;//实际有用的数据

    public String getError() {
        if (error != null) {
            return error;
        } else {
            return "";
        }

    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
