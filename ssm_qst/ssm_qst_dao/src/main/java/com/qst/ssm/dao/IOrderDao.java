package com.qst.ssm.dao;

import com.qst.ssm.domain.Member;
import com.qst.ssm.domain.Orders;
import com.qst.ssm.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IOrderDao {

    @Select("Select * from ORDERS")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.qst.ssm.dao.IProductDao.findById")),
    }
)
    public List<Orders> findAll() throws Exception;



@Select("select * from orders where id = #{ordersId}")
    @Results({
        @Result(id=true,property = "id",column = "id"),
        @Result(property = "orderNum",column = "orderNum"),
        @Result(property = "orderTime",column = "orderTime"),
        @Result(property = "orderStatus",column = "orderStatus"),
        @Result(property = "peopleCount",column = "peopleCount"),
        @Result(property = "payType",column = "payType"),
        @Result(property = "orderDesc",column = "orderDesc"),
        @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.qst.ssm.dao.IProductDao.findById")),
        @Result(property = "member",column = "MEMBERID",javaType = Member.class,one = @One(select = "com.qst.ssm.dao.IMemberDao.findById")),
        @Result(property = "travellers",column = "id",javaType = java.util.List.class,many = @Many(select = "com.qst.ssm.dao.ITravellerDao.findById")),
    }
    )
    public Orders findById(String ordersId) throws Exception;
}
