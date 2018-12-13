/*
 * @(#) WfProcTaskServiceImpl.java  1.0  August 22, 2016
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
package com.iqb.etep.workflow.task.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.utils.JSONUtil;
import com.iqb.etep.common.utils.StringUtil;
import com.iqb.etep.common.utils.SysUserSession;
import com.iqb.etep.workflow.base.WfReturnInfo;
import com.iqb.etep.workflow.param.WfProcAuthDataBean;
import com.iqb.etep.workflow.param.WfProcBizDataBean;
import com.iqb.etep.workflow.param.WfProcVariableDataBean;
import com.iqb.etep.workflow.param.WfProcessDataBean;
import com.iqb.etep.workflow.task.bean.IqbWfProcTaskBean;
import com.iqb.etep.workflow.task.bean.IqbWfProcTaskHistoryBean;
import com.iqb.etep.workflow.task.biz.WfProcTaskBiz;
import com.iqb.etep.workflow.task.service.IWfProcTaskService;
import com.iqb.etep.workflow.constant.WfAttribute.ProcToken;
import com.iqb.etep.workflow.constant.WfAttribute.WfProcessDataAttr;
import com.iqb.etep.workflow.constant.WfAttribute.WfRequestDataTypeAttr;
import com.iqb.etep.workflow.constant.WfAttribute.WfUserAuthType;

/**
 * Description: 工作流流程任务服务接口
 * 
 * @author mayongming
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date             Author      Version         Description 
 * ------------------------------------------------------------------
 * 2016.08.22    mayongming       1.0           1.0 Version
 * </pre>
 */
@Service("wfProcTaskService")
public class WfProcTaskServiceImpl implements IWfProcTaskService {
    /** 日志处理 **/
    private static Logger logger = LoggerFactory.getLogger(WfProcTaskServiceImpl.class);
    
    @Autowired
    WfProcTaskBiz wfProcTaskBiz;
    
    @Autowired
    SysUserSession sysUserSession;
    
    /**
     * 根据流程定义编码启动对应的工作流，通过用户登录信息验证启动流程合法性
     * 
     * @param objs  接口参数Json对象
     * @return  流程实例ID
     * 
     * @throws IqbException
     */
    public String startProcessInstance(JSONObject objs) throws IqbException {
        try {
            WfProcessDataBean procData = parseProcessData(objs);
            WfProcVariableDataBean procVarData = parseVariableData(objs);
            WfProcAuthDataBean authData = parseAuthData(objs);
            WfProcBizDataBean bizData = parseBizData(objs);
            userAuthenticate(authData, false, false);
            
            return wfProcTaskBiz
                .startProcessInstanceByKey(procData, procVarData, authData, bizData);
        } catch (IqbException iqbe) {
            throw iqbe;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new IqbException(WfReturnInfo.WF_TASK_02020199, e);
        }
    }

    /**
     * 根据流程定义编码启动工作流并提交第一个流程任务，通过用户登录信息验证流程操作合法性
     * 
     * @param objs  接口参数Json对象
     * @return  流程实例ID
     * 
     * @throws IqbException
     */
    public String startAndCompleteProcessInstance(JSONObject objs) throws IqbException {
        try {
            WfProcessDataBean procData = parseProcessData(objs);
            WfProcVariableDataBean procVarData = parseVariableData(objs);
            WfProcAuthDataBean authData = parseAuthData(objs);
            WfProcBizDataBean bizData = parseBizData(objs);
            userAuthenticate(authData, false, true);
            
            return wfProcTaskBiz.startAndCompleteProcessInstanceByKey(procData, procVarData,
                authData, bizData);
        } catch (IqbException iqbe) {
            throw iqbe;
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new IqbException(WfReturnInfo.WF_TASK_02020199, e);
        }
    }

    /**
     * 通过流程任务ID签收当前流程任务
     * 
     * @param objs  接口参数Json对象
     * @return 流程任务信息
     * 
     * @throws IqbException
     */
    public IqbWfProcTaskBean claimProcessInstance(JSONObject objs) throws IqbException {
        try {
            WfProcessDataBean procData = parseProcessData(objs);
            WfProcVariableDataBean procVarData = parseVariableData(objs);
            WfProcAuthDataBean authData = parseAuthData(objs);
            WfProcBizDataBean bizData = parseBizData(objs);
            userAuthenticate(authData, false, true);
            
            return wfProcTaskBiz.claimProcessInstance(procData, procVarData, authData,
                bizData);
        } catch (IqbException iqbe) {
            throw iqbe;
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new IqbException(WfReturnInfo.WF_TASK_02020399, e);
        }
    }
    
    /**
     * 通过流程任务ID取消签收当前流程任务
     * 
     * @param objs 接口参数Json对象
     * @return 流程任务信息
     * @throws IqbException
     */
    public IqbWfProcTaskBean unclaimProcessInstance(JSONObject objs) throws IqbException {
        try {
            WfProcessDataBean procData = parseProcessData(objs);
            WfProcVariableDataBean procVarData = parseVariableData(objs);
            WfProcAuthDataBean authData = parseAuthData(objs);
            WfProcBizDataBean bizData = parseBizData(objs);
            userAuthenticate(authData, false, false);
            
            return wfProcTaskBiz.unclaimProcessInstance(procData, procVarData, authData,
                bizData);
        } catch (IqbException iqbe) {
            throw iqbe;
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new IqbException(WfReturnInfo.WF_TASK_02020499, e);
        }
    }
    
    /**
     * 通过流程任务ID审批当前流程任务
     * 
     * @param objs  接口参数Json对象，包括：
     * 
     * @throws IqbException
     * @throws Exception
     */
    public void completeProcessInstanceByTaskId(JSONObject objs) throws IqbException {
        try {
            WfProcessDataBean procData = parseProcessData(objs);
            WfProcVariableDataBean procVarData = parseVariableData(objs);
            WfProcAuthDataBean authData = parseAuthData(objs);
            WfProcBizDataBean bizData = parseBizData(objs);
            userAuthenticate(authData, false, true);
            
            // 调用流程任务审批服务接口
            wfProcTaskBiz.completeProcessInstanceByTaskId(procData, procVarData, authData, bizData);
        } catch (IqbException iqbe) {
            throw iqbe;
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new IqbException(WfReturnInfo.WF_TASK_02020599, e);
        }
    }
    
    /**
     * 通过流程任务代码审批当前流程任务
     * 
     * @param objs  接口参数Json对象，包括：
     * 
     * @throws IqbException
     * @throws Exception
     */
    public void completeProcessInstanceByTaskCode(JSONObject objs) throws IqbException {
        try {
            WfProcessDataBean procData = parseProcessData(objs);
            WfProcVariableDataBean procVarData = parseVariableData(objs);
            WfProcAuthDataBean authData = parseAuthData(objs);
            WfProcBizDataBean bizData = parseBizData(objs);
            userAuthenticate(authData, false, true);
            
            // 调用流程任务审批服务接口
            wfProcTaskBiz.completeProcessInstanceByTaskCode(procData, procVarData, authData, bizData);
        } catch (IqbException iqbe) {
            throw iqbe;
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new IqbException(WfReturnInfo.WF_TASK_02020599, e);
        }
    }
    
    /**
     * 通过流程任务ID删除当前流程任务，删除的流程不可以在我的流程中查询到
     * 
     * @param objs  接口参数Json对象，包括：
     * 
     * @throws IqbException
     * @throws Exception
     */
    public void deleteProcessInstance(JSONObject objs) throws IqbException {
        try {
            WfProcessDataBean procData = parseProcessData(objs);
            WfProcVariableDataBean procVarData = parseVariableData(objs);
            WfProcAuthDataBean authData = parseAuthData(objs);
            WfProcBizDataBean bizData = parseBizData(objs);
            userAuthenticate(authData, false, false);
            
            wfProcTaskBiz.deleteProcessInstance(procData, procVarData, authData, bizData);
        } catch (IqbException iqbe) {
            throw iqbe;
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new IqbException(WfReturnInfo.WF_TASK_02020699, e);
        }
    }
    
    /**
     * 通过流程实例取消当前流程，取消的流程可在我的流程中查询到
     * 
     * @param objs  接口参数Json对象，包括：
     * 
     * @throws IqbException
     * @throws Exception
     */
    public void cancelProcessInstance(JSONObject objs) throws IqbException {
        try {
            WfProcessDataBean procData = parseProcessData(objs);
            WfProcVariableDataBean procVarData = parseVariableData(objs);
            WfProcAuthDataBean authData = parseAuthData(objs);
            WfProcBizDataBean bizData = parseBizData(objs);
            userAuthenticate(authData, false, false);
            
            wfProcTaskBiz.cancelProcessInstance(procData, procVarData, authData, bizData);
        } catch (IqbException iqbe) {
            throw iqbe;
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new IqbException(WfReturnInfo.WF_TASK_02021099, e);
        }
    }
    
    /**
     * 通过流程实例撤回当前流程任务，撤回的流程在待办任务中可进行再次处理
     * 
     * @param objs  接口参数Json对象，包括：
     * 
     * @throws IqbException
     * @throws Exception
     */
    public void retrieveProcessInstance(JSONObject objs) throws IqbException {
        try {
            WfProcessDataBean procData = parseProcessData(objs);
            WfProcVariableDataBean procVarData = parseVariableData(objs);
            WfProcAuthDataBean authData = parseAuthData(objs);
            WfProcBizDataBean bizData = parseBizData(objs);
            userAuthenticate(authData, false, false);
            
            wfProcTaskBiz.retrieveProcessInstance(procData, procVarData, authData, bizData);
        } catch (IqbException iqbe) {
            throw iqbe;
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new IqbException(WfReturnInfo.WF_TASK_02021199, e);
        }
    }
    
    /**
     * 通过流程实例终止流程，终止的流程可在我的流程中查询到
     * 
     * @param objs  接口参数Json对象，包括：
     * 
     * @throws IqbException
     * @throws Exception
     */
    public void endProcessInstance(JSONObject objs) throws IqbException {
        try {
            WfProcessDataBean procData = parseProcessData(objs);
            WfProcVariableDataBean procVarData = parseVariableData(objs);
            WfProcAuthDataBean authData = parseAuthData(objs);
            WfProcBizDataBean bizData = parseBizData(objs);
            userAuthenticate(authData, false, false);
            
            wfProcTaskBiz.endProcessInstance(procData, procVarData, authData, bizData);
        } catch (IqbException iqbe) {
            throw iqbe;
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new IqbException(WfReturnInfo.WF_TASK_02021299, e);
        }
    }
    
    /**
     * 通过流程任务ID委托当前流程
     * @param objs  接口参数Json对象
     * @throws IqbException
     */
    public void delegateProcessInstance(JSONObject objs) throws IqbException {
        try {
            WfProcessDataBean procData = parseProcessData(objs);
            WfProcVariableDataBean procVarData = parseVariableData(objs);
            WfProcAuthDataBean authData = parseAuthData(objs);
            userAuthenticate(authData, false, true);
            
            wfProcTaskBiz.delegateProcessInstance(procData, procVarData, authData);
        } catch (IqbException iqbe) {
            throw iqbe;
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new IqbException(WfReturnInfo.WF_TASK_02020999, e);
        }
    }
    
    /**
     * 取消流程委托
     * @param objs  接口参数Json对象
     * @throws IqbException
     */
    public void cancelProcDelegate(JSONObject objs) throws IqbException {
        try {
            WfProcessDataBean procData = parseProcessData(objs);
            WfProcVariableDataBean procVarData = parseVariableData(objs);
            WfProcAuthDataBean authData = parseAuthData(objs);
            userAuthenticate(authData, false, true);
            
            wfProcTaskBiz.cancelProcDelegate(procData, procVarData, authData);
        } catch (IqbException iqbe) {
            throw iqbe;
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new IqbException(WfReturnInfo.WF_TASK_02020999, e);
        }
    }
    
    /**
     * 通过流程任务ID指定流程任务处理人
     * @param objs
     * @throws IqbException
     */
    public void appointAssignee(JSONObject objs) throws IqbException {
        try {
            WfProcessDataBean procData = parseProcessData(objs);
            WfProcVariableDataBean procVarData = parseVariableData(objs);
            WfProcAuthDataBean authData = parseAuthData(objs);
            userAuthenticate(authData, false, false);
            
            wfProcTaskBiz.appointAssignee(procData, procVarData, authData);
        } catch (IqbException iqbe) {
            throw iqbe;
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new IqbException(WfReturnInfo.WF_TASK_02020999, e);
        }
    }
    
    /**
     * 通过流程实例ID将审批中的流程实例暂停，暂停的流程实例只可以被查询，流出任务不可以被处理
     * @param objs
     * @throws IqbException
     */
    public void suspendProcess(JSONObject objs) throws IqbException {
        try {
            WfProcessDataBean procData = parseProcessData(objs);
            WfProcVariableDataBean procVarData = parseVariableData(objs);
            WfProcAuthDataBean authData = parseAuthData(objs);
            userAuthenticate(authData, false, false);
            
            wfProcTaskBiz.suspendProcess(procData, procVarData, authData);
        } catch (IqbException iqbe) {
            throw iqbe;
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new IqbException(WfReturnInfo.WF_TASK_02020999, e);
        }
    }
    
    /**
     * 通过流程实例ID将暂停的流程实例激活，激活的流程实例状态变更为审批中，流程任务可以被处理
     * @param objs
     * @throws IqbException
     */
    public void activeProcess(JSONObject objs) throws IqbException {
        try {
            WfProcessDataBean procData = parseProcessData(objs);
            WfProcVariableDataBean procVarData = parseVariableData(objs);
            WfProcAuthDataBean authData = parseAuthData(objs);
            userAuthenticate(authData, false, false);
            
            wfProcTaskBiz.activeProcess(procData, procVarData, authData);
        } catch (IqbException iqbe) {
            throw iqbe;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new IqbException(WfReturnInfo.WF_TASK_02020999, e);
        }
    }
    
    /**
     * 查询业务流程审批历史
     * @param objs  接口参数Json对象
     * @throws IqbException
     */
    public PageInfo<IqbWfProcTaskHistoryBean> getProcApprovedHistory(JSONObject objs) throws IqbException {
        if (!objs.containsKey(WfProcessDataAttr.PROC_INSTANCEID)
            || StringUtil.isNull(objs.getString(WfProcessDataAttr.PROC_INSTANCEID))) {
            throw new IqbException(WfReturnInfo.WF_TASK_02020701);
        }

        try {
            return new PageInfo<IqbWfProcTaskHistoryBean>(wfProcTaskBiz.getProcApprovedHistory(objs));
        } catch (IqbException iqbe) {
            throw iqbe;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new IqbException(WfReturnInfo.WF_TASK_02020799, e);
        }
    }
    
    /**
     * 解析流程数据
     * @param objs  请求Json数据
     * @return
     * @throws IqbException
     */
    private WfProcessDataBean parseProcessData(JSONObject objs)
        throws IqbException {
        if (objs.containsKey(WfRequestDataTypeAttr.PROC_PROCDATA)) {
            return JSONUtil.toJavaObject(objs.getJSONObject(WfRequestDataTypeAttr.PROC_PROCDATA),
                WfProcessDataBean.class);
        } else {
            throw new IqbException(WfReturnInfo.WF_TASK_02020000);
        }
    }

    /**
     * 解析流程变量数据
     * @param objs  请求Json数据
     * @return
     * @throws IqbException
     */
    private WfProcVariableDataBean parseVariableData(JSONObject objs) throws IqbException {
        if (objs.containsKey(WfRequestDataTypeAttr.PROC_VARIABLEDATA)) {
            WfProcVariableDataBean variableData =
                JSONUtil.toJavaObject(objs, WfProcVariableDataBean.class);
            return (variableData == null || variableData.isEmpty()) ? new WfProcVariableDataBean()
                : variableData;
        } else {
            throw new IqbException(WfReturnInfo.WF_TASK_02020000);
        }
    }

    /**
     * 解析流程认证数据
     * @param objs  请求Json数据
     * @return
     * @throws IqbException
     */
    private WfProcAuthDataBean parseAuthData(JSONObject objs) throws IqbException {
        WfProcAuthDataBean authData = null;
        if (objs.containsKey(WfRequestDataTypeAttr.PROC_AUTHDATA)) {
            authData =
                JSONUtil.toJavaObject(objs.getJSONObject(WfRequestDataTypeAttr.PROC_AUTHDATA),
                    WfProcAuthDataBean.class);
        }

        if (authData == null && objs.containsKey(WfRequestDataTypeAttr.PROC_VARIABLEDATA)) {
            authData =
                JSONUtil.toJavaObject(objs.getJSONObject(WfRequestDataTypeAttr.PROC_VARIABLEDATA),
                    WfProcAuthDataBean.class);
        }
        
        if (authData == null || authData.isEmpty()) {
            throw new IqbException(WfReturnInfo.WF_TASK_02020000);
        }
        
        return authData;
    }
    
    /**
     * 解析流程业务数据
     * @param objs  请求Json数据
     * @return
     * @throws IqbException
     */
    private WfProcBizDataBean parseBizData(JSONObject objs) throws IqbException {
        if (objs.containsKey(WfRequestDataTypeAttr.PROC_BIZDATA)) {
            WfProcBizDataBean bizData = JSONUtil.toJavaObject(objs, WfProcBizDataBean.class);
            return (bizData == null || bizData.isEmpty()) ? new WfProcBizDataBean() : bizData;
        } else {
            throw new IqbException(WfReturnInfo.WF_TASK_02020000);
        }
    }
    
    /**
     * 用户认证，未指定认证方式，或者认证不通过的请求抛出IqbException，不能进行后续流程处理
     * @param authData      用户认证信息
     * @throws IqbException
     */
    private void userAuthenticate(WfProcAuthDataBean authData, boolean checkOrg, boolean checkRole)
        throws IqbException {
        String authType = authData.getProcAuthType();
        List<String> roleCodes = new ArrayList<String>();
        
        if (StringUtil.isNull(authType)) {
            throw new IqbException(WfReturnInfo.WF_TASK_02020004); 
        } 
        
        switch (authType) {
            case WfUserAuthType.PROC_AUTH_TOKEN:
                if (StringUtil.isNull(authData.getProcTaskUser())
                    || (checkOrg && StringUtil.isNull(authData.getProcOrgCode()))
                    || (checkRole && StringUtil.isNull(authData.getProcTaskRole()))
                    || !matchToken(authData.getProcTokenUser(), authData.getProcTokenPass())) {
                    throw new IqbException(WfReturnInfo.WF_TASK_02020005);
                }
                
                // 创建操作角色列表，并放到流程变量数据中
                roleCodes.add(authData.getProcTaskRole());
                authData.setProcTaskRoles(roleCodes);
                
                break;
            case WfUserAuthType.PROC_AUTH_SESSION:
                String userCode = sysUserSession.getUserCode();
                roleCodes = sysUserSession.getStationCodeList();
                String procOrgcode = sysUserSession.getOrgCode();
                
                if (StringUtil.isNull(userCode) || roleCodes == null || roleCodes.isEmpty()) {
                    throw new IqbException(WfReturnInfo.WF_TASK_02020006);
                } 

                authData.setProcTaskUser(userCode);
                authData.setProcTaskRoles(roleCodes);
                authData.setProcOrgCode(procOrgcode);
                break;
            default:
                throw new IqbException(WfReturnInfo.WF_TASK_02020004);   
        }
    }
    
    /**
     * 检查token认证是否合法
     * @param tokenUser
     * @param tokenPass
     * @return
     */
    private boolean matchToken(String tokenUser, String tokenPass) {
        boolean isMatched = false;

        for (ProcToken token : ProcToken.values()) {
            if (token.getTokenUser().equals(tokenUser) && token.getTokenPass().equals(tokenPass)) {
                return true;
            }
        }

        return isMatched;
    }
}
