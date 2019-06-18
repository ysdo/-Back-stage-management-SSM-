package com.qst.ssm.dao;


import com.qst.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ITravellerDao {
    @Select("select * from Traveller where id in(select travellerid from order_traveller where orderid = #{id})")
    public List<Traveller> findById(String id) throws Exception;
}
