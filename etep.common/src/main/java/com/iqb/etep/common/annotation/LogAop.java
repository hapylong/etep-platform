package com.iqb.etep.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.iqb.etep.common.utils.Attr.MongoCollections;

/**
 * Description: aop记录登录日志和菜单日志
 * 
 * @author wangxinbang
 * @version 1.0
 * 
 *          <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------
 * 2016年8月23日    wangxinbang       1.0        1.0 Version
 * </pre>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAop {

    LogAopOper logAopOper() default LogAopOper.SysUserLogin;
    
    String operType() default MongoCollections.BizLog;
    
    String operInfo() default "";

    public enum LogAopOper {
        SysUserLogin(MongoCollections.SysLog, "用户登录"),
        SysAccessOrgUrl(MongoCollections.BizLog, "访问机构菜单"),
        SysAccessUserUrl(MongoCollections.BizLog, "访问用户菜单");

        /**
         * 操作类型
         */
        private String operType;

        /**
         * 操作信息
         */
        private String operInfo;

        private LogAopOper(String operType, String operInfo) {
            this.operType = operType;
            this.operInfo = operInfo;
        }

        public String getOperType() {
            return operType;
        }

        public void setOperType(String operType) {
            this.operType = operType;
        }

        public String getOperInfo() {
            return operInfo;
        }

        public void setOperInfo(String operInfo) {
            this.operInfo = operInfo;
        }

    }

}
