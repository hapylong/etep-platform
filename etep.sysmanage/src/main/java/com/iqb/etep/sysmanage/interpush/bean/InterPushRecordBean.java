package com.iqb.etep.sysmanage.interpush.bean;

/**
 * 
 * Description: 接口推送记录bean
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年9月22日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */
public class InterPushRecordBean{
    
    /**
     * 主键id
     */
    private String id;
    
    /**
     * 通用code
     */
    private String commonCode;
    
    /**
     * 发起模块
     */
    private String initiate;
    
    /**
     * uuid
     */
    private String uuid;
    
    /**
     * 推送类型 1:新增 2:修改 3:删除
     */
    private Integer pushType;
    
    /**
     * 创建时间
     */
    private Integer createTime;
    
    /**
     * 接收端
     */
    private String receive;
    
    /**
     * 推送状态 1:开始 2:结束 3:异常
     */
    private Integer pushStatus;

    
    public String getId() {
        return id;
    }

    
    public void setId(String id) {
        this.id = id;
    }

    
    public String getCommonCode() {
        return commonCode;
    }

    
    public void setCommonCode(String commonCode) {
        this.commonCode = commonCode;
    }

    
    public String getInitiate() {
        return initiate;
    }

    
    public void setInitiate(String initiate) {
        this.initiate = initiate;
    }

    
    public String getUuid() {
        return uuid;
    }

    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    
    public Integer getPushType() {
        return pushType;
    }

    
    public void setPushType(Integer pushType) {
        this.pushType = pushType;
    }

    
    public Integer getCreateTime() {
        return createTime;
    }

    
    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    
    public String getReceive() {
        return receive;
    }

    
    public void setReceive(String receive) {
        this.receive = receive;
    }

    
    public Integer getPushStatus() {
        return pushStatus;
    }

    
    public void setPushStatus(Integer pushStatus) {
        this.pushStatus = pushStatus;
    }
    
}
