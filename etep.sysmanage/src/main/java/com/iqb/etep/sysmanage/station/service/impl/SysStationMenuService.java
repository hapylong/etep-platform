/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : SysStationMenuService.java
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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.common.base.CommonReturnInfo;
import com.iqb.etep.common.base.service.BaseService;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.sysmanage.station.bean.SysStationMenu;
import com.iqb.etep.sysmanage.station.biz.SysStationMenuBiz;
import com.iqb.etep.sysmanage.station.service.ISysStationMenuService;


/**
 * @author leiwenyang
 *
 */
@Service
public class SysStationMenuService extends BaseService implements ISysStationMenuService{
    @Autowired
    SysStationMenuBiz sysStationMenuBiz;
    /**
     * @param objs
     */
/*    public void deleteSysStationRole(JSONObject objs) {
        SysStationMenu bean = JSONUtil.toJavaObject(objs, SysStationMenu.class);
        synchronized (SysStationRoleService.class) {
            if (sysStationMenuBiz.selectOne(record)) {
                sysStationMenuBiz.delete(bean);
            } else {
                throw new IqbException(CommonReturnInfo.BASE00090002);
            }
        }
        
    }*/
    /**
     * @param objs 
     * @return
     * @throws IqbException 
     */
    public List<SysStationMenu> selectPurviewUrl(JSONObject objs) throws IqbException,IqbSqlException{
        synchronized (SysStationMenuService.class) {
            List<SysStationMenu> purviewslist =sysStationMenuBiz.selectPurviewUrl(objs);
            if(purviewslist.size()<0){
            throw new IqbException(CommonReturnInfo.BASE00000002);
            }
                return purviewslist;
            
          
          
      }
       
    }
}
