package com.iqb.etep.workflow.task.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.workflow.task.bean.IqbWfProcTaskBean;
import com.iqb.etep.workflow.task.bean.IqbWfProcTaskHistoryBean;

public interface IqbWfProcTaskBeanMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByPTaskCode(@Param("taskId") Integer taskId);

    int insert(IqbWfProcTaskBean record);

    int insertSelective(IqbWfProcTaskBean record);

    IqbWfProcTaskBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IqbWfProcTaskBean record);

    int updateByPrimaryKey(IqbWfProcTaskBean record);

    int updateByInstIdSelective(IqbWfProcTaskBean record);

    int updateTaskByTaskid(IqbWfProcTaskBean record);
    
    int deleteProcessTask(IqbWfProcTaskBean record);
    
    int cancelProcessTasks(IqbWfProcTaskBean record);
    
    int updateForUnclaim(IqbWfProcTaskBean record);

    int updateForDelete(IqbWfProcTaskBean record);
    
    List<IqbWfProcTaskBean> getParallelProcTask(@Param("procInstId") String procInstId,
        @Param("taskCode") List<String> taskCode);

    IqbWfProcTaskBean findTaskByTaskId(@Param("taskId") String taskId,
        @Param("taskStatus") List<String> taskStatus);

    List<IqbWfProcTaskBean> getProcTasksByParent(@Param("taskId") String taskId);
    
    List<IqbWfProcTaskBean> getProcessTasks(@Param("procInstId") String procInstId,
        @Param("taskStatus") List<String> taskStatus);

    IqbWfProcTaskBean getFinishedTask(@Param("procInstId") String procInstId,
        @Param("taskCode") String taskCode);
    
    IqbWfProcTaskBean getActiveTask(@Param("procInstId") String procInstId,
        @Param("taskCode") String taskCode, @Param("taskStatus") List<String> taskStatus);

    IqbWfProcTaskBean getStartTask(@Param("procInstId") String procInstId);
    
    int getTaskCount(@Param("taskid")String taskid);
    
    int getDelegatedTaskCount(@Param("procInstId") String procInstId,
        @Param("mandatary") String mandatary);

    int getActiveTaskCount(@Param("procInstId")String procInstId);
    
    int endProcessTasks(IqbWfProcTaskBean wfProcTaskBean);
    
    /**
     * 查询流程审批历史
     * @param objs
     * @return
     */
    List<IqbWfProcTaskHistoryBean> selectApprovedHistory(JSONObject objs);
}