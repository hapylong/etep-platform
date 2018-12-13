/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : IFlowDesign.java
 *
 * PURPOSE : 
 *
 * AUTHOR : zhaomingli
 *
 *
 * 创建日期: 2016年8月12日
 * HISTORY：
 * 变更日期 
 */
package com.iqb.etep.workflow.design.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.workflow.design.bean.WfProcActReModelBean;
import com.iqb.etep.workflow.design.bean.WfProcActReProcdefBean;

/**
 * 功能：流程设计器接口
 * 
 * @date 2016-08-12
 * @author zhaomingli
 */
public interface IWfProcDesignService{

    // 流程发布或部署
    public void deploymentProcess(JSONObject objs) throws IqbException, Exception;
    public void deploymentModel(JSONObject objs) throws IqbException, Exception;
    public void modeldelete(JSONObject objs) throws IqbException, Exception;
    public void wfdel(JSONObject objs) throws IqbException, Exception;
    public void wfsuspend(JSONObject objs) throws IqbException, Exception;
    public void wfactive(JSONObject objs) throws IqbException, Exception;
    public void export(JSONObject objs) throws IqbException, Exception;
    PageInfo<WfProcActReModelBean> selectModelList(JSONObject objs) throws IqbException, IqbSqlException;
    PageInfo<WfProcActReProcdefBean> processList(JSONObject objs) throws IqbException, IqbSqlException;
}
