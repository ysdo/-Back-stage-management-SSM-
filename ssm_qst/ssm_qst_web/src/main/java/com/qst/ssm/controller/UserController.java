package com.qst.ssm.controller;

import com.qst.ssm.domain.Role;
import com.qst.ssm.domain.UserInfo;
import com.qst.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;


@RequestMapping("/findAll.do")
@RolesAllowed("admin")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv  = new ModelAndView();

    List<UserInfo> all = userService.findAll();
    mv.addObject("userList",all);
    mv.setViewName("user-list");

        return mv;
    }

    @RequestMapping("/save.do")
    public String saveUser(UserInfo userInfo) throws Exception {

        userService.save(userInfo);

        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findAllById(String id) throws Exception {
    ModelAndView mv= new ModelAndView();
        UserInfo allById = userService.findAllById(id);
        mv.addObject("user",allById);
        System.out.println(allById.getId());
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name="id",required = true) String userid) throws  Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo allById = userService.findAllById(userid);

        //查询当前用户没有的角色（可以添加的角色）
        List<Role> otherRoles = userService.findOtherRoles(userid);
        mv.addObject("user",allById);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name="userId",required = true) String userId, @RequestParam(name="ids",required = true) String[] roleIds) throws  Exception{

    userService.addRoles(userId,roleIds);

    return "redirect:findAll.do";

    }



}
