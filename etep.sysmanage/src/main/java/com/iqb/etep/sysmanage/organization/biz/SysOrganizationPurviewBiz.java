package com.iqb.etep.sysmanage.organization.biz;

import java.util.List;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.common.base.biz.BaseBiz;
import com.iqb.etep.sysmanage.organization.dao.SysOrganizationPurviewDao;
@Component
public class SysOrganizationPurviewBiz extends BaseBiz {
	
	@Autowired
    private SysOrganizationPurviewDao sysOrganizationPurviewDao;

	/**
	 * 插入数据
	 * @param objs
	 * @return
	 * @author baiyapeng
	 */
    public int insert(JSONObject objs) {
    	//设置数据源
    	this.setDb(0, super.MASTER); 
        return sysOrganizationPurviewDao.insert(objs);
    };
    
    /**
     * 删除数据
     * @param id
     * @return
     * @author baiyapeng
     */
    public int deleteByOrgId(Integer orgId) {
    	//设置数据源
    	this.setDb(0, super.MASTER); 
        return sysOrganizationPurviewDao.deleteByOrgId(orgId);
    };  
    
    /**
     * 查询数据
     * @param objs
     * @return
     * @author baiyapeng
     */
    public List<Integer> selectSelective(JSONObject objs) {
    	//设置数据源
    	setDb(0, super.SLAVE); 
        return sysOrganizationPurviewDao.selectSelective(objs);
    }
    
    /**
     * 统计机构菜单数据
     * @param menuId
     * @return
     * @author baiyapeng
     */
    public int selectCountByMenuId(Integer menuId) {
    	//设置数据源
    	setDb(0, super.SLAVE); 
        return sysOrganizationPurviewDao.selectCountByMenuId(menuId);
    };
    
    /**
     * 统计角色菜单数据
     * @param objs
     * @return
     * @author baiyapeng
     */
    public int selectCountSysMenuPurview(JSONObject objs) {
    	//设置数据源
    	setDb(0, super.SLAVE); 
        return sysOrganizationPurviewDao.selectCountSysMenuPurview(objs);
    };

    /**
     * 
     * Description: 获取所有的机构信息
     * @param
     * @return List<SysOrganizationInfo>
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月30日 上午11:12:42
     */
    public List<Map<Integer, String>> getAllOrgInfo() {
        setDb(0, super.SLAVE); 
        return sysOrganizationPurviewDao.getAllOrgInfo();
    }
}
