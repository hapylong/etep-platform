package com.iqb.etep.sysmanage.organization.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.sysmanage.organization.bean.SysOrganizationInfo;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;
public interface ISysOrganizationService {

	/**
     * 新增机构数据
     * @param objs
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    void insertSysOrganizationInfo(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 删除机构数据
     * @param objs
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    void deleteSysOrganizationInfoById(JSONObject objs) throws IqbException, IqbSqlException;
    
   /**
    * 修改机构数据
    * @param objs
    * @throws IqbException     
    * @throws IqbSqlException
    * @author baiyapeng
    */
    void updateSysOrganizationInfoById(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 根据ID查询机构数据
     * @param objs
     * @return
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    SysOrganizationInfo selectSysOrganizationInfoById(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 查询机构数据
     * @param objs
     * @return
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    List<TreeNode> selectSysOrganizationInfoForTree(JSONObject objs) throws IqbException, IqbSqlException;
     
    /**
     * 新增机构菜单权限数据
     * @param objs
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    void insertSysOrganizationPurview(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 查询机构菜单权限数据
     * @param objs
     * @return
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    List<Integer> selectSysOrganizationPurview(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 统计机构菜单权限数据
     * @param menuId
     * @return
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    boolean selectCountOrganizationPurviewByMenuId(Integer menuId) throws IqbException, IqbSqlException; 
    
    /**
     * 查询机构类型(数据字典)
     * @param objs
     * @return
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    List<Map<String, Object>> selectOrgTpye(JSONObject objs) throws IqbException, IqbSqlException; 
    
    /** 
     * Description: 获取所有的机构信息
     * @param
     * @return 
     * @throws IqbException
     * @throws IqbSqlException
     * @author wangxinbang
     */
    public List<Map<Integer, String>> getAllOrgInfo() throws IqbException, IqbSqlException ;
   
    public abstract List<Map<String, Object>> selectOrgToListOfMap(JSONObject paramJSONObject)
            throws IqbException, IqbSqlException;

    /** 
     * Description: 获取机构下边部门信息
     * @param
     * @return 
     * @throws IqbException
     * @throws IqbSqlException
     * @author wangxinbang
     */
    public List<Map<Integer, String>> selectOrganizationDept(JSONObject objs)throws IqbException ,IqbSqlException ;

    /**
     * 
     * Description: 将机构信息推送至crm
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月22日 上午11:05:16
     */
    public void pushOrgInfoToCRM(JSONObject objs) throws IqbException;


}
