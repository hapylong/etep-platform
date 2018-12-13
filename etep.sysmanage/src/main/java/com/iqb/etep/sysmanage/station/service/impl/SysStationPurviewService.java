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
 * 创建日期: 2016年8月18日
 * HISTORY：
 * 变更日期 
 */
package com.iqb.etep.sysmanage.station.service.impl;

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
import com.iqb.etep.sysmanage.station.bean.SysStationRolePurview;
import com.iqb.etep.sysmanage.station.biz.SysStationRolePurviewBiz;
import com.iqb.etep.sysmanage.station.service.ISysStationRolePurviewService;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;
import com.iqb.etep.sysmanage.sysmenu.service.ISysMenuService;

/**
 * @author leiwenyang
 */
@SuppressWarnings("unchecked")
@Service
public class SysStationPurviewService extends BaseService implements ISysStationRolePurviewService{

    @Autowired
    SysStationRolePurviewBiz stationPurviewBiz;
    
    @Autowired
    private ISysMenuService sysMenuService;
    
  
    public void insertPurview(JSONObject objs) throws IqbException, IqbSqlException {

        if (objs == null || objs.get("roleId") ==null) {

            throw new IqbException(CommonReturnInfo.BASE00090001);
        }
        // 接收的是一个数组
        // 将map集合存到数据库中
        synchronized (SysStationPurviewService.class) {
          // 传过来参数
            Integer newstationid = NumberUtils.toInt(objs.get("roleId").toString());
            if(objs.get("menuIds") instanceof String){
                SysStationRolePurview stationRolePurview = new SysStationRolePurview();
                stationPurviewBiz.deleteByRoleId(newstationid);
                stationRolePurview.setStationRoleId(newstationid);
                stationRolePurview.setStationMenuId(NumberUtils.toInt(objs.get("menuIds").toString()));
                List<SysStationRolePurview> paraList = new ArrayList<SysStationRolePurview>();
                paraList.add(stationRolePurview);
                stationPurviewBiz.insertPurview(paraList);
                
              }
            else{
                   List<String> newpurviewids = (List<String>) objs.get("menuIds");
                   stationPurviewBiz.deleteByRoleId(newstationid);
                   List<SysStationRolePurview> paraList = new ArrayList<SysStationRolePurview>();
                    if(newpurviewids == null){
                     return;
                    }
                  for (int i = 0; i < newpurviewids.size(); i++) {
                    SysStationRolePurview stationRolePurview = new SysStationRolePurview();
                    stationRolePurview.setStationRoleId(newstationid);
                    stationRolePurview.setStationMenuId(NumberUtils.toInt(newpurviewids.get(i)));
                    paraList.add(stationRolePurview);
                }
                if(paraList.size() > 0){
                    stationPurviewBiz.insertPurview(paraList);
                } 
            }
            
            sysMenuService.removeAllUserSysMenuList();
        }

    }

    @Override
    public List<TreeNode> getSysRolePurview(JSONObject objs) throws IqbException, IqbSqlException  {
        if(objs == null || objs.get("roleId") == null){
            return null;
        }
        Integer roleId = NumberUtil.toInt(objs.get("roleId"));
        return stationPurviewBiz.selectForTree(roleId);
    }

    @Override
    public List<TreeNode> getSysRolePurview(Integer roleId) throws IqbException, IqbSqlException {
        if(roleId == null){
            return null;
        }
        return stationPurviewBiz.selectForTree(roleId);
    }
    
    /*
     *删除已分配角色 
     */
    public void deleteByRoleId(Integer newstationid) {
       if(newstationid!=null){
         stationPurviewBiz.deleteByRoleId(newstationid); 
    }
    }

    @Override
    public List<String> getStationIdList(List<String> stationCodeList) {
        return stationPurviewBiz.getStationIdList(stationCodeList);
    }

    @Override
    public List<TreeNode> getSysRolePurview(List<String> stationIdList) {
        if(stationIdList == null){
            return null;
        }
        return stationPurviewBiz.selectForTree(stationIdList);
    }


    
  
}
