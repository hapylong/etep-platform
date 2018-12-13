package com.iqb.etep.sysmanage.dict.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.common.base.biz.BaseBiz;
import com.iqb.etep.sysmanage.dict.bean.SysDictType;
import com.iqb.etep.sysmanage.dict.bean.TreeNode;
import com.iqb.etep.sysmanage.dict.dao.SysDictTypeDao;

@Component
public class SysDictTypeBiz extends BaseBiz {
	
	@Autowired
	private SysDictTypeDao sysDictTypeDao;
	
	/**
     * 插入数据
     * @param record
     * @return
     * @author baiyapeng
     */
    public int insert(SysDictType record) {
    	//设置数据源
    	this.setDb(0, super.MASTER); 
        return sysDictTypeDao.insert(record);
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
        return sysDictTypeDao.deleteByPrimaryKey(id);
    };
    
    /**
     * 选择性更新数据
     * @param record
     * @return
     * @author baiyapeng
     */
    public int updateByPrimaryKeySelective(SysDictType record) {
    	//设置数据源
    	this.setDb(0, super.MASTER); 
        return sysDictTypeDao.updateByPrimaryKeySelective(record);
    };
    
    /**
     * 更新数据
     * @param record
     * @return
     * @author baiyapeng
     */
    public int updateByPrimaryKey(SysDictType record) {
    	//设置数据源
    	this.setDb(0, super.MASTER); 
        return sysDictTypeDao.updateByPrimaryKey(record);
    };
    
    /**
     * 根据主键查询数据
     * @param id
     * @return
     * @author baiyapeng
     */
    public SysDictType selectByPrimaryKey(Integer id) {
    	//设置数据源
    	setDb(0, super.SLAVE); 
        return sysDictTypeDao.selectByPrimaryKey(id);
    };
    
    /**
     * 查询数据
     * @param objs
     * @return
     * @author baiyapeng
     */
    public List<TreeNode> selectSelective(JSONObject objs) {
    	//设置数据源
    	setDb(0, super.SLAVE); 
    	return sysDictTypeDao.selectSelective(objs);
    };
    
    /**
     * 选择性统计数据
     * @param record
     * @return
     * @author baiyapeng
     */
    public int selectCountSelective(SysDictType record) {
    	//设置数据源
    	setDb(0, super.SLAVE); 
    	return sysDictTypeDao.selectCountSelective(record);
    };

}
