package com.hhr.rlg.controller;

import com.hhr.rlg.common.ResponseCode;
import com.hhr.rlg.pojo.User;
import com.hhr.rlg.service.UserService;
import com.hhr.rlg.utils.PathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/manage/user/*")
public class UserController extends HttpServlet {
    private UserService us = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //怎样获取请求路径信息
        String path = request.getPathInfo();
        String p = PathUtil.getPath(path);
        //创建统一返回对象
        ResponseCode rs = null;
        switch (p){
            case "list":
                rs = listDo(request);
                break;
            case "login":
                rs = loginDo(request);
                break;
            case "prohibit":
                rs = prohibitDo(request);
                break;
        }
        //返回响应数据
        response.getWriter().write(rs.toString());
    }
    //获取所有用户列表的请求
    private ResponseCode listDo(HttpServletRequest request){
        ResponseCode rs = new ResponseCode();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null){
            rs.setStatus(3);
            rs.setMsg("请登录后在操作");
            return rs;
        }
        //获取参数
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");
        //调用业务层处理业务
        rs = us.selectAll(pageSize,pageNum);
        return rs;
    }
    //用户登录的请求
    private ResponseCode loginDo(HttpServletRequest request){
        //获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //调用业务层处理业务
        ResponseCode rs = us.selectOne(username, password);
        //获取session对象
        HttpSession session = request.getSession();
        session.setAttribute("user",rs.getData());
        return rs;
    }
    //用户禁用
    private ResponseCode prohibitDo(HttpServletRequest request){
        //获取参数
        String uid = request.getParameter("uid");
        //调用业务层处理业务
        ResponseCode rs = us.selectOne(uid);
        return rs;
    }

}
