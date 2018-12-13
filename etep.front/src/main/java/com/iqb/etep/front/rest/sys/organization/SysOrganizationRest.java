package com.iqb.etep.front.rest.sys.organization;

import java.util.LinkedHashMap;
import java.util.List;
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
import com.iqb.etep.common.base.CommonReturnInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.utils.JSONUtil;
import com.iqb.etep.front.base.FrontBaseService;
import com.iqb.etep.sysmanage.organization.bean.SysOrganizationInfo;
import com.iqb.etep.sysmanage.organization.service.impl.SysOrganizationService;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;

@Controller
@SuppressWarnings({"rawtypes"})
@RequestMapping("/sysOrganizationRest")
public class SysOrganizationRest extends FrontBaseService {
	
	/** 日志处理 **/
	private static Logger logger = LoggerFactory.getLogger(SysOrganizationRest.class);
	

	@Autowired
	private SysOrganizationService sysOrganizationService;
	
	@ResponseBody
    @RequestMapping(value = { "/getSysOrganization" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map getSysOrganization(@RequestBody JSONObject objs,HttpServletRequest request) {
        try {                       
            logger.debug("IQB信息---开始分页查询数据...");
            List<TreeNode> list = sysOrganizationService.selectSysOrganizationInfoForTree(objs);
            logger.info("IQB信息---分页查询数据完成.");
            
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
            linkedHashMap.put("result",  list);
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
    @RequestMapping(value = { "/getSysOrganizationPurview" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map getSysOrganizationPurview(@RequestBody JSONObject objs,HttpServletRequest request) {
        try {                       
            logger.debug("IQB信息---开始分页查询数据...");
            List<Integer> list = sysOrganizationService.selectSysOrganizationPurview(objs);
            logger.info("IQB信息---分页查询数据完成.");
            
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
            linkedHashMap.put("result",  list);
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
    @RequestMapping(value = { "/insertSysOrganizationPurview" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map insertSysOrganizationPurview(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {           
            logger.debug("IQB信息---开始插入数据...");
            sysOrganizationService.insertSysOrganizationPurview(objs);
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
    @RequestMapping(value = { "/insertSysOrganization" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map insertSysOrganization(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {           
            logger.debug("IQB信息---开始插入数据...");
            sysOrganizationService.insertSysOrganizationInfo(objs);
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
    @RequestMapping(value = { "/updateSysOrganization" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map updateSysOrganization(@RequestBody JSONObject objs, HttpServletRequest request) { 
        try {            
            logger.debug("IQB信息---开始更新数据...");
            sysOrganizationService.updateSysOrganizationInfoById(objs);
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
    @RequestMapping(value = { "/deleteSysOrganization" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map deleteSysOrganization(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {            
            logger.debug("IQB信息---开始删除数据...");
            sysOrganizationService.deleteSysOrganizationInfoById(objs);
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
    @RequestMapping(value = { "/getSysOrganizationById" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map getSysOrganizationById(@RequestBody JSONObject objs, HttpServletRequest request) {   
        try {            
            logger.debug("IQB信息---开始根据ID查询数据...");
            SysOrganizationInfo bean = sysOrganizationService.selectSysOrganizationInfoById(objs);
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
    
    @ResponseBody
    @RequestMapping(value = { "/getOrgTpye" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map getOrgTpye(@RequestBody JSONObject objs, HttpServletRequest request) {   
        try {            
            logger.debug("IQB信息---开始根据ID查询数据...");
            List<Map<String, Object>> list = sysOrganizationService.selectOrgTpye(objs);
            logger.info("IQB信息---根据ID查询数据完成.");    
            
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
            linkedHashMap.put("result",  list);
            return super.returnSuccessInfo(linkedHashMap);
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }
    
    /**
     * 
     * Description: 获取所有的机构信息
     * @param
     * @return Map
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月30日 上午11:09:58
     */
    @ResponseBody
    @RequestMapping(value = { "/getAllOrgInfo" }, method = {RequestMethod.POST })
    public Map getAllOrgInfo(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {            
            logger.debug("IQB信息---获取所有的机构信息...");
            List<Map<Integer, String>> map = sysOrganizationService.getAllOrgInfo();
            logger.info("IQB信息---获取所有的机构信息...");    
            
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
            linkedHashMap.put("result",  map);
            return super.returnSuccessInfo(linkedHashMap);
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }

	/**
	 * 查询部门信息
	 */
    @ResponseBody
    @RequestMapping(value={"/selectOrganizationDept"},method={RequestMethod.POST})
    public Map selectOrganizationDept(@RequestBody JSONObject objs,HttpServletRequest request){
        try{
            logger.debug("IQB部门信息查询开始...");
            List<Map<Integer, String>> listDepts = sysOrganizationService.selectOrganizationDept(objs);
            logger.info("IQB部门信息查询完成.");
            
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String,Object>();
            linkedHashMap.put("result",  listDepts);
       
            return super.returnSuccessInfo(linkedHashMap);
        } catch(IqbException iqbe){
            logger.error("IQB错误信息:"+iqbe.getRetInfo().getRetFactInfo(),iqbe);
           return super.returnFailtrueInfo(iqbe);
        } catch(Exception e){
            logger.error("IQB错误信息:"+CommonReturnInfo.BASE00000001.getRetFactInfo(),e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }
    
    /**
     * 
     * Description: 将机构信息推动至crm
     * @param
     * @return Object
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月22日 上午11:03:16
     */
    @ResponseBody
    @RequestMapping(value = "/pushOrgInfoToCRM", method = RequestMethod.POST)
    public Object pushOrgInfoToCRM(@RequestBody JSONObject objs, HttpServletRequest request){
        try {
            logger.info("IQB调试信息---将机构信息推动至crm开始" + JSONUtil.objToJson(objs));
            this.sysOrganizationService.pushOrgInfoToCRM(objs);
            logger.info("IQB调试信息---将机构信息推动至crm结束");
            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] { });
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }
   
  
}