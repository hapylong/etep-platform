package com.iqb.etep.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Description: mongo数据库查询的字段
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年8月22日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MongoQueryProperty {
    
    public enum MongoCriteriaWay{REGEX, LT, GT, IS};
    
    MongoCriteriaWay value() default MongoCriteriaWay.IS;
    
    String queryFieldName() default "";
}
