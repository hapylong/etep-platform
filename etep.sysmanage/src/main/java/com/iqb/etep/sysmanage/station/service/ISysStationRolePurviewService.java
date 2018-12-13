/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : ISysStationRolePurviewService.java
 *
 * PURPOSE : 
 *
 * AUTHOR : leiwenyang
 *
 *
 * 创建日期: 2016年8月18日
 * HISTORY：
 * 变更日期 
 */
package com.iqb.etep.sysmanage.station.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;


/**
 * @author leiwenyang
 *
 */
public interface ISysStationRolePurviewService{

    public void insertPurview(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 
     * Description: 获取角色菜单权限
     * @param
     * @return List<TreeNode>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月26日 下午1:13:19
     */
    public List<TreeNode> getSysRolePurview(JSONObject objs) throws IqbException, IqbSqlException ;
    
    /**
     * 
     * Description: 获取角色菜单权限
     * @param
     * @return List<TreeNode>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月26日 下午2:36:50
     */
    public List<TreeNode> getSysRolePurview(Integer roleId) throws IqbException, IqbSqlException ;

    /**
     * 
     * Description: 获取机构id集合
     * @param
     * @return List<String>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年10月21日 下午12:01:09
     */
    public List<String> getStationIdList(List<String> stationCodeList);

    /**
     * 
     * Description: 获取角色菜单权限
     * @param
     * @return List<TreeNode>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年10月21日 下午2:01:17
     */
    public List<TreeNode> getSysRolePurview(List<String> stationIdList);

  
}
