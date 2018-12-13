package com.iqb.etep.sysmanage.log.dao;

import java.util.List;

import com.iqb.etep.sysmanage.sysmenu.bean.SysMenu;

/**
 * 
 * Description: 日志dao
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年8月30日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */
public interface SysLogDao{

    /**
     * 
     * Description: 获取系统菜单list
     * @param
     * @return List<SysLogBean>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月30日 下午3:03:48
     */
    List<SysMenu> getSysMenuForLog();
}

