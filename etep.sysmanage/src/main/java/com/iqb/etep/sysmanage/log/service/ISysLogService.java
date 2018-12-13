package com.iqb.etep.sysmanage.log.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.sysmanage.sysmenu.bean.SysMenu;


/**
 * 
 * Description: 系统日志service
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年8月22日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */
@SuppressWarnings("rawtypes")
public interface ISysLogService{
    
    /**
     * 
     * Description: 插入用户登录日志
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月22日 下午8:18:16
     */
    public void insertUserLoginSysLog(Map map) throws IqbException, IqbSqlException ;
    
    /**
     * 
     * Description: 保存用户操作信息
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月22日 下午8:23:22
     */
    public void insertUserOperSysLog(Map map) throws IqbException, IqbSqlException;
    
    /**
     * 
     * Description: 插入用户业务日志
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月30日 下午2:47:26
     */
    public void insertUserBizLog(JSONObject json) throws IqbException, IqbSqlException;
    
    /**
     * 
     * Description: 查询系统日志
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月22日 下午8:25:43
     */
    public PageInfo querySysLog(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 
     * Description: 获取所有系统菜单
     * @param
     * @return List<SysMenu>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月5日 下午2:43:56
     */
    public List<SysMenu> getAllSysMenu() throws IqbException, IqbSqlException;
    
}
