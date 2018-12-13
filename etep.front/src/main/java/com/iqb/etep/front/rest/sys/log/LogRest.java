package com.iqb.etep.front.rest.sys.log;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.base.CommonReturnInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.front.base.FrontBaseService;
import com.iqb.etep.sysmanage.log.service.impl.SysLogService;

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
@SuppressWarnings({"unchecked", "rawtypes"})
@RestController
@RequestMapping("/log")
public class LogRest extends FrontBaseService {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private SysLogService sysLogService;
    
    /**
     * 
     * Description: 插入登录日志
     * @param
     * @return Object
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月23日 上午10:42:54
     */
    @ResponseBody
    @RequestMapping(value = "/insertUserLoginSysLog", method = RequestMethod.POST)
    public Object insertUserLoginSysLog(@RequestBody JSONObject objs, HttpServletRequest request){
        try {
            logger.debug("IQB信息---开始插入登录日志..." + objs.toJSONString());
            sysLogService.insertUserLoginSysLog(objs);
            logger.info("IQB信息---用户插入登录日志.");
            
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
     * Description: 插入用户操作日志
     * @param
     * @return Object
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月23日 上午10:42:54
     */
    @ResponseBody
    @RequestMapping(value = "/insertUserOperSysLog", method = RequestMethod.POST)
    public Object insertUserOperSysLog(@RequestBody JSONObject objs, HttpServletRequest request){
        try {
            logger.debug("IQB信息---开始插入用户操作日志..." + objs.toJSONString());
            sysLogService.insertUserBizLog(objs);
            logger.info("IQB信息---用户插入用户操作日志.");
            
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
     * Description: 查询日志信息
     * @param
     * @return Object
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月23日 上午10:42:54
     */
    @ResponseBody
    @RequestMapping(value = "/querySysLog", method = RequestMethod.POST)
    public Object querySysLog(@RequestBody JSONObject objs, HttpServletRequest request){
        try {
            logger.debug("IQB信息---开始查询日志信息..." + objs.toJSONString());
            PageInfo pageInfo = sysLogService.querySysLog(objs);
            logger.info("IQB信息---用户查询日志信息.");
            
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("result",  pageInfo);
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
     * Description: 查询日志信息
     * @param
     * @return Object
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月23日 上午10:42:54
     */
    @ResponseBody
    @RequestMapping(value = "/queryBizLog", method = RequestMethod.POST)
    public Object queryBizLog(@RequestBody JSONObject objs, HttpServletRequest request){
        try {
            logger.debug("IQB信息---开始查询日志信息..." + objs.toJSONString());
            PageInfo pageInfo = sysLogService.querySysLog(objs);
            logger.info("IQB信息---用户查询日志信息.");
            
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("result",  pageInfo);
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
