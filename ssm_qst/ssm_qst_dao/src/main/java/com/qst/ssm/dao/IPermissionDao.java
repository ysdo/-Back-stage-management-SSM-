package com.qst.ssm.dao;

import com.qst.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IPermissionDao {


    @Select("select * from permission where id in (select permissionid from role_permission where roleid = #{id})")
     List<Permission> findAllById(String id);

    @Select("select * from permission")
      List<Permission> findAll()throws Exception;
    @Insert("insert into permission(permissionName,url) values (#{permissionName},#{url})")
      void save(Permission permission) throws  Exception;
}
