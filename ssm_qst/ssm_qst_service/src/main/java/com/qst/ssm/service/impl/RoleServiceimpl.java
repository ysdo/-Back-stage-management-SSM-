package com.qst.ssm.service.impl;

import com.qst.ssm.dao.IRoleDao;
import com.qst.ssm.domain.Permission;
import com.qst.ssm.domain.Role;
import com.qst.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceimpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;
    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll() ;
    }

    @Override
    public void save(Role role )throws Exception {
        roleDao.save(role);
    }

    @Override
    public Role findAllById(String roleid) throws Exception {
        return roleDao.findAllById(roleid);
    }

    @Override
    public List<Permission> findOtherPermissions(String roleid) throws Exception {

        return roleDao.findOtherPermissions(roleid);
    }

    @Override
    public void addPermission(String roleId, String[] permissionIds) throws Exception {
        for(String permissionId:permissionIds ){
            roleDao.addPermissiona(roleId,permissionId);
        }

    }
}
