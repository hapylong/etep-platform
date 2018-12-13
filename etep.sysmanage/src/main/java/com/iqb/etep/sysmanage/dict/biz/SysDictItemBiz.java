package com.iqb.etep.sysmanage.dict.biz;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.iqb.etep.common.base.biz.BaseBiz;
import com.iqb.etep.sysmanage.dict.bean.SysDictItem;
import com.iqb.etep.sysmanage.dict.dao.SysDictItemDao;
@Component
public class SysDictItemBiz extends BaseBiz {
	
	@Autowired
	private SysDictItemDao sysDictTiemDao;
	
	/**
     * 插入数据
     * @param record
     * @return
     * @author baiyapeng
     */
    public int insert(SysDictItem record) {
    	//设置数据源
    	this.setDb(0, super.MASTER); 
        return sysDictTiemDao.insert(record);
    };
    
    /**
     * 删除数据
     * @param id
     * @return
     * @author baiyapeng
     */
    public int deleteByPrimaryKey(Integer id) {
    	//设置数据源
    	this.setDb(0, super.MASTER); 
        return sysDictTiemDao.deleteByPrimaryKey(id);
    };    
    
    /**
     * 选择性更新数据
     * @param record
     * @return
     * @author baiyapeng
     */
    public int updateByPrimaryKeySelective(SysDictItem record) {
    	//设置数据源
    	this.setDb(0, super.MASTER); 
        return sysDictTiemDao.updateByPrimaryKeySelective(record);
    };
    
    /**
     * 更新数据
     * @param record
     * @return
     * @author baiyapeng
     */
    public int updateByPrimaryKey(SysDictItem record) {
    	//设置数据源
    	this.setDb(0, super.MASTER); 
        return sysDictTiemDao.updateByPrimaryKey(record);
    };
    
    /**
     * 根据主键查询数据
     * @param id
     * @return
     * @author baiyapeng
     */
    public SysDictItem selectByPrimaryKey(Integer id) {
    	//设置数据源
    	setDb(0, super.SLAVE); 
        return sysDictTiemDao.selectByPrimaryKey(id);
    };

    /**
     * 查询数据
     * @param objs
     * @return
     * @author baiyapeng
     */
    public List<SysDictItem> selectSelective(JSONObject objs) {
    	//设置数据源
    	setDb(0, super.SLAVE); 
    	//开始分页,采用MyBatis分页插件分页,会在下一句查询中自动启用分页机制,底层使用拦截器,所以XML中不用关心分页参数
        PageHelper.startPage(getPagePara(objs));
    	return sysDictTiemDao.selectSelective(objs);
    };
    
    /**
     * 选择性统计数据
     * @param record
     * @return
     * @author baiyapeng
     */
    public int selectCountSelective(SysDictItem record) {
    	//设置数据源
    	setDb(0, super.SLAVE); 
    	return sysDictTiemDao.selectCountSelective(record);
    };
    
    /**
     * 查询数据(对外统一接口)
     * @param record
     * @return
     * @author baiyapeng
     */
    public List<SysDictItem> selectSysDictTypeToListOfBean(SysDictItem record) {
    	//设置数据源为从库
    	setDb(0, super.SLAVE); 
    	return sysDictTiemDao.selectSysDictTypeToListOfBean(record);
    };
   
}
