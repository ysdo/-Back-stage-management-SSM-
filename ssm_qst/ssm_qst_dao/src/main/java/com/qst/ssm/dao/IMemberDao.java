package com.qst.ssm.dao;

import com.qst.ssm.domain.Member;
import com.qst.ssm.domain.Product;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberDao {
    @Select("select * from Member where id = #{id}")
    public Member findById(String id) throws Exception;
}
