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
        List<Product> p = pd.selectAll(pageNum, pageSize);
        if (p == null){
            rs.setStatus(Const.COMMODITY_NO_LOGIN_CODE);
            rs.setMsg(Const.COMMODITY_NO_LOGIN_MSG);
            return rs;
        }
        rs.setStatus(0);
        rs.setData(p);
        return rs;
    }

    public ResponseCode selectOne(String productId) {
        if (productId == null){
            rs.setStatus(Const.COMMODITY_NO_LOGIN_CODE);
            rs.setMsg(Const.COMMODITY_NO_LOGIN_MSG);
            return rs;
        }
        Integer prid =  Integer.parseInt(productId);
        Product p = pd.selectOne(prid);
        if (p == null){
            rs.setStatus(2);
            rs.setMsg("该商品不存在");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(p);
        return rs;
    }
    public ResponseCode selectOne1(String productName){
        if (productName == null){
            rs.setStatus(Const.COMMODITY_NO_LOGIN_CODE);
            rs.setMsg(Const.COMMODITY_NO_LOGIN_MSG);
            return rs;
        }
        Product p = pd.selectOne1(productName);
        if (p == null){
            rs.setStatus(2);
            rs.setMsg("该商品不存在");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(p);
        return rs;
    }
}
