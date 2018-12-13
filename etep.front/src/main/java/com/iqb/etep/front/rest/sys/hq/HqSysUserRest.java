package com.iqb.etep.front.rest.sys.hq;

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
import com.iqb.etep.front.rest.sys.user.SysUserRest;
import com.iqb.etep.sysmanage.hq.service.impl.HqSysUserService;
import com.iqb.etep.sysmanage.user.bean.SysUser;

/**
 * 
 * Description: 总部用户接口
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
@SuppressWarnings({ "rawtypes" })
@RequestMapping("/hqSysUserRest")
public class HqSysUserRest extends FrontBaseService{
    

    private static Logger logger = LoggerFactory.getLogger(SysUserRest.class);

    @Autowired
    HqSysUserService sysUserService;

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

        try {
            logger.debug("IQB--修改数据开始");
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
    @RequestMapping(value = { "/getHqSysUser" }, method = { RequestMethod.GET, RequestMethod.POST })
    public Map getHqSysUser(@RequestBody JSONObject objs, HttpServletRequest reqeust) {
        try {
            logger.debug("IQB--开始分页查询数据开始...");
            PageInfo<SysUser> pageInfo = sysUserService.getHqSysUser(objs);
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
     * 功能:忘记密码
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


}
