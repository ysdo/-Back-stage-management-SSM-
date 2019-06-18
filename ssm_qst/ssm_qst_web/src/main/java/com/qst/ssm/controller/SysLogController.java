package com.qst.ssm.controller;


import com.qst.ssm.domain.SysLog;
import com.qst.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    ISysLogService sysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {

        ModelAndView mv = new ModelAndView();
        List<SysLog> all = sysLogService.findAll();
        mv.addObject("sysLogs",all);
        mv.setViewName("syslog-list");
        return mv;

    }
}
