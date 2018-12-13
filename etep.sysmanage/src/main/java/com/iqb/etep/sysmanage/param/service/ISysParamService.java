package com.iqb.etep.sysmanage.param.service;



import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.sysmanage.param.bean.SysParam;

public interface ISysParamService {
	
	/**
     * 新增参数数据
     * @param objs
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    void insertSysParam(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 删除参数数据
     * @param objs	
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
     void deleteSysParamById(JSONObject objs) throws IqbException, IqbSqlException;
    
   /**
    * 修改参数数据
    * @param objs
    * @throws IqbException
    * @throws IqbSqlException
    * @author baiyapeng
    */
    void updateSysParamById(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 根据ID查询参数数据
     * @param objs
     * @return
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    SysParam selectSysParamById(JSONObject objs) throws IqbException, IqbSqlException;
    
    /**
     * 查询参数数据
     * @param objs
     * @return
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    PageInfo<SysParam> selectSysParamForGrid(JSONObject objs) throws IqbException, IqbSqlException;

    /**
     * 查询参数数据(对外统一接口)
     * @param objs
     * @return
     * @throws IqbException
     * @throws IqbSqlException
     * @author baiyapeng
     */
    public SysParam selectSysParamFormRedis(JSONObject objs) throws IqbException, IqbSqlException;

}
