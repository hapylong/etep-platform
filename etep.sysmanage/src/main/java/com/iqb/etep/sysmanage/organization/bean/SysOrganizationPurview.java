package com.iqb.etep.sysmanage.organization.bean;


public class SysOrganizationPurview {
	
    private Integer id;
    private Integer orgId;
    private Integer orgMenuId;
    private String createUser;
    private Integer createTime;
    private String lastUser;
    private Integer lastTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getOrgMenuId() {
        return orgMenuId;
    }

    public void setOrgMenuId(Integer orgMenuId) {
        this.orgMenuId = orgMenuId;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getLastTime() {
        return lastTime;
    }

    public void setLastTime(Integer lastTime) {
        this.lastTime = lastTime;
    }

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getLastUser() {
		return lastUser;
	}

	public void setLastUser(String lastUser) {
		this.lastUser = lastUser;
	}
}