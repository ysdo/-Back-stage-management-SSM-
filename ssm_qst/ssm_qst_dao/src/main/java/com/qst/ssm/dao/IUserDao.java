package com.qst.ssm.dao;

import com.qst.ssm.domain.Role;
import com.qst.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao {


    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.qst.ssm.dao.IRoleDao.findById"))
               })
     UserInfo findByUsername(String username) throws Exception;


    @Select("select * from users")
     List<UserInfo> finAll() throws Exception;


    @Insert("insert into users(email,username,password,phoneNum,status) values (#{email},#{username},#{password},#{phoneNum},#{status})" )
     void save(UserInfo userInfo) throws Exception;

    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.qst.ssm.dao.IRoleDao.findById"))

    })
     UserInfo findAllById(String id) throws Exception;
    @Select("select * from role where id not in (select roleid from USERS_ROLE where userid = #{userid})")
    public List<Role> findOtherRoles(String userid) throws Exception;

    @Insert("insert into users_role(userId,roleid) values (#{userId},#{roleId})")
    public  void addRole(@Param("userId") String userId, @Param("roleId") String roleId) throws  Exception;
}
