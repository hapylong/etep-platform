package com.iqb.etep.front.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.iqb.etep.common.utils.Attr.SessionAttr;
import com.iqb.etep.common.utils.IPUtil;
import com.iqb.etep.common.utils.StringUtil;
import com.iqb.etep.common.utils.SysUserSession;

/**
 * 
 * Description: 验证用户是否登录
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年8月15日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private SysUserSession loginSysUser;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /** 不拦截校验的访问资源  **/
        String[] excludeRegex = new String[] {".*js/.*", ".*css/.*", ".*images/.*", ".*login$", ".*logout$",
                ".*unIntcpt-.*", ".*captcha*", ".*cps/.*"};
        String[] wfExcludeRegex =
            new String[] { ".*WfTask/startProcess.*", ".*WfTask/startProcess.*",
                ".*WfTask/startAndCommitProcess.*", ".*WfTask/startAndCommitProcessByToken.*",
                ".*WfTask/claimProcess.*", ".*WfTask/unclaimProcess.*",
                ".*WfTask/completeProcess.*", ".*WfTask/completeProcessByTaskCode.*",
                ".*WfTask/deleteProcess.*", ".*WfTask/delegateProcess.*",
                ".*WfTask/cancelProcess.*", ".*WfTask/retrieveProcess.*",
                ".*WfTask/cancelProcDelegate.*", ".*WfTask/procApproveHistory.*",
                ".*WfTask/endProcess.*", ".*WfTask/assign.*", ".*WfMonitor/activeProcessList.*",
                ".*WfMonitor/orgProcessList.*", ".*WfMonitor/processSummary.*",
                ".*WfMonitor/processDelegate.*", ".*WfMonitor/myProcDelegate.*", ".*business.*",
                ".*creditorInfo/WFReturn.*", ".*/pay.*",".*authoritycard/save.*",
                ".*/nr.*" };

        String uri = request.getRequestURI();
        if(!StringUtil.matches(uri, excludeRegex) && !StringUtil.matches(uri, wfExcludeRegex)){
            String userCode = loginSysUser.getUserCode();
            if(userCode == null){
                logger.info("“{}”访问“{}”受限",IPUtil.getIpAddress(request),uri);
                response.getWriter().write(SessionAttr.UnLoginReturn);
                return false;
            }
        }
        logger.info("“{}”访问“{}”成功",IPUtil.getIpAddress(request),uri);
        return true;
    }
    
}
