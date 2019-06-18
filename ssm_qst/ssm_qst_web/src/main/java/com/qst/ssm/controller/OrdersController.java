package com.qst.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.qst.ssm.domain.Orders;
import com.qst.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    //required = true（要求必须携带参数）,defaultValue = "1" （带了参数会接收，如果没带参数则设置一个默认值为1）
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1")Integer page,@RequestParam(name="size",required=true,defaultValue = "4")Integer size) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Orders> all = ordersService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(all);

        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-list");

        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name="id",required = true) String ordersId) throws Exception{
        ModelAndView mv = new ModelAndView();
        Orders order = ordersService.findById(ordersId);
        System.out.println(order);
        mv.addObject("orders",order);
        mv.setViewName("orders-show");
        return mv;
    }


}
