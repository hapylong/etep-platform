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
package com.iqb.etep.sysmanage.station.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.sysmanage.station.bean.SysStationRole;

/**
 * @author leiwenyang
 */
public interface ISysStationRoleService{

    // 添加角色
    public void insertSysStationRole(JSONObject objs) throws IqbException, IqbSqlException;

    // 删除角色
    public void deleteSysStationRole(JSONObject objs) throws IqbException, IqbSqlException;

    // 修改角色
    public void updateSysStationRole(JSONObject objs) throws IqbException, IqbSqlException;

    // 查询机构下面角色
    public   List<Map<Integer,String>> selectStationRoleName(JSONObject objs) throws IqbException,
        IqbSqlException;

    public PageInfo<SysStationRole> getStationRole(JSONObject objs) throws IqbException;

    // 按照ID查询角色
    public SysStationRole getSysStationRoleById(JSONObject objs) throws IqbException;

    // 根据机构id查询角色信息
    public List<SysStationRole> getUserEnableRole(JSONObject objs) throws IqbException,
        IqbSqlException;

   
    // 角色授权的用户
    public Integer selectAuthorizeUser(String stationCode) throws IqbException, IqbSqlException;



    // 查询机构下边角色
    public Boolean selectOrganizationStations(Integer orgId) throws IqbException, IqbSqlException;
    
    public  List<Map<Integer,String>>  selectHqStationRoleName(String userCode) throws IqbException,
    IqbSqlException;
    

}
