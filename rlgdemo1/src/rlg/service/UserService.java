package rlg.service;

import rlg.common.Const;
import rlg.common.ResponseCode;
import rlg.dao.UserDao;
import rlg.pojo.User;

import java.util.List;

public class UserService {
    private UserDao ud = new UserDao();
    public ResponseCode selectAll(String pageSize, String pageNum) {
        if (pageSize == null || pageSize.equals("")){
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")){
            pageNum = "1";
        }
        List<User> li = ud.selectAll(pageSize,pageNum);
        ResponseCode rs = new ResponseCode();
        rs.setData(0);
        rs.setData(li);
        return rs;
    }
    //用户登录
    public ResponseCode selectOne(String username, String password) {
        ResponseCode rs = new ResponseCode();
        if (username == null || username.equals("") || password == null || password.equals("")){
            rs.setStatus(1);
            rs.setMsg("账号或密码错误");
            return rs;
        }
        User u = ud.selectOne(username, password);
        //如果用户不存在
        if (u == null){
            rs.setStatus(1);
            rs.setMsg("账号或密码错误");
            return rs;
        }
        //用户权限
        if (u.getType() != 1){
            rs.setStatus(2);
            rs.setMsg("没有操作权限！");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(u);
        return rs;
    }

    public ResponseCode selectOne(String uid) {
        ResponseCode rs = new ResponseCode();
        if (uid == null || uid.equals("") ){
            rs.setStatus(Const.USER_PARAMETER_CODE);
            rs.setMsg(Const.USER_PARAMETER_MSG);
            return rs;
        }
        Integer ui = null;
        try {
            ui = Integer.parseInt(uid);
            System.out.println(ui);
        }catch(Exception e){
            rs.setStatus(105);
            rs.setMsg("输入非法参数");
        }
        User u = ud.selectOne(ui);
        //如果用户不存在
        if (u == null){
            rs.setStatus(Const.USER_NO_CODE);
            rs.setMsg(Const.USER_NO_MSG);
            return rs;
        }
        //用户禁用情况
        if (u.getStats() == 1){
            rs.setStatus(Const.USER_DISABLE_CODE);
            rs.setMsg(Const.USER_DISABLE_MSG);
            return rs;
        }
        int row = ud.updateByUid(ui);
        if (row<=0){
            rs.setStatus(106);
            rs.setMsg("用户禁用失败");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(row);
        return rs;
    }
}
