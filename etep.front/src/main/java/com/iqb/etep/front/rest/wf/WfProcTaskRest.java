/*
 * @(#) WfProcTaskRest.java  1.0  August 22, 2016
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
package com.iqb.etep.front.rest.wf;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.front.base.FrontBaseService;
import com.iqb.etep.workflow.base.WfReturnInfo;
import com.iqb.etep.workflow.constant.WfAttribute.WfProcessDataAttr;
import com.iqb.etep.workflow.task.bean.IqbWfProcTaskBean;
import com.iqb.etep.workflow.task.service.IWfProcTaskService;

/**
 * Description: 工作流流程任务Rest服务接口
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
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping("/WfTask")
public class WfProcTaskRest extends FrontBaseService {
    /** 日志处理 **/
    private static Logger logger = LoggerFactory.getLogger(WfProcTaskRest.class);
    
    @Autowired
    IWfProcTaskService wfProcTaskService;
    
    /**
     * 根据流程定义编码启动工作流，通过用户登录信息验证启动流程合法性
     * @param objs
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/startProcess"}, method = {RequestMethod.GET, RequestMethod.POST })
    public Map startProcess(@RequestBody JSONObject objs,HttpServletRequest request) {
        try {                       
            logger.debug("IQB信息---开始启动工作流...");
            String procInstId = wfProcTaskService.startProcessInstance(objs);
            LinkedHashMap<String, Object> resultData = new LinkedHashMap<String, Object>();
            resultData.put(WfProcessDataAttr.PROC_INSTANCEID, procInstId);
            
            return super.returnSuccessInfo(resultData);
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + WfReturnInfo.WF_COMM_02000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(WfReturnInfo.WF_COMM_02000001));
        } finally {
            logger.info("IQB信息---工作流启动结束.");
        }
    }
    
    /**
     * 根据流程定义编码启动工作流并提交第一个流程任务，通过用户登录信息验证流程启动合法性
     * @param objs
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/startAndCommitProcess"}, method = {RequestMethod.GET, RequestMethod.POST })
    public Map startAndCommitProcess(@RequestBody JSONObject objs,HttpServletRequest request) {
        try {                       
            logger.debug("IQB信息---开始启动并提交工作流...");
            String procInstId = wfProcTaskService.startAndCompleteProcessInstance(objs);
            LinkedHashMap<String, Object> resultData = new LinkedHashMap<String, Object>();
            resultData.put(WfProcessDataAttr.PROC_INSTANCEID, procInstId);
            
            return super.returnSuccessInfo(resultData);
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + WfReturnInfo.WF_COMM_02000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(WfReturnInfo.WF_COMM_02000001));
        } finally {
            logger.info("IQB信息---工作流启动并提交结束.");
        }
    }
    
    /**
     * 根据流程定义编码启动工作流并提交第一个流程任务，通过用户Token验证流程操作合法性
     * @param objs
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/startAndCommitProcessByToken"}, method = {RequestMethod.GET, RequestMethod.POST })
    public Map startAndCommitProcessByToken(@RequestBody JSONObject objs,HttpServletRequest request) {
        try {                       
            logger.debug("IQB信息---开始启动并提交工作流...");
            String procInstId = wfProcTaskService.startAndCompleteProcessInstance(objs);
            LinkedHashMap<String, Object> resultData = new LinkedHashMap<String, Object>();
            resultData.put(WfProcessDataAttr.PROC_INSTANCEID, procInstId);
            
            return super.returnSuccessInfo(resultData);
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + WfReturnInfo.WF_COMM_02000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(WfReturnInfo.WF_COMM_02000001));
        } finally {
            logger.info("IQB信息---工作流启动并提交结束.");
        }
    }
    
    /**
     * 通过流程任务ID签收当前流程任务
     * @param objs
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/claimProcess"}, method = {RequestMethod.GET, RequestMethod.POST })
    public Map claimProcess(@RequestBody JSONObject objs,HttpServletRequest request) {
        try {                       
            logger.debug("IQB信息---开始流程任务签收操作...");
            IqbWfProcTaskBean taskBean = wfProcTaskService.claimProcessInstance(objs);
            LinkedHashMap<String, Object> resultData = new LinkedHashMap<String, Object>();
            
            // 在此组装返回给前台的数据
            resultData.put(WfProcessDataAttr.PROC_DEFINITIONID, taskBean.getProcId());
            resultData.put(WfProcessDataAttr.PROC_DEFINITIONKEY, taskBean.getProcKey());
            resultData.put(WfProcessDataAttr.PROC_INSTANCEID, taskBean.getProcInstId());
            resultData.put(WfProcessDataAttr.PROC_TASKID, taskBean.getProcCtaskid());
            resultData.put(WfProcessDataAttr.PROC_TASKCODE, taskBean.getProcCtaskcode());
            resultData.put(WfProcessDataAttr.PROC_TASKNAME, taskBean.getProcCtaskname());
            
            return super.returnSuccessInfo(resultData);
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + WfReturnInfo.WF_COMM_02000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(WfReturnInfo.WF_COMM_02000001));
        } finally {
            logger.info("IQB信息---流程任务签收结束.");
        }
    }
    
    /**
     * 通过流程任务ID取消签收当前流程任务
     * @param objs
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/unclaimProcess"}, method = {RequestMethod.GET, RequestMethod.POST })
    public Map unclaimProcess(@RequestBody JSONObject objs,HttpServletRequest request) {
        try {
            logger.debug("IQB信息---开始流程任务取消签收操作...");
            IqbWfProcTaskBean taskBean = wfProcTaskService.unclaimProcessInstance(objs);
            LinkedHashMap<String, Object> resultData = new LinkedHashMap<String, Object>();

            // 在此组装返回给前台的数据
            resultData.put(WfProcessDataAttr.PROC_DEFINITIONID, taskBean.getProcId());
            resultData.put(WfProcessDataAttr.PROC_DEFINITIONKEY, taskBean.getProcKey());
            resultData.put(WfProcessDataAttr.PROC_INSTANCEID, taskBean.getProcInstId());
            resultData.put(WfProcessDataAttr.PROC_TASKID, taskBean.getProcCtaskid());
            resultData.put(WfProcessDataAttr.PROC_TASKCODE, taskBean.getProcCtaskcode());
            resultData.put(WfProcessDataAttr.PROC_TASKNAME, taskBean.getProcCtaskname());

            return super.returnSuccessInfo(resultData);
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + WfReturnInfo.WF_COMM_02000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(WfReturnInfo.WF_COMM_02000001));
        } finally {
            logger.info("IQB信息---流程任务取消签收结束.");
        }
    }
    
    /**
     * 通过流程任务ID审批当前流程任务
     * @param objs
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/completeProcess"}, method = {RequestMethod.GET, RequestMethod.POST })
    public Map completeProcessInstance(@RequestBody JSONObject objs,HttpServletRequest request) {
        try {                       
            logger.debug("IQB信息---开始流程任务审批操作...");
            wfProcTaskService.completeProcessInstanceByTaskId(objs);
            
            return super.returnSuccessInfo(new LinkedHashMap<String, Object>());
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + WfReturnInfo.WF_COMM_02000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(WfReturnInfo.WF_COMM_02000001));
        } finally {
            logger.info("IQB信息---流程任务审批结束.");
        }
    }
    
    /**

     * 通过流程任务代码审批当前流程任务
     * 
     * @param objs
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/completeProcessByTaskCode"}, method = {RequestMethod.GET, RequestMethod.POST })
    public Map completeProcessInstanceByTaskCode(@RequestBody JSONObject objs,HttpServletRequest request) {
        try {                       
            logger.debug("IQB信息---开始流程任务审批操作...");
            wfProcTaskService.completeProcessInstanceByTaskCode(objs);
            return super.returnSuccessInfo(new LinkedHashMap<String, Object>());
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + WfReturnInfo.WF_COMM_02000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(WfReturnInfo.WF_COMM_02000001));
        } finally {
            logger.info("IQB信息---流程任务审批结束.");
        }
    }
    
    /**
     * 通过流程任务ID删除当前流程任务
     * @param objs
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/deleteProcess"}, method = {RequestMethod.GET, RequestMethod.POST })
    public Map deleteProcessInstance(@RequestBody JSONObject objs,HttpServletRequest request) {
        try {                       
            logger.debug("IQB信息---开始流程任务删除...");
            wfProcTaskService.deleteProcessInstance(objs);
            return super.returnSuccessInfo(new LinkedHashMap<String, Object>());
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + WfReturnInfo.WF_COMM_02000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(WfReturnInfo.WF_COMM_02000001));
        } finally {
            logger.info("IQB信息---流程任务删除结束.");
        }
    }
    
    /**
     * 通过流程任务代码取消当前流程任务，取消的流程可在我的流程中查询到
     * @param objs
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/cancelProcess"}, method = {RequestMethod.GET, RequestMethod.POST })
    public Map cancelProcessInstance(@RequestBody JSONObject objs,HttpServletRequest reques) {
        try {                       
            logger.debug("IQB信息---开始流程任务取消操作...");
            wfProcTaskService.cancelProcessInstance(objs);
            return super.returnSuccessInfo(new LinkedHashMap<String, Object>());
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + WfReturnInfo.WF_COMM_02000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(WfReturnInfo.WF_COMM_02000001));
        } finally {
            logger.info("IQB信息---流程任务取消结束.");
        }
    }
    
    /**
     * 通过流程任务ID撤回当前流程任务，撤回的流程在待办任务中可进行再次处理
     * @param objs
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/retrieveProcess"}, method = {RequestMethod.GET, RequestMethod.POST })
    public Map retrieveProcessInstance(@RequestBody JSONObject objs,HttpServletRequest reques) {
        try {                       
            logger.debug("IQB信息---开始流程任务撤回操作...");
            wfProcTaskService.retrieveProcessInstance(objs);
            return super.returnSuccessInfo(new LinkedHashMap<String, Object>());
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + WfReturnInfo.WF_COMM_02000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(WfReturnInfo.WF_COMM_02000001));
        } finally {
            logger.info("IQB信息---流程任务撤回结束."); 
        }
    }
    
    /**
     * 通过流程任务ID终止当前流程任务，终止的流程可在我的流程中查询到
     * @param objs
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/endProcess"}, method = {RequestMethod.GET, RequestMethod.POST })
    public Map endProcessInstance(@RequestBody JSONObject objs,HttpServletRequest reques) {
        try {                       
            logger.debug("IQB信息---开始流程任务终止操作...");
            wfProcTaskService.endProcessInstance(objs);
            return super.returnSuccessInfo(new LinkedHashMap<String, Object>());
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + WfReturnInfo.WF_COMM_02000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(WfReturnInfo.WF_COMM_02000001));
        } finally {
            logger.info("IQB信息---流程任务终止结束.");
        }
    }
    
    /**
     * 指定流程任务的处理人
     * @param objs
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/assign" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map appointAssignee(@RequestBody JSONObject objs, HttpServletRequest reques) {
        try {
            logger.debug("IQB信息---开始设置流程任务处理人...");
            wfProcTaskService.appointAssignee(objs);

            return super.returnSuccessInfo(new LinkedHashMap<String, Object>());
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + WfReturnInfo.WF_COMM_02000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(WfReturnInfo.WF_COMM_02000001));
        } finally {
            logger.info("IQB信息---设置流程任务处理人结束.");
        }
    }
    
    /**
     * 通过流程任务ID委托当前流程任务
     * @param objs
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/delegateProcess"}, method = {RequestMethod.GET, RequestMethod.POST })
    public Map delegateProcessInstance(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {                       
            logger.debug("IQB信息---开始流程任务委托操作...");
            wfProcTaskService.delegateProcessInstance(objs);
            return super.returnSuccessInfo(new LinkedHashMap<String, Object>());
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + WfReturnInfo.WF_COMM_02000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(WfReturnInfo.WF_COMM_02000001));
        } finally {
            logger.info("IQB信息---流程任务委托结束.");
        }
    }
    
    /**
     * 取消流程委托
     * @param objs
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/cancelProcDelegate"}, method = {RequestMethod.GET, RequestMethod.POST })
    public Map cancelProcDelegate(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {                       
            logger.debug("IQB信息---开始取消流程委托...");
            wfProcTaskService.cancelProcDelegate(objs);
            return super.returnSuccessInfo(new LinkedHashMap<String, Object>());
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + WfReturnInfo.WF_COMM_02000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(WfReturnInfo.WF_COMM_02000001));
        } finally {
            logger.info("IQB信息---取消流程委托结束.");
        }
    }
    
    /**
     * 查询业务流程审批历史
     * 
     * @param objs
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping(value = { "/procApproveHistory"}, method = {RequestMethod.GET, RequestMethod.POST })
    public Map getProcApprovedHistory(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {
            logger.debug("IQB信息---开始查询业务流程审批历史...");
            LinkedHashMap map = new LinkedHashMap();
            map.put("result", wfProcTaskService.getProcApprovedHistory(objs));
            return super.returnSuccessInfo(map);
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + WfReturnInfo.WF_COMM_02000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(WfReturnInfo.WF_COMM_02000001));
        } finally {
            logger.info("IQB信息---查询业务流程审批历史结束.");
        }
    }
}