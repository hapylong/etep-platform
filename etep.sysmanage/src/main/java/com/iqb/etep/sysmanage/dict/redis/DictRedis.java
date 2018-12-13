package com.iqb.etep.sysmanage.dict.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.iqb.etep.common.redis.RedisPlatformDao2;
import com.iqb.etep.common.utils.BeanUtil;
import com.iqb.etep.common.utils.JSONUtil;
import com.iqb.etep.common.utils.StringUtil;
import com.iqb.etep.sysmanage.base.SysManageAttr.DictConst;
import com.iqb.etep.sysmanage.dict.bean.SysDictItem;

/**
 * 
 * Description: 字典缓存
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年9月7日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */

@Component
@SuppressWarnings({"rawtypes"})
public class DictRedis {

    /** 日志  **/
    private static final Logger logger = LoggerFactory.getLogger(DictRedis.class);
    
    @Autowired
    private RedisPlatformDao2 redisPlatformDao2;
    
    /**
     * 
     * Description: 设置系统字典进redis
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月7日 下午2:27:55
     */
    public void setSysDictToRedis(String key, SysDictItem sysDictItem){
        if(StringUtil.isEmpty(key) || sysDictItem == null || StringUtil.isEmpty(sysDictItem.getDictTypeCode())){
            logger.error("设置系统字典失败:传入参数有空值");
            return;
        }
        String sysRedisKey = DictConst.SysDictPrefix + key + "_" + sysDictItem.getDictCode();
        String sysRedisVal = JSONUtil.objToJson(sysDictItem);
        redisPlatformDao2.setKeyAndValue(sysRedisKey, sysRedisVal);
        
        if(StringUtil.isNotEmpty(sysDictItem.getCascadeCode())){
            String casSysRedisKey = DictConst.SysDictPrefix + "$_" + key + "_" + sysDictItem.getCascadeCode() + "_" + sysDictItem.getDictCode();
            redisPlatformDao2.setKeyAndValue(casSysRedisKey, sysRedisVal);
        }
    }
    
    /**
     * 
     * Description: 设置业务字典进redis
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月6日 下午3:03:16
     */
    public void setBizDictToRedis(String key, SysDictItem sysDictItem){
        if(StringUtil.isEmpty(key) || sysDictItem == null || StringUtil.isEmpty(sysDictItem.getDictTypeCode())){
            logger.error("设置业务字典失败:传入参数有空值");
            return;
        }
        String bizRedisKey = DictConst.BizDictPrefix + key + "_" + sysDictItem.getDictCode();
        String bizRedisVal = JSONUtil.objToJson(sysDictItem);
        redisPlatformDao2.setKeyAndValue(bizRedisKey, bizRedisVal);
    }
    
    /**
     * 
     * Description: 从redis中取出系统字典
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月6日 下午3:04:02
     */
    public List<Map> getSysDictItemFromRedis(String key){
        if(StringUtil.isEmpty(key)){
            logger.error("从redis中取出系统字典失败:传入参数有空值");
            return null;
        }
        String sysRedisKey = DictConst.SysDictPrefix + key + "_*";
        Set<String> keySet = redisPlatformDao2.getKeys(sysRedisKey);
        if(CollectionUtils.isEmpty(keySet)){
            return null;
        }
        List<Map> list = new ArrayList<Map>();
        for(String k : keySet){
            String bizRedisVal = redisPlatformDao2.getValueByKey(k);
            Map m = BeanUtil.toJavaObject(bizRedisVal, Map.class);
            list.add(m);
        }
        return list;
    }
    
    /**
     * 
     * Description: 从redis中取出业务字典
     * @param
     * @return SysParam
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月6日 下午3:08:01
     */
    public List<Map> getBizDictItemFromRedis(String key){
        if(StringUtil.isEmpty(key)){
            logger.error("从redis中取出业务字典失败:传入参数有空值");
            return null;
        }
        String bizRedisKey = DictConst.BizDictPrefix + key + "_*";
        Set<String> keySet = redisPlatformDao2.getKeys(bizRedisKey);
        if(CollectionUtils.isEmpty(keySet)){
            return null;
        }
        List<Map> list = new ArrayList<Map>();
        for(String k : keySet){
            String bizRedisVal = redisPlatformDao2.getValueByKey(k);
            Map m = BeanUtil.toJavaObject(bizRedisVal, Map.class);
            list.add(m);
        }
        return list;
    }
    
    /**
     * 
     * Description: 删除系统字典类别
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月7日 下午4:52:22
     */
    public void removeSysDictTypeFromRedis(String key){
        if(StringUtil.isEmpty(key)){
            logger.error("从redis删除系统字典类别失败:传入参数有空值");
            return;
        }
        String sysRedisKey = DictConst.SysDictPrefix + key + "_*";
        Set<String> keySet = redisPlatformDao2.getKeys(sysRedisKey);
        if(CollectionUtils.isEmpty(keySet)){
            return;
        }
        for(String k : keySet){
            redisPlatformDao2.removeValueByKey(k);
        }
    }
    
    /**
     * 
     * Description: 删除业务字典类别
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月7日 下午5:02:27
     */
    public void removeBizDictTypeFromRedis(String key){
        if(StringUtil.isEmpty(key)){
            logger.error("从redis删除业务字典类别失败:传入参数有空值");
            return;
        }
        String bizRedisKey = DictConst.BizDictPrefix + key + "_*";
        Set<String> keySet = redisPlatformDao2.getKeys(bizRedisKey);
        if(CollectionUtils.isEmpty(keySet)){
            return;
        }
        for(String k : keySet){
            redisPlatformDao2.removeValueByKey(k);
        }
    }
    
    /**
     * 
     * Description: 删除系统字典项
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月7日 下午5:03:04
     */
    public void removeSysDictItemFromRedis(String typeCode, String itemCode){
        if(StringUtil.isEmpty(typeCode) || StringUtil.isEmpty(itemCode)){
            logger.error("从redis删除系统字典项失败:传入参数有空值");
            return;
        }
        String sysRedisKey = DictConst.SysDictPrefix + typeCode + "_" + itemCode;
        redisPlatformDao2.removeValueByKey(sysRedisKey);
    }
    
    /**
     * 
     * Description: 删除业务字典项
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月7日 下午5:06:17
     */
    public void removeBizDictItemFromRedis(String typeCode, String itemCode){
        if(StringUtil.isEmpty(typeCode) || StringUtil.isEmpty(itemCode)){
            logger.error("从redis删除业务字典项失败:传入参数有空值");
            return;
        }
        String bizRedisKey = DictConst.SysDictPrefix + typeCode + "_" + itemCode;
        redisPlatformDao2.removeValueByKey(bizRedisKey);
    }
    
    /**
     * 
     * Description: 将系统字典列表添加至redis中
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月6日 下午3:11:15
     */
    public void setSysDictListToRedis(List<SysDictItem> sysDictItemList){
        if(CollectionUtils.isEmpty(sysDictItemList)){
            logger.error("将系统字典列表添加至redis失败:传入参数列表为空");
            return;
        }
        for(SysDictItem sysDictItem : sysDictItemList){
            if(sysDictItem == null || StringUtil.isEmpty(sysDictItem.getDictTypeCode())){
                continue;
            }
            String sysParamKey = sysDictItem.getDictTypeCode();
            this.setSysDictToRedis(sysParamKey, sysDictItem);
        }
    }
    
    /**
     * 
     * Description: 将业务字典列表添加至redis
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月6日 下午3:28:23
     */
    public void setBizDictListToRedis(List<SysDictItem> bizDictItemList){
        if(CollectionUtils.isEmpty(bizDictItemList)){
            logger.error("将业务字典列表添加至redis失败:传入参数列表为空");
            return;
        }
        for(SysDictItem bizDictItem : bizDictItemList){
            if(bizDictItem == null || StringUtil.isEmpty(bizDictItem.getDictTypeCode())){
                continue;
            }
            String bizParamKey = bizDictItem.getDictTypeCode();
            this.setBizDictToRedis(bizParamKey, bizDictItem);
        }
    }
}
