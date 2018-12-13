package com.iqb.etep.workflow.task.dao;

import org.springframework.stereotype.Repository;

import com.iqb.etep.workflow.task.bean.IqbWfProcBean;

@Repository
public interface IqbWfProcBeanMapper {
    int deleteByPrimaryKey(String procInstId);
    int insert(IqbWfProcBean record);
    int insertSelective(IqbWfProcBean record);
    IqbWfProcBean selectByPrimaryKey(String procInstId);
    //更新业务流程表通过流程实例id
    int updateByPrimaryKeySelective(IqbWfProcBean record);
    int updateByPrimaryKey(IqbWfProcBean record);
    //更新业务流程表通过流程id
    int updateWfProcBeanByProcId(IqbWfProcBean record);
    int updateWfProcBeanByInstId(IqbWfProcBean record);
}