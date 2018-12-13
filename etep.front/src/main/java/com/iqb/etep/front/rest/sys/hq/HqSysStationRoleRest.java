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
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.base.CommonReturnInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.utils.JSONUtil;
import com.iqb.etep.front.base.FrontBaseService;
import com.iqb.etep.front.rest.sys.station.role.SysStationRoleRest;
import com.iqb.etep.sysmanage.hq.service.impl.HqSysStationRoleService;
import com.iqb.etep.sysmanage.station.bean.SysStationRole;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;

/**
 * 
 * Description: 通用系统角色接口
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年9月13日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */
@Controller
@SuppressWarnings({ "rawtypes", "unchecked" })
@RequestMapping("/hqSysStationRoleRest")
public class HqSysStationRoleRest extends FrontBaseService{
    

    /** 日志处理 **/

    private static Logger logger = LoggerFactory.getLogger(SysStationRoleRest.class);

    @Autowired
    private HqSysStationRoleService sysStationRoleService;

    /**
     * 功能:岗位信息添加
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
     * 功能:删除岗位信息管理一条记录
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
            logger.debug("IQB信息----开始删除数据..." + JSONUtil.objToJson(objs));
            Integer count = sysStationRoleService.deleteSysStationRole(objs);
            LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<String,Integer>();
            linkedHashMap.put("result", count);
            logger.info("IQB信息---删除数据完成.");
            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] {});
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
    @RequestMapping(value = { "/updateHqSysStationRole" }, method = { RequestMethod.GET,
        RequestMethod.POST })
    public Map updateHqSysStationRole(@RequestBody JSONObject objs, HttpServletRequest request) {

        try {
            logger.debug("IQB信息---开始修改数据...");
            sysStationRoleService.updateHqSysStationRole(objs);
            logger.info("IQB信息---修改数据完成.");
            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] {});
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息:" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {
            logger.error("",e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }

    /**
     * 功能:查询所有岗位名称
     * 
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/selectStationRoleName" }, method = { RequestMethod.GET,
        RequestMethod.POST })
    public Map selectStationRoleName(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {
            logger.debug("IQB岗位--开始查询数据");
            List<Map<Integer, String>> list = sysStationRoleService.selectStationRoleName(objs);
            LinkedHashMap  hashMap =  new LinkedHashMap();
            hashMap.put("result", list);
            logger.info("IQB岗位--查询数据完成");

            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] {});

        } catch (IqbException iqbe) {
            logger.error("IQB错误信息:" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        } catch (Exception e) {
            logger.error("IQB错误信息:" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }

    }

    /**
     * 功能:分页查询
     */
    @ResponseBody
    @RequestMapping(value = { "/getHqStationRole" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map getHqStationRole(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {
            logger.debug("IQB岗位--开始查询数据...");
            PageInfo<SysStationRole> pageInfo = sysStationRoleService.getHqStationRole(objs);
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
            List<Map<Integer, String>> list = sysStationRoleService.selectStationRoleName(objs);
            logger.info("IQB获取用户可选择的角色列表--查询数据完成.");
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("result", list);
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
     * 
     * Description: 根据机构id获取角色信息
     * @param
     * @return Map
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年10月19日 下午4:37:02
     */
    @ResponseBody
    @RequestMapping(value = { "/getRoleInfoByOrgIdAndHq" }, method = { RequestMethod.POST })
    public Object getRoleInfoByOrgIdAndHq(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {
            logger.debug("IQB获取用户可选择的角色列表－－开始查询数据...");
            List<Map<Integer, String>> list = sysStationRoleService.getRoleInfoByOrgIdAndHq(objs);
            logger.info("IQB获取用户可选择的角色列表--查询数据完成.");
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("result", list);
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
     * 
     * Description: 用户授权通用服务角色
     * @param
     * @return Object
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年10月20日 上午10:55:40
     */
    @ResponseBody
    @RequestMapping(value = { "/saveUserHqRoleInfo" }, method = { RequestMethod.POST })
    public Object saveUserHqRoleInfo(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {
            logger.debug("IQB用户授权通用服务角色－开始..." + objs.toString());
            sysStationRoleService.saveUserHqRoleInfo(objs);
            logger.info("IQB用户授权通用服务角色－完成..." + objs.toString());
            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] {});
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

    // 查询所有的角色名
    @ResponseBody
    @RequestMapping(value = { "/getStationNameAll" }, method = { RequestMethod.POST })
    public Map getStationNameAll(HttpServletRequest request) {

        try {
            logger.debug("IQB保存角色信息－－开始查询数据...");
            List<SysStationRole> list = sysStationRoleService.getStationNameAll();
            logger.info("IQB保存角色信息--查询数据完成.");
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("result", list);
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
     * 更新用户的stationid
     */
    @ResponseBody
    @RequestMapping(value = { "/deleteSysUserStationId" }, method = { RequestMethod.POST })
    public Map deleteSysUserStationId(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {
            logger.debug("IQB信息----开始删除相关用户分配权限数据...");
            sysStationRoleService.updateAuthorizeUserStationId(objs);
           logger.info("IQB信息---删除数据完成.");
            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] {});

        } catch (IqbException iqbe) {
            logger.error("IQB错误信息:" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {
            logger.error("IQB错误信息:" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }
    /**
     * 通用角色树
     */
    @ResponseBody
    @RequestMapping(value = { "/selectHqStation" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map selectHqStation(@RequestBody JSONObject objs,HttpServletRequest request) throws IqbException{
        
        try {                       
            logger.debug("IQB信息---开始查询通用角色...");
            List<TreeNode> list = sysStationRoleService.selectHqStation();
            logger.info("IQB信息---查询通用角色完成.");
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
            linkedHashMap.put("result",  list);
            return super.returnSuccessInfo(linkedHashMap);
        }  catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
   }
 /**
  * 已选中的角色
  */
    
    @ResponseBody
    @RequestMapping(value = { "/getHqSysStation" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map getHqSysStation(@RequestBody JSONObject objs,HttpServletRequest request) throws IqbException{
        
        try {                       
            logger.debug("IQB信息---开始查询已授权通用角色...");
            List<TreeNode> list = sysStationRoleService.getHqSysStation(objs);
            logger.info("IQB信息---查询已授权通用角色完成.");
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
            linkedHashMap.put("result",  list);
            return super.returnSuccessInfo(linkedHashMap);
        }  catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
   }
    
    /**
     * 机构授权添加角色
     */
    @ResponseBody
    @RequestMapping(value = { "/insertStationRole" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map  insertStationRole(@RequestBody JSONObject objs,HttpServletRequest request) throws IqbException{
        try {                       
            logger.debug("IQB信息---添加机构授权角色...");
            sysStationRoleService.insertStationRole(objs);
            logger.info("IQB信息---添加机构授权角色完成.");
            logger.info("IQB信息---保存机构权限完成.");
            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] {});
          
        }  catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    
    }
    
    /**
     * 
     * Description: 获取所有的角色信息
     * @param
     * @return Object
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年10月20日 上午11:54:23
     */
    @ResponseBody
    @RequestMapping(value = { "/getAllStationInfoForSelect" }, method = { RequestMethod.POST })
    public Object getAllStationInfoForSelect(@RequestBody JSONObject objs,HttpServletRequest request) throws IqbException{
        try {                       
            logger.debug("IQB信息---开始获取所有的角色信息...");
            List<Map<String, Object>> list = sysStationRoleService.getAllStationInfoForSelect();
            logger.info("IQB信息---获取所有的角色信息完成.");
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
            linkedHashMap.put("result",  list);
            return super.returnSuccessInfo(linkedHashMap);
        }  catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }

}
