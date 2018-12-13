package com.iqb.etep.workflow.task.service.impl;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.utils.DateTools;
import com.iqb.etep.common.utils.JSONUtil;
import com.iqb.etep.common.utils.StringUtil;
import com.iqb.etep.workflow.base.WfReturnInfo;
import com.iqb.etep.workflow.task.bean.IqbWfProcBean;
import com.iqb.etep.workflow.task.biz.WfProcTaskCallBackBiz;
import com.iqb.etep.workflow.task.service.IWfProcTaskCallBackService;

@Service
public class WfProcTaskCallBackDefaultService implements
        IWfProcTaskCallBackService {
    @Autowired
    WfProcTaskCallBackBiz wfProcTaskCallBackBiz;

    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.REPEATABLE_READ)
    public void before(String dealType, Map<String, Object> procBizData)
            throws IqbException {
        System.out.println("process task deal type:" + dealType);
        IqbWfProcBean bean1 = new IqbWfProcBean();
        IqbWfProcBean bean2 = new IqbWfProcBean();

        try {
            String bizId = (String) procBizData.get(PROC_BIZID);
            String bizType = (String) procBizData.get(PROC_BIZTYPE);
            String bizMemo = (String) procBizData.get(PROC_BIZMEMO);
            String orgCode = (String) procBizData.get(PROC_ORGCODE);

            if (StringUtil.isNull(bizId)) {
                procBizData.put(PROC_BIZID, UUID.randomUUID().toString());
            }

            if (StringUtil.isNull(bizType)) {
                procBizData.put(PROC_BIZTYPE, "1101");
            }

            if (StringUtil.isNull(bizMemo)) {
                procBizData.put(PROC_BIZMEMO, "测试流程验证");
            }

            if (StringUtil.isNull(orgCode)) {
                procBizData.put(PROC_ORGCODE, "1001001");
            }

            if (procBizData != null) {
                System.out.println(JSONUtil.objToJson(procBizData));
                bean1.setProcInstId("068144cb-ea7f-11e6-8e2c-80a589ac6c46");
                bean1.setProcMemo("测试流程验证:"
                        + (String) procBizData.get(PROC_INSTANCEID));
                bean2.setProcInstId("db33e41f-3b76-11e7-9b54-80a589ac6c46");
                bean2.setProcMemo("测试流程验证:"
                        + (String) procBizData.get(PROC_INSTANCEID));
                bean2.setProcEndtime(DateTools.getCurrTime());
                wfProcTaskCallBackBiz.updateWfProcess(bean1);
                wfProcTaskCallBackBiz.updateWfProcess(bean2);

                if ("2".equals((String) procBizData.get("procCallback"))) {
                    throw new Exception();
                }
            }
        } catch (IqbException iqbe) {
            throw iqbe;
        } catch (Exception e) {
            throw new IqbException(WfReturnInfo.WF_COMM_02000001, e);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.REPEATABLE_READ)
    public void after(String dealType, Map<String, Object> procBizData)
            throws IqbException {
        System.out.println("process task deal type:" + dealType);
        IqbWfProcBean bean1 = new IqbWfProcBean();
        IqbWfProcBean bean2 = new IqbWfProcBean();

        try {
            String bizId = (String) procBizData.get(PROC_BIZID);
            String bizType = (String) procBizData.get(PROC_BIZTYPE);
            String bizMemo = (String) procBizData.get(PROC_BIZMEMO);
            String orgCode = (String) procBizData.get(PROC_ORGCODE);

            if (StringUtil.isNull(bizId)) {
                procBizData.put(PROC_BIZID, UUID.randomUUID().toString());
            }

            if (StringUtil.isNull(bizType)) {
                procBizData.put(PROC_BIZTYPE, "1101");
            }

            if (StringUtil.isNull(bizMemo)) {
                procBizData.put(PROC_BIZMEMO, "测试流程验证");
            }

            if (StringUtil.isNull(orgCode)) {
                procBizData.put(PROC_ORGCODE, "1001001");
            }

            if (procBizData != null) {
                System.out.println(JSONUtil.objToJson(procBizData));
                bean1.setProcInstId("000182b4-3b79-11e7-b453-80a589ac6c46");
                bean1.setProcMemo("测试流程验证:"
                        + (String) procBizData.get(PROC_INSTANCEID));
                bean2.setProcInstId("473b69ea-fe55-11e6-a5dc-80a589ac6c46");
                bean2.setProcMemo("测试流程验证:"
                        + (String) procBizData.get(PROC_INSTANCEID));
                bean2.setProcEndtime(DateTools.getCurrTime());
                wfProcTaskCallBackBiz.updateWfProcess(bean1);
                wfProcTaskCallBackBiz.updateWfProcess(bean2);

                if ("3".equals((String) procBizData.get("procCallback"))) {
                    throw new Exception();
                }
            }
        } catch (IqbException iqbe) {
            throw iqbe;
        } catch (Exception e) {
            throw new IqbException(WfReturnInfo.WF_COMM_02000001, e);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.REPEATABLE_READ)
    @Override
    public void parallelAfter(String dealType, Map<String, Object> procBizData) throws IqbException {
        System.out.println("process task deal type:" + dealType);
        IqbWfProcBean bean1 = new IqbWfProcBean();
        IqbWfProcBean bean2 = new IqbWfProcBean();

        try {
            String bizId = (String) procBizData.get(PROC_BIZID);
            String bizType = (String) procBizData.get(PROC_BIZTYPE);
            String bizMemo = (String) procBizData.get(PROC_BIZMEMO);
            String orgCode = (String) procBizData.get(PROC_ORGCODE);

            if (StringUtil.isNull(bizId)) {
                procBizData.put(PROC_BIZID, UUID.randomUUID().toString());
            }

            if (StringUtil.isNull(bizType)) {
                procBizData.put(PROC_BIZTYPE, "1101");
            }

            if (StringUtil.isNull(bizMemo)) {
                procBizData.put(PROC_BIZMEMO, "测试流程验证");
            }

            if (StringUtil.isNull(orgCode)) {
                procBizData.put(PROC_ORGCODE, "1001001");
            }

            if (procBizData != null) {
                System.out.println(JSONUtil.objToJson(procBizData));
                bean1.setProcInstId("000182b4-3b79-11e7-b453-80a589ac6c46");
                bean1.setProcMemo("测试流程验证:"
                        + (String) procBizData.get(PROC_INSTANCEID));
                bean2.setProcInstId("473b69ea-fe55-11e6-a5dc-80a589ac6c46");
                bean2.setProcMemo("测试流程验证:"
                        + (String) procBizData.get(PROC_INSTANCEID));
                bean2.setProcEndtime(DateTools.getCurrTime());
                wfProcTaskCallBackBiz.updateWfProcess(bean1);
                wfProcTaskCallBackBiz.updateWfProcess(bean2);

                if ("3".equals((String) procBizData.get("procCallback"))) {
                    throw new Exception();
                }
            }
        } catch (IqbException iqbe) {
            throw iqbe;
        } catch (Exception e) {
            throw new IqbException(WfReturnInfo.WF_COMM_02000001, e);
        }

    }
}
