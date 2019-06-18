package com.qst.ssm.service;

import com.qst.ssm.domain.SysLog;

import java.util.List;

public interface ISysLogService {

    void Save(SysLog sysLog) throws Exception;

    List<SysLog> findAll() throws Exception;
}
