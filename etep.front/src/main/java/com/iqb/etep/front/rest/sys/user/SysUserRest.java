/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : SysUserRest.java
 *
 * PURPOSE : 
 *
 * AUTHOR : leiwenyang
 *
 *
 * 创建日期: 2016年8月12日
 * HISTORY：
 * 变更日期 
 */
package com.iqb.etep.front.rest.sys.user;

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
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.front.base.FrontBaseService;
import com.iqb.etep.sysmanage.user.bean.SysUser;
import com.iqb.etep.sysmanage.user.service.impl.SysUserService;

/**
 * @author leiwenyang
 */
@Controller
@SuppressWarnings({ "rawtypes" })
@RequestMapping("/SysUserRest")
public class SysUserRest extends FrontBaseService{

    private static Logger logger = LoggerFactory.getLogger(SysUserRest.class);

    @Autowired
    SysUserService sysUserService;

    /**
     * 功能：增加用户信息
     * 
     * @param objs
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/insertSysUser" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map insertSysUser(@RequestBody JSONObject objs, HttpServletRequest request) {

        try {
            logger.debug("IQB--开始插入数据");
            sysUserService.insertSysUser(objs);
            logger.info("IQB信息--插入数据完成");
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
     * 功能:删除用户
     * 
     * @param objs
     * @param request
     * @return
     */

    @ResponseBody
    @RequestMapping(value = { "/deleteSysUser" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map deleteSysUser(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {
            logger.debug("IQB--开始删除数据");
            sysUserService.deleteSysUser(objs);
            logger.info("IQB信息--删除数据完成");
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
     * 功能:修改用户
     * 
     * @param objs
     * @param reqeust
     * @return
     */

    @ResponseBody
    @RequestMapping(value = { "/updateSysUserAll" }, method = { RequestMethod.GET,
        RequestMethod.POST })
    public Map updateSysUserAll(@RequestBody JSONObject objs, HttpServletRequest reqeust) {
       try {logger.debug("IQB--修改数据开始");
            sysUserService.updateSysUserAll(objs);
            logger.info("IQB信息--修改数据完成");
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
     * 功能:用户密码修改
     * 
     * @param objs
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/updateUserPassword" }, method = { RequestMethod.GET,
        RequestMethod.POST })
    public Map updateUserPassword(@RequestBody JSONObject objs, HttpServletRequest request) {

        try {
            logger.debug("IQB--修改用户密码开始");
            sysUserService.updateUserPassword(objs);
            logger.info("IQB信息--修改用户密码");
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
     * 功能:分页查询
     * 
     * @param objs
     * @param reqeust
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/getSysUser" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map getSysUser(@RequestBody JSONObject objs, HttpServletRequest reqeust) {
        try {
            logger.debug("IQB--开始分页查询数据开始...");
            PageInfo<SysUser> pageInfo = sysUserService.getSysUser(objs);
           logger.info("IQB信息--查询数据完成.");
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
    @RequestMapping(value = { "/getSysUserById" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map getSysUserById(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {
            logger.debug("IQB--开始查询数据开始...");
            SysUser sysUser = sysUserService.getSysUserById(objs);
            logger.info("IQB--查询数据完成.");
            LinkedHashMap<String, SysUser> linkedHashMap = new LinkedHashMap<String, SysUser>();
            linkedHashMap.put("result", sysUser);
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
     * 功能:修改密码
     * 
     * @param objs
     * @param reuqest
     * @return
     */

    @ResponseBody
    @RequestMapping(value = { "/updateModifyUserPassword" }, method = { RequestMethod.GET,
        RequestMethod.POST })
    public Map updateModifyUserPassword(@RequestBody JSONObject objs, HttpServletRequest reuqest) {
        try {
            logger.debug("IQB--修改密码开始...");
            sysUserService.updateModifyUserPassword(objs);
            logger.info("IQB--修改密码完成.");
            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] {});
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息:" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {
            logger.error("IQB错误信息:" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001, e));
        }
    }

    /**
     * 忘记密码
     * 
     * @param objs
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/updateForgetUserPassword" }, method = { RequestMethod.GET,
        RequestMethod.POST })
    public Map updateForgetUserPassword(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {
            logger.debug("IQB--忘记密码开始...");
            sysUserService.updateForgetUserPassword(objs);
            logger.info("IQB--忘记密码完成.");
            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] {});
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息:" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {
            logger.error("IQB错误信息:" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001, e));
        }
    }
    
    /**
     * 忘记密码
     * 
     * @param objs
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "/getSysUserAll" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map getSysUserAll(HttpServletRequest request) {
        try {
            logger.debug("IQB---查询所有用户...");
            List<Map<String, String>> mapUser =  sysUserService.getSysUserAll();
            logger.info("IQB--查询所有用户完成.");
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
            linkedHashMap.put("result", mapUser);
            return super.returnSuccessInfo(linkedHashMap);
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息:" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {
            logger.error("IQB错误信息:" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001, e));
        }
    }
    @ResponseBody
    @RequestMapping(value = { "/getSysUserOrgan" }, method = { RequestMethod.GET,RequestMethod.POST })
    public Map getSysUserOrgan(HttpServletRequest request) {
        try {
            logger.debug("IQB---查询所有用户...");
            List<Map<String, String>> mapUser  =  sysUserService.getSysUserOrgan();
            logger.info("IQB--查询所有用户完成.");
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
            linkedHashMap.put("result", mapUser);
            return super.returnSuccessInfo(linkedHashMap);
        } catch (IqbException iqbe) {
            logger.error("IQB错误信息:" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {
            logger.error("IQB错误信息:" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001, e));
        }
    }

   /**
    * 功能:查询登录次数超过五次的用户
    * @param objs
    * @param request
    * @return
    */
    @ResponseBody
    @RequestMapping(value = { "/getSysUserLock" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map getSysUserLock(@RequestBody JSONObject objs,HttpServletRequest request) throws IqbException{
        try{
            logger.debug("IQB--查询锁住的用户开始");
            PageInfo<SysUser > pageInfo  =    sysUserService.getSysUserLock(objs);
            logger.info("IQB--查询锁住用户结束");
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
            linkedHashMap.put("result", pageInfo);
            return super.returnSuccessInfo(linkedHashMap);
          
        } catch (Exception e) {
            logger.error("IQB错误信息:" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }
    /**
     * 功能:解除冻结用户
     * @param objs
     * @param request
     * @return
     * @throws IqbException
     */
    @ResponseBody
    @RequestMapping(value = { "/remSysUserLock" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map remSysUserLock(@RequestBody JSONObject objs,HttpServletRequest request) throws IqbException{
        try{
            logger.debug("IQB--查询锁住的用户开始");
            sysUserService.remSysUserLock(objs);
            logger.info("IQB--查询锁住用户结束");
            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] {});
          
        } catch (Exception e) {
            logger.error("IQB错误信息:" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }
    
}
