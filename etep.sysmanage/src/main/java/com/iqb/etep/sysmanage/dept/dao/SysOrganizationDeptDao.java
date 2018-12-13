package com.iqb.etep.sysmanage.dept.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.sysmanage.dept.bean.SysOrganizationDeptInfo;


public interface SysOrganizationDeptDao {
    
    /**
     * 插入数据
     * @param record
     * @return
     * @author baiyapeng
     */
    int insert(SysOrganizationDeptInfo record);
    
    /**
     * 删除数据
     * @param id
     * @return
     * @author baiyapeng
     */
    int deleteByPrimaryKey(Integer id);
    
    /**
     * 删除数据
     * @param orgId
     * @return
     * @author baiyapeng
     */
    int deleteByOrgId(Integer orgId);
    
    /**
     * 选择性更新数据
     * @param record
     * @return
     * @author baiyapeng
     */
    int updateByPrimaryKeySelective(SysOrganizationDeptInfo record);
    
    /**
     * 更新数据
     * @param record
     * @return
     * @author baiyapeng
     */
    int updateByPrimaryKey(SysOrganizationDeptInfo record);
    
    /**
     * 根据主键查询数据
     * @param id
     * @return
     * @author baiyapeng
     */
    SysOrganizationDeptInfo selectByPrimaryKey(Integer id);
    
    /**
     * 查询数据
     * @param objs
     * @return
     * @author baiyapeng
     */
    List<SysOrganizationDeptInfo> selectSelective(JSONObject objs);
    
    /**
     * 选择性统计数据
     * @param record
     * @return
     * @author baiyapeng
     */
    int selectCountSelective(SysOrganizationDeptInfo record);
    
}