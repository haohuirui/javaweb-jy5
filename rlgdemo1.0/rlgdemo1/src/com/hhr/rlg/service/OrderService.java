package com.hhr.rlg.service;

import com.hhr.rlg.common.Const;
import com.hhr.rlg.common.ResponseCode;
import com.hhr.rlg.dao.OrderDao;
import com.hhr.rlg.pojo.Order;

import java.util.List;

public class OrderService {
    ResponseCode rs = new ResponseCode();
    OrderDao od = new OrderDao();
    //订单list
    public ResponseCode selectAll(String pageNum, String pageSize) {
        if (pageNum == null || pageNum.equals("") || pageSize == null || pageSize.equals("")){
            pageNum = "1";
            pageSize = "10";
        }
        int i = Integer.parseInt(pageNum);
        int i1 = Integer.parseInt(pageSize);
        List<Order> li = od.selectAll(i,i1);
        if (li == null){
            rs.setStatus(Const.USER_NO_CODE);//103代表用户不存在
            rs.setMsg(Const.USER_NO_MSG);
        }
        rs.setStatus(0);
        rs.setData(li);
        return rs;
    }
    //按订单号查询
    public ResponseCode selectAll1(String orderNo) {
        if (orderNo == null || orderNo.equals("")){
            rs.setStatus(3);//3代表该参数不能为空
            rs.setMsg("该参数不能为空，请重新输入");
            return rs;
        }
        Integer i = Integer.parseInt(orderNo);
        List<Order> o = od.selectAll1(i);
        if (o == null){
            rs.setStatus(1);//没有找到订单
            rs.setMsg("没有找到订单");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(o);
        return rs;
    }
    //订单详情
    public ResponseCode selectAll2(String orderNo) {
        if (orderNo == null || orderNo.equals("")){
            rs.setStatus(3);//3代表该参数不能为空
            rs.setMsg("该参数不能为空，请重新输入");
            return rs;
        }
        Integer i = Integer.parseInt(orderNo);
        List<Order> o = od.selectAll2(i);
        if (o == null){
            rs.setStatus(1);//没有找到订单
            rs.setMsg("没有找到订单");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(o);
        return rs;
    }
    // 订单发货
    public ResponseCode selectAll3(String orderNo) {
        if (orderNo == null || orderNo.equals("")){
            rs.setStatus(3);//3代表该参数不能为空
            rs.setMsg("该参数不能为空，请重新输入");
            return rs;
        }
        Integer i = Integer.parseInt(orderNo);
        Order o = od.selectAll3(i);
        if (o == null){
            rs.setStatus(1);//没有找到订单
            rs.setMsg("发货失败");
            return rs;
        }
        rs.setStatus(0);
        rs.setData("发货成功");
        return rs;
    }
}
