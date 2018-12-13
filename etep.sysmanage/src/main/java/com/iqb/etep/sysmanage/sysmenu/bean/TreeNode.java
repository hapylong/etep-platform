package com.iqb.etep.sysmanage.sysmenu.bean;

import javax.persistence.Column;

import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders={"id","pId","name","open","checked"})
public class TreeNode {
	
	private Integer id;
	private Integer pId;
	private String name;
	private Boolean open;
	private Boolean isParent;
	private Boolean checked;	
	private Integer menuType;//菜单类型
	private String url;//菜单URL
	
	//构造器
	public TreeNode() {
		open = false;
		checked = false;
	}	
    
    public Integer getId() {
        return id;
    }  
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "pId")
    public Integer getpId() {
        return pId;
    }

    @Column(name = "pId")
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

    
    public Boolean getChecked() {
        return checked;
    }

    
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    
    public String getUrl() {
        return url;
    }

    
    public void setUrl(String url) {
        this.url = url;
    }

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
		if(menuType == 1) {
			isParent = true;
		} else {
			isParent = false;
		}
	}
    
    
	

}
