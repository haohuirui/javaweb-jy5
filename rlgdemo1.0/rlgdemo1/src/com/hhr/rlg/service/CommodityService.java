package com.hhr.rlg.service;

import com.hhr.rlg.common.Const;
import com.hhr.rlg.common.ResponseCode;
import com.hhr.rlg.dao.CommodityDao;
import com.hhr.rlg.pojo.Commodity;

import java.util.List;

public class CommodityService {
    CommodityDao cd = new CommodityDao();
    //子节点商品信息
    public ResponseCode selectAll(String parentid) {
        ResponseCode rs = new ResponseCode();
        /*if (parentid == null && parentid.equals("")){
            rs.setStatus(Const.COMMODITY_NO_LOGIN_CODE);//10代表用户未登录
            rs.setMsg(Const.COMMODITY_NO_LOGIN_MSG);//"用户未登录,请登录"
            return rs;
        }*/
        List<Commodity> com = cd.selectAll(parentid);
        if (com == null){
            rs.setStatus(Const.COMMODITY_NO_CATE_CODE);//1代表未找到该品类
            rs.setMsg(Const.COMMODITY_NO_CATE_MSG);//"未找到该品类"
        }
        rs.setStatus(0);
        rs.setData(com);
        return rs;
    }
    //增加节点
    public ResponseCode addOne(String parentId,String categoryName) {
        ResponseCode rs = new ResponseCode();
        /*if (parentId == null || parentId.equals("") || categoryName == null || categoryName.equals("")){
            rs.setStatus(Const.COMMODITY_NO_LOGIN_CODE);//10代表用户未登录
            rs.setMsg(Const.COMMODITY_NO_LOGIN_MSG);//"用户未登录,请登录"
            return rs;
        }*/
        int i = cd.addOne(parentId, categoryName);
        if (i == 0){
            rs.setStatus(1);
            rs.setMsg("添加品类失败");
        }
        rs.setStatus(0);
        rs.setMsg("添加品类成功");
        return rs;
    }
    //修改品类名称
    public ResponseCode updateOne(String categoryId,String categoryName) {
        ResponseCode rs = new ResponseCode();
        /*if (categoryId == null || categoryId.equals("") || categoryName == null || categoryName.equals("")){
            rs.setStatus(Const.COMMODITY_NO_LOGIN_CODE);//10代表用户未登录
            rs.setMsg(Const.COMMODITY_NO_LOGIN_MSG);//"用户未登录,请登录"
            return rs;
        }*/
        int i = cd.updateOne(categoryId, categoryName);
        if (i == 0){
            rs.setStatus(1);
            rs.setMsg("更新品类名字失败");
        }
        rs.setStatus(0);
        rs.setMsg("更新品类名字成功");
        return rs;
    }
}
