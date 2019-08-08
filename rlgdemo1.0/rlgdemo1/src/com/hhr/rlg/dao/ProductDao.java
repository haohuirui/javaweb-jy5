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
    //查询所有产品信息
    public List<Product> selectAll(Integer pageNum, Integer pageSize) {
        QueryRunner qr = new QueryRunner(RlgUtil.getCom());
        String sql = "select * from product limit ?,? ";
        List<Product> q = null;
        try {
            q = qr.query(sql, new BeanListHandler<Product>(Product.class), pageNum, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return q;
    }
    //查询搜索产品信息productId
    public Product selectOne(Integer productId,String pageNum,String pageSize) {
        QueryRunner qr = new QueryRunner(RlgUtil.getCom());
        String sql = "select * from product where pid = ? limit ?,? ";
        Product q =null;
        try {
            q = qr.query(sql, new BeanHandler<Product>(Product.class), productId,pageNum,pageSize);
        }catch(SQLException e){

        }
        return q;
    }
    //查询搜索产品信息productName
    public Product selectOne1(String productName,String pageNum,String pageSize) {
        QueryRunner qr = new QueryRunner(RlgUtil.getCom());
        String sql = "select * from product where pname = ? limit ?,? ";
        Product q =null;
        try {
            q = qr.query(sql, new BeanHandler<Product>(Product.class), productName,pageNum,pageSize);
        }catch(SQLException e){
        }
        return q;
    }
    //查询产品详情
    public Product selectOne2(Integer productId) {
        QueryRunner qr = new QueryRunner(RlgUtil.getCom());
        String sql = "select * from product where pid = ? ";
        Product q = null;
        try {
            q = qr.query(sql, new BeanHandler<Product>(Product.class), productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return q;
    }
    //修改产品的上下架状态
    public int updateOne(Integer productId, Integer status) {
        QueryRunner qr = new QueryRunner(RlgUtil.getCom());
        String sql = "update product set status = ? where pid = ? ";
        int ud = 0;
        try {
            ud = qr.update(sql, status, productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ud;
    }
    //更新产品信息
    public int updateOne1(int i, String name, String subtitle, String mainImage, double v, int i1, int i2) {
        QueryRunner qr = new QueryRunner(RlgUtil.getCom());
        String sql = "update product set categoryId = ? , pname = ? ,subtitle = ? ," +
                " mainImage = ? , status = ? , price = ? where pid = ? ";
        int in1 = 0;
        try {
            in1 = qr.update(sql, i, name, subtitle, mainImage, i1, v , i2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return in1;
    }
   //查询产品信息
    public boolean selectOne3(int i2) {
        QueryRunner qr = new QueryRunner(RlgUtil.getCom());
        String sql = "select * from product where pid = ? ";
        Product q = null;
        try {
           q = qr.query(sql, new BeanHandler<Product>(Product.class),i2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (q != null){
            return true;
        }
        return false;
    }
    //新增产品信息
    public int insertOne(int i, String name, String subtitle, String mainImage, double v, int i1) {
        QueryRunner qr = new QueryRunner(RlgUtil.getCom());
        String sql = "insert into porduct values(null , ? , ? , ? , ? , ? , ? )";
        int in2 = 0;
        try {
            in2 = qr.update(sql, i, name, subtitle, mainImage, i1, v);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return in2;
    }
}
