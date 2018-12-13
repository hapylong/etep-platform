package com.iqb.batch.business.log.biz;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iqb.batch.business.log.bean.SysLogBean;
import com.iqb.etep.common.utils.Attr.MongoCollections;
import com.iqb.etep.common.utils.MongoUtil;

/**
 * 
 * Description: 日志biz
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
public class LogBiz{
    
    /**
     * 注入mongo工具类
     */
    @Autowired
    private MongoUtil mongoUtil;

    /**
     * 
     * Description: 清空七天之前的日志信息
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月6日 下午6:26:02
     */
    public void clearLogInfoBefore7Days() {
        SysLogBean bean = new SysLogBean();
        bean.setQueryStartTime(get7DaysBefore());
        mongoUtil.remove(bean, MongoCollections.SysUserLogin);
        mongoUtil.remove(bean, MongoCollections.SysUserOper);
    }
    
    /**
     * 
     * Description: 获取七天之前时间戳
     * @param
     * @return Integer
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月7日 上午9:54:25
     */
    private Integer get7DaysBefore(){
        Calendar c = Calendar.getInstance();  
        c.add(Calendar.DATE, - 7);  
        return new Long(c.getTimeInMillis()/1000).intValue();
    }
    
}
