package com.qst.ssm.service;

import com.qst.ssm.domain.Permission;
import com.qst.ssm.domain.Role;

import java.util.List;

public interface IRoleService {

     List<Role> findAll() throws Exception;

     void save(Role role) throws  Exception;

    Role findAllById(String roleid) throws Exception;

    List<Permission> findOtherPermissions(String roleid) throws Exception;


    void addPermission(String roleId, String[] permissionIds) throws Exception;
}
