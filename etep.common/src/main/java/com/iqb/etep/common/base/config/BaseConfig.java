/*
 * @(#) BaseConfig.java  1.0  September 27, 2016
 *
 * Copyright 2016 by 北京爱钱帮财富科技有限公司
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * IQB("Confidential Information").  You shall not disclose such 
 * Confidential Information and shall use it only in accordance with
 * the terms of the license agreement
 * you entered into with IQB.
 */
package com.iqb.etep.common.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 系统基础配置参数类
 * 
 * @author mayongming
 * @version 1.0
 * 
 *          <pre>
 * Modification History: 
 * Date             Author      Version         Description 
 * ------------------------------------------------------------------
 * 2016.09.27    mayongming       1.0           1.0 Version
 * </pre>
 */

@Configuration
public class BaseConfig {
    @Value("${common_basedir}")
    private String common_basedir;
    
    public String getCommon_basedir() {
        return common_basedir;
    }
    
    public void setCommon_basedir(String common_basedir) {
        this.common_basedir = common_basedir;
    }
}
