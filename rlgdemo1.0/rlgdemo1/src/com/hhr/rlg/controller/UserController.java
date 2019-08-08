package com.hhr.rlg.controller;

import com.hhr.rlg.common.ResponseCode;
import com.hhr.rlg.service.UserService;
import com.hhr.rlg.utils.PathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
                listDo(request,response);
                break;
            case "login":
                loginDo(request,response);
                break;
            case "prohibit":
                prohibitDo(request,response);
                break;
            case "unProhibit":
                unProhibitDo(request,response);
                break;
        }
        //返回响应数据
        //response.getWriter().write(rs.toString());
    }
    //获取所有用户列表的请求
    private void listDo(HttpServletRequest request,HttpServletResponse response){
        //ResponseCode rs = new ResponseCode();

        /*HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null){
            rs.setStatus(3);
            rs.setMsg("请登录后在操作");
        }*/
        //获取参数
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");
        //调用业务层处理业务
        ResponseCode rs = us.selectAll(pageSize,pageNum);
        request.setAttribute("uli",rs);
        try {
            request.getRequestDispatcher("/WEB-INF/listhome.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //用户登录的请求
    private void loginDo(HttpServletRequest request,HttpServletResponse response)  {
        //获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //调用业务层处理业务
        ResponseCode rs = us.selectOne(username, password);
        //获取session对象
        HttpSession session = request.getSession();
        session.setAttribute("user",rs.getData());
//        return rs;

        try {
                       //response.sendRedirect("/home.jsp");
            request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //用户禁用*
    private void prohibitDo(HttpServletRequest request,HttpServletResponse response){
        //获取参数
        String uid = request.getParameter("uid");
        //调用业务层处理业务
        ResponseCode rs = us.selectOne(uid);
        try {
            request.getRequestDispatcher("/manage/user/list.do").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //用户解禁*
    private void  unProhibitDo(HttpServletRequest request,HttpServletResponse response){
        //获取参数
        String uid = request.getParameter("uid");
        //调用业务层处理业务
        ResponseCode rs = us.selectOne(uid);
        try {
            request.getRequestDispatcher("/manage/user/list.do").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
