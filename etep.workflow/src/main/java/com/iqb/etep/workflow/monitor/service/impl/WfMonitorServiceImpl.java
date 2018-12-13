/*
 * @(#) WfMonitorServiceImpl.java  1.0  August 29, 2016
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
package com.iqb.etep.workflow.monitor.service.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.utils.DatePattern;
import com.iqb.etep.common.utils.DateTools;
import com.iqb.etep.common.utils.DateUtil;
import com.iqb.etep.common.utils.StringUtil;
import com.iqb.etep.common.utils.SysUserSession;
import com.iqb.etep.workflow.base.WfReturnInfo;
import com.iqb.etep.workflow.constant.WfAttribute.ProcToken;
import com.iqb.etep.workflow.constant.WfAttribute.WfProcessAuthData;
import com.iqb.etep.workflow.constant.WfAttribute.WfProcessVariableDataAttr;
import com.iqb.etep.workflow.constant.WfAttribute.WfUserAuthType;
import com.iqb.etep.workflow.monitor.bean.IqbWfMyProcBackBean;
import com.iqb.etep.workflow.monitor.bean.IqbWfProcBackBean;
import com.iqb.etep.workflow.monitor.biz.WfMonitorBiz;
import com.iqb.etep.workflow.monitor.service.IWfMonitorService;

/**
 * Description: 工作流监控服务接口实现类
 * 
 * @author mayongming
 * @version 1.0
 * 
 *          <pre>
 * Modification History: 
 * Date             Author      Version         Description 
 * ------------------------------------------------------------------
 * 2016.08.29    mayongming       1.0        1.0 Version
 * </pre>
 */
@Service("wfMonitorService")
public class WfMonitorServiceImpl implements IWfMonitorService {
    @Autowired
    WfMonitorBiz wfMonitorBiz;
    @Autowired
    SysUserSession sysUserSession;
    
    /**
     * 查询用户已办流程任务列表
     * @param objs
     * @return
     * @throws IqbException
     */
    public PageInfo<IqbWfMyProcBackBean> getUserDoneTasks(JSONObject objs) throws IqbException {
        userAuthenticate(objs, false, false);

        parseAndUpdateDate(objs, "procCreateTimeStart", DateUtil.DATECONVERTYPE_DATESTART);
        parseAndUpdateDate(objs, "procCreateTimeEnd", DateUtil.DATECONVERTYPE_DATEEND);
        parseAndUpdateDate(objs, "procFinishedTimeStart", DateUtil.DATECONVERTYPE_DATESTART);
        parseAndUpdateDate(objs, "procFinishedTimeEnd", DateUtil.DATECONVERTYPE_DATEEND);

        return new PageInfo<IqbWfMyProcBackBean>(wfMonitorBiz.getUserDoneTasks(objs));
    }

    /**
     * 查询用户待办流程任务列表
     * @param objs
     * @return
     * @throws IqbException
     */
    public PageInfo<IqbWfProcBackBean> getUserToDoTasks(JSONObject objs) throws IqbException {
        userAuthenticate(objs, true, true);

        List<IqbWfProcBackBean> list = wfMonitorBiz.getUserToDoTasks(objs);
        return new PageInfo<IqbWfProcBackBean>(list);
    }
    
    /**
     * 查询用户待办流程任务列表
     * @param objs
     * @return
     * @throws IqbException
     */
    public PageInfo<IqbWfMyProcBackBean> getActiveProcessList(JSONObject objs) throws IqbException {
        userAuthenticate(objs, false, false);

        parseAndUpdateDate(objs, "procCreateTimeStart", DateUtil.DATECONVERTYPE_DATESTART);
        parseAndUpdateDate(objs, "procCreateTimeEnd", DateUtil.DATECONVERTYPE_DATEEND);
        parseAndUpdateDate(objs, "procCommitTimeStart", DateUtil.DATECONVERTYPE_DATESTART);
        parseAndUpdateDate(objs, "procCommitTimeEnd", DateUtil.DATECONVERTYPE_DATEEND);

        return new PageInfo<IqbWfMyProcBackBean>(wfMonitorBiz.getActiveProcessList(objs));
    }
    
    /**
     * 查询用户待办流程任务列表
     * @param objs
     * @return
     * @throws IqbException
     */
    public PageInfo<IqbWfMyProcBackBean> getOrgProcessList(JSONObject objs) throws IqbException {
        userAuthenticate(objs, true, false);

        parseAndUpdateDate(objs, "procCreateTimeStart", DateUtil.DATECONVERTYPE_DATESTART);
        parseAndUpdateDate(objs, "procCreateTimeEnd", DateUtil.DATECONVERTYPE_DATEEND);
        parseAndUpdateDate(objs, "procCommitTimeStart", DateUtil.DATECONVERTYPE_DATESTART);
        parseAndUpdateDate(objs, "procCommitTimeEnd", DateUtil.DATECONVERTYPE_DATEEND);

        return new PageInfo<IqbWfMyProcBackBean>(wfMonitorBiz.getOrgProcessList(objs));
    }
    
    /**
     * 流程实例汇总查询
     * @param objs
     * @return
     * @throws IqbException
     */
    public PageInfo<IqbWfMyProcBackBean> geyProcessSummary(JSONObject objs) throws IqbException {
        userAuthenticate(objs, true, false);

        parseAndUpdateDate(objs, "procCreateTimeStart", DateUtil.DATECONVERTYPE_DATESTART);
        parseAndUpdateDate(objs, "procCreateTimeEnd", DateUtil.DATECONVERTYPE_DATEEND);
        parseAndUpdateDate(objs, "procFinishedTimeStart", DateUtil.DATECONVERTYPE_DATESTART);
        parseAndUpdateDate(objs, "procFinishedTimeEnd", DateUtil.DATECONVERTYPE_DATEEND);
        
        return new PageInfo<IqbWfMyProcBackBean>(wfMonitorBiz.geyProcessSummary(objs));
    }
    
    /**
     * 流程委托查询
     * @param objs
     * @return
     * @throws IqbException
     */
    public PageInfo<IqbWfMyProcBackBean> getProcessDelegateList(JSONObject objs)
        throws IqbException {
        userAuthenticate(objs, true, false);

        parseAndUpdateDate(objs, "procCreateTimeStart", DateUtil.DATECONVERTYPE_DATESTART);
        parseAndUpdateDate(objs, "procCreateTimeEnd", DateUtil.DATECONVERTYPE_DATEEND);
        parseAndUpdateDate(objs, "procFinishedTimeStart", DateUtil.DATECONVERTYPE_DATESTART);
        parseAndUpdateDate(objs, "procFinishedTimeEnd", DateUtil.DATECONVERTYPE_DATEEND);

        return new PageInfo<IqbWfMyProcBackBean>(wfMonitorBiz.getProcessDelegateList(objs));
    }
    
    /**
     * 获取指定用户的流程委托列表
     * @param objs
     * @return
     * @throws IqbException
     */
    public PageInfo<IqbWfMyProcBackBean> getMyProcDelegateList(JSONObject objs) throws IqbException {
        userAuthenticate(objs, true, false);

        return new PageInfo<IqbWfMyProcBackBean>(wfMonitorBiz.getMyProcDelegateList(objs));
    }
    
    /**
     * 根据指定格式解析时间字符串，并转化成int类型更新对应数据
     * @param objs
     * @param key     
     * @param type
     * @throws IqbException
     */
    private void parseAndUpdateDate(JSONObject objs, String key, int type) throws IqbException {
        if (objs.containsKey(key) && !StringUtil.isNull(objs.getString(key))) {
            String value = objs.getString(key);
            objs.put(key, DateUtil.getTime(value, DatePattern.DATEPARTTEN_YYYYMMDD_TYPE3, type));
        }
    }

    /**
     * 用户认证，未指定认证方式，或者认证不通过的请求抛出IqbException，不能进行后续流程处理
     * 
     * @param objs
     *            请求Json数据
     * @param checkOrg
     *            是否强制校验机构，在使用Token方式时有效
     * @param checkRole
     *            是否强制校验角色，在使用Token方式时有效
     * @throws IqbException
     */
    private void userAuthenticate(JSONObject objs, boolean checkOrg, boolean checkRole)
        throws IqbException {
        String authType = objs.getString(WfProcessAuthData.PROC_AUTHTYPE);
        String procTaskUser = null;
        String procOrgcode = null;
        String procTaskRole = null;
        List<String> roleCodes = new ArrayList<String>();

        if (StringUtil.isNull(authType)) {
            throw new IqbException(WfReturnInfo.WF_TASK_02020004); 
        } 
        
        switch (authType) {
            case WfUserAuthType.PROC_AUTH_TOKEN:
                String tokenUser = objs.getString(WfProcessAuthData.PROC_TOKENUSER);
                String tokenPass = objs.getString(WfProcessAuthData.PROC_TOKENPASS);
                procTaskUser = objs.getString(WfProcessAuthData.PROC_TASKUSER);
                procTaskRole = objs.getString(WfProcessAuthData.PROC_TASKROLE);
                procOrgcode = objs.getString(WfProcessVariableDataAttr.PROC_ORGCODE);
                
                if (StringUtil.isNull(procTaskUser)
                    || (checkOrg && StringUtil.isNull(procOrgcode))
                    || (checkRole && StringUtil.isNull(procTaskRole))
                    || !matchToken(tokenUser, tokenPass)) {
                    throw new IqbException(WfReturnInfo.WF_TASK_02020005);
                }
                
                // 创建操作角色列表，并放到流程变量数据中
                roleCodes.add(procTaskRole);
                objs.put(WfProcessAuthData.PROC_TASKROLES, roleCodes);
                
                break;
            case WfUserAuthType.PROC_AUTH_SESSION:
                procTaskUser = sysUserSession.getUserCode();
                procOrgcode = sysUserSession.getOrgCode();
                roleCodes = sysUserSession.getStationCodeList();
                
                if (StringUtil.isNull(procTaskUser) || StringUtil.isNull(procOrgcode)
                    || roleCodes == null || roleCodes.isEmpty()) {
                    throw new IqbException(WfReturnInfo.WF_TASK_02020006);
                }

                objs.put(WfProcessAuthData.PROC_TASKUSER, procTaskUser);
                objs.put(WfProcessVariableDataAttr.PROC_ORGCODE, procOrgcode);
                objs.put(WfProcessAuthData.PROC_TASKROLES, roleCodes);
                
                break;
            default:
                throw new IqbException(WfReturnInfo.WF_TASK_02020004);   
        }
    }
    
    /**
     * 检查token认证是否合法
     * @param tokenUser
     * @param tokenPass
     * @return
     */
    private boolean matchToken(String tokenUser, String tokenPass) {
        boolean isMatched = false;

        for (ProcToken token : ProcToken.values()) {
            if (token.getTokenUser().equals(tokenUser) && token.getTokenPass().equals(tokenPass)) {
                return true;
            }
        }

        return isMatched;
    }

    /**
     * 导出流程实例汇总
     * @param params
     * @return  
     * @throws Exception 
     * @Author haojinlong 
     * Create Date: 2017年11月22日
     */
    @Override
    public String exportProcessSummary(JSONObject objs, HttpServletResponse response) throws Exception {
        try {
            userAuthenticate(objs, true, false);

            parseAndUpdateDate(objs, "procCreateTimeStart", DateUtil.DATECONVERTYPE_DATESTART);
            parseAndUpdateDate(objs, "procCreateTimeEnd", DateUtil.DATECONVERTYPE_DATEEND);
            parseAndUpdateDate(objs, "procFinishedTimeStart", DateUtil.DATECONVERTYPE_DATESTART);
            parseAndUpdateDate(objs, "procFinishedTimeEnd", DateUtil.DATECONVERTYPE_DATEEND);
            // 获取流程实例汇总列表
            List<IqbWfMyProcBackBean> procList = wfMonitorBiz.getProcessSummary(objs);
            
            // 2.导出excel表格
            HSSFWorkbook workbook = this.exportShouldDebtDetailList(procList);
            response.setContentType("application/vnd.ms-excel");
            String fileName = "attachment;filename=ProcessSummary-" + DateTools.getYmdhmsTime() + ".xls";
            response.setHeader("Content-disposition", fileName);
            OutputStream ouputStream = response.getOutputStream();
            workbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (Exception e) {
            throw e;
        }
        return "success";
    }
 // 导出
    private HSSFWorkbook exportShouldDebtDetailList(List<IqbWfMyProcBackBean> list) {
        String[] excelHeader =
        {"序号", "流程名称", "业务ID", "流程创建时间", "流程完成时间", "流程摘要", "任务名称", "提交人", "处理人/角色",
                "受托人", "任务到达时间", "任务受理时间", "流程状态"};
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("流程汇总查询页");
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));

        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
        }

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            IqbWfMyProcBackBean sdd = list.get(i);
            row.createCell(0).setCellValue(i + 1);// 序号
            row.createCell(1).setCellValue(sdd.getProcName());// 流程名称
            row.createCell(2).setCellValue(sdd.getProcBizId());// 业务id
            row.createCell(3).setCellValue(sdd.getProcCreatetime());// 手流程创建时间
            row.createCell(4).setCellValue(sdd.getProcEndtime()!=null ? sdd.getProcEndtime():"");// 流程完成时间
            row.createCell(5).setCellValue(sdd.getProcMemo()!=null ? sdd.getProcMemo():"");// 流程摘要
            row.createCell(6).setCellValue(sdd.getProcTaskname()!=null ? sdd.getProcTaskname():"");// 任务名称
            row.createCell(7).setCellValue(sdd.getProcTaskCommitter()!=null ? sdd.getProcTaskCommitter():"");// 提交人
            row.createCell(8).setCellValue(sdd.getProcTaskAssignee()!=null ? sdd.getProcTaskAssignee():"");// 处理人/角色
            row.createCell(9).setCellValue(sdd.getProcLicensor()!=null ? sdd.getProcLicensor():"");// 受托人
            row.createCell(10).setCellValue(sdd.getProcTaskCommittime()!=null ? sdd.getProcTaskCommittime():"");// 任务到达时间
            row.createCell(11).setCellValue(sdd.getProcTaskAssigntime() !=null ?sdd.getProcTaskAssigntime():""); // 任务受理时间
            row.createCell(12).setCellValue(sdd.getProcStatusName() !=null ? sdd.getProcStatusName():"");// 流程状态
        }

        // 设置列宽
        for (int j = 0; j < excelHeader.length; j++) {
            sheet.autoSizeColumn(j);
        }

        return wb;
    }
}
