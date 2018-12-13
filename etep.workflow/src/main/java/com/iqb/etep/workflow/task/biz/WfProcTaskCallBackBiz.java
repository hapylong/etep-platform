package com.iqb.etep.workflow.task.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.workflow.base.WfBaseBiz;
import com.iqb.etep.workflow.base.WfReturnInfo;
import com.iqb.etep.workflow.task.bean.IqbWfProcBean;
import com.iqb.etep.workflow.task.dao.IqbWfProcBeanMapper;

@Service
public class WfProcTaskCallBackBiz extends WfBaseBiz {
	@Autowired
    IqbWfProcBeanMapper wfProcBeanMapper;
	
	/**
     * 更新业务流程表
     * @param wfProcBean
     * @throws IqbException
     */
    public void updateWfProcess(IqbWfProcBean wfProcBean) throws IqbException {
        if (null != wfProcBean) {
            setDb(0, super.MASTER);
            wfProcBeanMapper.updateWfProcBeanByInstId(wfProcBean);
        } else {
            throw new IqbException(WfReturnInfo.WF_TASK_02020201);
        }
    }
}
