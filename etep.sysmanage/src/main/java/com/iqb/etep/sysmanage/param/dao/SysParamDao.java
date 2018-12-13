package com.iqb.etep.sysmanage.param.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.sysmanage.param.bean.SysParam;


public interface SysParamDao {
	
	/**
	 * 插入数据
	 * @param record
	 * @return
	 * @author baiyapeng
	 */
    int insert(SysParam record);

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
    int updateByPrimaryKeySelective(SysParam record);

    /**
     * 更新数据
     * @param record
     * @return
     * @author baiyapeng
     */
    int updateByPrimaryKey(SysParam record);
    
    /**
     * 根据主键查询数据
     * @param id
     * @return
     * @author baiyapeng
     */
    SysParam selectByPrimaryKey(Integer id);
    
    /**
     * 查询数据
     * @param objs
     * @return
     * @author baiyapeng
     */
    List<SysParam> selectSelective(JSONObject objs);
    
    /**
     * 选择性统计数据
     * @param record
     * @return
     * @author baiyapeng
     */
    int selectCountSelective(SysParam record);
    
    /**
     * 查询数据(对外统一接口)
     * @param itemKey
     * @return
     * @author baiyapeng
     */
    SysParam selectByItemKey(String itemKey);
}