package com.iqb.etep.sysmanage.login.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.redis.RedisPlatformDao;
import com.iqb.etep.common.utils.Attr.RedisLockConst;
import com.iqb.etep.common.utils.Attr.StatusAttr;
import com.iqb.etep.common.utils.NumberUtil;
import com.iqb.etep.common.utils.StringUtil;
import com.iqb.etep.sysmanage.base.SysManageBaseBiz;
import com.iqb.etep.sysmanage.base.SysManageReturnInfo;
import com.iqb.etep.sysmanage.base.SysManageAttr.SysRedisConst;
import com.iqb.etep.sysmanage.login.dao.SysLoginDao;
import com.iqb.etep.sysmanage.user.bean.SysUser;

/**
 * 
 * Description: 登录biz
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年8月15日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */
@Component
public class SysLoginBiz extends SysManageBaseBiz{
    
    @Autowired
    private SysLoginDao sysLoginDao;
    
    @Autowired
    private RedisPlatformDao redisPlatformDao;
    
    /**
     * 
     * Description: 根据用户名和密码查询用户信息
     * @param
     * @return SysUser
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月15日 下午3:20:33
     */
    public SysUser getUserByNameAndPwd(SysUser sysUser){
        super.setDb(0, super.SLAVE);
        return sysLoginDao.getUserByNameAndPwd(sysUser);
    }
    
    /**
     * 
     * Description: 根据用户编码查询用户信息
     * @param
     * @return SysUser
     * @throws IqbException 
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月6日 下午3:56:47
     */
    public SysUser getUserByUserCode(SysUser sysUser) throws IqbException{
        super.setDb(0, super.SLAVE);
        String userCode = sysUser.getUserCode();
        SysUser sysUserRet = sysLoginDao.getUserByUserCode(sysUser);
        /** 校验用户是否被加登录锁  **/
        if(this.checkLoginLock(userCode)){
            throw new IqbException(SysManageReturnInfo.SYS_LOGIN_01010004);
        }
        /** 校验用户名**/
        if(sysUserRet == null){
            throw new IqbException(SysManageReturnInfo.SYS_LOGIN_01010003);
        }
        /** 校验密码**/
        if(!sysUser.getUserPassword().equals(sysUserRet.getUserPassword())){
            this.recordLoginLock(userCode);
            throw new IqbException(SysManageReturnInfo.SYS_LOGIN_01010001);
        }
        this.removeLoginLock(userCode);
        /** 校验用户状态 **/
        if(sysUserRet.getStatus() == null || StatusAttr.userStatusFrezz.equals(sysUserRet.getStatus().toString())){
            throw new IqbException(SysManageReturnInfo.SYS_LOGIN_01010002);
        }
        return sysUserRet;
    }
    
    /**
     * 
     * Description: 获取系统当前状态
     * @param
     * @return String
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月18日 下午6:37:27
     */
    public String getSysCurrStatus(){
        return redisPlatformDao.getValueByKey(SysRedisConst.SYS_STATUS);
    }
    
    /**
     * 
     * Description: 检查登录锁
     * @param
     * @return Boolean
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月6日 下午4:23:35
     */
    private Boolean checkLoginLock(String userCode){
        String key = RedisLockConst.LoginFailLockPrex + userCode;
        String val = redisPlatformDao.getValueByKey(key);
        if(StringUtil.isEmpty(val)){
            val = "0";
            return false;
        }
        int failTimes = NumberUtil.toInt(val);
        if(failTimes < NumberUtil.toInt(RedisLockConst.LoginFailPermTimes)){
            return false;
        }
        return true;
    }
    
    /**
     * 
     * Description: 记录用密码错误次数
     * @param
     * @return Boolean
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月6日 下午4:40:03
     */
    private void recordLoginLock(String userCode){
        String key = RedisLockConst.LoginFailLockPrex + userCode;
        String val = redisPlatformDao.getValueByKey(key);
        if(StringUtil.isEmpty(val)){
            val = "0";
        }
        int failTimes = NumberUtil.toInt(val);
        redisPlatformDao.setKeyAndValueTimeout(key, (failTimes + 1) + "", NumberUtil.toInt(RedisLockConst.LoginFailLockInterval));
    }
    
    /**
     * 
     * Description: 移除登录锁
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月6日 下午4:42:49
     */
    private void removeLoginLock(String userCode){
        String key = RedisLockConst.LoginFailLockPrex + userCode;
        redisPlatformDao.removeValueByKey(key);
    }

}
