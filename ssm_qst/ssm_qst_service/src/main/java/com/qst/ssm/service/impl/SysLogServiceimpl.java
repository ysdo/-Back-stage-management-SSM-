package com.qst.ssm.service.impl;

import com.qst.ssm.dao.ISysLogDao;
import com.qst.ssm.domain.SysLog;
import com.qst.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class SysLogServiceimpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;
    @Override
    public void Save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() throws Exception {
        return  sysLogDao.findAll();
    }
}
