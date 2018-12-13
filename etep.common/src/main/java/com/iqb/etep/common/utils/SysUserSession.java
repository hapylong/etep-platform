package com.iqb.etep.common.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iqb.etep.common.annotation.LogAop;
import com.iqb.etep.common.annotation.LogAop.LogAopOper;
import com.iqb.etep.common.utils.Attr.SessionAttr;

/**
 * Description: 系统用户
 * 
 * @author wangxinbang
 * @version 1.0
 * 
 *          <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------
 * 2016年8月15日    wangxinbang       1.0        1.0 Version
 * </pre>
 */
@Component
public class SysUserSession implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1425578064084920836L;
    @Autowired(required = false)
    private HttpSession session;

    /**
     * Description: 获取当前登录用户
     * 
     * @param
     * @return SysUser
     * @throws
     * @Author wangxinbang Create Date: 2016年8月15日 下午1:34:10
     */
    private SessionSysUser getSysUser() {
        SessionSysUser sessionSysUser = (SessionSysUser) session.getAttribute(SessionAttr.LoginUser);
        if (sessionSysUser == null) {
            return null;
        }
        return sessionSysUser;
    }
    
    /**
     * 
     * Description: 获取用户code
     * @param
     * @return String
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月23日 下午4:27:45
     */
    public String getUserCode(){
        SessionSysUser sessionSysUser = this.getSysUser();
        if(sessionSysUser == null){
            return null;
        }
        return sessionSysUser.getUserCode();
    }
    
    /**
     * 
     * Description: 获取真实姓名
     * @param
     * @return String
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月23日 下午5:05:47
     */
    public String getRealName(){
        SessionSysUser sessionSysUser = this.getSysUser();
        if(sessionSysUser == null){
            return null;
        }
        return sessionSysUser.getRealName();
    }
    
    /**
     * 
     * Description: 获取部门id
     * @param
     * @return Integer
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月24日 上午10:26:35
     */
    public Integer getDeptId(){
        SessionSysUser sessionSysUser = this.getSysUser();
        if(sessionSysUser == null){
            return null;
        }
        return sessionSysUser.getDeptId();
    }
    
    /**
     * 
     * Description: 获取机构id
     * @param
     * @return Integer
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月24日 上午10:27:03
     */
    public Integer getOrgId(){
        SessionSysUser sessionSysUser = this.getSysUser();
        if(sessionSysUser == null){
            return null;
        }
        return sessionSysUser.getOrgId();
    }
    
    /**
     * 
     * Description: 获取角色id
     * @param
     * @return Integer
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月24日 上午10:27:25
     */
    public Integer getStationId(){
        SessionSysUser sessionSysUser = this.getSysUser();
        if(sessionSysUser == null){
            return null;
        }
        return sessionSysUser.getStationId();
    }
    
    /**
     * 
     * Description: 获取用户状态
     * @param
     * @return Integer
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月24日 上午10:27:51
     */
    public Integer getStatus(){
        SessionSysUser sessionSysUser = this.getSysUser();
        if(sessionSysUser == null){
            return null;
        }
        return sessionSysUser.getStatus();
    }
    
    /**
     * 
     * Description: 获取用户手机号
     * @param
     * @return Integer
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月24日 上午10:28:16
     */
    public String getUserPhone(){
        SessionSysUser sessionSysUser = this.getSysUser();
        if(sessionSysUser == null){
            return null;
        }
        return sessionSysUser.getUserPhone();
    }
    
    /**
     * 
     * Description: 获取用户邮箱
     * @param
     * @return String
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月24日 上午10:28:46
     */
    public String getUserEmail(){
        SessionSysUser sessionSysUser = this.getSysUser();
        if(sessionSysUser == null){
            return null;
        }
        return sessionSysUser.getUserEmail();
    }
    
    /**
     * 
     * Description: 获取机构信息
     * @param
     * @return String
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月25日 下午3:25:25
     */
    public String getOrgName(){
        SessionSysUser sessionSysUser = this.getSysUser();
        if(sessionSysUser == null){
            return null;
        }
        return sessionSysUser.getOrgName();
    }
    
    /**
     * 
     * Description: 获取机构信息
     * @param
     * @return String
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月25日 下午3:25:25
     */
    public String getOrgShortName(){
        SessionSysUser sessionSysUser = this.getSysUser();
        if(sessionSysUser == null){
            return null;
        }
        return sessionSysUser.getOrgShortName();
    }
    
    /**
     * 
     * Description: 获取角色代码
     * @param
     * @return String
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月29日 下午3:15:20
     */
    public List<String> getStationCodeList(){
        SessionSysUser sessionSysUser = this.getSysUser();
        if(sessionSysUser == null){
            return null;
        }
        String stationCode = sessionSysUser.getStationCode();
        if(StringUtil.isEmpty(stationCode)){
            return null;
        }
        if(StringUtil.contains(stationCode, "[")){
            return BeanUtil.toJavaList(stationCode, String.class);
        }
        List<String> stationCodeList = new ArrayList<String>();
        stationCodeList.add(stationCode);
        return stationCodeList;
    }
    
    /**
     * 
     * Description: 获取角色代码
     * @param
     * @return String
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月29日 下午3:15:20
     */
    public String getStationCode(){
        SessionSysUser sessionSysUser = this.getSysUser();
        if(sessionSysUser == null){
            return null;
        }
        return sessionSysUser.getStationCode();
    }
    
    /**
     * 
     * Description: 获取机构代码
     * @param
     * @return String
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月6日 下午5:08:29
     */
    public String getOrgCode(){
        SessionSysUser sessionSysUser = this.getSysUser();
        if(sessionSysUser == null){
            return null;
        }
        return sessionSysUser.getOrgCode();
    }
    
    /**
     * 
     * Description: 获取部门名称
     * @param
     * @return String
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月8日 下午6:41:15
     */
    public String getDetpName(){
        SessionSysUser sessionSysUser = this.getSysUser();
        if(sessionSysUser == null){
            return null;
        }
        return sessionSysUser.getDetpName();
    }

    /**
     * Description: 缓存登录用户信息
     * 
     * @param
     * @return void
     * @throws
     * @Author wangxinbang Create Date: 2016年8月15日 下午4:06:14
     */
    @LogAop(logAopOper = LogAopOper.SysUserLogin)
    public void setSysUserSession(Object sysUser) {
        SessionSysUser sessionSysUser = new SessionSysUser();
        BeanUtils.copyProperties(sysUser, sessionSysUser);
        session.setAttribute(SessionAttr.LoginUser, sessionSysUser);
        session.setMaxInactiveInterval(NumberUtil.toInt(SessionAttr.LoginUserMaxInactiveInterval));
    }

    /**
     * Description: 刷新session
     * 
     * @param
     * @return void
     * @throws
     * @Author wangxinbang Create Date: 2016年8月23日 下午4:07:57
     */
    public void refreshSysUserSession() {
        session.setMaxInactiveInterval(NumberUtil.toInt(SessionAttr.LoginUserMaxInactiveInterval));
    }

    /**
     * Description: 注销用户
     * 
     * @param
     * @return void
     * @throws
     * @Author wangxinbang Create Date: 2016年8月15日 下午4:10:43
     */
    public void cancelSysUserSession() {
        session.removeAttribute(SessionAttr.LoginUser);
    }

    /** session用户类 **/
    public class SessionSysUser implements Serializable {

        private Integer id;
        private Integer stationId;// 角色ID外键
        private Integer deptId;// 部门ID外键
        private Integer orgId;//机构id
        private String userCode;// 用户编码
        private String userPassword;// 用户登录密码
        private String realName;// 用户真实姓名
        private String userPhone;// 手机号
        private String userEmail;// 用户邮箱
        private Integer status;// 用户状态：1正常(默认)、2冻结
        private String orgName;//机构名称
        private String stationCode;//角色code
        private String orgCode;//机构代码
        private String detpName;//部门名称
        private String stationRoleName;//角色名称
        private String orgShortName;//机构简称

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getStationId() {
            return stationId;
        }

        public void setStationId(Integer stationId) {
            this.stationId = stationId;
        }
        
        public Integer getOrgId() {
            return orgId;
        }

        public void setOrgId(Integer orgId) {
            this.orgId = orgId;
        }

        public Integer getDeptId() {
            return deptId;
        }

        public void setDeptId(Integer deptId) {
            this.deptId = deptId;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
        
        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }
       
        public String getStationCode() {
            return stationCode;
        }

        public void setStationCode(String stationCode) {
            this.stationCode = stationCode;
        } 
        
        public String getOrgCode() {
            return orgCode;
        }

        public void setOrgCode(String orgCode) {
            this.orgCode = orgCode;
        }
        
        public String getDetpName() {
            return detpName;
        }

        public void setDetpName(String detpName) {
            this.detpName = detpName;
        }

        public String getStationRoleName() {
            return stationRoleName;
        }

        public void setStationRoleName(String stationRoleName) {
            this.stationRoleName = stationRoleName;
        }
        
        public String getOrgShortName() {
            return orgShortName;
        }

        public void setOrgShortName(String orgShortName) {
            this.orgShortName = orgShortName;
        }

    }
    

}
