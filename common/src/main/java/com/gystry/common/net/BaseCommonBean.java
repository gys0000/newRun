package com.gystry.common.net;

public class BaseCommonBean<T> {
    private String message;//提示信息
    private int status;//状态码
    private  T data;//实际有用的数据

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
