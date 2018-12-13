/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : SysStationRoleBiz.java
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
package com.iqb.etep.sysmanage.station.biz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.iqb.etep.common.base.biz.CommonBiz;
import com.iqb.etep.sysmanage.organization.bean.SysStationOrganization;
import com.iqb.etep.sysmanage.station.bean.SysStationRole;
import com.iqb.etep.sysmanage.station.dao.SysStationRoleDao;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;
import com.iqb.etep.sysmanage.user.bean.SysUser;

/**
 * @author leiwenyang
 */
@Component
public class SysStationRoleBiz extends CommonBiz<SysStationRole>{

   @Autowired
   private SysStationRoleDao sysStationRoleDao;

    /**
     * @param id
     * @return
     */
    public SysStationRole selectStationRoleById(int id) {
        setDb(0, super.SLAVE);
         return sysStationRoleDao.selectStationRoleById(id);
    }

    /**
     * @param bean
     */
    public void updateSysStationRole(SysStationRole bean) {
        setDb(0, super.SLAVE);
        sysStationRoleDao.updateSysStationRole(bean);
    }

    /**
     * @param list
     */
    public void deleteSysStationRoles(List<Integer> list) {
        setDb(0, super.SLAVE);
        sysStationRoleDao.deleteSysStationRoles(list);
        
    }

    /**
     * @return
     */
    public List<Map<Integer, String>> selectStationName(String userCode) {
        setDb(0, super.SLAVE);
        return sysStationRoleDao.selectStationName(userCode);
    }
    

    /**
     * 
     * Description: 查询全部通用角色
     * @param
     * @return List<SysStationRole>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月13日 下午9:06:22
     */
    public  List<Map<Integer, String>> selectHqStationName1() {
        setDb(0, super.SLAVE);
        return sysStationRoleDao.selectHqStationName();
    }

    /**
     * @param objs
     * @return
     */
    public List<SysStationRole> stationRoleByPage(JSONObject objs) {
        
        setDb(0, super.SLAVE);
        PageHelper.startPage(getPagePara(objs));
      
        
           return sysStationRoleDao.stationRoleByPage(objs);
    }
    
    /**
     * 
     * Description: 获取通用角色
     * @param
     * @return List<SysStationRole>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月13日 下午7:21:53
     */
    public List<SysStationRole> getHqStationRoleByPage(JSONObject objs) {
        setDb(0, super.SLAVE);
        PageHelper.startPage(getPagePara(objs));
        return sysStationRoleDao.getHqStationRoleByPage(objs);
    }

    /**
     * @param id
     * @return
     */
    public SysStationRole getSysStationRoleById(Integer id) {
        setDb(0, super.SLAVE);
        return  sysStationRoleDao.getSysStationRoleById(id);
    }

    /**
     * @param bean
     */
    public void updateSysStationRoleLastUser(SysStationRole bean) {
        setDb(0, super.SLAVE);
        sysStationRoleDao.updateSysStationRoleLastUser(bean);
        
    }

    /**
     * 
     * Description: 保存角色信息
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月29日 下午11:31:48
     */
    public void saveRoleInfo(JSONObject objs) {
        setDb(0, super.MASTER);
        sysStationRoleDao.saveRoleInfo(objs);
    }
    
    /**
     * 
     * Description: 根据机构id查询角色信息
     * @param
     * @return List<SysStationRole>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月29日 下午11:51:08
     */
    public List<SysStationRole> getUserEnableRole(JSONObject objs) {
        setDb(0, super.SLAVE);
        return sysStationRoleDao.getUserEnableRole(objs);
    }

    /**
     * @param bean
     * 添加角色信息管理信息
     */
    public void insertSysStationRole(SysStationRole bean) {
        // TODO Auto-generated method stub
        setDb(0, super.SLAVE);
        sysStationRoleDao.insertSysStationRole(bean);
    }

    /**
     * @param b
     * @return
     */
    public boolean selectStationCode(boolean b) {
        // TODO Auto-generated method stub
        return false;
    }

 
    /**
     * @param stationCode
     * 角色编码不能重复
     * @return
     */
    public  SysStationRole selectExistStationCode(String stationCode) {
        // TODO Auto-generated method stub
        setDb(0, super.SLAVE);
       return sysStationRoleDao.selectExistStationCode(stationCode);
 
    }

    /**
     * @param stationRoleName
     * 角色名不能重复
     * @return
     */
    public SysStationRole selectExistStationName(String stationRoleName) {
        // TODO Auto-generated method stub
        setDb(0, super.SLAVE);
        
        return sysStationRoleDao.selectExistStationName(stationRoleName);
    }

    /**
     * @return
     * 查询所有角色名和角色编码
     */
    public List<SysStationRole> selectStationRoleAll(Integer id) {
        // TODO Auto-generated method stub
        setDb(0, super.SLAVE);
        return sysStationRoleDao.selectStationRoleAll(id);
    }

    /**
     * @return
     * 查询所有角色名
     */
    public List<SysStationRole> getStationNameAll() {
        setDb(0, super.SLAVE);
        return  sysStationRoleDao.getStationNameAll();
    }



    /**
     * @param stationCode
     * @return
     */
    public List<SysUser> selectAuthorizeUser(String stationCode) {
        setDb(0, super.SLAVE);
        return sysStationRoleDao.selectAuthorizeUser(stationCode);
    }

    /**
     * @param bean
     * @return
     */
    public int selectStationRoleCount(SysStationRole bean) {
        setDb(0, super.SLAVE);
        return sysStationRoleDao.selectStationRoleCount(bean);
    }

    /**
     * 
     * Description: 根据菜单id查询使用该菜单的角色数量
     * @param
     * @return Integer
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月13日 下午9:18:57
     */
    public Integer getHqRoleCountByMenuId(Integer menuId) {
        setDb(0, super.SLAVE);
        return sysStationRoleDao.getHqRoleCountByMenuId(menuId);
    }

    /**
     * 
     * Description: 判断通用角色权限是否已存在
     * @param
     * @return boolean
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月14日 上午11:26:49
     */
    public boolean isHqStationRoleExit(SysStationRole bean) {
        setDb(0, super.SLAVE);
        List<SysStationRole> sysStationRoleList = sysStationRoleDao.getHqStationRole(bean);
        return CollectionUtils.isEmpty(sysStationRoleList) ? false : true;
    }
    
    /**
     * 
     * Description: 判断通用角色权限是否已存在
     * @param
     * @return boolean
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月14日 上午11:26:49
     */
    public boolean isHqStationRoleExitForUpdate(SysStationRole bean) {
        setDb(0, super.SLAVE);
        List<SysStationRole> sysStationRoleList = sysStationRoleDao.getHqStationRole(bean);
        if(CollectionUtils.isEmpty(sysStationRoleList)){
            return false;
        }
        if(sysStationRoleList.size() == 1){
            if(bean.getId().equals(sysStationRoleList.get(0).getId())){
                return false;
            }
        }
        return true;
    }


    /**
     * @param orgId
     * @return
     */
    public int selectOrganizationStations(Integer orgId) {
        setDb(0, super.SLAVE);
        return sysStationRoleDao.selectOrganizationStations(orgId);
    }

    /**
     * 
     * @param userCode
     * @return
     */
	public List<Map<Integer, String>> selectHqStationNameCode(String userCode) {
		  setDb(0, super.SLAVE);
	  return sysStationRoleDao.selectHqStationNameCode(userCode);
	}

	
	  /**
     * 
     * @param userCode
     * @return
     */
    public List<Map<Integer, String>> selectHqStationName() {
          setDb(0, super.SLAVE);
      return sysStationRoleDao.selectHqStationName();
    }

    /**
     * @return
     */
    public List<TreeNode> selectHqStation() {
        setDb(0, super.SLAVE);
        return sysStationRoleDao.selectHqStation();
    }

    /**
     * @param orgId
     * @return
     */
    public List<TreeNode> getHqSysStation(Integer orgId) {
        setDb(0, super.SLAVE);
        return  sysStationRoleDao.getHqSysStation(orgId);
    }

      /**
     * @param paraList
     */
    public void insertSysStationOrgan(List<SysStationOrganization> paraList) {
        setDb(0, super.SLAVE);
        sysStationRoleDao.insertSysStationOrgan(paraList);
        
    }

    /**
     * @param newstationid
     */
    public void deleteStationRoleOrgId(Integer newstationid) {
        setDb(0, super.SLAVE);
        sysStationRoleDao.deleteStationRoleOrgId(newstationid);
    }

    /**
     * @param id
     * @return
     */
    public Integer selectAuthorizeOrgan(Integer id) {
        setDb(0,super.SLAVE);
        return sysStationRoleDao.selectAuthorizeOrgan(id);
    }

    /**
     * 
     * Description: 根据机构id获取角色信息
     * @param
     * @return List<Map<Integer,String>>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年10月19日 下午5:05:07
     */
    public List<Map<Integer, String>> getRoleInfoByOrgIdAndHq(String orgId) {
        List<Map<Integer,String>> stationNameList = this.getRoleInfoByOrgId(orgId);
        List<Map<Integer,String>> hqStationnameList =this.getHqRoleInfoByOrgId(orgId);
        stationNameList.addAll(hqStationnameList);
        return stationNameList;
    }

    /**
     * 
     * Description: 根据机构id获取通用角色信息
     * @param
     * @return List<Map<Integer,String>>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年10月20日 上午10:11:01
     */
    private List<Map<Integer, String>> getHqRoleInfoByOrgId(String orgId) {
        setDb(0,super.SLAVE);
        return sysStationRoleDao.getHqRoleInfoByOrgId(orgId);
    }

    /**
     * 
     * Description: 根据机构id获取角色信息
     * @param
     * @return List<Map<Integer,String>>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年10月20日 上午10:10:31
     */
    private List<Map<Integer, String>> getRoleInfoByOrgId(String orgId) {
        return sysStationRoleDao.getRoleInfoByOrgId(orgId);
    }

    /**
     * 
     * Description: 保存通用角色信息
     * @param
     * @return List<Map<Integer,String>>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年10月20日 上午11:08:13
     */
    public Integer saveUserHqRoleInfo(SysUser sysUser) {
        setDb(0,super.MASTER);
        return sysStationRoleDao.saveUserHqRoleInfo(sysUser);
    }

    /**
     * 
     * Description: 查询通用角色信息
     * @param
     * @return List<Map<Integer,String>>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年10月20日 上午11:38:22
     */
    public List<Map<Integer, String>> selectHqUserStationName() {
        setDb(0,super.SLAVE);
        return sysStationRoleDao.selectHqUserStationName();
    }

    /**
     * 
     * Description: 获取所有的角色信息
     * @param
     * @return List<Map<String,Object>>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年10月20日 上午11:57:07
     */
    public List<Map<String, Object>> getAllStationInfoForSelect() {
        setDb(0,super.SLAVE);
        return sysStationRoleDao.getAllStationInfoForSelect();
    }

  


 
  
}
