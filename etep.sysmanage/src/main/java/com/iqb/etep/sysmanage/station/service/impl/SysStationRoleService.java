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
package com.iqb.etep.sysmanage.station.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.base.CommonReturnInfo;
import com.iqb.etep.common.base.service.BaseService;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.common.utils.Attr.CommonConst;
import com.iqb.etep.common.utils.JSONUtil;
import com.iqb.etep.common.utils.SysUserSession;
import com.iqb.etep.sysmanage.base.SysManageReturnInfo;
import com.iqb.etep.sysmanage.station.bean.SysStationRole;
import com.iqb.etep.sysmanage.station.biz.SysStationRoleBiz;
import com.iqb.etep.sysmanage.station.service.ISysStationRoleService;
import com.iqb.etep.sysmanage.sysmenu.service.ISysMenuService;
import com.iqb.etep.sysmanage.user.bean.SysUser;
import com.iqb.etep.sysmanage.user.service.impl.SysUserService;

/**
 * @author leiwenyang
 */
@Service
public  class SysStationRoleService extends BaseService implements ISysStationRoleService{

    @Autowired
    SysStationRoleBiz sysStationRoleBiz;

    @Autowired
    private SysUserSession sysUserSession;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysStationPurviewService sysStationPurviewService;
    
    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 功能:新增角色信息
     */
    public void insertSysStationRole(JSONObject objs) throws IqbException, IqbSqlException {

        SysStationRole bean = JSONUtil.toJavaObject(objs, SysStationRole.class);

        synchronized (SysStationRoleService.class) {
            if (CheckoutMethod(bean) == true) {
                bean.setCreateTime(NumberUtils.toInt(System.currentTimeMillis() / 1000 + ""));// 时间戳
                bean.setCreateUser(sysUserSession.getUserCode());//
                bean.setLastTime(NumberUtils.toInt(System.currentTimeMillis() / 1000 + ""));
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
    public void deleteSysStationRole(JSONObject objs) throws IqbException, IqbSqlException {
        synchronized (SysStationRoleService.class) {
            SysStationRole bean = JSONUtil.toJavaObject(objs, SysStationRole.class);
            if(bean!=null && bean.getId()!=null && !bean.getId().equals("")){
            Integer id = bean.getId();
            SysStationRole dbBean = sysStationRoleBiz.getSysStationRoleById(id);
            if (sysStationRoleBiz.selectCount(bean) > 0) {
                if (selectAuthorizeUser(dbBean.getStationCode()) == 0) {
                    bean.setLastUser(sysUserSession.getUserCode());
                    bean.setLastTime(NumberUtils.toInt(System.currentTimeMillis() + ""));
                    sysStationPurviewService.deleteByRoleId(id);//删除菜单权限
                    sysStationRoleBiz.updateSysStationRoleLastUser(bean);
                } else {
                	//角色已被用户使用
                	throw new IqbException(SysManageReturnInfo.SYS_STATION_01030005);
                }
            } else {
                throw new IqbException(CommonReturnInfo.BASE00090003);
            }
            sysMenuService.removeAllUserSysMenuList();

          }else{
              throw new IqbException(SysManageReturnInfo.SYS_STATION_O103007);
          }
            }

    }

    /**
     * 功能:修改岗位信息
     * 
     * @param objs
     * @throws IqbException
     */
    public void updateSysStationRole(JSONObject objs) throws IqbException, IqbSqlException {
        synchronized (SysStationRoleService.class) {
             SysStationRole bean = JSONObject.toJavaObject(objs, SysStationRole.class);
             if(bean!=null && bean.getId()!=null && !bean.getId().equals("")){
             SysStationRole paramStationRole = new SysStationRole();
             paramStationRole.setId(bean.getId());
                 if (checkoutStationMethod(bean) == true) {
                     if (sysStationRoleBiz.selectCount(paramStationRole) > 0) {
                     sysStationRoleBiz.updateSysStationRole(bean);
                   } else {
                    throw new IqbException(CommonReturnInfo.BASE00090003);
                }
            } else {
                throw new IqbException(SysManageReturnInfo.SYS_STATION_01030004);
            }
                sysMenuService.removeAllUserSysMenuList();
            }else{
                throw new IqbException(SysManageReturnInfo.SYS_STATION_O103007);
            }
        }
            
           }
    

    /**
     * @return 查询机构下面角色
     */
    public  List<Map<Integer,String>>  selectStationRoleName(JSONObject objs) throws IqbException,
        IqbSqlException {
        synchronized (SysStationRoleService.class) {
            String userCode = objs.get("data").toString();
            if(userCode!=null && !userCode.equals("")){
            List<Map<Integer,String>>  stationNameList = sysStationRoleBiz.selectStationName(userCode);
            List<Map<Integer,String>> hqStationnameList =sysStationRoleBiz.selectHqStationNameCode(userCode);
            stationNameList.addAll(hqStationnameList);
           if (stationNameList.size() > 0) {
                return stationNameList;
            } else {
           
                throw new IqbException(SysManageReturnInfo.SYS_STATION_O103008);
            }
           } else{
               throw new IqbException(SysManageReturnInfo.SYS_USER_0102009);
           }

        }

    }
    /**
     * @return 查询机构下面角色
     */
    public  List<Map<Integer,String>>  selectHqStationRoleName(String userCode) throws IqbException,
        IqbSqlException {
        synchronized (SysStationRoleService.class) {
            List<Map<Integer,String>>  stationNameList = sysStationRoleBiz.selectStationName(userCode);
            List<Map<Integer,String>> hqStationnameList =sysStationRoleBiz.selectHqStationNameCode(userCode);
            stationNameList.addAll(hqStationnameList);
            return stationNameList;

        }

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
     * 功能:按照主键查询
     * 
     * @param objs
     * @return
     */
    public SysStationRole getSysStationRoleById(JSONObject objs) throws IqbException,
        IqbSqlException {
        synchronized (SysStationRoleService.class) {
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
        synchronized (SysStationRoleService.class) {
            return sysStationRoleBiz.getUserEnableRole(objs);
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
        synchronized (SysStationRoleService.class) {
            sysStationRoleBiz.saveRoleInfo(objs);
        }
    }

    /**
     * 功能:校验重要字段是否为空
     * 
     * @throws IqbException
     */
    private boolean CheckoutMethod(SysStationRole bean) throws IqbException {

        if (bean.getOrgId() != null && bean.getStationStatus() != null
            && bean.getStationIsSuperadmin() != null) {
            if (bean.getStationCode() != null && !bean.getStationCode().equals("")
                && bean.getStationRoleName() != null && !bean.getStationRoleName().equals("")) {
                if (sysStationRoleBiz.selectExistStationCode(bean.getStationCode()) == null)
                    // 不重复
                    return true;
                else {
                    throw new IqbException(SysManageReturnInfo.SYS_STATION_01030001);
                }
            }

        }
        return false;

    }

    /**
     * 功能:修改校验字段
     * 
     * @throws IqbException
     */
    private boolean checkoutStationMethod(SysStationRole bean) throws IqbException {

        if (bean.getOrgId() != null && bean.getRemark() != null && bean.getStationStatus() != null
            && bean.getStationIsSuperadmin() != null && bean.getStationCode() != null
            && !bean.getStationCode().equals("") && bean.getStationRoleName() != null
            && !bean.getStationRoleName().equals("")) {
            if (checkImporantField(bean) == true)
                return true;
        }
        return false;

    }

    /**
     * 修改时：角色编码 不能重复
     * 
     * @throws IqbException
     */
    private boolean checkImporantField(SysStationRole bean) throws IqbException {
        List<SysStationRole> stationnamelist = sysStationRoleBiz.selectStationRoleAll(bean.getId());
        for (int i = 0; i < stationnamelist.size(); i++) {
             if (stationnamelist.get(i).getStationCode().equals(bean.getStationCode().trim())) {
                throw new IqbException(SysManageReturnInfo.SYS_STATION_01030001);// 角色编码不能重复
            }
        }
        return true;
    }



    /**
     * 查询角色授权的用户
     */

    public Integer selectAuthorizeUser(String stationCode) throws IqbException, IqbSqlException {
        // 通过stationid来查询有哪些用户关联
        {
            List<SysUser> listUsers = sysStationRoleBiz.selectAuthorizeUser(stationCode);
            return listUsers.size();
        }
    }



    /**
     * 机构下边的角色
     */
    public Boolean selectOrganizationStations(Integer orgId) throws IqbException, IqbSqlException {
        return sysStationRoleBiz.selectOrganizationStations(orgId) < 1;
    }

 

}
