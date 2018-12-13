/*
 * @(#) WfProcMonitorRest.java  1.0  August 29, 2016
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

import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.common.base.CommonReturnInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.front.base.FrontBaseService;
import com.iqb.etep.workflow.base.WfReturnInfo;
import com.iqb.etep.workflow.monitor.service.IWfMonitorService;
import com.iqb.etep.workflow.task.service.IWfProcTaskService;

/**
 * Description: 工作流流程监控Rest服务接口
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
@Controller
@SuppressWarnings({ "rawtypes", "unchecked" })
@RequestMapping("/WfMonitor")
public class WfProcMonitorRest extends FrontBaseService {
    private static Logger logger = LoggerFactory.getLogger(WfProcMonitorRest.class);

    @Autowired
    IWfMonitorService wfMonitorService;
    @Autowired
    IWfProcTaskService wfProcTaskService;

    /**
     * 流程监控列表查询
     * @param objs
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/activeProcessList" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map getActiveProcessList(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {
            logger.debug("IQB信息---开始查询未完成流程列表...");
            LinkedHashMap map = new LinkedHashMap();
            map.put("result", wfMonitorService.getActiveProcessList(objs));

            return super.returnSuccessInfo(map);
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + WfReturnInfo.WF_COMM_02000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(WfReturnInfo.WF_COMM_02000001));
        } finally {
            logger.info("IQB信息---查询未完成流程列表结束.");
        }
    }

    /**
     * 流程监控列表查询
     * @param objs
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/orgProcessList" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map getOrgProcessList(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {
            logger.debug("IQB信息---开始查询未完成流程列表...");
            LinkedHashMap map = new LinkedHashMap();
            map.put("result", wfMonitorService.getOrgProcessList(objs));

            return super.returnSuccessInfo(map);
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + WfReturnInfo.WF_COMM_02000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(WfReturnInfo.WF_COMM_02000001));
        } finally {
            logger.info("IQB信息---查询未完成流程列表结束.");
        }
    }
    
    /**
     * 查询我的已办流程列表
     * @param objs
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/userDoneTasks" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map geyUserDoneTasks(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {
            logger.debug("IQB信息---开始查询用户已办任务列表...");
            LinkedHashMap map = new LinkedHashMap();
            map.put("result", wfMonitorService.getUserDoneTasks(objs));

            return super.returnSuccessInfo(map);
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + WfReturnInfo.WF_COMM_02000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(WfReturnInfo.WF_COMM_02000001));
        } finally {
            logger.info("IQB信息---查询用户已办任务列表结束.");
        }
    }
    
    /**
     * 查询用户待办流程任务列表
     * @param objs
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/userToDoTasks" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map getUserToDoTasks(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {
            logger.debug("IQB信息---开始查询用户待办任务列表...");
            LinkedHashMap map = new LinkedHashMap();
            map.put("result", wfMonitorService.getUserToDoTasks(objs));

            return super.returnSuccessInfo(map);
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + WfReturnInfo.WF_COMM_02000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(WfReturnInfo.WF_COMM_02000001));
        } finally {
            logger.info("IQB信息---查询用户待办任务列表结束.");
        }
    }

    /**
     * 流程实例暂停
     * @param objs
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/suspend" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map suspendProcess(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {                       
            logger.debug("IQB信息---开始流程实例暂停处理...");
            wfProcTaskService.suspendProcess(objs);
            return super.returnSuccessInfo(new LinkedHashMap<String, Object>());
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + WfReturnInfo.WF_COMM_02000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(WfReturnInfo.WF_COMM_02000001));
        } finally {
            logger.info("IQB信息---流程实例暂停处理结束.");
        }
    }

    /**
     * 将暂停的流程实例激活
     * @param objs
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/active" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map activeProcess(@RequestBody JSONObject objs) {
        try {                       
            logger.debug("IQB信息---开始流程实例激活处理...");
            wfProcTaskService.activeProcess(objs);
            return super.returnSuccessInfo(new LinkedHashMap<String, Object>());
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + WfReturnInfo.WF_COMM_02000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(WfReturnInfo.WF_COMM_02000001));
        } finally {
            logger.info("IQB信息---流程实例激活处理结束.");
        }
    }

    /**
     * 流程实例汇总查询
     * @param objs
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/processSummary" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map geyProcessSummary(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {
            logger.debug("IQB信息---开始流程实例汇总查询...");
            LinkedHashMap map = new LinkedHashMap();
            map.put("result", wfMonitorService.geyProcessSummary(objs));

            return super.returnSuccessInfo(map);
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + WfReturnInfo.WF_COMM_02000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(WfReturnInfo.WF_COMM_02000001));
        } finally {
            logger.info("IQB信息---流程实例汇总查询结束.");
        }
    }
    /**
     * 导出流程实例汇总
     * 
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"/exportProcessSummary"}, method = {RequestMethod.GET, RequestMethod.POST})
    public Map exportProcessSummary(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            logger.debug("开始导出流程实例汇总报表数据");
            Map<String, String> data = new HashMap<>();
            Enumeration<String> paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paraName = paramNames.nextElement();
                String para = request.getParameter(paraName);
                data.put(paraName, para.trim());
            }
            String json = JSON.toJSONString(data);
            JSONObject objs = JSONObject.parseObject(json);
            String result = wfMonitorService.exportProcessSummary(objs, response);
            logger.debug("导出流程实例汇总报表数据完成.结果：{}", result);
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put("result", result);
            return super.returnSuccessInfo(linkedHashMap);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }
    /**
     * 流程委托查询
     * @param objs
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/processDelegate" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map getProcessDelegateList(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {
            logger.debug("IQB信息---开始流程委托查询...");
            LinkedHashMap map = new LinkedHashMap();
            map.put("result", wfMonitorService.getProcessDelegateList(objs));

            return super.returnSuccessInfo(map);
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + WfReturnInfo.WF_COMM_02000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(WfReturnInfo.WF_COMM_02000001));
        } finally {
            logger.info("IQB信息---流程委托查询结束.");
        }
    }
    
    /**
     * 流程委托查询
     * @param objs
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/myProcDelegate" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map getMyProcDelegateList(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {
            logger.debug("IQB信息---开始我的流程委托查询...");
            LinkedHashMap map = new LinkedHashMap();
            map.put("result", wfMonitorService.getMyProcDelegateList(objs));

            return super.returnSuccessInfo(map);
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + WfReturnInfo.WF_COMM_02000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(WfReturnInfo.WF_COMM_02000001));
        } finally {
            logger.info("IQB信息---我的流程委托查询结束.");
        }
    }
}
