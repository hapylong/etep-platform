package com.iqb.etep.sysmanage.dict.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.sysmanage.dict.bean.SysDictType;
import com.iqb.etep.sysmanage.dict.bean.TreeNode;


public interface SysDictTypeDao {
	
    /**
     * 插入数据
     * @param record
     * @return
     * @author baiyapeng
     */
    int insert(SysDictType record);
    
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
    int updateByPrimaryKeySelective(SysDictType record);
    
    /**
     * 更新数据
     * @param record
     * @return
     * @author baiyapeng
     */
    int updateByPrimaryKey(SysDictType record);
    
    /**
     * 根据主键查询数据
     * @param id
     * @return
     * @author baiyapeng
     */
    SysDictType selectByPrimaryKey(Integer id);
    
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
    int selectCountSelective(SysDictType record);
    
}