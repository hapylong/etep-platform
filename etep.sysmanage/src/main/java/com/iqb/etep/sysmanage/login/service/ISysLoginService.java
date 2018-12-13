package com.iqb.etep.sysmanage.login.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.common.exception.IqbException;

/**
 * 
 * Description: 用户登录service
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年8月15日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */
@SuppressWarnings({"rawtypes"})
public interface ISysLoginService{
    
    /**
     * 
     * Description: 系统用户登录
     * @param
     * @return Object
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月15日 下午2:17:26
     */
    public Object sysUserLogin(JSONObject objs) throws Exception;

    /**
     * 
     * Description: 用户注销登录
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月15日 下午4:18:27
     */
    public void sysUserLogout() throws IqbException;

    /**
     * 
     * Description: 获取用户登录之后系统相关信息
     * @param
     * @return Map
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月18日 下午2:23:17
     */
    public Map getSysLoginInfo() throws IqbException, Exception ;

    public void get2DCaptcha(String key, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
