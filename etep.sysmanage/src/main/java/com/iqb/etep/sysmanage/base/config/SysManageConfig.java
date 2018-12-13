package com.iqb.etep.sysmanage.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.iqb.etep.common.base.config.BaseConfig;

/**
 * 
 * Description: 系统管理配置
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年9月27日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */
@Configuration
public class SysManageConfig extends BaseConfig{
    
    @Value("${url_sysmanage_org_crm_customer_push}")
    private String urlSysmanageOrgCrmCustomerPush;
    
    @Value("${http_interface_interaction_mode}")
    private String httpInterfaceInteractionMode;

    public String getUrlSysmanageOrgCrmCustomerPush() {
        return urlSysmanageOrgCrmCustomerPush;
    }

    public void setUrlSysmanageOrgCrmCustomerPush(String urlSysmanageOrgCrmCustomerPush) {
        this.urlSysmanageOrgCrmCustomerPush = urlSysmanageOrgCrmCustomerPush;
    }

    public String getHttpInterfaceInteractionMode() {
        return httpInterfaceInteractionMode;
    }
    
    public void setHttpInterfaceInteractionMode(String httpInterfaceInteractionMode) {
        this.httpInterfaceInteractionMode = httpInterfaceInteractionMode;
    }
    
}
