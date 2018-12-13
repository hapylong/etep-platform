/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : SysStationMenu.java
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
package com.iqb.etep.sysmanage.station.biz;





import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.common.base.biz.CommonBiz;
import com.iqb.etep.sysmanage.station.bean.SysStationMenu;
import com.iqb.etep.sysmanage.station.dao.SysStationMenuDao;


/**
 * @author leiwenyang
 *
 */
@Component
public class SysStationMenuBiz extends CommonBiz<SysStationMenu>{
    
    @Autowired
    private SysStationMenuDao menuDao;

    /**
     * @param objs 
     * @return
     */
    public List<SysStationMenu> selectPurviewUrl(JSONObject objs) {
      // setDb(, dbType);
        return menuDao.selectPurviewUrl(objs);
    }
}
