package com.iqb.etep.sysmanage.dict.dao;

import java.util.List;



import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.sysmanage.dict.bean.SysDictItem;



public interface SysDictItemDao {
	
    /**
     * 插入数据
     * @param record
     * @return
     * @author baiyapeng
     */
    int insert(SysDictItem record);
    
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
    int updateByPrimaryKeySelective(SysDictItem record);
    
    /**
     * 更新数据
     * @param record
     * @return
     * @author baiyapeng
     */
    int updateByPrimaryKey(SysDictItem record);
    
    /**
     * 根据主键查询数据
     * @param id
     * @return
     * @author baiyapeng
     */
    SysDictItem selectByPrimaryKey(Integer id);
    
    /**
     * 查询数据
     * @param objs
     * @return
     * @author baiyapeng
     */
    List<SysDictItem> selectSelective(JSONObject objs);
    
    /**
     * 选择性统计数据
     * @param record
     * @return
     * @author baiyapeng
     */
    int selectCountSelective(SysDictItem record);
    
    /**
     * 查询数据(对外统一接口)
     * @param record
     * @return
     * @author baiyapeng
     */
    List<SysDictItem> selectSysDictTypeToListOfBean(SysDictItem record);
    
}