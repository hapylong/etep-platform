package com.iqb.etep.front.rest.sys.dept;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.base.CommonReturnInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.front.base.FrontBaseService;
import com.iqb.etep.sysmanage.dept.bean.SysOrganizationDeptInfo;
import com.iqb.etep.sysmanage.dept.service.impl.SysOrganizationDeptService;

@Controller
@SuppressWarnings({"rawtypes"})
@RequestMapping("/sysOrganizationDeptRest")
public class SysOrganizationDeptRest extends FrontBaseService {
    
    /** 日志处理 **/
    private static Logger logger = LoggerFactory.getLogger(SysOrganizationDeptRest.class);
    
    @Autowired
    private SysOrganizationDeptService sysOrganizationDeptService;
    
    @ResponseBody
    @RequestMapping(value = { "/getSysOrganizationDept" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map getSysOrganizationDept(@RequestBody JSONObject objs,HttpServletRequest request) {
        try {                       
            logger.debug("IQB信息---开始分页查询数据...");
            PageInfo<SysOrganizationDeptInfo> pageInfo = sysOrganizationDeptService.selectSysOrganizationDeptInfoForGrid(objs);
            logger.info("IQB信息---分页查询数据完成.");
            
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
            linkedHashMap.put("result",  pageInfo);
            return super.returnSuccessInfo(linkedHashMap);
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }
    
    @ResponseBody
    @RequestMapping(value = { "/insertSysOrganizationDept" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map insertSysOrganizationDept(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {           
            logger.debug("IQB信息---开始插入数据...");
            sysOrganizationDeptService.insertSysOrganizationDeptInfo(objs);
            logger.info("IQB信息---插入数据完成.");                 
            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] { });
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }
    
    @ResponseBody
    @RequestMapping(value = { "/updateSysOrganizationDept" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map updateSysOrganizationDept(@RequestBody JSONObject objs, HttpServletRequest request) { 
        try {            
            logger.debug("IQB信息---开始更新数据...");
            sysOrganizationDeptService.updateSysOrganizationDeptInfoById(objs);
            logger.info("IQB信息---更新数据完成.");     
            
            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] { });
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }
    
    @ResponseBody
    @RequestMapping(value = { "/deleteSysOrganizationDept" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map deleteSysOrganizationDept(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {            
            logger.debug("IQB信息---开始删除数据...");
            sysOrganizationDeptService.deleteSysOrganizationDeptInfoById(objs);
            logger.info("IQB信息---删除数据完成.");     
            
            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] { });
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }
    
    @ResponseBody
    @RequestMapping(value = { "/getSysOrganizationDeptById" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map getSysOrganizationDeptById(@RequestBody JSONObject objs, HttpServletRequest request) {   
        try {            
            logger.debug("IQB信息---开始根据ID查询数据...");
            SysOrganizationDeptInfo bean = sysOrganizationDeptService.selectSysOrganizationDeptInfoById(objs);
            logger.info("IQB信息---根据ID查询数据完成.");    
            
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
            linkedHashMap.put("result",  bean);
            return super.returnSuccessInfo(linkedHashMap);
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }

}
