package com.iqb.etep.sysmanage.base;

import com.iqb.etep.common.utils.Attr;

/**
 * 
 * Description: 
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年9月12日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */
public class SysManageAttr extends Attr{
    
    /** 字典相关常量  **/
    public class DictConst{
        /** 系统字典前缀  **/
        public static final String SysDictPrefix = "sys_dict_";
        /** 业务字典前缀  **/
        public static final String BizDictPrefix = "biz_dict_";
        /** 机构类型系统字典  **/
        public static final String SYS_DICT_ORGTYPE = "SYS_DICT_ORGTYPE";
    }
    
    /** 参数相关常量  **/
    public class ParamConst{
        /** 系统参数前缀  **/
        public static final String SysParamPrefix = "sys_param_";
        /** 业务参数前缀  **/
        public static final String BizParamPrefix = "biz_param_";
    }
    
    /** 系统redis相关常量  **/
    public class SysRedisConst{
        /** 系统状态 1:正常 2:停用 3:部分停用  **/
        public static final String SYS_STATUS = "sys_status";
        /**  1:正常  **/
        public static final String SYS_STATUS_NORMAL = "1";
        /** 2:停用  **/
        public static final String SYS_STATUS_DISABLE = "2";
        /** 3:部分停用  **/
        public static final String SYS_STATUS_PARTIALLY_DISABLE  = "3";
    }
    
    /** 接口推送常量  **/
    public class InterPushAttr{
        /** 推送类型 1:新增 **/
        public static final String INTER_PUSH_TYPE_ADD = "1";
        /** 推送类型 2:修改 **/
        public static final String INTER_PUSH_TYPE_UPDATE = "2";
        /** 推送类型 3:删除 **/
        public static final String INTER_PUSH_TYPE_DELETE = "3";
        
        /** 推送状态 1:开始  **/
        public static final String INTER_PUSH_STATUS_START = "1";
        /** 推送状态 2:结束  **/
        public static final String INTER_PUSH_STATUS_END = "2";
        /** 推送状态 3:异常  **/
        public static final String INTER_PUSH_STATUS_ERROE = "3";
    }
    
    /** sys推送接收端  **/
    public class SysPushReceiveAttr{
        /** crm **/
        public static final String RECEIVE_CRM = "CRM";
    }
    
    /** sys发送端模块  **/
    public class SysPushInitiateAttr{
        /** org **/
        public static final String INITIATE_ORG = "机构信息";
    }
    
    /** crm推送地址  **/
    public class SysPushUrlAttr{
        /** crm  **/
        public static final String URL_CRM = "http://localhost:8088/consumer.manage.front/customer/unIntcpt-saveCustomerInfoFromEtep";
    }
    
    /** 用户角色相关  **/
    public class StationRoleAttr{
        /** 通用角色标识  **/
        public static final String STATION_IS_HQ = "1";
    }
    
    /** http请求方式  **/
    public class HttpInterMode{
        /** http请求方式  **/
        public static final String HTTPINTERMODE_HTTP = "http";
        /** https请求方式  **/
        public static final String HTTPINTERMODE_HTTPS = "https";
    }
    
}
