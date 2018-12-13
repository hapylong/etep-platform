package com.iqb.etep.sysmanage.dept.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.sysmanage.dept.bean.SysOrganizationDeptInfo;


public interface ISysOrganizationDeptService {
    
    /**
     * 新增部门数据
     * @param objs
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    void insertSysOrganizationDeptInfo(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 删除部门数据
     * @param objs
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
     void deleteSysOrganizationDeptInfoById(JSONObject objs) throws IqbException, IqbSqlException;
     
    /**
     * 删除部门数据
     * @param objs
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
     void deleteSysOrganizationDeptInfoByOrgId(Integer orgId) throws IqbException, IqbSqlException;
    
    /**
     * 修改部门数据
     * @param objs
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
     void updateSysOrganizationDeptInfoById(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 根据ID查询部门数据
     * @param objs
     * @return
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    SysOrganizationDeptInfo selectSysOrganizationDeptInfoById(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 查询部门数据
     * @param objs
     * @return
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    PageInfo<SysOrganizationDeptInfo> selectSysOrganizationDeptInfoForGrid(JSONObject objs) throws IqbException, IqbSqlException;
}
