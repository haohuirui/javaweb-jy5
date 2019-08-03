package com.hhr.rlg.controller;

import com.hhr.rlg.common.ResponseCode;
import com.hhr.rlg.service.ProductService;
import com.hhr.rlg.utils.PathUtil;
import com.hhr.rlg.utils.RlgUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manage/product/*")
public class ProductController extends HttpServlet {
    ProductService ps = new ProductService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        String p = PathUtil.getPath(path);
        ResponseCode rs = null;
        switch (p){
            case "list":
                rs = listDo(request);
                break;
            case "search":
                rs = searchDo(request);
                break;
        }
        response.getWriter().write(rs.toString());
    }
    //产品list
    private ResponseCode listDo(HttpServletRequest request){
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        ResponseCode rs = ps.selectAll(pageNum, pageSize);
        return rs;
    }
    //产品搜索
    private ResponseCode searchDo(HttpServletRequest request){
        String productName = request.getParameter("productName");
        String productId = request.getParameter("productId");
        if (productName == null){
            ResponseCode rs = ps.selectOne(productId);
            return rs;
        }else {
            ResponseCode rs = ps.selectOne1(productName);
            return rs;
        }
    }
}
