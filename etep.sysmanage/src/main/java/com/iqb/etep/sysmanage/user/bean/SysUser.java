/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : SysUser.java
 *
 * PURPOSE : 
 *
 * AUTHOR : leiwenyang
 *
 *
 * 创建日期: 2016年8月11日
 * HISTORY：
 * 变更日期 
 */
package com.iqb.etep.sysmanage.user.bean;

import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import com.iqb.etep.common.annotation.ConcernProperty;
import com.iqb.etep.common.annotation.ConcernProperty.ConcernActionScope;

/**
 * @author leiwenyang
 */
// 用户-用户
@Table(name = "IQB_SYS_USER")
public class SysUser{

    @Id
    private Integer id;
    private Integer stationId;// 角色ID外键
    private Integer deptId;// 部门ID外键
    private Integer orgId;// 机构ID外键
    @ConcernProperty(scope = ConcernActionScope.Login)
    private String userPassword;// 用户登录密码
    @ConcernProperty(scope = ConcernActionScope.Login)
    private String userCode;// 用户编码
    private String userName;//用户名
    private String realName;// 用户真实姓名
    private Integer sex;// 性别
    private Integer idType;// 证件类型：1身份证、2护照、3警官证、4组织机构编号
    private String idNo;// 证件号码
    private String userPhone;// 手机号
    private String userEmail;// 用户邮箱
    private String address;// 联系地址
    private Integer status;// 用户状态：1正常(默认)、2冻结
    private String createUser;// 创建人
    private Integer createTime;// 创建时间
    private String lastUser;// 最后修改人
    private Integer lastTime;// 最后修改时间
    private String remark;// 备注
    private Integer deleteFlag;// 删除标识
    private Integer version;// 版本号
    private String orgName;//机构名称
    private String stationCode;//角色code
    private String stationRoleName;//角色名
    private String orgCode;//机构代码
    private String detpName;//部门名称
    private String isHq;//是否是总部
    private String orgShortName;
    private String count;//错误次数

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the stationId
     */
    public Integer getStationId() {
        return stationId;
    }

    /**
     * @param stationId
     *            the stationId to set
     */
    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    /**
     * @return the deptId
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * @param deptId
     *            the deptId to set
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

  
    /**
     * @return the userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * @param userPassword
     *            the userPassword to set
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * @return the realName
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName
     *            the realName to set
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * @return the sex
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * @param sex
     *            the sex to set
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * @return the idType
     */
    public Integer getIdType() {
        return idType;
    }

    /**
     * @param idType
     *            the idType to set
     */
    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    /**
     * @return the idNo
     */
    public String getIdNo() {
        return idNo;
    }

    /**
     * @param idNo
     *            the idNo to set
     */
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    /**
     * @return the userPhone
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * @param userPhone
     *            the userPhone to set
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * @return the userEmail
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * @param userEmail
     *            the userEmail to set
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the createTime
     */
    public Integer getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the createUser
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser
     *            the createUser to set
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * @return the lastUser
     */
    public String getLastUser() {
        return lastUser;
    }

    /**
     * @param lastUser
     *            the lastUser to set
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }

    /**
     * @return the lastTime
     */
    public Integer getLastTime() {
        return lastTime;
    }

    /**
     * @param lastTime
     *            the lastTime to set
     */
    public void setLastTime(Integer lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     *            the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version
     *            the version to set
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @return the orgId
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * @param orgId
     *            the orgId to set
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * @return the userCode
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * @param userCode
     *            the userCode to set
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    
    /**
     * @return the deleteFlag
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    
    /**
     * @param deleteFlag the deleteFlag to set
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
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

    
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    
    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    /**
     * @return the stationRoleName
     */
    public String getStationRoleName() {
        return stationRoleName;
    }

    
    /**
     * @param stationRoleName the stationRoleName to set
     */
    public void setStationRoleName(String stationRoleName) {
        this.stationRoleName = stationRoleName;
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

    
    public String getIsHq() {
        return isHq;
    }

    
    public void setIsHq(String isHq) {
        this.isHq = isHq;
    }

    
    public String getOrgShortName() {
        return orgShortName;
    }

    
    public void setOrgShortName(String orgShortName) {
        this.orgShortName = orgShortName;
    }

    
    public String getCount() {
        return count;
    }

    
    public void setCount(String count) {
        this.count = count;
    }
    
    
}
