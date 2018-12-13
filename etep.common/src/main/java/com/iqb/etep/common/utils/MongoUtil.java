package com.iqb.etep.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.iqb.etep.common.annotation.MongoQueryProperty;
import com.iqb.etep.common.mongo.MongoTemp;
import com.iqb.etep.common.utils.Attr.MongoCollections;

/**
 * Description: mongo工具类
 * 
 * @author iqb
 * @version 1.0
 * 
 *          <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------
 * 2016年3月30日    wangxinbang     1.0        1.0 Version
 * </pre>
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@Repository
public class MongoUtil{

    /**
     * 日志
     */
    private final static Logger logger = LoggerFactory.getLogger(MongoUtil.class);

    /**
     * 注入mongoTemp
     */
    @Autowired
    private MongoTemp mongoTemp;

    /**
     * Description: 记录用户登录日志
     * 
     * @param
     * @return void
     * @throws
     * @Author wangxinbang Create Date: 2016年8月22日 下午5:27:51
     */
    public void saveUserLoginLog(Object obj) {
        try {
            mongoTemp.save(obj, MongoCollections.SysUserLogin);
        } catch (Exception e) {
            logger.error("mongo记录用户登录日志异常：", e);
        }
    }

    /**
     * Description: 记录用户操作日志
     * 
     * @param
     * @return void
     * @throws
     * @Author wangxinbang Create Date: 2016年8月22日 下午5:28:21
     */
    public void saveUserOperLog(Object obj) {
        try {
            mongoTemp.save(obj, MongoCollections.SysUserOper);
        } catch (Exception e) {
            logger.error("mongo记录用户操作日志异常：", e);
        }
    }

    /**
     * 
     * Description: mongo查询
     * @param
     * @return List<T>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月23日 上午10:39:23
     */
    @SuppressWarnings("static-access")
    public <T> List<T> query(T t, int begin, int end, String collectionName) {
        Query query = new Query();
        try {
            Class targetClass = t.getClass();
            Field[] fields = targetClass.getDeclaredFields();
            String gtFiledName = null;
            Object gtObjGet = null;
            Criteria criteria = new Criteria();
            boolean ltFlag = false;
            for (int i = 0; i < fields.length; i++) {
                MongoQueryProperty mqp = fields[i].getAnnotation(MongoQueryProperty.class);
                if (mqp == null) {
                    continue;
                }
                String fie = fields[i].getName().substring(0, 1).toUpperCase() + fields[i].getName().substring(1);
                Method method = targetClass.getMethod("get" + fie);
                Object objGet = method.invoke(t);
                if (objGet == null) {
                    continue;
                }
                String filedName = fields[i].getName();
                if(StringUtil.isNotEmpty(mqp.queryFieldName())){
                    filedName = mqp.queryFieldName();
                }
                
                switch (mqp.value()) {
                    case REGEX:
                        query.addCriteria(criteria.where(filedName).regex(".*" + objGet + ".*"));
                        break;
                    case GT:
                        gtFiledName = filedName;
                        gtObjGet = objGet;
                        break;
                    case LT:
                        ltFlag = true;
                        if(StringUtil.isEmpty(gtFiledName)){
                            query.addCriteria(criteria.where(filedName).lte(objGet));
                        }else{
                            query.addCriteria(criteria.andOperator(criteria.where(gtFiledName).gte(gtObjGet), criteria.where(filedName).lte(objGet)));
                        }
                        break;
                    default:
                        query.addCriteria(criteria.where(filedName).is(objGet));
                        break;
                }
            }
            if(ltFlag == false && StringUtil.isNotEmpty(gtFiledName)){
                query.addCriteria(criteria.where(gtFiledName).gte(gtObjGet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        query.with(new Sort(new Order(Direction.DESC,"createTime"))); 
        return (List<T>) mongoTemp.find(query.limit(end - begin).skip(begin), t.getClass(), collectionName);
    }
    
    /**
     * 
     * Description: mongo查询总数
     * @param
     * @return List<T>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月23日 上午10:39:23
     */
    @SuppressWarnings("static-access")
    public <T> List<T> queryTotal(T t, String collectionName) {
        Query query = new Query();
        try {
            Class targetClass = t.getClass();
            Field[] fields = targetClass.getDeclaredFields();
            String gtFiledName = null;
            Object gtObjGet = null;
            Criteria criteria = new Criteria();
            boolean ltFlag = false;
            for (int i = 0; i < fields.length; i++) {
                MongoQueryProperty mqp = fields[i].getAnnotation(MongoQueryProperty.class);
                if (mqp == null) {
                    continue;
                }
                String fie = fields[i].getName().substring(0, 1).toUpperCase() + fields[i].getName().substring(1);
                Method method = targetClass.getMethod("get" + fie);
                Object objGet = method.invoke(t);
                if (objGet == null) {
                    continue;
                }
                String filedName = fields[i].getName();
                if(StringUtil.isNotEmpty(mqp.queryFieldName())){
                    filedName = mqp.queryFieldName();
                }
                
                switch (mqp.value()) {
                    case REGEX:
                        query.addCriteria(criteria.where(filedName).regex(".*" + objGet + ".*"));
                        break;
                    case GT:
                        gtFiledName = filedName;
                        gtObjGet = objGet;
                        break;
                    case LT:
                        ltFlag = true;
                        if(StringUtil.isEmpty(gtFiledName)){
                            query.addCriteria(criteria.where(filedName).lte(objGet));
                        }else{
                            query.addCriteria(criteria.andOperator(criteria.where(gtFiledName).gte(gtObjGet), criteria.where(filedName).lte(objGet)));
                        }
                        break;
                    default:
                        query.addCriteria(criteria.where(filedName).is(objGet));
                        break;
                }
            }
            if(ltFlag == false && StringUtil.isNotEmpty(gtFiledName)){
                query.addCriteria(criteria.where(gtFiledName).gte(gtObjGet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<T> list = (List<T>) mongoTemp.find(query, t.getClass(), collectionName);
        return list;
    }
    
    /**
     * 
     * Description: 删除
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月6日 下午6:16:32
     */
    @SuppressWarnings("static-access")
    public <T> void remove(T t, String collectionName){
        Query query = new Query();
        try {
            Class targetClass = t.getClass();
            Field[] fields = targetClass.getDeclaredFields();
            String gtFiledName = null;
            Object gtObjGet = null;
            Criteria criteria = new Criteria();
            boolean ltFlag = false;
            for (int i = 0; i < fields.length; i++) {
                MongoQueryProperty mqp = fields[i].getAnnotation(MongoQueryProperty.class);
                if (mqp == null) {
                    continue;
                }
                String fie = fields[i].getName().substring(0, 1).toUpperCase() + fields[i].getName().substring(1);
                Method method = targetClass.getMethod("get" + fie);
                Object objGet = method.invoke(t);
                if (objGet == null) {
                    continue;
                }
                String filedName = fields[i].getName();
                if(StringUtil.isNotEmpty(mqp.queryFieldName())){
                    filedName = mqp.queryFieldName();
                }
                
                switch (mqp.value()) {
                    case REGEX:
                        query.addCriteria(criteria.where(filedName).regex(".*" + objGet + ".*"));
                        break;
                    case GT:
                        gtFiledName = filedName;
                        gtObjGet = objGet;
                        break;
                    case LT:
                        ltFlag = true;
                        if(StringUtil.isEmpty(gtFiledName)){
                            query.addCriteria(criteria.where(filedName).lte(objGet));
                        }else{
                            query.addCriteria(criteria.andOperator(criteria.where(gtFiledName).gte(gtObjGet), criteria.where(filedName).lte(objGet)));
                        }
                        break;
                    default:
                        query.addCriteria(criteria.where(filedName).is(objGet));
                        break;
                }
            }
            if(ltFlag == false && StringUtil.isNotEmpty(gtFiledName)){
                query.addCriteria(criteria.where(gtFiledName).gte(gtObjGet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mongoTemp.remove(query, t.getClass(), collectionName);
    }

    /**
     * 保存数据到mongo
     * 
     * @param objectToSave
     * @param collectionName
     * @author baiyanbing
     * @date 2016年4月15日下午2:30:54
     */
    public void save(Object objectToSave, String collectionName) {
        try {
            mongoTemp.save(objectToSave, collectionName);
        } catch (Exception e) {
            logger.error("mongo保存信息异常：", e);
        }
    }
}
