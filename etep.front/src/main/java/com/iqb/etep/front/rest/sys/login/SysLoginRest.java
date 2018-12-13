package com.iqb.etep.front.rest.sys.login;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.common.base.CommonReturnInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.front.base.FrontBaseService;
import com.iqb.etep.sysmanage.base.SysManageReturnInfo;
import com.iqb.etep.sysmanage.login.service.ISysLoginService;

/**
 * 
 * Description: 系统登录
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年8月15日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */
@SuppressWarnings({"rawtypes"})
@RestController
@RequestMapping("/sysLogin")
public class SysLoginRest extends FrontBaseService {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    private static final String KEY_CAPTCHA_CACHE = "2D_CAPTCHA";
    @Autowired
    private ISysLoginService sysLoginService;
    
    /**
     * 
     * Description: 登录方法
     * @param
     * @return Object
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月15日 下午2:02:05
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object doLogin(@RequestBody JSONObject objs, HttpServletRequest request){
        try {
            String captcha = objs.getString("captcha").toLowerCase();
            HttpSession session = request.getSession();
            String captchaCache = ((String) session.getAttribute(KEY_CAPTCHA_CACHE)).toLowerCase();
            if (!captchaCache.equals(captcha)) {
                return super.returnFailtrueInfo(new IqbException(SysManageReturnInfo.SYS_LOGIN_01010005));
            }
            logger.debug("IQB信息---开始用户登录..." + objs.toJSONString());
            sysLoginService.sysUserLogin(objs);
            logger.info("IQB信息---用户登录完成.");
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
    @RequestMapping(value = {"/image/get2Dcaptcha"}, method = {RequestMethod.POST, RequestMethod.GET})
    public void get2DCaptcha(HttpServletRequest request, HttpServletResponse response) {
        try {
            sysLoginService.get2DCaptcha(KEY_CAPTCHA_CACHE, request, response);
        } catch (Throwable e) {
            logger.error("get2Dcaptcha error:", e);
            response.setStatus(500);
        }
    }

    /**
     * 
     * Description: 注销登录
     * @param
     * @return Object
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月15日 下午4:19:19
     */
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Object doLogout(HttpServletRequest request){
        try {
            logger.debug("IQB信息---开始用户注销...");
            sysLoginService.sysUserLogout();
            logger.info("IQB信息---用户注销完成.");
            
            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] { });
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
     * Description: 获取用户登录之后系统相关信息
     * @param
     * @return Object
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月18日 下午2:19:35
     */
    @ResponseBody
    @RequestMapping(value = "/getSysLoginInfo", method = RequestMethod.POST)
    public Object getSysLoginInfo(HttpServletRequest request){
        try {
            logger.debug("IQB信息---开始获取用户登录之后系统相关信息...");
            Map m = sysLoginService.getSysLoginInfo();
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put("result", m);
            logger.info("IQB信息---用户获取用户登录之后系统相关信息完成.");
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
