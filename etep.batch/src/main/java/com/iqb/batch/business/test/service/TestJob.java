package com.iqb.batch.business.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iqb.batch.base.TaskBase;
import com.iqb.etep.common.utils.SysUserSession;

/**
 * 
 * Description: 队列监控job
 * @author iqb
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年6月3日    wangxinbang     1.0        1.0 Version 
 * </pre>
 */
@Component
public class TestJob implements TaskBase{
	
	private static final Logger logger = LoggerFactory.getLogger(TestJob.class);
	
	@Autowired
	private SysUserSession sysUserSession;

	@Override
	public void autoExecute() {
//	    logger.info("------------------" + sysUserSession + "------------------");
	    if(sysUserSession != null){
	        logger.info("------------------" + sysUserSession.getUserCode() + "------------------");
	    }
	}
	
	/**
	 * 
	 * Description: 判断与当前时间是否间隔大于1小时
	 * @param
	 * @return boolean
	 * @throws ParseException 
	 * @throws
	 * @Author wangxinbang
	 * Create Date: 2016年6月7日 下午4:53:48
	 */
//	private boolean isBetweenAnHour(String startTimeLast) throws ParseException{
//		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//	    Date date = format.parse(startTimeLast);  
//	    Long startTimeLastLong = date.getTime()/1000;
//	    Long nowTimeLong = System.currentTimeMillis()/1000;
//	    if((nowTimeLong == null ? false : nowTimeLong - startTimeLastLong >= 60*60) ){
//	    	return true;
//	    }
//	    return false;
//	}
	
}
