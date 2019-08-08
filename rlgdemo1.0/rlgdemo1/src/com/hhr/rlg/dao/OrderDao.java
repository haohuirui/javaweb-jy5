package com.hhr.rlg.dao;

import com.hhr.rlg.common.ResponseCode;
import com.hhr.rlg.pojo.Order;
import com.hhr.rlg.utils.RlgUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class OrderDao {
    //查询订单list
    public List<Order> selectAll(int i, int i1) {
        QueryRunner qr = new QueryRunner(RlgUtil.getCom());
        String sql = "select * from order limit ? , ? ";
        List<Order> q = null;
        try {
            q = qr.query(sql, new BeanListHandler<Order>(Order.class), i, i1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return q;
    }
    //按订单号查询
    public List<Order> selectAll1(Integer i) {
        QueryRunner qr = new QueryRunner(RlgUtil.getCom());
        String sql = "select * from order where orderNO = ? ";
        List<Order> q = null;
        try {
            q = qr.query(sql, new BeanListHandler<Order>(Order.class), i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return q;
    }
    //查询订单详情
    public List<Order> selectAll2(Integer i) {
        QueryRunner qr = new QueryRunner(RlgUtil.getCom());
        String sql = "select * from order where orderNO = ? ";
        List<Order> q = null;
        try {
            q = qr.query(sql, new BeanListHandler<Order>(Order.class), i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return q;
    }
    //查询订单发货状态
    public Order selectAll3(Integer i) {
        QueryRunner qr = new QueryRunner(RlgUtil.getCom());
        String sql = "select * from order where orderNO = ? and status = 1 ";
        Order q = null;
        try {
            q = qr.query(sql, new BeanHandler<Order>(Order.class), i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return q;
    }
}
