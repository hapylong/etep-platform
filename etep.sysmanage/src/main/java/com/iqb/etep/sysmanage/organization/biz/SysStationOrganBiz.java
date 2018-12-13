/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : SysStationPurviewService.java
 *
 * PURPOSE : 
 *
 * AUTHOR : leiwenyang
 *
 *
 * 创建日期: 2016年9月19日
 * HISTORY：
 * 变更日期 
 */
package com.iqb.etep.sysmanage.organization.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;






import com.iqb.etep.common.base.biz.CommonBiz;
import com.iqb.etep.sysmanage.organization.bean.SysStationOrganization;
import com.iqb.etep.sysmanage.organization.dao.SysStationOrganDao;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;



/**
 * 
 * @author leiwenyang
 *
 */

@Component
public class SysStationOrganBiz  extends CommonBiz<SysStationOrganization> {
	
	@Autowired
	private SysStationOrganDao stationOrganDao;

	public void deleteByRoleId(Integer newstationid) {
		 setDb(0, super.MASTER); 
		 stationOrganDao.deleteByRoleId(newstationid);
	}

	public void insertSysStationOrgan(List<SysStationOrganization> paraList) {
		 setDb(0, super.MASTER); 
		 stationOrganDao.insertSysStationOrgan(paraList); 
	}

	public List<TreeNode> selectOrganTree(Integer roleId) {
		 setDb(0, super.MASTER); 
		return stationOrganDao.selectOrganTree(roleId);
	}

}
