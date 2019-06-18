package com.qst.ssm.controller;


import com.qst.ssm.domain.Product;
import com.qst.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;
    //查询全部产品
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
       ModelAndView mv = new ModelAndView();
        List<Product> all = productService.findAll();
        for(Product pro:all){
            System.out.println(pro);
        }
        mv.addObject("productList",all);
        mv.setViewName("product-list");

        return mv;
    }

//产品添加
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        productService.saveProduct(product);

        return "redirect:findAll.do";
    }


}
