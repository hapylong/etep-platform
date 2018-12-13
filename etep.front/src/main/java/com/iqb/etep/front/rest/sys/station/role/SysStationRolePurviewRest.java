/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : SysStationRolePurviewRest.java
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
import com.iqb.etep.sysmanage.station.service.impl.SysStationPurviewService;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;
import com.iqb.etep.sysmanage.sysmenu.service.ISysMenuService;

/**
 * @author leiwenyang
 */
@SuppressWarnings({"rawtypes"})
@Controller
@RequestMapping("/stationRolePurviewRest")
public class SysStationRolePurviewRest extends FrontBaseService{

    /** 日志处理 **/

    private static Logger logger = LoggerFactory.getLogger(SysStationRolePurviewRest.class);

    @Autowired
    private SysStationPurviewService stationPurviewService;
   
    @ResponseBody
    @RequestMapping(value = { "/insertPurview" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map insertPurview(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {
         
            logger.debug("IQB信息---保存用户权限开始...");
            stationPurviewService.insertPurview(objs);
            logger.info("IQB信息---保存用户权限完成.");
            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] {});
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {
            logger.error("IQB错误信息:" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }
    
    /**
     * 
     * Description: 根据角色id获取角色菜单信息
     * @param
     * @return Map
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月26日 下午1:12:14
     */
    @ResponseBody
    @RequestMapping(value = { "/getSysRolePurview" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map getSysOrganizationPurview(@RequestBody JSONObject objs,HttpServletRequest request) {
        try {                       
            logger.debug("IQB信息---开始分页查询数据...");
            List<TreeNode> list = stationPurviewService.getSysRolePurview(objs);
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

}
