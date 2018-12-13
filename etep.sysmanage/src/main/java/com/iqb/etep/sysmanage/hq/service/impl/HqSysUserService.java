/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : IqbSysUserService.java
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
package com.iqb.etep.sysmanage.hq.service.impl;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.base.CommonReturnInfo;
import com.iqb.etep.common.base.service.BaseService;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.common.utils.Attr.CommonConst;
import com.iqb.etep.common.utils.Attr.UserRelevant;
import com.iqb.etep.common.utils.Cryptography;
import com.iqb.etep.common.utils.GenerationUtil;
import com.iqb.etep.common.utils.JSONUtil;
import com.iqb.etep.common.utils.MessageUtil;
import com.iqb.etep.common.utils.SysUserSession;
import com.iqb.etep.sysmanage.base.SysManageReturnInfo;
import com.iqb.etep.sysmanage.hq.service.IHqSysUserService;
import com.iqb.etep.sysmanage.station.biz.SysStationRoleBiz;
import com.iqb.etep.sysmanage.user.bean.SysUser;
import com.iqb.etep.sysmanage.user.biz.SysUserBiz;

/**
 * @author leiwenyang
 */
@Service
public class HqSysUserService extends BaseService implements IHqSysUserService{

    @SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(HqSysUserService.class);

    @Autowired
    SysUserBiz sysUserBiz;

    @Autowired
    SysStationRoleBiz stationRoleBiz;

    @Autowired
    SysUserSession sysUserSession;

    /**
     * 功能:保存用户的信息
     * 
     * @throws Exception
     */
    public void insertSysUser(JSONObject objs) throws Exception {
        SysUser bean = JSONUtil.toJavaObject(objs, SysUser.class);
        synchronized (HqSysUserService.class) {
            if (bean!=null && CheckoutMethod(bean)) {
                if (sysUserBiz.selectSysUserCount(bean) < 1) {
                   bean.setUserPassword(Cryptography.encrypt(UserRelevant.DefaultPassword));
                    bean.setCreateTime(NumberUtils.toInt(System.currentTimeMillis() / 1000 + ""));// 时间戳
                    bean.setCreateUser(sysUserSession.getUserCode());
                    bean.setDeleteFlag(1);
                    bean.setVersion(NumberUtils.toInt(CommonConst.Version));// 版本号
                    bean.setLastTime(NumberUtils.toInt(System.currentTimeMillis() / 1000 + ""));
                    bean.setLastUser(sysUserSession.getUserCode());
                    bean.setIsHq(UserRelevant.HQ);
                    sysUserBiz.insertUser(bean);
                    
                } else {
                    throw new IqbException(CommonReturnInfo.BASE00090002);
                }
            } else {
                throw new IqbException(SysManageReturnInfo.SYS_USER_01020007);
            }
        }
    }

    /**
     * 功能:删除一条用户数据
     */
    public void deleteSysUser(JSONObject objs) throws IqbException, IqbSqlException {
        SysUser bean = JSONUtil.toJavaObject(objs, SysUser.class);
        synchronized (HqSysUserService.class) {
            if (sysUserBiz.selectSysUserCount(bean) > 0) {
                bean.setLastUser(sysUserSession.getUserCode());
                bean.setLastTime(NumberUtils.toInt(System.currentTimeMillis() / 1000 + ""));
                // bean.setDeleteFlag(1);
                sysUserBiz.updateSysUser(bean);
            } else {
                throw new IqbException(CommonReturnInfo.BASE00090002);
            }
        }
    }

    /**
     * 修改用户信息
     */
    public void updateSysUserAll(JSONObject objs) throws IqbException, IqbSqlException {
        synchronized (HqSysUserService.class) {
            SysUser bean = JSONObject.toJavaObject(objs, SysUser.class);
            SysUser sysUser = new SysUser();
            if(bean!=null && bean.getId()!=null && !bean.getId().equals("")){
            sysUser.setId(bean.getId());
             if (updateCheckoutMethod(bean) &&checkImportantField(bean)) {
                 if (sysUserBiz.selectSysUserCount(sysUser) > 0) {
                   sysUserBiz.updateSysUserAll(bean);
                } 
                 else {
                   throw new IqbException(CommonReturnInfo.BASE00090003);
                }
            } else {
               throw new IqbException(SysManageReturnInfo.SYS_USER_01020007);
           }
         }else{
             throw new IqbException(CommonReturnInfo.BASE00090003);
         }
       }
    }

    /**
     * 功能:分页查询
     */
    @Override
    public PageInfo<SysUser> getSysUser(JSONObject objs) throws IqbException, IqbSqlException {
  
        return new PageInfo<SysUser>(sysUserBiz.selectByPage(objs));
    }
    
    @Override
    public PageInfo<SysUser> getHqSysUser(JSONObject objs) throws IqbException, IqbSqlException {
       
        return new PageInfo<SysUser>(sysUserBiz.selectHqByPage(objs));
    }

    /**
     * @param objs
     * @return
     * @throws IqbException
     */
    public SysUser getSysUserById(JSONObject objs) throws IqbException, IqbSqlException {
        SysUser bean = JSONUtil.toJavaObject(objs, SysUser.class);
        synchronized (HqSysUserService.class) {
            if (sysUserBiz.selectSysUserCount(bean) > 0) {
                return sysUserBiz.selectSysUserById(bean.getId());
            } else {
                throw new IqbException(CommonReturnInfo.BASE00090002);
            }

        }
    }

    /**
     * @param objs
     * @throws Exception
     */
    public void updateUserPassword(JSONObject objs) throws Exception {
         synchronized (HqSysUserService.class) {
            SysUser bean = JSONObject.toJavaObject(objs, SysUser.class);
            bean.setUserPassword(Cryptography.encrypt(bean.getUserPassword()));
            SysUser sysUser = new SysUser();
            sysUser.setId(bean.getId());
            synchronized (HqSysUserService.class) {
                if (sysUserBiz.selectSysUserCount(sysUser) > 0) {
                    sysUserBiz.updateUserPassword(bean);
                } else {

                    throw new IqbException(CommonReturnInfo.BASE00090002);

                }

            }

        }
    }

    /**
     * @param objs
     */
    public void updateModifyUserPassword(JSONObject objs) throws Exception {
        synchronized (HqSysUserService.class) {
            String oldPwd = objs.get("oldPwd").toString();
            String newPwd = objs.get("newPwd").toString();
            SysUser bean = new SysUser();
            SysUser sysUser = sysUserBiz.getUserCode(sysUserSession.getUserCode());// 根据userCode查询
   //         System.out.println(sysUser.getUserPassword());
            if (Cryptography.encrypt(oldPwd).equals(sysUser.getUserPassword())) {
                bean.setId(sysUser.getId());
                bean.setUserPassword(Cryptography.encrypt(newPwd));
                sysUserBiz.updateUserPassword(bean);
            
            }else {
                throw new IqbException(SysManageReturnInfo.SYS_USER_01020002);

            }
        }

    }

    /**
     * @param objs
     */
    public void updateForgetUserPassword(JSONObject objs) throws Exception {
        synchronized (HqSysUserService.class) {
            // 从页面获取到邮箱 在数据库中查看邮箱是否存在
            String userEmail = objs.get("userEmail").toString();
            SysUser beanSysUser = sysUserBiz.getUserEmail(sysUserSession.getUserCode());
           // 邮箱存在
           if(beanSysUser.getUserEmail().equals(userEmail)){
                String newUserPassword = GenerationUtil.getSix();
                MessageUtil.sendByEmail(userEmail, "新的密码", "新的密码是:" + newUserPassword);// 随机生成六位数的邮箱密码
                // 修改密码
                beanSysUser.setUserPassword(Cryptography.encrypt(newUserPassword));

                sysUserBiz.updateUserPassword(beanSysUser);
            
               }else {
                // 邮箱不存在
                System.out.println("邮箱不存在");
               throw new IqbException(SysManageReturnInfo.SYS_USER_01020003);
            }

        }

    }
  
    private boolean CheckoutMethod(SysUser bean) throws IqbException{
        if(bean.getUserName()==null ||bean.getUserName().equals("")){
            throw  new IqbException(SysManageReturnInfo.SYS_USER_01020004);//用户名为空
        }
        if(bean.getUserCode()==null || bean.getUserCode().equals("")){
            throw new IqbException(SysManageReturnInfo.SYS_USER_01020006);//用户编码为空
        }
        if(bean.getOrgId()==null ){
            throw new IqbException(SysManageReturnInfo.SYS_USER_0102010);//机构编码为空
        }
        if(bean.getRealName()==null || bean.getRealName().equals("")){
            throw new IqbException(SysManageReturnInfo.SYS_USER_0102011);//真实姓名为空
        }
        if(bean.getUserPhone()==null || bean.getUserPhone().equals("")){
            throw new IqbException(SysManageReturnInfo.SYS_USER_0102012);//电话为空
        }
        if(bean.getUserEmail()==null || bean.getUserEmail().equals("")){
            throw new IqbException(SysManageReturnInfo.SYS_USER_0102013);//邮箱
        }
        if(bean.getStatus() == null ){
            throw new IqbException(SysManageReturnInfo.SYS_USER_0102014);//状态
        }
        if(sysUserBiz.selectExitUserCode(bean.getUserCode())!=null){
            throw new IqbException(SysManageReturnInfo.SYS_USER_01020001);//用户编码已存在
        }
        return true;
    }
   
    private boolean updateCheckoutMethod(SysUser bean) throws IqbException{
        if(bean.getUserName()==null ||bean.getUserName().equals("")){
            throw  new IqbException(SysManageReturnInfo.SYS_USER_01020004);//用户名为空
        }
        if(bean.getUserCode()==null || bean.getUserCode().equals("")){
            throw new IqbException(SysManageReturnInfo.SYS_USER_01020006);//用户编码为空
        }
        if(bean.getOrgId()==null ){
            throw new IqbException(SysManageReturnInfo.SYS_USER_0102010);//机构编码为空
        }
        if(bean.getRealName()==null || bean.getRealName().equals("")){
            throw new IqbException(SysManageReturnInfo.SYS_USER_0102011);//真实姓名为空
        }
        if(bean.getUserPhone()==null || bean.getUserPhone().equals("")){
            throw new IqbException(SysManageReturnInfo.SYS_USER_0102012);//电话为空
        }
        if(bean.getUserEmail()==null || bean.getUserEmail().equals("")){
            throw new IqbException(SysManageReturnInfo.SYS_USER_0102013);//邮箱
        }
        if(bean.getStatus() == null ){
            throw new IqbException(SysManageReturnInfo.SYS_USER_0102014);//状态
        }
       
        return true;
    }
    


    // 修改时 检查 编码 邮箱  邮箱 不重复
    private boolean checkImportantField(SysUser bean) throws IqbException {
        List<SysUser> sysUserList = sysUserBiz.selectUserAll(bean.getId());
        for (int i = 0; i < sysUserList.size(); i++) {
            if (sysUserList.get(i).getUserCode().equals(bean.getUserCode())) {
                throw new IqbException(SysManageReturnInfo.SYS_USER_01020001);// 用户编码已存在
            }
        }
     
          return true;
    }

    @Override
    public boolean isOrgIdBeUsedFromHqUser(SysUser bean) throws IqbException, IqbSqlException {
        if(bean == null || bean.getOrgId() == null || bean.getDeptId() == null){
            return false;
        }
        Integer ret = sysUserBiz.getHqUserCountByOrgId(bean.getOrgId());
        if(ret == null || ret == 0){
            return false;
        }
        ret = sysUserBiz.getHqUserCountByDeptId(bean.getDeptId());
        if(ret == null || ret == 0){
            return false;
        }
        return true;
    }
    
    

}
