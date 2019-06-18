package com.qst.ssm.service.impl;

import com.qst.ssm.dao.IRoleDao;
import com.qst.ssm.dao.IUserDao;
import com.qst.ssm.domain.Role;
import com.qst.ssm.domain.UserInfo;
import com.qst.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceimpl implements IUserService {

    @Autowired
    private IUserDao userDao;



    @Autowired
    private  BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo=null;
        try {
             userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //将自己的用户对象封装到UserDetails
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }


    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){

        List<SimpleGrantedAuthority> list = new ArrayList<>();

        for(Role role:roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }

        return list;
    }

    @Override
    public List<UserInfo> findAll() throws Exception {

        return userDao.finAll();
    }

    @Override
    public void save(UserInfo userInfo) throws Exception {
      userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));

         userDao.save(userInfo);
    }

    @Override
    public UserInfo findAllById(String id) throws Exception {

       return userDao.findAllById(id);
    }

    @Override
    public List<Role> findOtherRoles(String userId) throws Exception {

        return userDao.findOtherRoles(userId) ;
    }

    @Override
    public void addRoles(String userId, String[] roleIds) throws Exception {

        for(String roleId:roleIds){
            userDao.addRole(userId,roleId);
        }
    }
}
