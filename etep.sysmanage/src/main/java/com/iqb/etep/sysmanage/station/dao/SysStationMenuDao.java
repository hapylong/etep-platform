/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : SysStationMenuDao.java
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
package com.iqb.etep.sysmanage.station.dao;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.sysmanage.station.bean.SysStationMenu;


/**
 * @author leiwenyang
 *
 */
public interface SysStationMenuDao extends Mapper<SysStationMenu>{

  
    List<SysStationMenu> selectPurviewUrl(JSONObject objs);

}
