package com.hhr.rlg.common;

public class ResponseCode<T> {
    private Integer status;//登录状态
    private T data;//因为data可以存放数组，集合等类型的数据所以定义为T类型。T为类的泛型
    private String msg;//信息

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    //成功的时候只要返回状态码和成功获取的数据就可以了
    //失败的时候只要返回状态码和失败的信息就可以了

    @Override
    public String toString() {
        return "ResponseCode{" +
                "status=" + status +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
