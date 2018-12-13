package com.iqb.etep.workflow.monitor.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.workflow.monitor.bean.IqbWfMyProcBackBean;
import com.iqb.etep.workflow.monitor.bean.IqbWfProcBackBean;

@Repository
public interface IqbWfProcMonitorBeanMapper {
    /**
     * 查询用户待办流程任务列表
     * 
     * @param objs
     * @return
     */
    List<IqbWfProcBackBean> getUserToDoTasks(JSONObject objs);
    
    /**
     * 查询我的已办任务列表
     * @param objs
     * @return
     */
    List<IqbWfMyProcBackBean> selectMyProcTasks(JSONObject objs);
    
    /**
     * 流程实例汇总查询
     * @param objs
     * @return
     */
    List<IqbWfMyProcBackBean> selectProcessSummary(JSONObject objs);
    
    /**
     * 查询未完成的流程列表
     * @param objs
     * @return
     */
    List<IqbWfMyProcBackBean> selectActiveProcessList(JSONObject objs);
    
    /**
     * 通过机构代码查询未完成的流程列表
     * @param objs
     * @return
     */
    List<IqbWfMyProcBackBean> selectOrgProcessList(JSONObject objs);
    
    /**
     * 流程委托记录查询
     * @param objs
     * @return
     */
    List<IqbWfMyProcBackBean> selectProcessDelegate(JSONObject objs);
    
    /**
     * 流程委托记录查询
     * @param objs
     * @return
     */
    List<IqbWfMyProcBackBean> selectMyProcessDelegate(JSONObject objs);
}
