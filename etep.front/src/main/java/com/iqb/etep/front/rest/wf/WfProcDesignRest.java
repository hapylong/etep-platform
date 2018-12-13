/*
 * @(#) WfProcDesinerRest.java  1.0  August 29, 2016
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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.base.CommonReturnInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.front.base.FrontBaseService;
import com.iqb.etep.workflow.design.bean.WfProcActReModelBean;
import com.iqb.etep.workflow.design.bean.WfProcActReProcdefBean;
import com.iqb.etep.workflow.design.service.IWfProcDesignService;

/**
 * Description: 工作流流程设计Rest服务接口
 * 
 * @author mayongming
 * @version 1.0
 * 
 *          <pre>
 * Modification History: 
 * Date             Author      Version         Description 
 * ------------------------------------------------------------------
 * 2016.08.29    mayongming       1.0        1.0 Version
 * 2016.09.01    zhaomingli       1.0        新增流程部署功能
 * </pre>
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping("/WfDesign")
public class WfProcDesignRest extends FrontBaseService{

    /** 日志处理 **/
    private static Logger logger = LoggerFactory.getLogger(WfProcDesignRest.class);

    @Autowired
    IWfProcDesignService wfProcDesignService;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    ProcessEngineConfiguration processEngineConfiguration;
    @Autowired
    ProcessEngineFactoryBean processEngine;

    /**
     * 功能：部署流程
     * 
     * @param objs
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deployementProcess")
    public Map deploymentProcess(@RequestBody JSONObject objs) {
        try {
            logger.debug("IQB信息---部署工作流...开始");
            wfProcDesignService.deploymentProcess(objs);
            logger.debug("IQB信息---部署工作流...结束");
            return super.returnSuccessInfo(new LinkedHashMap<String, Object>());
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }

    }

    /**
     * 根据Model部署流程
     */
    @ResponseBody
    @RequestMapping(value = "modeldeploy")
    public Map deploy(@RequestBody JSONObject objs) {
        try {
            logger.debug("IQB信息---部署工作流...开始");
            wfProcDesignService.deploymentModel(objs);
            logger.debug("IQB信息---部署工作流...结束");
            return super.returnSuccessInfo(new LinkedHashMap<String, Object>());
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }

    /**
     * 模型列表
     */
    @RequestMapping(value = "modellist")
    @ResponseBody
    public Map modelList(@RequestBody JSONObject objs) {
        try {
            logger.debug("IQB信息---开始分页查询数据...");
            PageInfo<WfProcActReModelBean> pageInfo = wfProcDesignService.selectModelList(objs);
            logger.info("IQB信息---分页查询数据完成.");
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
            linkedHashMap.put("result", pageInfo);
            return super.returnSuccessInfo(linkedHashMap);
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }

    /**
     * 功能：流程模型删除
     * 
     * @date 2016-09-13
     * @author zhaomingli
     */
    @ResponseBody
    @RequestMapping(value = "/modeldelete")
    public Map modeldelete(@RequestBody JSONObject objs) {
        try {
            logger.debug("IQB信息---删除模型...开始");
            wfProcDesignService.modeldelete(objs);
            // repositoryService.deleteModel(objs.getString(key));
            logger.debug("IQB信息---删除模型...结束");
            return super.returnSuccessInfo(new LinkedHashMap<String, Object>());
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }

    }

    /**
     * 功能：部署流程删除
     * 
     * @date 2016-09-20
     * @author zhaomingli
     */
    @ResponseBody
    @RequestMapping(value = "/wfdel")
    public Map wfdel(@RequestBody JSONObject objs) {
        try {
            logger.debug("IQB信息---删除流程...开始");
            wfProcDesignService.wfdel(objs);
            // repositoryService.deleteModel(objs.getString(key));
            logger.debug("IQB信息---删除流程...结束");
            return super.returnSuccessInfo(new LinkedHashMap<String, Object>());
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }

    }

    /**
     * 功能：部署流程挂起
     * 
     * @date 2016-09-20
     * @author zhaomingli
     */
    @ResponseBody
    @RequestMapping(value = "/wfsuspend")
    public Map wfsuspend(@RequestBody JSONObject objs) {
        try {
            logger.debug("IQB信息---挂起流程...开始");
            wfProcDesignService.wfsuspend(objs);
            // repositoryService.deleteModel(objs.getString(key));
            logger.debug("IQB信息---挂起流程...结束");
            return super.returnSuccessInfo(new LinkedHashMap<String, Object>());
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }

    }

    /**
     * 功能：部署流程恢复
     * 
     * @date 2016-09-20
     * @author zhaomingli
     */
    @ResponseBody
    @RequestMapping(value = "/wfactive")
    public Map wfactive(@RequestBody JSONObject objs) {
        try {
            logger.debug("IQB信息---恢复流程...开始");
            wfProcDesignService.wfactive(objs);
            // repositoryService.deleteModel(objs.getString(key));
            logger.debug("IQB信息---恢复流程...结束");
            return super.returnSuccessInfo(new LinkedHashMap<String, Object>());
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }

    }

    /**
     * 功能：查询已经部署的流程{@暂时不用}
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryDeplPro")
    public void queryDeploymentProcessList() {
        List<Object[]> objects = new ArrayList<Object[]>();
        ProcessDefinitionQuery processDefinitionQuery =
            repositoryService.createProcessDefinitionQuery().orderByDeploymentId().desc();
        List<ProcessDefinition> processDefinitionList = processDefinitionQuery.listPage(1, 2);
        for (ProcessDefinition processDefinition : processDefinitionList) {
            String deploymentId = processDefinition.getDeploymentId();
            Deployment deployment =
                repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
            System.out.println(deployment);
            System.out.println(processDefinition.getId());
            System.out.println(processDefinition.getDeploymentId());
            System.out.println(processDefinition.getKey());
            System.out.println(processDefinition.getName());
            System.out.println(deployment.getId());
            objects.add(new Object[] { processDefinition, deployment });
        }
    }

    /**
     * 流程定义列表{@正式代码}
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/processlist")
    public Map processList(@RequestBody JSONObject objs) {
        try {
            logger.debug("IQB信息---开始分页查询数据...");
            PageInfo<WfProcActReProcdefBean> pageInfo = wfProcDesignService.processList(objs);
            logger.info("IQB信息---分页查询数据完成.");
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
            linkedHashMap.put("result", pageInfo);
            return super.returnSuccessInfo(linkedHashMap);
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }

    /**
     * 功能：获取流程图片{@正式代码}
     * 
     * @date 2016-09-26
     * @author zhaomingli
     */
    @RequestMapping(value = "/openimage")
    public void loadByDeployment(@RequestParam("procDefId") String procDefId,
        HttpServletResponse response) throws Exception {
        try {
            ProcessDefinition procDef =
                repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefId)
                    .singleResult();
            String diagramResourceName = procDef.getDiagramResourceName();
            InputStream pic =
                repositoryService.getResourceAsStream(procDef.getDeploymentId(),
                    diagramResourceName);
            byte[] b = new byte[1024];
            int len = -1;
            while ((len = pic.read(b, 0, 1024)) != -1) {
                response.setContentType("image/png");
                response.getOutputStream().write(b, 0, len);
            }
        } catch (Exception e) {
            logger.error("获取图片失败。。。");
            e.printStackTrace();
        }
    }

    /**
     * 功能：获取流程高亮显示当前节点图片{@暂时不用}
     * http://localhost/etep.front/WfDesign/openimage2?executionId=edb4858f-943f-11e6-b980-64006a272fcf
     * @date 2016-09-26
     * executionId 流程实例ID
     * @author zhaomingli
     */
    @RequestMapping(value = "/openimage2")
    public void readResource(@RequestParam("executionId") String executionId, HttpServletResponse response)
        throws Exception {
    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(executionId).singleResult();
    BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
    List<String> activeActivityIds = runtimeService.getActiveActivityIds(executionId);
    // 不使用spring请使用下面的两行代码
//ProcessEngineImpl defaultProcessEngine = (ProcessEngineImpl) ProcessEngines.getDefaultProcessEngine();
//Context.setProcessEngineConfiguration(defaultProcessEngine.getProcessEngineConfiguration());

    // 使用spring注入引擎请使用下面的这行代码
    processEngineConfiguration = processEngine.getProcessEngineConfiguration();
    Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);

    ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
    InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", activeActivityIds);

    // 输出资源内容到相应对象
    byte[] b = new byte[1024];
    int len;
    while ((len = imageStream.read(b, 0, 1024)) != -1) {
        response.getOutputStream().write(b, 0, len);
    }
}
    
    /**
     * 功能：导出xml{@正式代码}
     * 
     * @date 2016-10-10
     * @author zhaomingli
     */
    @ResponseBody
    @RequestMapping(value = "/export")
    public Map export(@RequestBody JSONObject objs) {
        try {
            logger.debug("IQB信息---导出模型...开始");
            wfProcDesignService.export(objs);
            logger.debug("IQB信息---导出模型...结束");
            return super.returnSuccessInfo(new LinkedHashMap<String, Object>());
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }

    }

}
