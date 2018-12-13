package com.iqb.etep.front.rest.sys.sysmenu;

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
import com.iqb.etep.sysmanage.organization.service.impl.SysOrganizationService;
import com.iqb.etep.sysmanage.sysmenu.bean.SysMenu;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;
import com.iqb.etep.sysmanage.sysmenu.service.impl.SysMenuService;

@Controller
@SuppressWarnings({"rawtypes"})
@RequestMapping("/sysMenuRest")
public class SysMenuRest extends FrontBaseService {
    
    /** 日志处理 **/
    private static Logger logger = LoggerFactory.getLogger(SysMenuRest.class);
    
    @Autowired
    private SysMenuService sysMenuService;
    
    @Autowired
    private SysOrganizationService sysOrganizationService;
    @ResponseBody
    @RequestMapping(value = { "/getSysMenu" }, method = {RequestMethod.GET,RequestMethod.POST })
    public Map getSysMenu(@RequestBody JSONObject objs,HttpServletRequest request) {
        try {           
            logger.debug("IQB信息---开始分页查询数据...");
            List<TreeNode> list = sysMenuService.selectSysMenuForTree(objs);
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
    @RequestMapping(value = { "/insertSysMenu" }, method = {RequestMethod.GET,RequestMethod.POST })
    public Map insertSysMenu(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {           
            logger.debug("IQB信息---开始插入数据...");
            sysMenuService.insertSysMenu(objs);
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
    @RequestMapping(value = { "/updateSysMenu" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map updateSysMenu(@RequestBody JSONObject objs, HttpServletRequest request) { 
        try {            
            logger.debug("IQB信息---开始更新数据...");
            sysMenuService.updateSysMenuById(objs);
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
    @RequestMapping(value = { "/deleteSysMenu" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map deleteSysMenu(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {            
            logger.debug("IQB信息---开始删除数据...");
            sysMenuService.deleteSysMenuById(objs);
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
    @RequestMapping(value = { "/getSysMenuById" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map getSysMenuById(@RequestBody JSONObject objs, HttpServletRequest request) {   
        try {            
            logger.debug("IQB信息---开始根据ID查询数据...");
            SysMenu bean = sysMenuService.selectSysMenuById(objs);
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
    
    /**
     * 
     * Description: 根据角色获取菜单
     * @param
     * @return Map
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月29日 下午5:19:14
     */
    @ResponseBody
    @RequestMapping(value = { "/getSysMenuListByRole" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map getSysMenuListByRole() {   
        try {            
            logger.debug("IQB信息---开始根据角色获取菜单查询数据...");
            List<TreeNode> menuList = sysMenuService.getSysMenuListByRole();
            logger.info("IQB信息---根据角色获取菜单数据完成.");    
            
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
            linkedHashMap.put("result",  menuList);
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
    @RequestMapping(value = { "/getSysOrganationMenu" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map getSysOrganationMenu(@RequestBody JSONObject objs, HttpServletRequest request){
        try {            
            logger.debug("IQB信息---开始根据ID查询数据...");
            List<TreeNode>  listree = sysMenuService.selectSysMenuForTree(objs);
            logger.info("IQB信息---根据ID查询数据完成.");    
              LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
            linkedHashMap.put("result",  listree);
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
