package com.iqb.etep.sysmanage.param.redis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.iqb.etep.common.redis.RedisPlatformDao2;
import com.iqb.etep.common.utils.BeanUtil;
import com.iqb.etep.common.utils.JSONUtil;
import com.iqb.etep.common.utils.StringUtil;
import com.iqb.etep.sysmanage.base.SysManageAttr.ParamConst;
import com.iqb.etep.sysmanage.param.bean.SysParam;

/**
 * 
 * Description: 
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
public class ParamRedis{
    
    /** 日志  **/
    private static final Logger logger = LoggerFactory.getLogger(ParamRedis.class);
    
    @Autowired
    private RedisPlatformDao2 redisPlatformDao2;
    
    /**
     * 
     * Description: 设置系统参数进redis
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月6日 下午2:52:27
     */
    public void setSysParamToRedis(String key, SysParam sysParam){
        if(StringUtil.isEmpty(key) || sysParam == null){
            logger.error("设置系统参数失败:传入参数有空值");
            return;
        }
        String sysRedisKey = ParamConst.SysParamPrefix + key;
        String sysRedisVal = JSONUtil.objToJson(sysParam);
        redisPlatformDao2.setKeyAndValue(sysRedisKey, sysRedisVal);
    }
    
    /**
     * 
     * Description: 设置业务参数进redis
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月6日 下午3:03:16
     */
    public void setBizParamToRedis(String key, SysParam sysParam){
        if(StringUtil.isEmpty(key) || sysParam == null){
            logger.error("设置业务参数失败:传入参数有空值");
            return;
        }
        String bizRedisKey = ParamConst.BizParamPrefix + key;
        String bizRedisVal = JSONUtil.objToJson(sysParam);
        redisPlatformDao2.setKeyAndValue(bizRedisKey, bizRedisVal);
    }
    
    /**
     * 
     * Description: 从redis中取出系统参数
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月6日 下午3:04:02
     */
    public SysParam getSysParamFromRedis(String key){
        if(StringUtil.isEmpty(key)){
            logger.error("从redis中取出系统参数失败:传入参数有空值");
            return null;
        }
        String sysRedisKey = ParamConst.SysParamPrefix + key;
        String sysRedisVal = redisPlatformDao2.getValueByKey(sysRedisKey);
        if(StringUtil.isEmpty(sysRedisVal)){
            logger.error("从redis中取出系统参数失败:redis中参数值为空");
            return null;
        }
        return BeanUtil.toJavaObject(sysRedisVal, SysParam.class);
    }
    
    /**
     * 
     * Description: 从redis中取出业务参数
     * @param
     * @return SysParam
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月6日 下午3:08:01
     */
    public SysParam getBizParamFromRedis(String key){
        if(StringUtil.isEmpty(key)){
            logger.error("从redis中取出业务参数失败:传入参数有空值");
            return null;
        }
        String bizRedisKey = ParamConst.BizParamPrefix + key;
        String bizRedisVal = redisPlatformDao2.getValueByKey(bizRedisKey);
        if(StringUtil.isEmpty(bizRedisVal)){
            logger.error("从redis中取出业务参数失败:redis中参数值为空");
            return null;
        }
        return BeanUtil.toJavaObject(bizRedisVal, SysParam.class);
    }
    
    /**
     * 
     * Description: 从redis删除系统参数
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月7日 下午3:08:07
     */
    public void removeSysParamFromRedis(String key){
        if(StringUtil.isEmpty(key)){
            logger.error("从redis删除系统参数失败:传入参数有空值");
            return;
        }
        String sysRedisKey = ParamConst.SysParamPrefix + key;
        redisPlatformDao2.removeValueByKey(sysRedisKey);
    }
    
    /**
     * 
     * Description: 从redis删除业务参数
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月7日 下午3:09:25
     */
    public void removeBizParamFromRedis(String key){
        if(StringUtil.isEmpty(key)){
            logger.error("从redis删除业务参数失败:传入参数有空值");
            return;
        }
        String sysRedisKey = ParamConst.BizParamPrefix + key;
        redisPlatformDao2.removeValueByKey(sysRedisKey);
    }
    
    /**
     * 
     * Description: 将系统参数列表添加至redis中
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月6日 下午3:11:15
     */
    public void setSysParamListToRedis(List<SysParam> sysParamList){
        if(CollectionUtils.isEmpty(sysParamList)){
            logger.error("将系统参数列表添加至redis失败:传入参数列表为空");
            return;
        }
        for(SysParam sysParam : sysParamList){
            if(sysParam == null || StringUtil.isEmpty(sysParam.getItemKey())){
                continue;
            }
            String sysParamKey = sysParam.getItemKey();
            this.setSysParamToRedis(sysParamKey, sysParam);
        }
    }
    
    /**
     * 
     * Description: 将业务参数列表添加至redis
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月6日 下午3:28:23
     */
    public void setBizParamListToRedis(List<SysParam> bizParamList){
        if(CollectionUtils.isEmpty(bizParamList)){
            logger.error("将业务参数列表添加至redis失败:传入参数列表为空");
            return;
        }
        for(SysParam sysParam : bizParamList){
            if(sysParam == null || StringUtil.isEmpty(sysParam.getItemKey())){
                continue;
            }
            String bizParamKey = sysParam.getItemKey();
            this.setSysParamToRedis(bizParamKey, sysParam);
        }
    }
    
}
