package com.iqb.etep.front.rest.sys.hq;

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
import com.iqb.etep.front.base.FrontBaseService;
import com.iqb.etep.sysmanage.organization.service.impl.SysStationOrganService;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;
import com.iqb.etep.sysmanage.sysmenu.service.ISysMenuService;


@SuppressWarnings({"rawtypes"})
@Controller
@RequestMapping("/hqSysStationOrganRest")
public class HqSysStationOrganRest  extends FrontBaseService  {

    /** 日志处理 **/

    private static Logger logger = LoggerFactory.getLogger(HqSysStationOrganRest.class);
    
    @Autowired
    private SysStationOrganService stationOrganService;
    
 

   
    @ResponseBody
    @RequestMapping(value = { "/insertSysStationOrgan" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map insertSysStationOrgan(@RequestBody JSONObject objs, HttpServletRequest request){
    	 try {
    
             logger.debug("IQB信息---保存机构权限开始...");
             stationOrganService.insertSysStationOrgan(objs);
             logger.info("IQB信息---保存机构权限完成.");
             return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] {});
         } catch (IqbException iqbe) {
             logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
             return super.returnFailtrueInfo(iqbe);
         } catch (Exception e) {
             logger.error("IQB错误信息:" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
             return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
         }
    }
    
    @ResponseBody
    @RequestMapping(value = { "/getStationOrgan" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map getStationOrgan(@RequestBody JSONObject objs,HttpServletRequest request){
    	 try {                       
             logger.debug("IQB信息---开始查询已授权机构的角色...");
             List<TreeNode> list = stationOrganService.getStationOrgan(objs);
             logger.info("IQB信息---查询已授权机构的角色完成.");
             
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
}

