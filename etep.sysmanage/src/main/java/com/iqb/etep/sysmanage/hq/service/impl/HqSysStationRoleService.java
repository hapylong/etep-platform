/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : SysStationRoleService.java
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
package com.iqb.etep.sysmanage.hq.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.base.CommonReturnInfo;
import com.iqb.etep.common.base.service.BaseService;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.common.utils.Attr.CommonConst;
import com.iqb.etep.common.utils.BeanUtil;
import com.iqb.etep.common.utils.JSONUtil;
import com.iqb.etep.common.utils.NumberUtil;
import com.iqb.etep.common.utils.StringUtil;
import com.iqb.etep.common.utils.SysUserSession;
import com.iqb.etep.sysmanage.base.SysManageAttr.StationRoleAttr;
import com.iqb.etep.sysmanage.base.SysManageReturnInfo;
import com.iqb.etep.sysmanage.hq.service.IHqSysStationRoleService;
import com.iqb.etep.sysmanage.organization.bean.SysStationOrganization;
import com.iqb.etep.sysmanage.organization.service.impl.SysStationOrganService;
import com.iqb.etep.sysmanage.station.bean.SysStationRole;
import com.iqb.etep.sysmanage.station.biz.SysStationRoleBiz;
import com.iqb.etep.sysmanage.station.service.impl.SysStationPurviewService;
import com.iqb.etep.sysmanage.station.service.impl.SysStationRoleService;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;
import com.iqb.etep.sysmanage.sysmenu.service.ISysMenuService;
import com.iqb.etep.sysmanage.user.bean.SysUser;
import com.iqb.etep.sysmanage.user.biz.SysUserBiz;
import com.iqb.etep.sysmanage.user.service.impl.SysUserService;

/**
 * @author leiwenyang
 */
@SuppressWarnings({"unused"})
@Service
public  class HqSysStationRoleService extends BaseService implements IHqSysStationRoleService{

    @Autowired
    SysStationRoleBiz sysStationRoleBiz;

    @Autowired
    private SysUserSession sysUserSession;

    @Autowired
    SysUserBiz sysUserBiz;

    @Autowired
    private SysUserService sysUserService;
    
    @Autowired
    private SysStationPurviewService sysStationPurviewService;
    
    @Autowired
    private ISysMenuService sysMenuService;
    
    @Autowired
    private SysStationRoleService newsysStationRoleService;
    


    /**
     * 功能:新增岗位信息
     */
    public void insertSysStationRole(JSONObject objs) throws IqbException, IqbSqlException {
        SysStationRole bean = JSONUtil.toJavaObject(objs, SysStationRole.class);
         synchronized (HqSysStationRoleService.class) {
            sysMenuService.removeAllUserSysMenuList();
            if (CheckoutMethod(bean) == true) {
                    bean.setCreateTime(NumberUtils.toInt(System.currentTimeMillis() / 1000 + ""));// 时间戳
                    bean.setCreateUser(sysUserSession.getUserCode());//
                    bean.setLastTime(NumberUtils.toInt(System.currentTimeMillis() / 1000 + ""));// 时间戳
                    bean.setLastUser(sysUserSession.getUserCode());
                    bean.setVersion(NumberUtils.toInt(CommonConst.Version));// 版本号
                    bean.setDeleteFlag(1);
                if (sysStationRoleBiz.selectStationRoleCount(bean) < 1) {
                    sysStationRoleBiz.insertSysStationRole(bean);
                } else {
                    throw new IqbException(CommonReturnInfo.BASE00090002);
                }
            } else {
                throw new IqbException(SysManageReturnInfo.SYS_STATION_01030004);

            }
        }
    }

    /**
     * @param objs
     *            删除角色信息
     */
    public Integer deleteSysStationRole(JSONObject objs) throws IqbException, IqbSqlException {
           synchronized (HqSysStationRoleService.class) {
            sysMenuService.removeAllUserSysMenuList();
            SysStationRole bean = JSONUtil.toJavaObject(objs, SysStationRole.class);
            Integer id = bean.getId();
            SysStationRole dbBean = sysStationRoleBiz.getSysStationRoleById(id);
            if (sysStationRoleBiz.selectCount(bean) > 0) {
                 if(selectAuthorizeUser(dbBean.getStationCode())==0){
                   if(selectAuthorizeOrgan(id)==0){
                    bean.setLastUser(sysUserSession.getUserCode());   
                    bean.setLastTime(NumberUtils.toInt(System.currentTimeMillis() + ""));
                    sysStationPurviewService.deleteByRoleId(id);//删除菜单权限
                    sysStationRoleBiz.updateSysStationRoleLastUser(bean);
                 return 0;
                 }else{
                   throw new IqbException(SysManageReturnInfo.SYS_STATION_0103006);
                   }
                 }
                 else{
                  throw new IqbException(SysManageReturnInfo.SYS_STATION_01030005);
                  }
            } else {
                throw new IqbException(CommonReturnInfo.BASE00090002);
            }
        }

    }



    /**
     * 功能:修改岗位信息
     * 
     * @param objs
     * @throws IqbException
     */
    public void updateHqSysStationRole(JSONObject objs) throws IqbException, IqbSqlException {
        synchronized (HqSysStationRoleService.class) {
            sysMenuService.removeAllUserSysMenuList();
            SysStationRole bean = JSONObject.toJavaObject(objs, SysStationRole.class);
            SysStationRole paramStationRole = new SysStationRole();
            paramStationRole.setId(bean.getId());
            if (checkoutStationMethod(bean) == true) {
                if (sysStationRoleBiz.selectCount(paramStationRole) > 0) {

                    sysStationRoleBiz.updateSysStationRole(bean);
                } else {
                    throw new IqbException(CommonReturnInfo.BASE00000001);
                }
            } else {
                throw new IqbException(SysManageReturnInfo.SYS_STATION_01030004);
            }
        }
    }
 /**
     * @return
     */
    public List<Map<Integer, String>> selectStationRoleName(JSONObject objs) throws IqbException,IqbSqlException {
        synchronized (HqSysStationRoleService.class) {
            sysMenuService.removeAllUserSysMenuList();
            String userCode = objs.get("userCode").toString();
            String isHq = objs.get("isHq").toString();
            List<Map<Integer, String>> hqStationnameList = new ArrayList<Map<Integer, String>>();
            if (isHq.equals("1")) {
                if (userCode != null && !userCode.equals("")) {
                    hqStationnameList = sysStationRoleBiz.selectHqStationName();
                    if (hqStationnameList.size() > 0) {
                        return hqStationnameList;
                    } else {
                        return null;
                    }
                } else {
                    throw new IqbException(SysManageReturnInfo.SYS_USER_0102009);
                }
            } else {
                hqStationnameList = newsysStationRoleService.selectHqStationRoleName(userCode);
                if (hqStationnameList.size() > 0) {
                    return hqStationnameList;
                } else {
                    throw new IqbException(SysManageReturnInfo.SYS_STATION_O103008);
                }
            }

        }

    }
    
    @Override
    public List<Map<Integer, String>> getRoleInfoByOrgIdAndHq(JSONObject objs) throws IqbException,IqbSqlException {
        sysMenuService.removeAllUserSysMenuList();
        String orgId = objs.getString("orgId");
        String isHq = objs.getString("isHq");
        /** 校验机构orgId **/
        if(StringUtil.isEmpty(orgId)){
            return null;
        }
        /** 判断是否是通用角色  **/
        if(!StringUtil.isEmpty(isHq) && StationRoleAttr.STATION_IS_HQ.equals(isHq)){
            return sysStationRoleBiz.selectHqUserStationName();
        }
        return sysStationRoleBiz.getRoleInfoByOrgIdAndHq(orgId);
    }


    /**
     * 功能:分页查询
     * 
     * @param objs
     * @return
     */
    public PageInfo<SysStationRole> getStationRole(JSONObject objs) throws IqbException,
        IqbSqlException {

        return new PageInfo<SysStationRole>(sysStationRoleBiz.stationRoleByPage(objs));
    }

    /**
     * 
     * Description: 获取通用角色信息
     * @param
     * @return PageInfo<SysStationRole>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月13日 下午7:20:32
     */
    public PageInfo<SysStationRole> getHqStationRole(JSONObject objs) throws IqbException,IqbSqlException {
        return new PageInfo<SysStationRole>(sysStationRoleBiz.getHqStationRoleByPage(objs));
    }

    /**
     * 功能:按照主键查询
     * 
     * @param objs
     * @return
     */
    public SysStationRole getSysStationRoleById(JSONObject objs) throws IqbException,
        IqbSqlException {
        synchronized (HqSysStationRoleService.class) {
            SysStationRole stationRole = JSONObject.toJavaObject(objs, SysStationRole.class);
            if (sysStationRoleBiz.selectCount(stationRole) > 0) {
                return sysStationRoleBiz.getSysStationRoleById(stationRole.getId());
            } else {
                throw new IqbException(CommonReturnInfo.BASE00090002);
            }

        }

    }

    /**
     * Description: 根据机构id查询角色信息
     * 
     * @param
     * @return List<SysStationRole>
     * @throws
     * @Author wangxinbang Create Date: 2016年8月29日 下午11:50:44
     */
    public List<SysStationRole> getUserEnableRole(JSONObject objs) throws IqbException,
        IqbSqlException {
        if (objs == null || (objs.get("orgId") == null || "".equals(objs.get("orgId")))) {
            throw new IqbException(CommonReturnInfo.BASE00090004);
        }
        synchronized (HqSysStationRoleService.class) {
            return sysStationRoleBiz.getUserEnableRole(objs);
        }
    }

    /**
     * @return 查询所有角色信息
     */
    public List<SysStationRole> getStationNameAll() throws IqbException, IqbSqlException {
        synchronized (HqSysStationRoleService.class) {
            List<SysStationRole> list = sysStationRoleBiz.getStationNameAll();
            if (list.size() > 0) {
                return list;
            } else {
                throw new IqbException(CommonReturnInfo.BASE00000000);
            }
        }
    }

    /**
     * Description: 保存角色信息
     * 
     * @param
     * @return void
     * @throws
     * @Author wangxinbang Create Date: 2016年8月29日 下午11:27:49
     */
    public void saveRoleInfo(JSONObject objs) throws IqbException, IqbSqlException {
        if (objs == null || (objs.get("userCode") == null || "".equals(objs.get("userCode")))) {
            throw new IqbException(CommonReturnInfo.BASE00090004);
        }
        synchronized (HqSysStationRoleService.class) {
            sysMenuService.removeAllUserSysMenuList();
            sysStationRoleBiz.saveRoleInfo(objs);
        }
    }

    /**
     * 功能:校验重要字段是否为空
     * 
     * @throws IqbException
     */
    private boolean CheckoutMethod(SysStationRole bean) throws IqbException {
        if(bean.getStationStatus() == null){
            return false;
        }
        if(bean.getStationIsSuperadmin() == null){
            return false;
        }
        if(StringUtil.isEmpty(bean.getStationCode())){
            return false;
        }
        if(StringUtil.isEmpty(bean.getStationRoleName())){
            return false;
        }
        SysStationRole paramBean;
        paramBean = new SysStationRole();
        paramBean.setStationCode(bean.getStationCode());
        if (sysStationRoleBiz.isHqStationRoleExit(paramBean)){
            throw new IqbException(SysManageReturnInfo.SYS_STATION_01030001);
        }
        paramBean.setStationCode(null);
        paramBean.setStationRoleName(bean.getStationRoleName());
       /* if (sysStationRoleBiz.isHqStationRoleExit(paramBean)){
            throw new IqbException(SysManageReturnInfo.SYS_STATION_01030002);
        }*/
        return true;

    }

    /**
     * 功能:修改校验字段
     * 
     * @throws IqbException
     */
    private boolean checkoutStationMethod(SysStationRole bean) throws IqbException {
        if(bean.getStationStatus() == null){
            return false;
        }
        if(bean.getStationIsSuperadmin() == null){
            return false;
        }
        if(StringUtil.isEmpty(bean.getStationCode())){
            return false;
        }
        if(StringUtil.isEmpty(bean.getStationRoleName())){
            return false;
        }
        SysStationRole paramBean;
        paramBean = new SysStationRole();
        paramBean.setStationCode(bean.getStationCode());
        paramBean.setId(bean.getId());
        if (sysStationRoleBiz.isHqStationRoleExitForUpdate(paramBean)){
            throw new IqbException(SysManageReturnInfo.SYS_STATION_01030001);
        }
        paramBean.setStationCode(null);
        paramBean.setStationRoleName(bean.getStationRoleName());
       
        return true;
    }

    /**
     * 修改时：角色编码 角色名不能重复
     * 
     * @throws IqbException
     */
    private boolean checkImporantField(SysStationRole bean) throws IqbException {
        List<SysStationRole> stationnamelist = sysStationRoleBiz.selectStationRoleAll(bean.getId());
        for (int i = 0; i < stationnamelist.size(); i++) {
            System.out.println(stationnamelist.get(i).getStationCode());
            if (stationnamelist.get(i).getStationCode().equals(bean.getStationCode().trim())) {
                throw new IqbException(SysManageReturnInfo.SYS_STATION_01030001);// 角色编码不能重复
            }
        }
       
        return true;
    }

    /**
     * 用户表角色外键为NULL
     */
    public void updateAuthorizeUserStationId(JSONObject objs) throws IqbException {
        synchronized (HqSysStationRoleService.class) {
           Integer id =  NumberUtils.toInt(objs.get("id").toString());
            SysStationRole bean = new SysStationRole();
            bean.setId(id);
            if (sysStationRoleBiz.selectCount(bean) > 0) {
                bean.setLastUser(sysUserSession.getUserCode());
                bean.setLastTime(NumberUtils.toInt(System.currentTimeMillis() + ""));
                //菜单权限表删除
                sysStationPurviewService.deleteByRoleId(id);//删除菜单权限
                updateAuthorizeUser(bean.getStationCode());
                sysStationRoleBiz.updateSysStationRoleLastUser(bean);
            } else {
                throw new IqbException(CommonReturnInfo.BASE00090002);
            }
        }
    }
    
    /**
     * 查询角色授权的用户
     */

    public Integer selectAuthorizeUser(String stationCode) throws IqbException, IqbSqlException {
        synchronized (HqSysStationRoleService.class) {
            // 通过stationid来查询有哪些用户关联 synchronized (SysStationRoleService.class)
            {
                List<SysUser> liststationUsers = sysStationRoleBiz.selectAuthorizeUser(stationCode);
                return liststationUsers.size();
            }
        }
    }
    
    public Integer selectAuthorizeOrgan(Integer id)throws IqbException,IqbSqlException{
        synchronized(HqSysStationRoleService.class){
         return  sysStationRoleBiz.selectAuthorizeOrgan(id);
        }
    }

    /**
     * 该角色关联的用户stationid置为空
     */
    public void updateAuthorizeUser(String stationCode) throws IqbException, IqbSqlException {
        synchronized (HqSysStationRoleService.class) {
            List<SysUser> liststationUsers = sysStationRoleBiz.selectAuthorizeUser(stationCode);
            if (liststationUsers.size() > 0) {
                sysUserBiz.updateStationId(liststationUsers);

            }
        }
    }

    @Override
    public boolean isMenuIdBeUsedFromHqRole(Integer menuId) throws IqbException, IqbSqlException {
        if(menuId == null){
            return false;
        }
        Integer ret = sysStationRoleBiz.getHqRoleCountByMenuId(menuId);
        return ret == null || ret == 0 ? false : true;
    }

    /**
     * @return
     */
    public List<TreeNode> selectHqStation() {
       
        return sysStationRoleBiz.selectHqStation();
    }

    /**
     * @param objs
     * @return
     */
    public List<TreeNode> getHqSysStation(JSONObject objs) {
      if(objs==null ||objs.get("orgId")==null){
          return null;
      }
        Integer orgId=NumberUtil.toInt(objs.get("orgId"));
       return sysStationRoleBiz.getHqSysStation(orgId);
    }

    /**
     * @param objs
     */
    public void insertStationRole(JSONObject objs)throws IqbException, IqbSqlException {
      
        if(objs==null || objs.get("orgId")==null){
            throw new IqbException(CommonReturnInfo.BASE00090001);
        }
           synchronized (SysStationOrganService.class) {
                // 传过来参数
               
                 Integer newOrgId = NumberUtils.toInt(objs.get("orgId").toString());
                 sysStationRoleBiz.deleteStationRoleOrgId(newOrgId);
                 if(objs.get("newMenuIds") instanceof String){//字符串
                     SysStationOrganization stationOrgan = new SysStationOrganization();
                     stationOrgan.setCreateTime(NumberUtils.toInt(System.currentTimeMillis() / 1000 + ""));
                     stationOrgan.setCreateUser(sysUserSession.getUserCode());
                     stationOrgan.setOrgId(newOrgId);
                    stationOrgan.setStationId(Integer.parseInt(objs.get("newMenuIds").toString()));
                    List<SysStationOrganization> paraList = new ArrayList<SysStationOrganization>();
                    paraList.add(stationOrgan);
                    sysStationRoleBiz.insertSysStationOrgan(paraList);
                 }else{
                     List<String> newStationIds = (List<String>) objs.get("newMenuIds");//前台传值ArrayList
                     sysStationRoleBiz.deleteStationRoleOrgId(newOrgId);
                     List<SysStationOrganization> paraList = new ArrayList<SysStationOrganization>();
                  if(newStationIds == null){
                     return;
                  }
                for (int i = 0; i < newStationIds.size(); i++) {
                    SysStationOrganization stationOrgan = new SysStationOrganization();
                    stationOrgan.setOrgId(newOrgId);
                    stationOrgan.setStationId(Integer.parseInt(newStationIds.get(i)));
                    stationOrgan.setCreateTime(NumberUtils.toInt(System.currentTimeMillis() / 1000 + ""));
                    stationOrgan.setCreateUser(sysUserSession.getUserCode());
                    paraList.add(stationOrgan);
                    }
                 
                if(paraList.size() > 0){
                    sysStationRoleBiz.insertSysStationOrgan(paraList);
                    
                }
                 }
            }
    }

    /**
     * 
     * Description: 保存通用角色信息
     * @param
     * @return List<Map<Integer,String>>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年10月20日 上午10:57:03
     */
    public void saveUserHqRoleInfo(JSONObject objs) throws IqbException, IqbSqlException {
        /** 校验传入参数  **/
        if(CollectionUtils.isEmpty(objs)){
            throw new IqbException(SysManageReturnInfo.SYS_STATION_O103009);
        }
        if(StringUtil.isEmpty(objs.getString("userCode"))){
            throw new IqbException(SysManageReturnInfo.SYS_STATION_O103009);
        }
        SysUser sysUser = BeanUtil.mapToBean(objs, SysUser.class);
        sysStationRoleBiz.saveUserHqRoleInfo(sysUser);
    }

    /**
     * 
     * Description: 获取所有的角色信息
     * @param
     * @return List<Map<String,Object>>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年10月20日 上午11:56:12
     */
    public List<Map<String, Object>> getAllStationInfoForSelect() {
        return sysStationRoleBiz.getAllStationInfoForSelect();
    }
  
}
