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
package com.iqb.etep.sysmanage.hq.service;



import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.sysmanage.user.bean.SysUser;

/**
 * @author leiwenyang
 */
public interface IHqSysUserService{

    // 添加用户信息
    public void insertSysUser(JSONObject objs) throws Exception;

    // 删除用户信息
    public void deleteSysUser(JSONObject objs) throws IqbException, IqbSqlException;

    // 修改用户信息
    public void updateSysUserAll(JSONObject objs) throws IqbException, IqbSqlException;

    PageInfo<SysUser> getSysUser(JSONObject objs) throws IqbException, IqbSqlException;

    // 修改密码
    public void updateUserPassword(JSONObject objs) throws Exception;

    public SysUser getSysUserById(JSONObject objs) throws IqbException, IqbSqlException;

    // 个人管理 修改密码
    public void updateModifyUserPassword(JSONObject objs) throws Exception;

    // 忘记密码
    public void updateForgetUserPassword(JSONObject objs) throws Exception;

    /**
     * 
     * Description: 获取总部用户信息
     * @param
     * @return PageInfo<SysUser>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月13日 下午8:34:14
     */
    public PageInfo<SysUser> getHqSysUser(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 
     * Description: 判断机构或者部门是否被占用
     * @param
     * @return boolean
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月14日 下午3:03:12
     */
    public boolean isOrgIdBeUsedFromHqUser(SysUser bean) throws IqbException, IqbSqlException;

}
