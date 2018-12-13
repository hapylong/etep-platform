package com.iqb.batch.business.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqb.batch.business.log.biz.LogBiz;

/**
 * 
 * Description: 日志服务
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年9月6日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */
@Service
public class LogService{

    @Autowired
    private LogBiz logBiz;
    
    /**
     * 
     * Description: 清空七天之前的日志信息
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月6日 下午5:40:44
     */
    public void clearLogInfoBefore7Days() {
        logBiz.clearLogInfoBefore7Days();
    }

}
