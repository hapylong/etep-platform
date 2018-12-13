package com.iqb.etep.sysmanage.login.dao;

import com.iqb.etep.sysmanage.user.bean.SysUser;


/**
 * 
 * Description: 登录dao
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年8月15日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */
public interface SysLoginDao{

    /**
     * 
     * Description: 根据用户名和密码查询用户信息
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月15日 下午3:22:23
     */
    public SysUser getUserByNameAndPwd(SysUser sysUser);

    /**
     * 
     * Description: 根据用户编码查询用户信息
     * @param
     * @return SysUser
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月6日 下午3:57:05
     */
    public SysUser getUserByUserCode(SysUser sysUser);

}
