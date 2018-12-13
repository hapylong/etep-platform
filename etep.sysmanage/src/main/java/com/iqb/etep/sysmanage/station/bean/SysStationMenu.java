/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : SysStationMenu.java
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

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author leiwenyang
 */
// 岗位-系统菜单表
@Table(name="IQB_SYS_STATION_MENU")
public class SysStationMenu{
    @Id
    private Integer id;
    private Integer stationParentId;// 上级菜单id：默认一级菜单为0
    private String stationMenuName;// 菜单名称
    private String stationMenuDescription;// 菜单描述
    private Integer stationMenuLevel;// 菜单等级：1一级菜单、2二级菜单、3三级菜单、4四级菜单
    private String stationMenuUrl;// 菜单URL
    private Integer stationSequence;// 排序序号
    private Integer createUid;// 创建人
    private Integer createTime;// 创建时间
    private Integer lastUid;// 最后修改人
    private Integer lastTime;// 最后修改时间
    private String remark;// 备注
    private Integer isEnable;// 是否启用：1启用(默认)、2停用
    private Integer version;// 版本

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
     * @return the stationParentId
     */
    public Integer getStationParentId() {
        return stationParentId;
    }

    /**
     * @param stationParentId
     *            the stationParentId to set
     */
    public void setStationParentId(Integer stationParentId) {
        this.stationParentId = stationParentId;
    }

    /**
     * @return the stationMenuName
     */
    public String getStationMenuName() {
        return stationMenuName;
    }

    /**
     * @param stationMenuName
     *            the stationMenuName to set
     */
    public void setStationMenuName(String stationMenuName) {
        this.stationMenuName = stationMenuName;
    }

    /**
     * @return the stationMenuDescription
     */
    public String getStationMenuDescription() {
        return stationMenuDescription;
    }

    /**
     * @param stationMenuDescription
     *            the stationMenuDescription to set
     */
    public void setStationMenuDescription(String stationMenuDescription) {
        this.stationMenuDescription = stationMenuDescription;
    }

    /**
     * @return the stationMenuLevel
     */
    public Integer getStationMenuLevel() {
        return stationMenuLevel;
    }

    /**
     * @param stationMenuLevel
     *            the stationMenuLevel to set
     */
    public void setStationMenuLevel(Integer stationMenuLevel) {
        this.stationMenuLevel = stationMenuLevel;
    }

    /**
     * @return the stationMenuUrl
     */
    public String getStationMenuUrl() {
        return stationMenuUrl;
    }

    /**
     * @param stationMenuUrl
     *            the stationMenuUrl to set
     */
    public void setStationMenuUrl(String stationMenuUrl) {
        this.stationMenuUrl = stationMenuUrl;
    }

    /**
     * @return the stationSequence
     */
    public Integer getStationSequence() {
        return stationSequence;
    }

    /**
     * @param stationSequence
     *            the stationSequence to set
     */
    public void setStationSequence(Integer stationSequence) {
        this.stationSequence = stationSequence;
    }

    /**
     * @return the isEnable
     */
    public Integer getIsEnable() {
        return isEnable;
    }

    /**
     * @param isEnable
     *            the isEnable to set
     */
    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
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
