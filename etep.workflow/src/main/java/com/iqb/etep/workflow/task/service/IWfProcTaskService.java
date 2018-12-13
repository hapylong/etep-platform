/*
 * @(#) IWfProcTaskService.java  1.0  August 22, 2016
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
package com.iqb.etep.workflow.task.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.workflow.task.bean.IqbWfProcTaskBean;
import com.iqb.etep.workflow.task.bean.IqbWfProcTaskHistoryBean;

/**
 * Description: 工作流流程任务相关处理接口类
 * 
 * @author mayongming
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date             Author      Version         Description 
 * ------------------------------------------------------------------
 * 2016.08.22    mayongming       1.0        1.0 Version
 * </pre>
 */
public interface IWfProcTaskService {

    /**
     * 根据流程定义编码启动对应的工作流
     * 
     * @param objs  接口参数Json对象
     * @return  流程实例ID
     * 
     * @throws IqbException
     */
    public String startProcessInstance(JSONObject objs) throws IqbException;
    
    
    /**
     * 根据流程定义编码启动工作流并提交第一个流程任务，通过用户登录信息验证启动流程合法性
     * 
     * @param objs  接口参数Json对象
     * @return  流程实例ID
     * 
     * @throws IqbException
     */
    public String startAndCompleteProcessInstance(JSONObject objs) throws IqbException;
    
    /**
     * 通过流程任务ID签收当前流程任务
     * 
     * @param objs  接口参数Json对象
     * @return 流程任务信息
     * 
     * @throws IqbException
     */
    public IqbWfProcTaskBean claimProcessInstance(JSONObject objs) throws IqbException;

    /**
     * 通过流程任务ID取消签收当前流程任务
     * 
     * @param objs 接口参数Json对象
     * @return 流程任务信息
     * @throws IqbException
     */
    public IqbWfProcTaskBean unclaimProcessInstance(JSONObject objs) throws IqbException;
    
    /**
     * 通过流程任务ID审批当前流程任务
     * 
     * @param objs  接口参数Json对象，包括：
     * 
     * @throws IqbException
     * @throws Exception
     */
    public void completeProcessInstanceByTaskId(JSONObject objs) throws IqbException;
    
    /**
     * 通过流程任务代码审批当前流程任务
     * 
     * @param objs  接口参数Json对象，包括：
     * 
     * @throws IqbException
     * @throws Exception
     */
    public void completeProcessInstanceByTaskCode(JSONObject objs) throws IqbException;
    
    /**
     * 通过流程任务ID删除当前流程任务，删除的流程可以在我的流程中查询到
     * 
     * @param objs  接口参数Json对象，包括：
     * 
     * @throws IqbException
     * @throws Exception
     */
    public void deleteProcessInstance(JSONObject objs) throws IqbException;
    
    /**
     * 通过流程实例取消当前流程任务，取消的流程可在我的流程中查询到
     * 
     * @param objs  接口参数Json对象，包括：
     * 
     * @throws IqbException
     * @throws Exception
     */
    public void cancelProcessInstance(JSONObject objs) throws IqbException;
    
    /**
     * 通过流程实例撤回当前流程任务，撤回的流程在待办任务中可进行再次处理
     * 
     * @param objs  接口参数Json对象，包括：
     * 
     * @throws IqbException
     * @throws Exception
     */
    public void retrieveProcessInstance(JSONObject objs) throws IqbException;
    
    /**
     * 通过流程实例终止当前流程任务，终止的流程可在我的流程中查询到
     * 
     * @param objs  接口参数Json对象，包括：
     * 
     * @throws IqbException
     * @throws Exception
     */
    public void endProcessInstance(JSONObject objs) throws IqbException;
    
    /**
     * 通过流程任务ID委托当前流程
     * @param objs  接口参数Json对象
     * @throws IqbException
     */
    public void delegateProcessInstance(JSONObject objs) throws IqbException;
    
    /**
     * 取消流程委托
     * @param objs  接口参数Json对象
     * @throws IqbException
     */
    public void cancelProcDelegate(JSONObject objs) throws IqbException;
    
    /**
     * 通过流程任务ID指定流程任务处理人
     * @param objs
     * @throws IqbException
     */
    public void appointAssignee(JSONObject objs) throws IqbException;
    
    /**
     * 通过流程实例ID将审批中的流程实例暂停，暂停的流程实例只可以被查询，流出任务不可以被处理
     * @param objs
     * @throws IqbException
     */
    public void suspendProcess(JSONObject objs) throws IqbException;
    
    /**
     * 通过流程实例ID将暂停的流程实例激活，激活的流程实例状态变更为审批中，流程任务可以被处理
     * @param objs
     * @throws IqbException
     */
    public void activeProcess(JSONObject objs) throws IqbException;
    
    /**
     * 查询业务流程审批历史
     * 
     * @param objs
     *            接口参数Json对象
     * @throws IqbException
     */
    public PageInfo<IqbWfProcTaskHistoryBean> getProcApprovedHistory(JSONObject objs)
        throws IqbException;
}
