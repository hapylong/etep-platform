/*
 * @(#) WfMonitorBiz.java  1.0  August 29, 2016
 *
 * Copyright 2016 by 北京爱钱帮财富科技有限公司
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * IQB("Confidential Information").  You shall not disclose such 
 * Confidential Information and shall use it only in accordance with
 * the terms of the license agreement
 * you entered into with IQB.
 */
package com.iqb.etep.workflow.monitor.biz;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.workflow.base.WfBaseBiz;
import com.iqb.etep.workflow.monitor.bean.IqbWfMyProcBackBean;
import com.iqb.etep.workflow.monitor.bean.IqbWfProcBackBean;
import com.iqb.etep.workflow.monitor.dao.IqbWfProcMonitorBeanMapper;

/**
 * Description: 工作流监控服务业务实现类
 * 
 * @author mayongming
 * @version 1.0
 * 
 *          <pre>
 * Modification History: 
 * Date             Author      Version         Description 
 * ------------------------------------------------------------------
 * 2016.08.29    mayongming       1.0        1.0 Version
 * </pre>
 */
@Component
public class WfMonitorBiz extends WfBaseBiz {
    private static Logger logger = LoggerFactory.getLogger(WfMonitorBiz.class);
    @Autowired
    IqbWfProcMonitorBeanMapper iqbWfMyProcBeanMapper;
    
    /**
     * 流程汇总查询
     * @param objs
     * @return
     */
    public List<IqbWfMyProcBackBean> geyProcessSummary(JSONObject objs) throws IqbException {
        logger.info("流程汇总查询--开始查询流程汇总信息...");

        try {
            setDb(0, super.SLAVE); 
            PageHelper.startPage(getPagePara(objs));
            return iqbWfMyProcBeanMapper.selectProcessSummary(objs);
        } finally {
            logger.info("流程汇总查询--查询流程汇总信息完成.");
        }
    }
    
    /**
     * 查询用户已办流程任务列表
     * @param objs
     * @return
     */
    public List<IqbWfMyProcBackBean> getUserDoneTasks(JSONObject objs) throws IqbException {
        logger.info("用户已办任务查询--开始查询用户已办任务...");

        try {
            setDb(0, super.SLAVE); 
            PageHelper.startPage(getPagePara(objs));
            return iqbWfMyProcBeanMapper.selectMyProcTasks(objs);
        } finally {
            logger.info("用户已办任务查询--查询用户已办任务完成.");
        }
    }
    
    /**
     * 查询用户待办流程任务列表
     * @param objs
     * @return
     */
    public List<IqbWfProcBackBean> getUserToDoTasks(JSONObject objs) throws IqbException {
        logger.info("用户待办任务查询--开始查询用户待办任务...");

        try {
            setDb(0, super.SLAVE);
            PageHelper.startPage(getPagePara(objs));
            return iqbWfMyProcBeanMapper.getUserToDoTasks(objs);
        } finally {
            logger.info("用户待办任务查询--查询用户待办任务完成.");
        }
    }
    
    /**
     * 流程监控任务查询
     * @param objs
     * @return
     */
    public List<IqbWfMyProcBackBean> getActiveProcessList(JSONObject objs) throws IqbException {
        logger.info("流程监控任务查询--开始查询流程监控任务列表...");

        try {
            setDb(0, super.SLAVE);
            PageHelper.startPage(getPagePara(objs));
            return iqbWfMyProcBeanMapper.selectActiveProcessList(objs);
        } finally {
            logger.info("流程监控任务查询--查询流程监控任务列表完成.");
        }
    }
    
    /**
     * 流程监控任务查询
     * @param objs
     * @return
     */
    public List<IqbWfMyProcBackBean> getOrgProcessList(JSONObject objs) throws IqbException {
        logger.info("流程异常处理--开始按照机构查询未结束的流程任务列表...");

        try {
            setDb(0, super.SLAVE);
            PageHelper.startPage(getPagePara(objs));
            return iqbWfMyProcBeanMapper.selectOrgProcessList(objs);
        } finally {
            logger.info("流程异常处理--按照机构查询未结束的流程任务列表完成.");
        }
    }
    
    /**
     * 流程委托查询
     * @param objs
     * @return
     */
    public List<IqbWfMyProcBackBean> getProcessDelegateList(JSONObject objs) throws IqbException {
        logger.info("流程委托查询--开始查询流程委托记录...");

        try {
            setDb(0, super.SLAVE);
            PageHelper.startPage(getPagePara(objs));
            return iqbWfMyProcBeanMapper.selectProcessDelegate(objs);
        } finally {
            logger.info("流程委托查询--查询流程委托记录完成.");
        }
    }
    
    /**
     * 查询我的流程委托记录
     * @param objs
     * @return
     */
    public List<IqbWfMyProcBackBean> getMyProcDelegateList(JSONObject objs) throws IqbException {
        logger.info("流程委托查询--开始查询我的流程委托记录...");

        try {
            setDb(0, super.SLAVE);
            PageHelper.startPage(getPagePara(objs));
            return iqbWfMyProcBeanMapper.selectMyProcessDelegate(objs);
        } finally {
            logger.info("流程委托查询--查询我的流程委托记录完成.");
        }
    }
    /**
     * 导出流程汇总查询
     * @param objs
     * @return
     */
    public List<IqbWfMyProcBackBean> getProcessSummary(JSONObject objs) throws IqbException {
        logger.info("流程汇总查询--开始查询流程汇总信息...");
        try {
            return iqbWfMyProcBeanMapper.selectProcessSummary(objs);
        } finally {
            logger.info("流程汇总查询--查询流程汇总信息完成.");
        }
    }
}
