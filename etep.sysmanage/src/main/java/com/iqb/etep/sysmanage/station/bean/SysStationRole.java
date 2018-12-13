/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : SysStationRole.java
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
package com.iqb.etep.sysmanage.station.bean;

import javax.persistence.Table;

import org.springframework.data.annotation.Id;

/**
 * @author leiwenyang 岗位-用户角色表
 */
// 岗位-用户角色
@Table(name = "IQB_SYS_STATION_ROLE")
public class SysStationRole{

    @Id
    private Integer id;
    private Integer orgId;// 机构ID
    private String stationCode;// 角色编码
    private String stationRoleName;// 角色名称
    private Integer stationIsSuperadmin;// 是否为系统管理员：1是、0否(默认)
    private Integer stationStatus;// 角色状态
    private String createUser;// 创建用户ID
    private Integer createTime;// 创建时间
    private Integer version;// 版本
    private String remark;// 备注
    private String lastUser;// 最后修改人
    private Integer lastTime;// 最后修改时间
    private Integer deleteFlag;//删除标志
    
    private String orgName;
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
     * @return the stationIsSuperadmin
     */
    public Integer getStationIsSuperadmin() {
        return stationIsSuperadmin;
    }

    /**
     * @param stationIsSuperadmin
     *            the stationIsSuperadmin to set
     */
    public void setStationIsSuperadmin(Integer stationIsSuperadmin) {
        this.stationIsSuperadmin = stationIsSuperadmin;
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
     * @return the stationRoleName
     */
    public String getStationRoleName() {
        return stationRoleName;
    }

    /**
     * @param stationRoleName
     *            the stationRoleName to set
     */
    public void setStationRoleName(String stationRoleName) {
        this.stationRoleName = stationRoleName;
    }

    /**
     * @return the stationStatus
     */
    public Integer getStationStatus() {
        return stationStatus;
    }

    /**
     * @param stationStatus
     *            the stationStatus to set
     */
    public void setStationStatus(Integer stationStatus) {
        this.stationStatus = stationStatus;
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
     * @return the stationCode
     */
    public String getStationCode() {
        return stationCode;
    }

    /**
     * @param stationCode
     *            the stationCode to set
     */
    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
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

    
    /**
     * @return the orgName
     */
    public String getOrgName() {
        return orgName;
    }

    
    /**
     * @param orgName the orgName to set
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    
    
}
