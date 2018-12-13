package com.iqb.etep.sysmanage.organization.bean;

public class TreeNode {
	
	private Integer id;
	private Integer pId;
	private String name;
	private Boolean open;
	private Boolean isParent;
	
	//构造器
	public TreeNode() {
		open = false;
		isParent = true;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	
	

}
