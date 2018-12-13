package com.iqb.etep.front.aop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.common.annotation.LogAop;
import com.iqb.etep.common.utils.StringUtil;
import com.iqb.etep.common.utils.SysUserSession;
import com.iqb.etep.sysmanage.log.service.impl.SysLogService;
import com.iqb.etep.sysmanage.user.bean.SysUser;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * Description: 利用aop实现的日志记录
 * 
 * @author wangxinbang
 * @version 1.0
 * 
 *          <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------
 * 2016年8月20日    wangxinbang       1.0        1.0 Version
 * </pre>
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Aspect
@Component
public class LogService{

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private SysUserSession sysUserSession;

    /**
     * Description: 插入切点
     * 
     * @param
     * @return void
     * @throws
     * @Author wangxinbang Create Date: 2016年8月20日 下午1:36:38
     */
    @Pointcut("execution(* com.iqb..*.setSysUserSession(..))")
    public void logSysLogin() {
    }

    /**
     * Description: 记录业务日志
     * 
     * @param
     * @return void
     * @throws
     * @Author wangxinbang Create Date: 2016年8月23日 下午7:20:48
     */
    @Pointcut("execution(* com.iqb.front..*.*(..))")
    public void logBizLog() {

    }

    /**
     * Description: 插入数据记录日志
     * 
     * @param
     * @return Object
     * @throws
     * @Author wangxinbang Create Date: 2016年8月20日 下午2:17:00
     */
    @Around(value = "logSysLogin() && @annotation(annotation)", argNames = "annotation")
    public Object logSysLogin(ProceedingJoinPoint pj, LogAop annotation) {
        Object result = null;
        try {
            Map map = new HashMap();
            Object[] arguments = pj.getArgs(); // 获得参数列表
            SysUser sysUser = (SysUser) arguments[0];
            map.put("userCode", sysUser.getUserCode());
            map.put("operType", annotation.logAopOper().getOperType());
            map.put("operInfo", annotation.logAopOper().getOperInfo());
            map.put("orgName", sysUser.getOrgName());
            map.put("createUser", sysUser.getUserCode());
            map.put("createTime", System.currentTimeMillis() / 1000);
            sysLogService.insertUserLoginSysLog(map);
            result = pj.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Description: 业务日志记录
     * 
     * @param
     * @return Object
     * @throws
     * @Author wangxinbang Create Date: 2016年8月23日 下午7:21:47
     */
    @Around(value = "logBizLog() && @annotation(annotation)", argNames = "annotation")
    public Object logBizLog(ProceedingJoinPoint pj, LogAop annotation) {
        Object result = null;
        try {
            String userCode = sysUserSession.getUserCode();
            if (StringUtil.isNotEmpty(userCode)) {
                Map map = new HashMap();
                map.put("userCode", userCode);
                map.put("operType", annotation.operType());
                map.put("operInfo", annotation.operInfo());
                map.put("orgName", sysUserSession.getOrgName());
                map.put("createUser", userCode);
                map.put("createTime", System.currentTimeMillis() / 1000);
                sysLogService.insertUserOperSysLog(map);
            }
            result = pj.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }
    

}
