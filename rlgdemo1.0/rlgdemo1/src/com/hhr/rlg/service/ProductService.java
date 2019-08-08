package com.hhr.rlg.service;

import com.hhr.rlg.common.Const;
import com.hhr.rlg.common.ResponseCode;
import com.hhr.rlg.dao.ProductDao;
import com.hhr.rlg.pojo.Product;

import java.util.List;

public class ProductService {
    ProductDao pd = new ProductDao();
    ResponseCode rs = new ResponseCode();
    //产品list
    public ResponseCode selectAll(String pageNum, String pageSize) {
        if (pageSize == null || pageSize.equals("")){
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")){
            pageNum = "1";
        }
        Integer i = Integer.parseInt(pageNum);
        Integer i1 = Integer.parseInt(pageSize);
        List<Product> p = pd.selectAll(i,i1);
        if (p == null){
            rs.setStatus(Const.COMMODITY_NO_LOGIN_CODE);
            rs.setMsg(Const.COMMODITY_NO_LOGIN_MSG);
            return rs;
        }
        rs.setStatus(0);
        rs.setData(p);
        return rs;
    }
    //产品搜索productId
    public ResponseCode selectOne(String productId,String pageNum,String pageSize) {
        if (pageSize == null || pageSize.equals("")){
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")){
            pageNum = "1";
        }
        if (productId == null || productId.equals("")){
            rs.setStatus(3);//3代表该参数不能为空
            rs.setMsg("该参数不能为空，请重新输入");
            return rs;
        }
        Integer prid =  Integer.parseInt(productId);
        Product p = pd.selectOne(prid,pageNum,pageSize);
        if (p == null){
            rs.setStatus(2);
            rs.setMsg("该商品不存在");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(p);
        return rs;
    }
    //产品搜索productName
    public ResponseCode selectOne1(String productName,String pageNum,String pageSize){
        if (pageSize == null || pageSize.equals("")){
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")){
            pageNum = "1";
        }
        if (productName == null || productName.equals("")){
            rs.setStatus(3);//3代表该参数不能为空
            rs.setMsg("该参数不能为空，请重新输入");
            return rs;
        }
        Product p = pd.selectOne1(productName,pageNum,pageSize);
        if (p == null){
            rs.setStatus(2);
            rs.setMsg("该商品不存在");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(p);
        return rs;
    }
    //产品详情
    public ResponseCode selectOne2(String productId) {
        if (productId == null || productId.equals("")){
            rs.setStatus(3);//3代表该参数不能为空
            rs.setMsg("该参数不能为空，请重新输入");
            return rs;
        }
        int i = 0;
        try{
            i = Integer.parseInt(productId);
        }catch(Exception e){
            rs.setStatus(4);//4代表非法输入
            rs.setMsg("输入非法参数");
        }
        Product p = pd.selectOne2(i);
        if (p == null){
            rs.setStatus(1);
            rs.setMsg("没有权限");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(p);
        return rs;

    }
    //产品上下架
    public ResponseCode updateOne(String productId, String status) {
        if (productId == null || productId.equals("") || status == null || status.equals("")){
            rs.setStatus(3);//3代表该参数不能为空
            rs.setMsg("该参数不能为空，请重新输入");
            return rs;
        }
        Integer i = null;
        Integer s = null;
        try {
            i = Integer.parseInt(productId);
            s = Integer.parseInt(status);
        }catch(Exception e){
            rs.setStatus(4);//4代表非法输入
            rs.setMsg("输入非法参数");
        }
        int i1 = pd.updateOne(i,s);
        if (i1 == 0){
            rs.setStatus(1);//1代表修改状态失败
            rs.setMsg("修改产品状态失败");
            return rs;
        }
        rs.setStatus(0);//0代表修改状态成功
        rs.setMsg("修改产品状态成功");
        return rs;
    }
    //产品新增OR更新
    public ResponseCode updateOne1(String categoryId, String name, String subtitle,
                                   String mainImage, String price, String status, String id) {
        if (categoryId == null || categoryId.equals("") || status == null || status.equals("") ||name == null ||
                name.equals("") || subtitle == null || subtitle.equals("") || mainImage == null ||
                mainImage.equals("") || price == null || price.equals("") || id == null || id.equals("")){
            rs.setStatus(3);//3代表该参数不能为空
            rs.setMsg("该参数不能为空，请重新输入");
            return rs;
        }
        int i = 0;
        int i1 = 0;
        int i2 = 0;
        double v = 0;
        try {
            i = Integer.parseInt(categoryId);
            i1 = Integer.parseInt(status);
            i2 = Integer.parseInt(id);
            v = Double.parseDouble(price);
        }catch(Exception e){
            rs.setStatus(4);//4代表非法输入
            rs.setMsg("输入非法参数");
        }
        boolean b = pd.selectOne3(i2);
        if (b == true) {
            int i3 = pd.updateOne1(i, name, subtitle, mainImage, v, i1, i2);
            if (i3 == 0) {
                rs.setStatus(1);
                rs.setMsg("更新产品失败");
                return rs;
            }
            rs.setStatus(0);
            rs.setMsg("更新产品成功");
            return rs;
        }else{
            int i4 = pd.insertOne(i, name, subtitle, mainImage, v, i1);
            if (i4 == 0){
                rs.setStatus(1);
                rs.setMsg("更新产品失败");
                return rs;
            }
            rs.setStatus(0);
            rs.setMsg("新增产品成功");
            return rs;
        }

    }
}
