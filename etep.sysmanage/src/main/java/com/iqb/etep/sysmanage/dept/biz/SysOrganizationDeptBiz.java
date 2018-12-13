package com.iqb.etep.sysmanage.dept.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.iqb.etep.common.base.biz.BaseBiz;
import com.iqb.etep.sysmanage.dept.bean.SysOrganizationDeptInfo;
import com.iqb.etep.sysmanage.dept.dao.SysOrganizationDeptDao;

@Component
public class SysOrganizationDeptBiz extends BaseBiz {
    
    @Autowired
    private SysOrganizationDeptDao sysOrganizationDeptDao;
    
    /**
     * 插入数据
     * @param record
     * @return
     * @author baiyapeng
     */
    public int insert(SysOrganizationDeptInfo record) {
    	//设置数据源
        this.setDb(0, super.MASTER); 
        return sysOrganizationDeptDao.insert(record);
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
        return sysOrganizationDeptDao.deleteByPrimaryKey(id);
    };    
    
    /**
     * 删除数据
     * @param orgId
     * @return
     * @author baiyapeng
     */
    public int deleteByOrgId(Integer orgId) {
    	//设置数据源
    	this.setDb(0, super.MASTER); 
        return sysOrganizationDeptDao.deleteByOrgId(orgId);
    };
    
    /**
     * 选择性更新数据
     * @param record
     * @return
     * @author baiyapeng
     */
    public int updateByPrimaryKeySelective(SysOrganizationDeptInfo record) {
    	//设置数据源
        this.setDb(0, super.MASTER); 
        return sysOrganizationDeptDao.updateByPrimaryKeySelective(record);
    };
    
    /**
     * 更新数据
     * @param record
     * @return
     * @author baiyapeng
     */
    public int updateByPrimaryKey(SysOrganizationDeptInfo record) {
    	//设置数据源
        this.setDb(0, super.MASTER); 
        return sysOrganizationDeptDao.updateByPrimaryKey(record);
    };
    
    /**
     * 根据主键查询数据
     * @param id
     * @return
     * @author baiyapeng
     */
    public SysOrganizationDeptInfo selectByPrimaryKey(Integer id) {
    	//设置数据源
        setDb(0, super.SLAVE); 
        return sysOrganizationDeptDao.selectByPrimaryKey(id);
    };
    
    /**
     * 分页查询数据
     * @param objs
     * @return
     * @author baiyapeng
     */
    public List<SysOrganizationDeptInfo> selectSelective(JSONObject objs) {
    	//设置数据源
        setDb(0, super.SLAVE); 
        //开始分页,采用MyBatis分页插件分页,会在下一句查询中自动启用分页机制,底层使用拦截器,所以XML中不用关心分页参数
        PageHelper.startPage(getPagePara(objs));
        return sysOrganizationDeptDao.selectSelective(objs);
    };
    
    /**
     * 选择性统计数据
     * @param record
     * @return
     * @author baiyapeng
     */
    public int selectCountSelective(SysOrganizationDeptInfo record) {
    	//设置数据源
        setDb(0, super.SLAVE); 
        return sysOrganizationDeptDao.selectCountSelective(record);
    };
    
}