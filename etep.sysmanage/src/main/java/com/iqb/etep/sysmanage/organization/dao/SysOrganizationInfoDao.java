package com.iqb.etep.sysmanage.organization.dao;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.sysmanage.organization.bean.SysOrganizationInfo;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;

public interface SysOrganizationInfoDao {
	
    
	/**
	 * 插入数据
	 * @param record
	 * @return
	 * @author baiyapeng
	 */
    int insert(SysOrganizationInfo record);
    
    /**
     * 删除数据
     * @param id
     * @return
     * @author baiyapeng
     */
    int deleteByPrimaryKey(Integer id);   

    /**
     * 选择性更新数据
     * @param record
     * @return
     * @author baiyapeng
     */
    int updateByPrimaryKeySelective(SysOrganizationInfo record);
    
    /**
     * 更新数据
     * @param record
     * @return
     * @author baiyapeng
     */
    int updateByPrimaryKey(SysOrganizationInfo record);
    
    /**
     * 根据主键查询数据
     * @param id
     * @return
     * @author baiyapeng
     */
    SysOrganizationInfo selectByPrimaryKey(Integer id);
    
    /**
     * 查询数据
     * @param objs
     * @return
     * @author baiyapeng
     */
    List<TreeNode> selectSelective(JSONObject objs);
    
    /**
     * 选择性统计数据
     * @param record
     * @return
     * @author baiyapeng
     */
    int selectCountSelective(SysOrganizationInfo record);
    
    /**
     * 查询最大机构编码
     * @param parentId
     * @return
     * @author baiyapeng
     */
    String selectMaxOrgCode(Integer parentId);
    
    /**
     * 统计子级机构
     * @param id
     * @return
     * @author baiyapeng
     */
    int selectCountChildLevel(Integer id);

    /**
     * @param objs
     * @return
     */
   
    List<Map<Integer, String>> selectOrganizationDept(String orgId);

    /**
     * 
     * Description: 根据机构编码获取机构信息
     * @param
     * @return SysOrganizationInfo
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月25日 下午4:24:08
     */
    public SysOrganizationInfo getSysOrganizationInfoByOrgCode(String orgCode);
    
    public abstract List<Map<String, Object>> selectOrgToListOfMap(SysOrganizationInfo paramSysOrganizationInfo);
}
