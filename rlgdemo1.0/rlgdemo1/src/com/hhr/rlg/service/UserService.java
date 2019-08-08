package com.hhr.rlg.service;

import com.hhr.rlg.common.Const;
import com.hhr.rlg.dao.UserDao;
import com.hhr.rlg.common.ResponseCode;
import com.hhr.rlg.pojo.User;

import java.util.List;

public class UserService {
    private UserDao ud = new UserDao();
    ResponseCode rs = new ResponseCode();
    public ResponseCode selectAll(String pageSize, String pageNum) {
        if (pageSize == null || pageSize.equals("")){
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")){
            pageNum = "1";
        }
        Integer i = Integer.parseInt(pageNum);
        Integer i1 = Integer.parseInt(pageSize);
        List<User> li = ud.selectAll(i,i1);
        if (li == null){
            rs.setStatus(Const.COMMODITY_NO_LOGIN_CODE);
            rs.setMsg(Const.COMMODITY_NO_LOGIN_MSG);
            return rs;
        }
        rs.setStatus(0);
        rs.setData(li);
        return rs;
    }
    //用户登录
    public ResponseCode selectOne(String username, String password) {
        ResponseCode rs = new ResponseCode();
        if (username == null || username.equals("") || password == null || password.equals("")){
            rs.setStatus(10);//10代表账号或密码错误及账号不存在
            rs.setMsg("账号或密码错误");
            return rs;
        }
        User u = ud.selectOne(username, password);
        //如果用户不存在
        if (u == null){
            rs.setStatus(10);
            rs.setMsg("账号或密码错误");
            return rs;
        }
        //用户权限
        if (u.getType() != 1){
            rs.setStatus(1);//1代表普通用户没有操作权限！
            rs.setMsg("没有操作权限！");
            return rs;
        }
        rs.setStatus(0);//0代表登录成功显示管理员信息
        rs.setData(u);
        return rs;
    }
    //用户禁用
    public ResponseCode selectOne(String uid) {
        ResponseCode rs = new ResponseCode();
        if (uid == null || uid.equals("") ){
            rs.setStatus(Const.USER_PARAMETER_CODE);//101
            rs.setMsg(Const.USER_PARAMETER_MSG);//参数为空
            return rs;
        }
        Integer ui = null;
        try {
            ui = Integer.parseInt(uid);
        }catch(Exception e){
            rs.setStatus(105);
            rs.setMsg("输入非法参数");
        }
        User u = ud.selectOne(ui);
        //如果用户不存在
        if (u == null){
            rs.setStatus(Const.USER_NO_CODE);//103
            rs.setMsg(Const.USER_NO_MSG);//用户不存在
            return rs;
        }
        //用户禁用情况
        if (u.getStats() == 1){
            rs.setStatus(Const.USER_DISABLE_CODE);//105
            rs.setMsg(Const.USER_DISABLE_MSG);//用户已被禁用
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
    //用户解禁
    public ResponseCode selectOne1(String uid) {
        ResponseCode rs = new ResponseCode();
        if (uid == null || uid.equals("") ){
            rs.setStatus(Const.USER_PARAMETER_CODE);//101
            rs.setMsg(Const.USER_PARAMETER_MSG);//参数为空
            return rs;
        }
        Integer ui = null;
        try {
            ui = Integer.parseInt(uid);
        }catch(Exception e){
            rs.setStatus(105);
            rs.setMsg("输入非法参数");
        }
        User u = ud.selectOne(ui);
        //如果用户不存在
        if (u == null){
            rs.setStatus(Const.USER_NO_CODE);//103
            rs.setMsg(Const.USER_NO_MSG);//用户不存在
            return rs;
        }
        //用户禁用情况
        if (u.getStats() == 0){
            /*rs.setStatus(Const.USER_DISABLE_CODE);//105
            rs.setMsg(Const.USER_DISABLE_MSG);//用户已被禁用
            return rs;*/
        }
        int row = ud.updateByUid1(ui);
        if (row<=0){
            rs.setStatus(106);
            rs.setMsg("用户解禁失败");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(row);
        return rs;
    }
}
