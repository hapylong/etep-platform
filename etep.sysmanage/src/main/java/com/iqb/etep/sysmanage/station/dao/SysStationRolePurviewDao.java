/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : SysStationRolePurviewDao.java
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
package com.iqb.etep.sysmanage.station.dao;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

import com.iqb.etep.sysmanage.station.bean.SysStationRolePurview;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;

/**
 * @author leiwenyang
 */
public interface SysStationRolePurviewDao extends Mapper<SysStationRolePurview>{



    /**
     * @param sysList
     */
    void insertPurview(List<SysStationRolePurview> sysList);

    /**
     * 
     * Description: 查询角色菜单权限
     * @param
     * @return List<TreeNode>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月26日 下午1:15:03
     */
    List<TreeNode> selectSelective(Integer roleId);

    /**
     * 
     * Description: 根据角色id删除角色信息
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月26日 下午1:41:28
     */
    void deleteByRoleId(Integer newstationid);

    /**
     * 
     * Description: 获取角色id集合
     * @param
     * @return List<String>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年10月21日 下午12:41:09
     */
    public List<String> getStationIdList(List<String> stationCodeList);

    /**
     * 
     * Description: 获取角色菜单权限
     * @param
     * @return List<TreeNode>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年10月21日 下午2:02:44
     */
    public List<TreeNode> selectSelectiveByStationIdList(List<String> stationIdList);

}
