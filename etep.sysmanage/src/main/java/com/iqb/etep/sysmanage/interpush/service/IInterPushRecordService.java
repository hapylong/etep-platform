package com.iqb.etep.sysmanage.interpush.service;

import java.util.Map;

import com.iqb.etep.sysmanage.interpush.bean.InterPushRecordBean;

/**
 * 
 * Description: 接口推送记录服务接口
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年9月22日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */
public interface IInterPushRecordService{

    /**
     * 
     * Description: 插入推送数据记录表
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月22日 下午1:59:17
     */
    public void insertInterPushPushRecord(InterPushRecordBean interPushRecordBean);

    /**
     * 
     * Description: 推送数据信息
     * @param
     * @return String
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月22日 下午2:00:25
     */
    public String sysPushByPost(String urlCrm, Map<String, String> entity2map);
    
    /**
     * 
     * Description: 推送数据信息
     * @param
     * @return String
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月22日 下午2:00:25
     */
    public String sysPushByPostByHttps(String urlCrm, Map<String, String> entity2map);

}
