/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : SysStationRoleRest.java
 *
 * PURPOSE : 
 *
 * AUTHOR : leiwenyang
 *
 *
 * 创建日期: 2016年8月17日
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
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.base.CommonReturnInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.front.base.FrontBaseService;
import com.iqb.etep.sysmanage.station.bean.SysStationRole;
import com.iqb.etep.sysmanage.station.service.impl.SysStationRoleService;

/**
 * @author leiwenyang
 */
@Controller
@SuppressWarnings({ "rawtypes", "unchecked" })
@RequestMapping("/sysStationRoleRest")
public class SysStationRoleRest extends FrontBaseService{

    /** 日志处理 **/

    private static Logger logger = LoggerFactory.getLogger(SysStationRoleRest.class);

    @Autowired
    private SysStationRoleService sysStationRoleService;
    /**
     * 功能:角色信息添加
     * 
     * @param objs
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/insertSysStationRole" }, method = { RequestMethod.GET,
        RequestMethod.POST })
    public Map insertSysStationRole(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {
            logger.debug("IQB信息---开始插入角色信息数据...");
            sysStationRoleService.insertSysStationRole(objs);
            logger.info("IQB信息---插入角色信息数据完成.");

            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] {});
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }

    /**
     * 功能:删除角色信息
     * 
     * @param objs
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/deleteSysStationRole" }, method = { RequestMethod.GET,
        RequestMethod.POST })
    public Map deleteSysStationRole(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {
            logger.debug("IQB信息----开始删除数据...");
            sysStationRoleService.deleteSysStationRole(objs);
            logger.info("IQB信息---删除数据完成.");
            
            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] { });
          } catch (IqbException iqbe) {
            logger.error("IQB错误信息:" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {
            logger.error("IQB错误信息:" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }

    /**
     * 功能：修改岗位信息
     * 
     * @param objs
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/updateSysStationRole" }, method = { RequestMethod.GET,
        RequestMethod.POST })
    public Map updateSysStationRole(@RequestBody JSONObject objs, HttpServletRequest request) {

        try {
            logger.debug("IQB信息---开始修改数据...");
            sysStationRoleService.updateSysStationRole(objs);
            logger.info("IQB信息---修改数据完成.");
            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] {});
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息:" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);

        } catch (Exception e) {
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }
    /**
     * 功能:分页查询
     */
    @ResponseBody
    @RequestMapping(value = { "/getStationRole" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map getStationRole(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {
            logger.debug("IQB岗位--开始查询数据...");
            PageInfo<SysStationRole> pageInfo = sysStationRoleService.getStationRole(objs);
            logger.info("IQB岗位--查询数据完成.");
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
            linkedHashMap.put("result", pageInfo);
            return super.returnSuccessInfo(linkedHashMap);
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息:" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);

        } catch (Exception e) {
            logger.error("IQB错误信息:" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }

    /**
     * 功能:按照主键查询
     */
    @ResponseBody
    @RequestMapping(value = { "/getSysStationRoleById" }, method = { RequestMethod.GET,
        RequestMethod.POST })
    public Map getSysStationRoleById(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {
            logger.debug("IQB岗位查询－－开始查询数据...");
            SysStationRole stationRole = sysStationRoleService.getSysStationRoleById(objs);
            LinkedHashMap<String, SysStationRole> linkedHashMap = new LinkedHashMap<String, SysStationRole>();
            linkedHashMap.put("result", stationRole);
            logger.info("IQB岗位--查询数据完成.");
            return super.returnSuccessInfo(linkedHashMap);
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息:" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {
            logger.error("IQB错误信息:" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));

        }
    }

    /**
     * Description: 获取用户可选择的角色列表
     * 机构Id
     * 
     * @param
     * @return Map
     * @throws
     * @Author wangxinbang Create Date: 2016年8月29日 下午11:04:08
     */
    @ResponseBody
    @RequestMapping(value = { "/getUserEnableRole" }, method = { RequestMethod.POST })
    public Map getUserEnableRole(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {
            logger.debug("IQB获取用户可选择的角色列表－－开始查询数据...");
            List<Map<Integer,String>> list = sysStationRoleService.selectStationRoleName(objs);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
           linkedHashMap.put("result", list);
           logger.info("IQB获取用户可选择的角色列表--查询数据完成.");
           return super.returnSuccessInfo(linkedHashMap);
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息:" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {
            logger.error("IQB错误信息:" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));

        }
    }

    /**
     * Description: 保存角色信息
     * 
     * @param
     * @return Map
     * @throws
     * @Author wangxinbang Create Date: 2016年8月29日 下午11:27:11
     */
    @ResponseBody
    @RequestMapping(value = { "/saveRoleInfo" }, method = { RequestMethod.POST })
    public Map saveRoleInfo(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {
            logger.debug("IQB保存角色信息－－开始查询数据...");
            sysStationRoleService.saveRoleInfo(objs);
            logger.info("IQB保存角色信息--查询数据完成.");
            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] {});
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息:" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {
            logger.error("IQB错误信息:" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));

        }
    }

   
}
   


