package com.iqb.etep.sysmanage.organization.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.common.base.CommonReturnInfo;
import com.iqb.etep.common.base.service.BaseService;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.common.utils.NumberUtil;
import com.iqb.etep.common.utils.SysUserSession;
import com.iqb.etep.sysmanage.organization.bean.SysStationOrganization;
import com.iqb.etep.sysmanage.organization.biz.SysStationOrganBiz;
import com.iqb.etep.sysmanage.organization.service.ISysStationOrganService;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;
import com.iqb.etep.sysmanage.sysmenu.service.ISysMenuService;

@SuppressWarnings("unchecked")
@Service
public class SysStationOrganService extends BaseService implements ISysStationOrganService {
   
	@Autowired
	SysStationOrganBiz stationOrganBiz;
	 @Autowired
	private ISysMenuService sysMenuService;
	@Autowired
	SysUserSession sysUserSession;
	public void insertSysStationOrgan(JSONObject objs)throws IqbException, IqbSqlException  {
		if(objs==null || objs.get("roleId")==null){
			throw new IqbException(CommonReturnInfo.BASE00090001);
		}
		   synchronized (SysStationOrganService.class) {
		       sysMenuService.removeAllUserSysMenuList();
	            // 传过来参数
	            Integer newstationid = NumberUtils.toInt(objs.get("roleId").toString());
	            deleteByRoleId(newstationid);
	           if( objs.get("menuIds") instanceof String){
	               SysStationOrganization stationOrgan = new SysStationOrganization();
                   stationOrgan.setStationId(newstationid);
                   stationOrgan.setOrgId(NumberUtils.toInt(objs.get("menuIds").toString()));
                   stationOrgan.setCreateTime(NumberUtils.toInt(System.currentTimeMillis() / 1000 + ""));
                   stationOrgan.setCreateUser(sysUserSession.getUserCode()); 
                   List<SysStationOrganization> paraList = new ArrayList<SysStationOrganization>();
                   paraList.add(stationOrgan);
                   stationOrganBiz.insertSysStationOrgan(paraList);
	           }else{
	            List<String> neworgids = (List<String>) objs.get("menuIds");
	            List<SysStationOrganization> paraList = new ArrayList<SysStationOrganization>();
	            if(neworgids == null){
	                return;
	            }
	            for (int i = 0; i < neworgids.size(); i++) {
	            	SysStationOrganization stationOrgan = new SysStationOrganization();
	            	stationOrgan.setStationId(newstationid);
	                stationOrgan.setOrgId(NumberUtils.toInt(neworgids.get(i)));
	                stationOrgan.setCreateTime(NumberUtils.toInt(System.currentTimeMillis() / 1000 + ""));
	                stationOrgan.setCreateUser(sysUserSession.getUserCode());
	                paraList.add(stationOrgan);
	            }
	            if(paraList.size() > 0){
	            	stationOrganBiz.insertSysStationOrgan(paraList);
	            	
	            }
	        }
		   }
	}
	
	public void deleteByRoleId(Integer newstationid)throws IqbException, IqbSqlException{
	    sysMenuService.removeAllUserSysMenuList();
	    stationOrganBiz.deleteByRoleId(newstationid);
	}
	public List<TreeNode> getStationOrgan(JSONObject objs)throws IqbException, IqbSqlException  {
	if(objs==null|| objs.get("roleId")==null){
		return null;
	}
	 Integer roleId = NumberUtil.toInt(objs.get("roleId"));
	 return stationOrganBiz.selectOrganTree(roleId);
      
	}
}
