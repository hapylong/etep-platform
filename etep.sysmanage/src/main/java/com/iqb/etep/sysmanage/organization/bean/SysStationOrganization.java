package com.iqb.etep.sysmanage.organization.bean;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author leiwenyang
 *角色--机构权限表
 */
@Table(name = "IQB_SYS_STATION_ORGANIZATION")
public class SysStationOrganization {

	@Id
	private Integer id;
	private Integer orgId;// 机构编码
	private Integer stationId;// 角色编码
	private String createUser;
	private Integer createTime;
	private String remark;

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

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
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

}
