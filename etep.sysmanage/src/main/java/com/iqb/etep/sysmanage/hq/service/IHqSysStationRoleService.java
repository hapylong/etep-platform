/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : ISysStationRoleService.java
 *
 * PURPOSE : 
 *
 * AUTHOR : leiwenyang
 *
 *
 * 创建日期: 2016年8月17日
 * HISTORY：
 * 变更日期 
 */
package com.iqb.etep.sysmanage.hq.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.sysmanage.station.bean.SysStationRole;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;

/**
 * 
 * Description: 总部角色
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年9月13日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */
public interface IHqSysStationRoleService{

    // 添加角色
    public void insertSysStationRole(JSONObject objs) throws IqbException, IqbSqlException;

    // 删除角色
    public Integer  deleteSysStationRole(JSONObject objs) throws IqbException, IqbSqlException;

    /**
     * 
     * Description: 修改通用角色信息
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月14日 上午11:41:57
     */
    public void updateHqSysStationRole(JSONObject objs) throws IqbException, IqbSqlException;


    // 查询机构下面角色
    public List<Map<Integer, String>> selectStationRoleName(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 
     * Description: 根据机构id获取角色信息
     * @param
     * @return List<Map<Integer,String>>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年10月19日 下午4:38:27
     */
    public List<Map<Integer, String>> getRoleInfoByOrgIdAndHq(JSONObject objs) throws IqbException,IqbSqlException;
    
    /**
     * 
     * Description: 查询全部通用角色信息
     * @param
     * @return List<SysStationRole>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月13日 下午9:09:52
     */
 /*   public List<SysStationRole> selectHqStationRoleName(JSONObject objs) throws IqbException, IqbSqlException;*/

    public PageInfo<SysStationRole> getStationRole(JSONObject objs) throws IqbException;

    // 按照ID查询角色
    public SysStationRole getSysStationRoleById(JSONObject objs) throws IqbException;
    
    //查询所有的角色
    public List<SysStationRole> getStationNameAll() throws IqbException, IqbSqlException;

    /**
     * 
     * Description: 根据菜单id查询是否有角色使用该菜单
     * @param
     * @return Integer
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月13日 下午9:14:56
     */
    public boolean isMenuIdBeUsedFromHqRole(Integer menuId) throws IqbException, IqbSqlException;
  
    /*
     * 查询通用角色树
     */
    public List<TreeNode> selectHqStation()throws IqbException, IqbSqlException;
    /**
     * 添加角色
     * @param objs
     * @throws IqbException
     * @throws IqbSqlException
     */
    public void insertStationRole(JSONObject objs)throws IqbException, IqbSqlException;
    
    
    /**
     *  Description:根据通用角色id查询分配的机构
     * @param id
     * @return
     *@author leiwenyang
     *
     */
    Integer selectAuthorizeOrgan(Integer id) throws IqbException, IqbSqlException;
  

}
