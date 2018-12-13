package com.iqb.etep.workflow.task.service;

import java.util.Map;

import com.iqb.etep.common.exception.IqbException;

public interface IWfProcTaskNewCallBackService extends IWfProcTaskCallBackBaseService {
	/**
	 * 流程任务处理前的业务回调接口
	 * 
	 * @param dealType
	 *            流程处理类型
	 * @param procTaskData
	 *            流程任务数据
	 * @param procTaskSelfProps
	 *            流程任务自定义属性
	 * @param procBizData
	 *            流程业务数据
	 * @throws IqbException
	 */
	public void before(String dealType, Map<String, Object> procTaskData,
			Map<String, String> procTaskSelfProps,
			Map<String, Object> procBizData) throws IqbException;

	/**
	 * 流程任务处理完成后的业务回调接口
	 * 
	 * @param dealType
	 *            流程处理类型
	 * @param procTaskData
	 *            流程任务数据
	 * @param procTaskSelfProps
	 *            流程任务自定义属性
	 * @param procBizData
	 *            流程业务数据
	 * @throws IqbException
	 */
	public void after(String dealType, Map<String, Object> procTaskData,
			Map<String, String> procTaskSelfProps,
			Map<String, Object> procBizData) throws IqbException;
}
