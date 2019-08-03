package com.hhr.rlg.pojo;
/**
 * 商品类
 * */
public class Commodity {
    private Integer cid;
    private Integer parentid;//父id
    private String name;
    private boolean status;//库存状况(存在为true)

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "cid=" + cid +
                ", parentid=" + parentid +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
