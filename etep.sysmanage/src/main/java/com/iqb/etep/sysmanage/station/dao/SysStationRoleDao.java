/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : SysStationRoleDao.java
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
package com.iqb.etep.sysmanage.station.dao;

import java.util.List;
import java.util.Map;

import tk.mybatis.mapper.common.Mapper;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.sysmanage.organization.bean.SysStationOrganization;
import com.iqb.etep.sysmanage.station.bean.SysStationRole;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;
import com.iqb.etep.sysmanage.user.bean.SysUser;


/**
 * @author leiwenyang
 *
 */
public interface SysStationRoleDao extends Mapper<SysStationRole>{

    /**
     * @param id
     * @return
     */
    SysStationRole selectStationRoleById(int  id);

    /**
     * @param bean
     */
    void updateSysStationRole(SysStationRole bean);

    /**
     * @param list
     */
    void deleteSysStationRoles(List<Integer> list);

    /**
     * @return
     */
    List<Map<Integer, String>> selectStationName(String userCode);
    

    /**
     * @return
     */
    List<SysStationRole> selectHqStationNameByUserCode(String userCode);
    
    
    /**
     * 
     * Description: 获取所有通用的角色信息
     * @param
     * @return List<SysStationRole>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月13日 下午9:07:25
     */
    List<Map<Integer, String>> selectHqStationName();

    /**
     * @param objs
     * @return
     */
    List<SysStationRole> stationRoleByPage(JSONObject objs);

    /**
     * @param id
     * @return
     */
    SysStationRole getSysStationRoleById(Integer id);

    /**
     * @param bean
     */
    void updateSysStationRoleLastUser(SysStationRole bean);

    /**
     * 
     * Description: 保存角色信息
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月29日 下午11:32:12
     */
    void saveRoleInfo(JSONObject objs);

    /**
     * 
     * Description: 根据机构id查询角色信息
     * @param
     * @return List<SysStationRole>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月29日 下午11:52:02
     */
    List<SysStationRole> getUserEnableRole(JSONObject objs);

    /**
     * @param bean
     * 保存角色信息
     */
    void insertSysStationRole(SysStationRole bean);

    /**
     * @param stationRoleName
     * 角色名不能重复
     * @return
     */
    SysStationRole selectExistStationName(String stationRoleName);

    /**
     * @param stationCode
     * 角色编码不能重复
     * @return
     */
    SysStationRole selectExistStationCode(String stationCode);

    /**
     * @return
     * 查询所有机构角色信息
     */
    List<SysStationRole> selectStationRoleAll(Integer id);

    /**
     * @return
     * 查询所有角色信息
     */
    List<SysStationRole> getStationNameAll();

    /**
     * @param stationCode
     * @return
     * 查询角色分配的用户
     */
    List<SysUser> selectAuthorizeUser(String stationCode);

    /**
     * @param bean
     * @return
     */
    int selectStationRoleCount(SysStationRole bean);


    /**
     * @param orgId
     * @return
     */
    int selectOrganizationStations(Integer orgId);


    /**
     * 
     * Description: 获取通用角色
     * @param
     * @return List<SysStationRole>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月13日 下午7:22:27
     */
    List<SysStationRole> getHqStationRoleByPage(JSONObject objs);

    /**
     * 
     * Description: 根据菜单id查询使用该菜单的角色数量
     * @param
     * @return Integer
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月13日 下午9:19:42
     */
    Integer getHqRoleCountByMenuId(Integer menuId);

    /**
     * 
     * Description: 根据传入信息获取角色信息
     * @param
     * @return SysStationRole
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月14日 上午11:28:39
     */
    List<SysStationRole> getHqStationRole(SysStationRole bean);


 
 

	List<Map<Integer, String>> selectHqStationNameCode(String userCode);
	
	
	

    /**
     * @return
     */
    List<TreeNode> selectHqStation();



    /**
     * @param orgId
     * @return
     */
    List<TreeNode> getHqSysStation(Integer orgId);

    /**
     * @param paraList
     */
    void insertSysStationOrgan(List<SysStationOrganization> paraList);

    /**
     * @param newstationid
     */
    void deleteStationRoleOrgId(Integer newstationid);

    /**
     * @param id
     * @return
     */
    Integer selectAuthorizeOrgan(Integer id);

    /**
     * 
     * Description: 
     * @param
     * @return List<Map<Integer,String>>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年10月19日 下午5:05:48
     */
    public List<Map<Integer, String>> getRoleInfoByOrgIdAndHq(String orgId);

    /**
     * 
     * Description: 根据机构id获取通用角色信息
     * @param
     * @return List<Map<Integer,String>>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年10月20日 上午10:12:12
     */
    public List<Map<Integer, String>> getHqRoleInfoByOrgId(String orgId);

    /**
     * 
     * Description: 根据机构id获取角色信息
     * @param
     * @return List<Map<Integer,String>>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年10月20日 上午10:12:27
     */
    public List<Map<Integer, String>> getRoleInfoByOrgId(String orgId);

    /**
     * 
     * Description: 保存通用角色信息
     * @param
     * @return List<Map<Integer,String>>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年10月20日 上午11:09:32
     */
    public Integer saveUserHqRoleInfo(SysUser sysUser);

    /**
     * 
     * Description: 查询通用角色信息
     * @param
     * @return List<Map<Integer,String>>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年10月20日 上午11:39:02
     */
    public List<Map<Integer, String>> selectHqUserStationName();

    /**
     * 
     * Description: 获取所有的角色信息
     * @param
     * @return List<Map<String,Object>>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年10月20日 上午11:57:30
     */
    public List<Map<String, Object>> getAllStationInfoForSelect();










}
