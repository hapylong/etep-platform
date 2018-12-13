package com.iqb.etep.sysmanage.organization.dao;

import java.util.List;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface SysOrganizationPurviewDao{

    /**
     * 插入数据
     * @param objs
     * @return
     * @author baiyapeng
     */
    int insert(JSONObject objs);

    /**
     * 删除数据
     * @param id
     * @return
     * @author baiyapeng
     */
    int deleteByOrgId(Integer orgId);

    /**
     * 查询数据
     * @param objs
     * @return
     * @author baiyapeng
     */
    List<Integer> selectSelective(JSONObject objs);

    /**
     * 统计机构菜单数据
     * @param menuId
     * @return
     * @author baiyapeng
     */
    int selectCountByMenuId(Integer menuId);

    /**
     * 统计角色菜单数据
     * @param objs
     * @return
     * @author baiyapeng
     */
    int selectCountSysMenuPurview(JSONObject objs);

    /**
     * Description: 获取所有的机构信息
     * 
     * @param
     * @return List<SysOrganizationInfo>
     * @throws
     * @Author wangxinbang Create Date: 2016年8月30日 上午11:13:13
     */
    List<Map<Integer, String>> getAllOrgInfo();

}
