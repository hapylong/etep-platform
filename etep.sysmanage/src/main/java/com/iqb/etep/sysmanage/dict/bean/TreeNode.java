package com.iqb.etep.sysmanage.dict.bean;

public class TreeNode {
	
	private Integer id;
	private Integer pId;
	private String name;
	private Boolean open;
	private Boolean isParent;
	private String dictTypeCode;//字典类别代码
	private Integer isContent;//是否是目录
	private Integer isEnable;//是否启用
	
	//构造器
	public TreeNode() {
		open = false;
	}	
	
	public Integer getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	public String getDictTypeCode() {
		return dictTypeCode;
	}
	public void setDictTypeCode(String dictTypeCode) {
		this.dictTypeCode = dictTypeCode;
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
	public Integer getIsContent() {
		return isContent;
	}
	public void setIsContent(Integer isContent) {
		this.isContent = isContent;
		if(isContent == 1) {
			isParent = true;
		} else {
			isParent = false;
		}
	}   
}
