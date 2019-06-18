package com.qst.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.qst.ssm.dao.IOrderDao;
import com.qst.ssm.domain.Orders;
import com.qst.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrdersServiceimpl implements IOrdersService {

    @Autowired
    private IOrderDao orderDao;
    @Override
    public List<Orders> findAll(int page,int size) throws Exception {

        //pageNum代表页码值，pageSize代表每页条数
        PageHelper.startPage(page,size);
        return orderDao.findAll();
    }

    @Override
    public Orders findById(String ordersId)throws Exception {

        return orderDao.findById(ordersId) ;
    }
}
