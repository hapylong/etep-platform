package com.iqb.etep.workflow.task.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iqb.etep.workflow.task.bean.IqbWfProcDelegateBean;

public interface IqbWfProcDelegateBeanMapper{

    IqbWfProcDelegateBean selectByPrimaryKey(Integer id);
    IqbWfProcDelegateBean selectByInstAndLicens(@Param("procInstId")String procInstId,  @Param("procLicensor")String procLicensor);
    IqbWfProcDelegateBean selectByInstAndMand(@Param("procInstId")String procInstId,  @Param("mandatary")String mandatary);

    int deleteByPrimaryKey(Integer id);

    int insert(IqbWfProcDelegateBean record);

    int insertSelective(IqbWfProcDelegateBean record);

    int updateByPrimaryKeySelective(IqbWfProcDelegateBean record);

    int cancelProcDelegate(IqbWfProcDelegateBean record);
    
    int updateByPrimaryKey(IqbWfProcDelegateBean record);

    List<IqbWfProcDelegateBean> getProcInstDelegateList(@Param("procInstId")String procInstId,  @Param("mandatary")String mandatary);
}