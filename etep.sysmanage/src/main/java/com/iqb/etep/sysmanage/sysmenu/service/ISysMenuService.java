package com.iqb.etep.sysmanage.sysmenu.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.sysmanage.sysmenu.bean.SysMenu;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;


public interface ISysMenuService {
    
    /**
     * 新增菜单数据
     * @param objs
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    void insertSysMenu(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 删除菜单数据
     * @param objs
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    void deleteSysMenuById(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 修改菜单数据
     * @param objs
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    void updateSysMenuById(JSONObject objs) throws IqbException, IqbSqlException;
      
    /***
     * 根据ID查询菜单数据
     * @param objs
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    SysMenu selectSysMenuById(JSONObject objs) throws IqbException, IqbSqlException;
    
    /***
     * 查询菜单数据
     * @param objs
     * @return
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    List<TreeNode> selectSysMenuForTree(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 根据角色查询系统菜单信息
     * @param userCode
     * @param roleId
     * @return 
     * @throws IqbException
     * @throws IqbSqlException
     * @author wangxinbang
     */
    public List<TreeNode> getSysMenuListByRole() throws IqbException, IqbSqlException;
    
    /**
     * 刷新菜单缓存
     * @param userCode
     * @param roleId
     * @return 
     * @throws IqbException
     * @throws IqbSqlException
     * @author wangxinbang
     */
    public String refreshSysMenuListByRole(String userCode, Integer roleId) throws IqbException, IqbSqlException;
    
    /**
     * 删除所有的用户菜单信息
     * @param
     * @return 
     * @throws IqbException
     * @throws IqbSqlException
     * @author wangxinbang
     */
    public void removeAllUserSysMenuList() throws IqbException, IqbSqlException;

}
