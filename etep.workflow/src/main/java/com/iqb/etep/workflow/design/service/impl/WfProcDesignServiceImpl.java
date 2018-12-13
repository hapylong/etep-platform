/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : FlowDesignServiceImpl.java
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
package com.iqb.etep.workflow.design.service.impl;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.workflow.base.WfReturnInfo;
import com.iqb.etep.workflow.design.bean.WfProcActReModelBean;
import com.iqb.etep.workflow.design.bean.WfProcActReProcdefBean;
import com.iqb.etep.workflow.design.biz.WfProcDesinerBiz;
import com.iqb.etep.workflow.design.service.IWfProcDesignService;

/**
 * 功能：流程设计器接口
 * 
 * @date 2016-08-12
 * @author zhaomingli
 */
@Service("wfProcDesignService")
public class WfProcDesignServiceImpl implements IWfProcDesignService{

    @Autowired
    WfProcDesinerBiz wfProcDesinerBiz;


    /**
     * 功能：流程部署
     * 
     * @date 2016-09-01
     * @author zhaomingli
     */
    public void deploymentProcess(JSONObject objs) throws IqbException, Exception {
        wfProcDesinerBiz.deploymentProcess(objs);

    }
    /**
     * 功能：流程模型部署
     * 
     * @date 2016-09-01
     * @author zhaomingli
     */
    public void deploymentModel(JSONObject objs) throws IqbException, Exception {
        wfProcDesinerBiz.deploymentModel(objs);
        
    }


    /**
     * 功能：流程模型列表
     * 
     * @date 2016-09-13
     * @author zhaomingli
     */
    public PageInfo<WfProcActReModelBean> selectModelList(JSONObject objs) throws IqbException, IqbSqlException {
        return new PageInfo<WfProcActReModelBean>(wfProcDesinerBiz.selectModelList(objs));
    }
    
    /**
     * 功能：流程部署列表
     * 
     * @date 2016-09-13
     * @author zhaomingli
     */
    public PageInfo<WfProcActReProcdefBean> processList(JSONObject objs) throws IqbException, IqbSqlException {
        return new PageInfo<WfProcActReProcdefBean>(wfProcDesinerBiz.processList(objs));
    }
    
    /**
     * 功能：删除流程
     * 
     * @date 2016-09-13
     * @author zhaomingli
     */
    public void wfdel(JSONObject objs) throws IqbException, Exception {
        try {
            wfProcDesinerBiz.wfdel(objs);
        } catch(PersistenceException iqbe) {
            //存在外键
            throw new IqbException(WfReturnInfo.WF_DESIGN_02010103, iqbe);
        }
        
    }
    /**
     * 功能：导出流程
     * 
     * @date 2016-09-13
     * @author zhaomingli
     */
    public void export(JSONObject objs) throws IqbException, Exception {
        try {
            wfProcDesinerBiz.export(objs);
        } catch(PersistenceException iqbe) {
            //存在外键
            throw new IqbException(WfReturnInfo.WF_DESIGN_02010104, iqbe);
        }
        
    }
    
    /**
     * 功能：挂起流程
     * 
     * @date 2016-09-20
     * @author zhaomingli
     */
    public void wfsuspend(JSONObject objs) throws IqbException, Exception {
            wfProcDesinerBiz.wfsuspend(objs);
    }
    /**
     * 功能：挂起流程
     * 
     * @date 2016-09-20
     * @author zhaomingli
     */
    public void wfactive(JSONObject objs) throws IqbException, Exception {
        wfProcDesinerBiz.wfactive(objs);
    }


    /**
     * 功能：流程模型删除
     * 
     * @date 2016-09-13
     * @author zhaomingli
     */
    @Override
    public void modeldelete(JSONObject objs) throws IqbException, Exception {
        wfProcDesinerBiz.modeldelete(objs);
    }

}
