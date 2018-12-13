package com.iqb.etep.sysmanage.sysmenu.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.sysmanage.sysmenu.bean.SysMenu;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;





public interface SysMenuDao {
	
	
	/**
     * 插入数据
     * @param record
     * @return
     * @author baiyapeng
     */
    int insert(SysMenu record);
    
    /**
     * 删除数据
     * @param id
     * @return
     * @author baiyapeng
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 选择性更新数据
     * @param record
     * @return
     * @author baiyapeng
     */
    int updateByPrimaryKeySelective(SysMenu record);

    /**
     * 更新数据
     * @param record
     * @return
     * @author baiyapeng
     */
    int updateByPrimaryKey(SysMenu record);
    
    /**
     * 根据主键查询数据
     * @param id
     * @return
     * @author baiyapeng
     */
    SysMenu selectByPrimaryKey(Integer id);
    
    /**
     * 查询数据
     * @param objs
     * @return
     * @author baiyapeng
     */
    List<TreeNode> selectSelective(JSONObject objs);
    
    /**
     * 选择性统计数据
     * @param record
     * @return
     * @author baiyapeng
     */
    int selectCountSelective(SysMenu record);
    
    /**
     * 查询最大菜单编码
     * @param parentId
     * @return
     * @author baiyapeng
     */
    String selectMaxMenuCode(Integer parentId);
    
    /**
     * 统计子级菜单
     * @param id
     * @return
     * @author baiyapeng
     */
    int selectCountChildLevel(Integer id);
     
}
