/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : SysUserDao.java
 *
 * PURPOSE : 
 *
 * AUTHOR : leiwenyang
 *
 *
 * 创建日期: 2016年8月12日
 * HISTORY：
 * 变更日期 
 */
package com.iqb.etep.sysmanage.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.sysmanage.user.bean.SysUser;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author leiwenyang
 */
public interface SysUserDao extends Mapper<SysUser>{

    
    SysUser selectSysUserById(Integer id);

    /**
     * @param bean
     */
    void updateSysUser(SysUser bean);

    /**
     * @param bean
     */
    void updateUserPassword(SysUser bean);

    /**
     * @param objs
     * @return
     */
    List<SysUser> selectByPage(JSONObject objs);
    
    /**
     * @param bean
     */
    void updateSysUserAll(SysUser bean);

    /**
     * @param usercode
     * @return
     */
    SysUser getUserCode(String usercode);

    /**
     * @param userEmail
     * @return
     */
    SysUser getUserEmail(String usercode);

 

    /**
     * @param bean
     */
    void insertUser(SysUser bean);

 

    /**
     * @param userCode
     * @return
     */
    SysUser selectExitUserCode(String userCode);


    /**
     * @param id
     * @return
     */
    List<SysUser> selectUserAll(Integer id);



    /**
     * @param liststationUsers
     * @return
     */
   void  updateStationId(List<SysUser> liststationUsers);

    /**
     * @param bean
     * @return
     */
    int selectSysUserCount(SysUser bean);

    /**
     * 
     * Description: 获取总部用户信息
     * @param
     * @return List<SysUser>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月13日 下午8:36:03
     */
    List<SysUser> selectHqByPage(JSONObject objs);

    /**
     * 
     * Description: 根据orgId获取总部用户信息
     * @param
     * @return Integer
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月14日 下午3:06:20
     */
    Integer getHqUserCountByOrgId(Integer orgId);
    
    /**
     * 
     * @param
     * @return Integer
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月14日 下午3:19:51
     */
    Integer getHqUserCountByDeptId(Integer deptId);

    /**获取所有用户
     * @return
     */
    List<Map<String, String>> getSysUserAll();

    /**
     * 获取组织机构下边用户
     * @param orgCode
     * @return
     */
    List<Map<String, String>> getSysUserOrgan(@Param("orgCode") String orgCode,@Param("userCode") String userCode);

    /**
     * @param usercode
     * @return
     * 
     */
  List<SysUser> getSysUserName(List<SysUser> usercodelist);


}
