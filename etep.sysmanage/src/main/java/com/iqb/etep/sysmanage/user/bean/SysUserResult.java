/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : SysUserResult.java
 *
 * PURPOSE : 
 *
 * AUTHOR : leiwenyang
 *
 *
 * 创建日期: 2016年9月22日
 * HISTORY：
 * 变更日期 
 */
package com.iqb.etep.sysmanage.user.bean;


/**
 * @author leiwenyang
 *
 */
public class SysUserResult extends SysUser{

    private String orgName;
    private String stationRoleName;
    
    public String getOrgName() {
        return orgName;
    }
    
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    
    public String getStationRoleName() {
        return stationRoleName;
    }
    
    public void setStationRoleName(String stationRoleName) {
        this.stationRoleName = stationRoleName;
    }
    

    
}
