/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : SysStationMenuRest.java
 *
 * PURPOSE : 
 *
 * AUTHOR : leiwenyang
 *
 *
 * 创建日期: 2016年8月18日
 * HISTORY：
 * 变更日期 
 */
package com.iqb.etep.front.rest.sys.station.role;

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
import com.iqb.etep.sysmanage.station.bean.SysStationMenu;
import com.iqb.etep.sysmanage.station.service.impl.SysStationMenuService;

/**
 * @author leiwenyang
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Controller
@RequestMapping("/stationMenuRest")
public class SysStationMenuRest extends FrontBaseService{

    /** 日志处理 **/

    private static Logger logger = LoggerFactory.getLogger(SysStationMenuRest.class);

    @Autowired
    private SysStationMenuService sysStationMenuService;
    
    /**
     * 功能:查询所有菜单
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/selectMenuUrl" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map selectMenuUrl(@RequestBody JSONObject objs,HttpServletRequest request) {
      try {
            logger.debug("IQB信息--开始查询数据...");
            List<SysStationMenu> purviewslist = sysStationMenuService.selectPurviewUrl(objs);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("result",  purviewslist);
            return super.returnSuccessInfo(linkedHashMap);
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息---查询数据完成.");
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
  
    }
}
