package rlg.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import rlg.common.ResponseCode;
import rlg.pojo.User;
import rlg.utils.RlgUtil;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    //查找所有用户
    public List<User> selectAll(String pageSize, String pageNum) {
        QueryRunner qr = new QueryRunner(RlgUtil.getCom());
        String sql = "select * from users ";
        List<User> li = null;
        try {
            li = qr.query(sql, new BeanListHandler<User>(User.class)/*,pageNum,pageSize*/);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return li;
    }
    //根据用户名和密码查找一个用户
    public User selectOne(String username, String password) {
        QueryRunner qr = new QueryRunner(RlgUtil.getCom());
        String sql = "select * from users where uname=? and psd=?";
        User u = null;
        try {
            u = qr.query(sql, new BeanHandler<User>(User.class),username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }
    //根据ID查找一个用户
    public User selectOne(Integer uid) {
        QueryRunner qr = new QueryRunner(RlgUtil.getCom());
        String sql = "select * from users where uid = ?";
        User u = null;
        try {
            u = qr.query(sql, new BeanHandler<User>(User.class),uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public int updateByUid(Integer uid) {
        QueryRunner qr = new QueryRunner(RlgUtil.getCom());
        String sql = "update users set stats = 1 where uid = ?";
        int row = 0;
        try {
            row = qr.update(sql,uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
}
