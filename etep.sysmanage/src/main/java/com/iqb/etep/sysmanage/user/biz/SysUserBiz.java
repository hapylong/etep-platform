/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : IqbSysUserBiz.java
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
package com.iqb.etep.sysmanage.user.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.iqb.etep.common.base.biz.CommonBiz;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.redis.RedisPlatformDao;
import com.iqb.etep.common.utils.Attr.RedisLockConst;
import com.iqb.etep.sysmanage.base.SysManageReturnInfo;
import com.iqb.etep.sysmanage.user.bean.SysUser;
import com.iqb.etep.sysmanage.user.dao.SysUserDao;

/**
 * @author leiwenyang
 */
@Component
public class SysUserBiz extends CommonBiz<SysUser>{

    @Autowired
   private  SysUserDao sysUserDao;
    @Autowired
    private RedisPlatformDao redisPlatformDao;

    /**
     * @param id
     * @return
     */

    public SysUser selectSysUserById(Integer id) {
        setDb(0, super.SLAVE);
        return sysUserDao.selectSysUserById(id);

    }

    /**
     * @param bean
     */
    public void updateSysUser(SysUser bean) {
        setDb(0, super.SLAVE);
        sysUserDao.updateSysUser(bean);
    }

    /**
     * @param bean
     */
    public void updateUserPassword(SysUser bean) {
        setDb(0, super.SLAVE);
        sysUserDao.updateUserPassword(bean);

    }

    /**
     * @param objs
     * @return
     */
    public List<SysUser> selectByPage(JSONObject objs) {

        setDb(0, super.SLAVE);
        PageHelper.startPage(getPagePara(objs));

        return sysUserDao.selectByPage(objs);
    }

    /**
     * 
     * Description: 获取总部用户信息
     * @param
     * @return List<SysUser>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月13日 下午8:35:14
     */
    public List<SysUser> selectHqByPage(JSONObject objs) {
        setDb(0, super.SLAVE);
        PageHelper.startPage(getPagePara(objs));
        return sysUserDao.selectHqByPage(objs);
    }

    /**
     * @param bean
     */
    public void updateSysUserAll(SysUser bean) {
        setDb(0, super.SLAVE);
        sysUserDao.updateSysUserAll(bean);

    }

    /**
     * @param usercode
     * @return
     */
    public SysUser getUserCode(String usercode) {
        setDb(0, super.SLAVE);
        return sysUserDao.getUserCode(usercode);
    }

    /**
     * @param userEmail
     * @return
     */
    public SysUser getUserEmail(String userCode) {
        setDb(0, super.SLAVE);
        return sysUserDao.getUserEmail(userCode);
    }

    /**
     * @param bean
     */
    public void insertUser(SysUser bean) {
        setDb(0, super.SLAVE);
        sysUserDao.insertUser(bean);
        
    }

    /**
     * @param userCode
     * @return
     * 用户编码不能重复
     */
    public SysUser selectExitUserCode(String userCode) {
        // TODO Auto-generated method stub
        setDb(0, super.SLAVE);
        return sysUserDao.selectExitUserCode(userCode);
    }


    /**
     * @param id
     * 修改时重要字段不能重复
     * @return
     */
    public List<SysUser> selectUserAll(Integer id) {
        setDb(0, super.SLAVE);
        return sysUserDao.selectUserAll(id);
    }



    /**
     * @param liststationUsers
     * 关联角色的用户stationdid置为空
     */
    public void updateStationId(List<SysUser> liststationUsers) {
       // TODO Auto-generated method stub
        setDb(0,super.SLAVE);
     sysUserDao.updateStationId(liststationUsers);
    }

    /**
     * @param bean
     * @return
     * 数据不能重复添加
     */
    public int selectSysUserCount(SysUser bean) {
        // TODO Auto-generated method stub
        setDb(0, super.SLAVE);
        return  sysUserDao.selectSysUserCount(bean);
    }

    /**
     * 
     * Description: 根据orgId获取总部用户信息
     * @param
     * @return Integer
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月14日 下午3:05:19
     */
    public Integer getHqUserCountByOrgId(Integer orgId) {
        setDb(0, super.SLAVE);
        return sysUserDao.getHqUserCountByOrgId(orgId);
    }
 
    /**
     * 
     * Description: 根据deptId获取总部用户信息
     * @param
     * @return Integer
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月14日 下午3:20:13
     */
    public Integer getHqUserCountByDeptId(Integer orgId) {
        setDb(0, super.SLAVE);
        return sysUserDao.getHqUserCountByOrgId(orgId);
    }

    /**
     * @return
     */
    public   List<Map<String, String>>  getSysUserAll() {
        setDb(0, super.SLAVE);
        return sysUserDao.getSysUserAll();
    }

    /**
     * @param orgCode 
     * @return
     */
    public   List<Map<String, String>> getSysUserOrgan(String orgCode,String userCode) {
        setDb(0, super.SLAVE);
        return sysUserDao.getSysUserOrgan(orgCode,userCode);
    }

    /**
     * @param objs
     * @return
     * @throws IqbException 
     */
    @SuppressWarnings("unchecked")
    public List<SysUser> getSysUserLock(JSONObject objs) throws IqbException {
        setDb(0, super.SLAVE);
        Map<String,Object> usercodelist=getList(objs);
        if(usercodelist!=null ){
        PageHelper.startPage(getPagePara(objs));
        List<SysUser> userlist= (List<SysUser>) usercodelist.get("sqllist");
        Map<String,String> usrmap= (Map<String, String>) usercodelist.get("parammap");
         List<SysUser> list = sysUserDao.getSysUserName(userlist);
         for(SysUser sqllist:list){
             sqllist.setCount(usrmap.get(sqllist.getUserCode()));
             }    
         return list;
        }else{
          return null;
       }
        }
    
    private Map<String ,Object> getList(JSONObject objs){
        SysUser bean = JSONObject.toJavaObject(objs, SysUser.class);
        if(bean.getUserCode()==null||bean.getUserCode().equals("")){
        String  key="*"+ RedisLockConst.LoginFailLockPrex+"*";
        Set<String> val = redisPlatformDao.getKeys(key);
        if(val.size()>0){
         return newSysUser(val);
        }else{
            return null;
            }
        }else{
            String  key="*"+ RedisLockConst.LoginFailLockPrex+bean.getUserCode().trim()+"*";
            Set<String> val = redisPlatformDao.getKeys(key);
            if(val.size()>0){
           return  newSysUser(val);
           }else{
               return null;
           }
            }
       
    }
   private  Map<String,Object> newSysUser( Set<String> val){
       Map<String,String> map=new HashMap<String, String>();
       List<SysUser> ls=new ArrayList<SysUser>();
       Map<String,Object> mapall=new HashMap<String, Object>();
       for(String emu:val){
           String usercode = emu.toString().substring(21);
           SysUser user = new SysUser();
           String count=redisPlatformDao.getValueByKey(emu);
           user.setCount(count);
           user.setUserCode(usercode);
           ls.add(user);
           map.put(usercode, count);
       }
       mapall.put("sqllist", ls);
       mapall.put("parammap", map);
       return mapall;
   }

   /**
    * 解锁
     * @param objs
    */
  public void remSysUserLock(JSONObject objs)throws IqbException {
    if(objs==null||objs.get("usercode")==null || objs.get("usercode").equals("")){
        throw new IqbException(SysManageReturnInfo.SYS_USER_0102009);
    }else{
     String id = objs.get("usercode").toString();
     redisPlatformDao.removeValueByKey(RedisLockConst.LoginFailLockPrex+id);
    }
    }


  
    
    

}
