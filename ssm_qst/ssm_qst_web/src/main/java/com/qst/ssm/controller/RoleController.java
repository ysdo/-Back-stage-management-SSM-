package com.qst.ssm.controller;

import com.qst.ssm.domain.Permission;
import com.qst.ssm.domain.Role;
import com.qst.ssm.service.IRoleService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.ognl.DynamicSubscript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.apache.ibatis.ognl.DynamicSubscript.all;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @RequestMapping("/findAll.do")
    public ModelAndView findall() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> all = roleService.findAll();
        mv.addObject("roleList", all);
        mv.setViewName("role-list");
        return mv;
    }
@RequestMapping("/save.do")
    public String Save(Role role) throws Exception{
    roleService.save(role);

    return "redirect:findAll.do";

    }
    @RequestMapping("/findUserByIdAndAllPermission.do")
    public ModelAndView findUserByIdAndAllPermission(@RequestParam(name="id",required = true) String roleid) throws  Exception{
        ModelAndView mv = new ModelAndView();
        Role allById = roleService.findAllById(roleid);
        List<Permission> findother=roleService.findOtherPermissions(roleid);
        mv.addObject("role",allById);
        mv.addObject("permissionList",findother);
        mv.setViewName("role-permission-add");
        return mv;
    }
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name="roleId",required = true) String roleId,@RequestParam(name="ids",required = true) String[] permissionIds) throws Exception{
        roleService.addPermission(roleId,permissionIds);
        return "redirect:findAll.do";
    }
}
