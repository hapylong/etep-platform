/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : SysStationRolePurviewBiz.java
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

import com.iqb.etep.common.base.biz.CommonBiz;
import com.iqb.etep.sysmanage.station.bean.SysStationRolePurview;
import com.iqb.etep.sysmanage.station.dao.SysStationRolePurviewDao;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;

/**
 * @author leiwenyang
 */
@Component
public class SysStationRolePurviewBiz extends CommonBiz<SysStationRolePurview>{

    @Autowired
    private SysStationRolePurviewDao stationPurviewDao;

 

/*

    *//**
     * @param newstationid
     * @param insertList
     *//*
    public void insertPurview(Integer newstationid, List<Integer> insertList) {
      
        stationPurviewDao.insertPurview(newstationid, insertList);
    }
*/




    /**
     * @param sysList
     */
    public void insertPurview(List<SysStationRolePurview> sysList) {
        setDb(0, super.MASTER); 
        stationPurviewDao.insertPurview(sysList); 
    }


    /**
     * 
     * Description: 查询角色菜单权限
     * @param
     * @return List<TreeNode>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月26日 下午1:14:47
     */
    public List<TreeNode> selectForTree(Integer roleId) {
        setDb(0, super.SLAVE); 
        return stationPurviewDao.selectSelective(roleId);
    }

    /**
     * 
     * Description: 根据角色id删除角色信息
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月26日 下午1:40:52
     */
    public void deleteByRoleId(Integer newstationid) {
        setDb(0, super.MASTER); 
        stationPurviewDao.deleteByRoleId(newstationid); 
    }

    /**
     * 
     * Description: 获取角色id集合
     * @param
     * @return List<String>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年10月21日 下午12:01:58
     */
    public List<String> getStationIdList(List<String> stationCodeList) {
        setDb(0, super.SLAVE); 
        return stationPurviewDao.getStationIdList(stationCodeList); 
    }

    /**
     * 
     * Description: 获取角色菜单权限
     * @param
     * @return List<TreeNode>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年10月21日 下午2:01:59
     */
    public List<TreeNode> selectForTree(List<String> stationIdList) {
        setDb(0, super.SLAVE); 
        return stationPurviewDao.selectSelectiveByStationIdList(stationIdList);
    }



}
