package com.iqb.etep.sysmanage.organization.dao;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

import com.iqb.etep.sysmanage.organization.bean.SysStationOrganization;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;

public interface SysStationOrganDao extends Mapper<SysStationOrganization> {

	void deleteByRoleId(Integer newstationid);

	void insertSysStationOrgan(List<SysStationOrganization> paraList);

	List<TreeNode> selectOrganTree(Integer roleId);

}
