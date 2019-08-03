package com.hhr.rlg.dao;

import com.hhr.rlg.common.ResponseCode;
import com.hhr.rlg.pojo.Commodity;
import com.hhr.rlg.utils.RlgUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CommodityDao {
    public List<Commodity> selectAll(String parentid) {
        QueryRunner qr = new QueryRunner(RlgUtil.getCom());
        String sql = "select * from category where parentid = ?";
        List<Commodity> q = null;
        try {
            q = qr.query(sql, new BeanListHandler<Commodity>(Commodity.class), parentid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return q;
    }

    public int addOne(String parentId,String categoryName) {
        QueryRunner qr = new QueryRunner(RlgUtil.getCom());
        String sql = "insert into parent values(?,?)";
        int i = 0;
        try {
            i = qr.update(sql,parentId,categoryName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public int updateOne(String categoryId, String categoryName) {
        QueryRunner qr = new QueryRunner(RlgUtil.getCom());
        String sql = "update parent set pname = ? where parentid = ?";
        int i = 0;
        try {
            i = qr.update(sql,categoryName,categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
