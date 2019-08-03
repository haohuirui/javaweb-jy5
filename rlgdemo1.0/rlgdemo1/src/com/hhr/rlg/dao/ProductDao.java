package com.hhr.rlg.dao;

import com.hhr.rlg.common.ResponseCode;
import com.hhr.rlg.pojo.Product;
import com.hhr.rlg.utils.RlgUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDao {
    public List<Product> selectAll(String pageNum, String pageSize) {
        QueryRunner qr = new QueryRunner(RlgUtil.getCom());
        String sql = "select * from product limit ?,?";
        List<Product> q = null;
        try {
            q = qr.query(sql, new BeanListHandler<Product>(Product.class), pageNum, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return q;
    }

    public Product selectOne(Integer productId) {
        QueryRunner qr = new QueryRunner(RlgUtil.getCom());
        String sql = "select * from product where pid = ? ";
        Product q =null;
        try {
            q = qr.query(sql, new BeanHandler<Product>(Product.class), productId);
        }catch(SQLException e){

        }
        return q;
    }

    public Product selectOne1(String productName) {
        QueryRunner qr = new QueryRunner(RlgUtil.getCom());
        String sql = "select * from product where pname = ? ";
        Product q =null;
        try {
            q = qr.query(sql, new BeanHandler<Product>(Product.class), productName);
        }catch(SQLException e){
        }
        return q;
    }
}
