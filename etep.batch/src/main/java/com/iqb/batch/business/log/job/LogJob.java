package com.iqb.batch.business.log.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iqb.batch.base.TaskBase;
import com.iqb.batch.business.log.service.LogService;

/**
 * 
 * Description: 日志相关定时任务
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年9月6日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */
@Component
public class LogJob implements TaskBase{
    
    private static final Logger logger = LoggerFactory.getLogger(LogJob.class);
    
    @Autowired
    private LogService logService;

    @Override
    public void autoExecute() {
        logger.info("执行日志定时任务开始（清空七天之前的日志信息）");
        try {
            logService.clearLogInfoBefore7Days();
        } catch (Exception e) {
            logger.error("执行日志定时任务异常（清空七天之前的日志信息）", e);
        }
        logger.info("执行日志定时任务结束（清空七天之前的日志信息）");
    }

}
