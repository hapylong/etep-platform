package com.iqb.etep.sysmanage.interpush.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iqb.etep.sysmanage.base.SysManageBaseBiz;
import com.iqb.etep.sysmanage.interpush.bean.InterPushRecordBean;
import com.iqb.etep.sysmanage.interpush.dao.InterPushRecordDao;

/**
 * 
 * Description: 接口推送记录biz
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年9月22日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */
@Component
public class InterPushRecordBiz extends SysManageBaseBiz{
    
    @Autowired
    private InterPushRecordDao interPushRecordDao;

    /**
     * 
     * Description: 插入推送数据记录表
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月22日 下午2:47:00
     */
    public synchronized void insertInterPushPushRecord(InterPushRecordBean interPushRecordBean) {
        super.setDb(0, super.MASTER);
        interPushRecordDao.insertInterPushPushRecord(interPushRecordBean);
    }
    

}
