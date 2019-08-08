package com.hhr.rlg.controller;

import com.hhr.rlg.common.ResponseCode;
import com.hhr.rlg.service.ProductService;
import com.hhr.rlg.utils.PathUtil;

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
                listDo(request,response);
                break;
            case "search":
                rs = searchDo(request);
                break;
            case "detail":
                rs = detailDo(request);
                break;
            case "set_sale_status":
                set_sale_statusDo(request,response);
                break;
            case "save":
                rs = saveDo(request);
                break;
        }
        //response.getWriter().write(rs.toString());
    }
    //产品list
    private void listDo(HttpServletRequest request,HttpServletResponse response){
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        ResponseCode rs = ps.selectAll(pageNum,pageSize);
        request.setAttribute("product",rs);
        try {
            request.getRequestDispatcher("/WEB-INF/productlist.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return rs;
    }
    //产品搜索
    private ResponseCode searchDo(HttpServletRequest request){
        String productName = request.getParameter("productName");
        String productId = request.getParameter("productId");
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");
        if (productName == null){
            ResponseCode rs = ps.selectOne(productId,pageNum,pageSize);
            return rs;
        }else {
            ResponseCode rs = ps.selectOne1(productName,pageNum,pageSize);
            return rs;
        }
    }
    //产品详情
    private ResponseCode detailDo(HttpServletRequest request){
        String productId = request.getParameter("productId");
        ResponseCode rs = ps.selectOne2(productId);
        return rs;
    }
    //产品上下架*
    private void set_sale_statusDo(HttpServletRequest request,HttpServletResponse response){
        String productId = request.getParameter("productId");
        String status = request.getParameter("status");//1代表上架，0代表下架
        ResponseCode rs = ps.updateOne(productId, status);
        try {
            request.getRequestDispatcher("/manage/product/list.do").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //产品新增OR更新
    private ResponseCode saveDo(HttpServletRequest request){
        String categoryId = request.getParameter("categoryId");
        String name = request.getParameter("name");
        String subtitle = request.getParameter("subtitle");
        String mainImage = request.getParameter("mainImage");
        String price = request.getParameter("price");
        String status = request.getParameter("status");
        String id = request.getParameter("id");
        ResponseCode rs = ps.updateOne1(categoryId, name, subtitle, mainImage, price, status, id);
        return rs;
    }
}
