package com.hhr.rlg.common;

public class ResponseCode<T> {
    private Integer status;//登录状态
    private T data;//因为data可以存放数组，集合等类型的数据所以定义为T类型。T为类的泛型
    private String msg;//信息
    private boolean success;//上传是否成功
    private String file_path;//图片路径

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
    //成功的时候只要返回状态码和成功获取的数据就可以了
    //失败的时候只要返回状态码和失败的信息就可以了


    @Override
    public String toString() {
        return "ResponseCode{" +
                "status=" + status +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", success=" + success +
                ", file_path='" + file_path + '\'' +
                '}';
    }
}
