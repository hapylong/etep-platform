package com.iqb.etep.workflow.task.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.iqb.etep.workflow.task.bean.IqbWfMyProcBean;

@Repository
public interface IqbWfMyProcBeanMapper {
	int deleteByPrimaryKey(Integer id);

	int deleteByInstIdandUserId(@Param("instId") Integer instId,
			@Param("userId") String userId);

	int insert(IqbWfMyProcBean record);

	int insertSelective(IqbWfMyProcBean record);

	IqbWfMyProcBean selectByPrimaryKey(Integer id);

	List<IqbWfMyProcBean> selectByProcInstId(
			@Param("procInstId") String procInstId);

	int updateByPrimaryKeySelective(IqbWfMyProcBean record);

	int updateByPrimaryKey(IqbWfMyProcBean record);

	IqbWfMyProcBean getMyProcessByUser(@Param("procInstId") String procInstId,
			@Param("userId") String userId);

	List<IqbWfMyProcBean> getMyProcessByUsers(
			@Param("procInstId") String procInstId,
			@Param("userId") List<String> userId);
}