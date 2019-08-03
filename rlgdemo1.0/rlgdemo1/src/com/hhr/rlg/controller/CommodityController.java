package com.hhr.rlg.controller;

import com.hhr.rlg.common.ResponseCode;
import com.hhr.rlg.service.CommodityService;
import com.hhr.rlg.utils.PathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manage/category/*")
public class CommodityController extends HttpServlet {
    CommodityService cs = new CommodityService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取商品路径
        String p = request.getPathInfo();
        String path = PathUtil.getPath(p);
        //统一返回类型
        ResponseCode rs = null;
        switch (path){
            case "get_category":
                rs = get_categoryDo(request);
                break;
            case "add_category":
                rs = add_categoryDo(request);
                break;
            case "set_category_name":
                rs = set_category_nameDo(request);
                break;
        }
        response.getWriter().write(rs.toString());

    }
    //获取品类子节点所有信息
    private ResponseCode get_categoryDo(HttpServletRequest request){
        String parentid = request.getParameter("categoryId");
        ResponseCode rs = cs.selectAll(parentid);
        return rs;
    }
    //增加商品类别
    private ResponseCode add_categoryDo(HttpServletRequest request){
        String parentId = request.getParameter("parentId");
        String categoryName = request.getParameter("categoryName");
        ResponseCode rs = cs.addOne(parentId,categoryName);
        return rs;
    }
    //修改品类名称
    private ResponseCode set_category_nameDo(HttpServletRequest request){
        String categoryId = request.getParameter("categoryId");
        String categoryName = request.getParameter("categoryName");
        ResponseCode rs = cs.updateOne(categoryId, categoryName);
        return rs;
    }
}
