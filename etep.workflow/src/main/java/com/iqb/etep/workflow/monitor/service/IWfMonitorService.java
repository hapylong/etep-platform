/*
 * @(#) IWfMonitorService.java  1.0  August 29, 2016
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
package com.iqb.etep.workflow.monitor.service;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.workflow.monitor.bean.IqbWfMyProcBackBean;
import com.iqb.etep.workflow.monitor.bean.IqbWfProcBackBean;


/**
 * Description: 工作流监控服务接口
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
public interface IWfMonitorService {

    public PageInfo<IqbWfMyProcBackBean> getUserDoneTasks(JSONObject objs) throws IqbException;

    public PageInfo<IqbWfMyProcBackBean> getActiveProcessList(JSONObject objs) throws IqbException;

    public PageInfo<IqbWfMyProcBackBean> getOrgProcessList(JSONObject objs) throws IqbException;

    public PageInfo<IqbWfProcBackBean> getUserToDoTasks(JSONObject objs) throws IqbException;

	public PageInfo<IqbWfMyProcBackBean> getProcessDelegateList(JSONObject objs)
			throws IqbException;

	public PageInfo<IqbWfMyProcBackBean> geyProcessSummary(JSONObject objs)
			throws IqbException;

	public PageInfo<IqbWfMyProcBackBean> getMyProcDelegateList(JSONObject objs)
			throws IqbException;
	public String exportProcessSummary(JSONObject objs, HttpServletResponse response) throws Exception;
}
