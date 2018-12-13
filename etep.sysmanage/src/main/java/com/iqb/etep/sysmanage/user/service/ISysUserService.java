/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : ISysUserService.java
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
package com.iqb.etep.sysmanage.user.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.sysmanage.user.bean.SysUser;

/**
 * @author leiwenyang
 */
public interface ISysUserService{

    // 添加用户信息
    public void insertSysUser(JSONObject objs) throws Exception;

    // 删除用户信息
    public void deleteSysUser(JSONObject objs) throws IqbException, IqbSqlException;

    // 修改用户信息
    public void updateSysUserAll(JSONObject objs) throws IqbException, IqbSqlException;

    PageInfo<SysUser> getSysUser(JSONObject objs) throws IqbException, IqbSqlException;

    public SysUser getSysUserById(JSONObject objs) throws IqbException, IqbSqlException;

    // 修改密码
    public void updateUserPassword(JSONObject objs) throws IqbException, IqbSqlException;

    // 个人管理 修改密码
    public void updateModifyUserPassword(JSONObject objs) throws IqbException, IqbSqlException;

    // 个人管理 忘记密码
    public void updateForgetUserPassword(JSONObject objs) throws IqbException, IqbSqlException;

    public void updateStationId(List<SysUser> liststationUsers) throws IqbException,
        IqbSqlException;

    // 查询所有用户
    public  List<Map<String, String>> getSysUserAll() throws IqbException, IqbSqlException;

    // 查询机构下面用户
    public List<Map<String, String>> getSysUserOrgan() throws IqbException, IqbSqlException;

    //查询登陆出错用户
    public PageInfo<SysUser>   getSysUserLock(JSONObject objs) throws IqbException, IqbSqlException;
    
    //清楚登陆次数超过五次用户
    public void remSysUserLock(JSONObject objs) throws  IqbException, IqbSqlException ;
}

