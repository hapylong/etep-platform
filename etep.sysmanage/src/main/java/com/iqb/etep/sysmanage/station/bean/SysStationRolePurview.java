/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : StationPurview.java
 *
 * PURPOSE : 
 *
 * AUTHOR : leiwenyang
 *
 *
 * 创建日期: 2016年8月18日
 * HISTORY：
 * 变更日期 
 */
package com.iqb.etep.sysmanage.station.bean;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author leiwenyang
 */
// 岗位--角色权限表
@Table(name = "IQB_SYS_STATION_PURVIEW")
public class SysStationRolePurview{

    @Id
    private Integer id;// 主键
    private Integer stationRoleId;// 角色id
    private Integer stationMenuId;// 菜单id
    private Integer createUid;// 创建人
    private Integer createTime;// 创建时间
    private Integer lastUid;// 最后修改人
    private Integer lastTime;// 最后修改时间
    private String remark;// 备注

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
     * @return the stationRoleId
     */
    public Integer getStationRoleId() {
        return stationRoleId;
    }

    /**
     * @param stationRoleId
     *            the stationRoleId to set
     */
    public void setStationRoleId(Integer stationRoleId) {
        this.stationRoleId = stationRoleId;
    }

    /**
     * @return the stationMenuId
     */
    public Integer getStationMenuId() {
        return stationMenuId;
    }

    /**
     * @param stationMenuId
     *            the stationMenuId to set
     */
    public void setStationMenuId(Integer stationMenuId) {
        this.stationMenuId = stationMenuId;
    }

    /**
     * @return the createUid
     */
    public Integer getCreateUid() {
        return createUid;
    }

    /**
     * @param createUid
     *            the createUid to set
     */
    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
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
     * @return the lastUid
     */
    public Integer getLastUid() {
        return lastUid;
    }

    /**
     * @param lastUid
     *            the lastUid to set
     */
    public void setLastUid(Integer lastUid) {
        this.lastUid = lastUid;
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

}
