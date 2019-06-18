package com.qst.ssm.service;

import com.qst.ssm.domain.Role;
import com.qst.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {


     List<UserInfo> findAll() throws Exception;
     void save(UserInfo userInfo) throws Exception;

      UserInfo findAllById(String id) throws Exception;

    List<Role> findOtherRoles(String userId)  throws Exception;


    void addRoles(String userId, String[] roleIds) throws Exception;
}
