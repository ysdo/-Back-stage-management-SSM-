package com.qst.ssm.dao;

import com.qst.ssm.domain.Permission;
import com.qst.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IRoleDao {

    @Select("Select * from role where id in (select roleid from users_role where userid = #{userId})")
    @Results({
            @Result(id=true,property = "id" ,column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class, many =@Many(select="com.qst.ssm.dao.IPermissionDao.findAllById")),
    })
    public List<Role> findById(String userId) throws Exception;
    @Select("select * from role")
    public List<Role> findAll() throws  Exception;

    @Insert("insert into role(roleName,roleDesc) values (#{roleName},#{roleDesc})")
    public void save(Role role);

    @Select("select * from role where id = #{roleid}")
    Role findAllById(String roleid) throws Exception;
    @Select("select * from Permission where id not in (select permissionid from ROLE_Permission where roleid = #{roleid})")
    List<Permission> findOtherPermissions(String roleid) throws  Exception;

    @Insert("insert into ROLE_Permission(roleId,permissionId) values (#{roleId},#{permissionId})")
    void addPermissiona(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
