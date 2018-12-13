/*
 * @(#) WfProcTaskBiz.java  1.0  Dec 13, 2016
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
package com.iqb.etep.workflow.notify.biz;

import java.util.List;

import org.springframework.stereotype.Component;

import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.workflow.base.WfBaseBiz;
import com.iqb.etep.workflow.task.bean.IqbWfMyProcBean;
import com.iqb.etep.workflow.task.bean.IqbWfProcBean;
import com.iqb.etep.workflow.task.bean.IqbWfProcTaskBean;

/**
 * Description: 工作流流程任务通知实现接口
 * 
 * @author mayongming
 * @version 1.0
 * 
 *          <pre>
 * Modification History: 
 * Date             Author      Version         Description 
 * ------------------------------------------------------------------
 * 2016.12.13    mayongming       1.0        1.0 Version
 * </pre>
 */
@Component
public class WfProcTaskNotifyBiz extends WfBaseBiz{

    /**
     * 流程任务通知发布处理
     * 
     * @param wfProc
     *            流程数据
     * @param wfProcTasks
     *            流程任务数据
     * @throws IqbException
     */
    public void notify(IqbWfProcBean wfProc, List<IqbWfProcTaskBean> wfProcTasks)
        throws IqbException {
        if (wfProcTasks != null) {
            for (IqbWfProcTaskBean wfProcTask : wfProcTasks) {
                System.out.println("流程名称：" + wfProc.getProcName());
                System.out.println("流程业务ID：" + wfProc.getProcBizid());
                System.out.println("流程机构代码：" + wfProc.getProcOrgcode());
                System.out.println("流程业务摘要：" + wfProc.getProcMemo());
                System.out.println("流程任务名称：" + wfProcTask.getProcCtaskname());
                System.out.println("流程提交人：" + wfProcTask.getProcTaskCommitter());
                System.out.println("流程提交时间：" + wfProcTask.getProcTaskCommittime());
            }
        } else {
            System.out.println("没有流程任务数据");
        }
    }
    
    /**
     * 流程任务通知发布服务接口，通知该流程所有处理用户流程已经处理结束。
     * @param wfProc        流程数据
     * @param myProcess     该流程所有处理用户
     * @throws IqbException
     */
    public void notifyAll(IqbWfProcBean wfProc, List<IqbWfMyProcBean> myProcesses)
        throws IqbException {
        if (myProcesses != null) {
            for (IqbWfMyProcBean myProcess : myProcesses) {
                System.out.println("流程名称：" + wfProc.getProcName());
                System.out.println("流程业务ID：" + wfProc.getProcBizid());
                System.out.println("流程机构代码：" + wfProc.getProcOrgcode());
                System.out.println("流程业务摘要：" + wfProc.getProcMemo());
                System.out.println("流程通知用户：" + myProcess.getProcUser());
                System.out.println("流程完成时间：" + wfProc.getProcEndtime());
            }
        } else {
            System.out.println("没有流程任务数据");
        }
    }
}
