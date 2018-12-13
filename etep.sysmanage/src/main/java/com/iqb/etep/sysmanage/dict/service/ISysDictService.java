package com.iqb.etep.sysmanage.dict.service;


import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.sysmanage.dict.bean.SysDictItem;
import com.iqb.etep.sysmanage.dict.bean.SysDictType;
import com.iqb.etep.sysmanage.dict.bean.TreeNode;

public interface ISysDictService {
	
	/**
	 * 新增字典类别数据
	 * @param objs
	 * @throws IqbException
	 * @throws IqbSqlException
	 * @author baiyapeng
	 */
    void insertSysDictType(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 删除字典类别数据
     * @param objs
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    void deleteSysDictTypeById(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 修改字典类别数据
     * @param objs
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    void updateSysDictTypeById(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 根据ID查询字典类别数据
     * @param objs
     * @return
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    SysDictType selectSysDictTypeById(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 查询字典类别项数据
     * @param objs
     * @return
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    public List<TreeNode> selectSysDictTypeForTree(JSONObject objs) throws IqbException, IqbSqlException;
        
    /**
	 * 新增字典项数据
	 * @param objs
	 * @throws IqbException
	 * @throws IqbSqlException
	 * @author baiyapeng
	 */
    void insertSysDictItem(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 删除字典项数据
     * @param objs
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    void deleteSysDictItemById(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 修改字典项数据
     * @param objs
     * @throws IqbException
     * @throws IqbSqlException
     */
    void updateSysDictItemById(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 根据ID查询字典项数据
     * @param objs
     * @return
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    SysDictItem selectSysDictItemById(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 查询字典项数据
     * @param objs
     * @return
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    public PageInfo<SysDictItem> selectSysDictItemForGrid(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 查询字典项数据(对外统一接口)
     * @param objs
     * @return
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    public List<SysDictItem> selectSysDictTypeToListOfBeanFormRedis(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 查询字典项数据(对外统一接口,仅供下拉框查询)
     * @param objs
     * @return
     * @throws IqbException
     * @throws IqbSqlException
     */
    public List<Map<String, Object>> selectSysDictTypeToListOfMapFormRedis(JSONObject objs) throws IqbException, IqbSqlException;
    	

}
