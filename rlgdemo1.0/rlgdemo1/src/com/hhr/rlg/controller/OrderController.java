package com.hhr.rlg.controller;

import com.hhr.rlg.common.ResponseCode;
import com.hhr.rlg.service.OrderService;
import com.hhr.rlg.utils.PathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manage/order/*")
public class OrderController extends HttpServlet {
    ResponseCode rs = new ResponseCode();
    OrderService os = new OrderService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        String p = PathUtil.getPath(path);
        switch (p){
            case "list":
                rs = listDo(request);
                break;
            case "search":
                rs = searchDo(request,response);
                break;
            case "detail":
                rs = detailDo(request,response);
                break;
            case "send_goods":
                rs = send_goodsDo(request,response);
                break;
        }
    }
    //订单list
    private ResponseCode listDo(HttpServletRequest request){
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        rs = os.selectAll(pageNum,pageSize);
        return rs;
    }
    //按订单号查询
    private ResponseCode searchDo(HttpServletRequest request,HttpServletResponse response){
        String orderNo = request.getParameter("orderNo");
        rs = os.selectAll1(orderNo);
        return rs;
    }
    //订单详情
    private ResponseCode detailDo(HttpServletRequest request,HttpServletResponse response){
        String orderNo = request.getParameter("orderNo");
        rs = os.selectAll2(orderNo);
        return rs;
    }
    // 订单发货
    private ResponseCode send_goodsDo(HttpServletRequest request,HttpServletResponse response){
        String orderNo = request.getParameter("orderNo");
        rs = os.selectAll3(orderNo);
        return rs;
    }
}
