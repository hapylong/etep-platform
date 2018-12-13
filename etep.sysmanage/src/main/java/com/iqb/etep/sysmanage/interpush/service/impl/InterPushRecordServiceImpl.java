package com.iqb.etep.sysmanage.interpush.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.common.utils.HttpClientUtil;
import com.iqb.etep.common.utils.StringUtil;
import com.iqb.etep.common.utils.https.HttpsClientUtil;
import com.iqb.etep.common.utils.https.SendHttpsUtil;
import com.iqb.etep.sysmanage.interpush.bean.InterPushRecordBean;
import com.iqb.etep.sysmanage.interpush.biz.InterPushRecordBiz;
import com.iqb.etep.sysmanage.interpush.service.IInterPushRecordService;

/**
 * 
 * Description: 接口推送记录service
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年9月22日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */
@Service
public class InterPushRecordServiceImpl implements IInterPushRecordService{

    /** 日志  **/
    private static Logger logger = LoggerFactory.getLogger(InterPushRecordServiceImpl.class);
    
    @Autowired
    private InterPushRecordBiz interPushRecordBiz;
    
    private HttpsClientUtil httpsClientUtil = HttpsClientUtil.getInstance();
    
    @Override
    public void insertInterPushPushRecord(InterPushRecordBean interPushRecordBean) {
        interPushRecordBiz.insertInterPushPushRecord(interPushRecordBean);
    }

    @Override
    public String sysPushByPost(String url, Map<String, String> paramMap) {
        if(StringUtil.isEmpty(url)){
            logger.error("etep推送url空值");
            return null;
        }
        if(CollectionUtils.isEmpty(paramMap)){
            logger.warn("etep推送paraMap空值");
        }
        logger.info("etep推送url:" + url +",参数信息:" + JSONObject.toJSONString(paramMap));
        try {
            return HttpClientUtil.restPost(url, paramMap);
        } catch (Exception e) {
            logger.error("调用httpClient接口异常",e);
            return null;
        }
    }
    
    @Override
    public String sysPushByPostByHttps(String url, Map<String, String> paramMap) {
        if(StringUtil.isEmpty(url)){
            logger.error("etep推送url空值");
            return null;
        }
        if(CollectionUtils.isEmpty(paramMap)){
            logger.warn("etep推送paraMap空值");
        }
        logger.info("etep推送url:" + url +",参数信息:" + JSONObject.toJSONString(paramMap));
        try {
            return JSONObject.toJSONString(SendHttpsUtil.postMsg4GetMap(url, paramMap));
        } catch (Exception e) {
            logger.error("调用httpClient接口异常",e);
            return null;
        }
    }
    
    
}
