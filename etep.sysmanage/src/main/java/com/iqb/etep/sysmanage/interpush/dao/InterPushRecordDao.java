package com.iqb.etep.sysmanage.interpush.dao;

import com.iqb.etep.sysmanage.interpush.bean.InterPushRecordBean;

/**
 * 
 * Description: 接口推送记录dao
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年9月22日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */
public interface InterPushRecordDao{

    /**
     * 
     * Description: 插入推送数据记录表
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月22日 下午2:49:28
     */
    public void insertInterPushPushRecord(InterPushRecordBean interPushRecordBean);

}
