package com.iqb.etep.sysmanage.log.bean;

import com.iqb.etep.common.annotation.MongoQueryProperty;
import com.iqb.etep.common.annotation.MongoQueryProperty.MongoCriteriaWay;

/**
 * 
 * Description: 系统日志bean
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年8月22日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */
public class SysLogBean{
    
    /**
     * 用户编码
     */
    @MongoQueryProperty(MongoCriteriaWay.REGEX)
    private String userCode;
    
    /**
     * 操作类型 1:登录 2:点击菜单
     */
    private String operType;
    
    /**
     * 操作内容
     */
    private String operInfo;
    
    /**
     * 创建人
     */
    private String createUser;
    
    /**
     * 机构名称
     */
    private String orgName;
    
    /**
     * 创建时间
     */
    private Integer createTime;
    
    /**
     * 查询开始时间
     */
    @MongoQueryProperty(value = MongoCriteriaWay.GT, queryFieldName = "createTime")
    private Integer queryStartTime;
    
    /**
     * 查询结束时间
     */
    @MongoQueryProperty(value = MongoCriteriaWay.LT, queryFieldName = "createTime")
    private Integer queryEndTime;
    
    /**
     * 备注
     */
    private String remark;

    
    public String getUserCode() {
        return userCode;
    }

    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    
    public String getOperType() {
        return operType;
    }

    
    public void setOperType(String operType) {
        this.operType = operType;
    }

    
    public String getOperInfo() {
        return operInfo;
    }

    
    public void setOperInfo(String operInfo) {
        this.operInfo = operInfo;
    }

    
    public String getCreateUser() {
        return createUser;
    }

    
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    
    
    public Integer getQueryStartTime() {
        return queryStartTime;
    }

    
    public void setQueryStartTime(Integer queryStartTime) {
        this.queryStartTime = queryStartTime;
    }

    
    public Integer getQueryEndTime() {
        return queryEndTime;
    }

    
    public void setQueryEndTime(Integer queryEndTime) {
        this.queryEndTime = queryEndTime;
    }


     public Integer getCreateTime() {
        return createTime;
    }

    
    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    
    public String getRemark() {
        return remark;
    }

    
    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getOrgName() {
        return orgName;
    }

    
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    
}
