package com.iqb.etep.sysmanage.organization.biz;




import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.common.base.biz.BaseBiz;
import com.iqb.etep.sysmanage.organization.bean.SysOrganizationInfo;
import com.iqb.etep.sysmanage.organization.dao.SysOrganizationInfoDao;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;

@Component
public class SysOrganizationInfoBiz extends BaseBiz {
    
    @Autowired
    private SysOrganizationInfoDao sysOrganizationInfoDao;
    
    /**
	 * 插入数据
	 * @param record
	 * @return
	 * @author baiyapeng
	 */
    public int insert(SysOrganizationInfo record) {
    	//设置数据源
    	this.setDb(0, super.MASTER); 
        return sysOrganizationInfoDao.insert(record);
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
        return sysOrganizationInfoDao.deleteByPrimaryKey(id);
    };   

    /**
     * 选择性更新数据
     * @param record
     * @return
     * @author baiyapeng
     */
    public int updateByPrimaryKeySelective(SysOrganizationInfo record) {
    	//设置数据源
    	this.setDb(0, super.MASTER); 
        return sysOrganizationInfoDao.updateByPrimaryKeySelective(record);
    };
    
    /**
     * 更新数据
     * @param record
     * @return
     * @author baiyapeng
     */
    public int updateByPrimaryKey(SysOrganizationInfo record) {
    	//设置数据源
    	this.setDb(0, super.MASTER); 
        return sysOrganizationInfoDao.updateByPrimaryKey(record);
    };
    
    /**
     * 根据主键查询数据
     * @param id
     * @return
     * @author baiyapeng
     */
    public SysOrganizationInfo selectByPrimaryKey(Integer id) {
    	//设置数据源
    	setDb(0, super.SLAVE); 
        return sysOrganizationInfoDao.selectByPrimaryKey(id);
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
        return sysOrganizationInfoDao.selectSelective(objs);
    };
    
    /**
     * 选择性统计数据
     * @param record
     * @return
     * @author baiyapeng
     */
    public int selectCountSelective(SysOrganizationInfo record) {
    	//设置数据源
    	setDb(0, super.SLAVE); 
        return sysOrganizationInfoDao.selectCountSelective(record);
    };
    
    /**
     * 查询最大机构编码
     * @param parentId
     * @return
     * @author baiyapeng
     */
    public String selectMaxOrgCode(Integer parentId) {
    	//设置数据源
    	setDb(0, super.SLAVE); 
        return sysOrganizationInfoDao.selectMaxOrgCode(parentId);
    }
    
    /**
     * 统计子级菜单
     * @param id
     * @return
     * @author baiyapeng
     */
    public int selectCountChildLevel(Integer id) {
    	//设置数据源
    	setDb(0, super.SLAVE); 
        return sysOrganizationInfoDao.selectCountChildLevel(id);
    };

    /**
     * @param orgId
     * @return
     */
    public List<Map<Integer, String>> selectOrganizationDept(String orgId) {
        setDb(0, super.SLAVE);
        return sysOrganizationInfoDao.selectOrganizationDept(orgId);
    }

    /**
     * 
     * Description: 根据机构编码获取机构信息
     * @param
     * @return SysOrganizationInfo
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月25日 下午4:23:29
     */
    public SysOrganizationInfo getSysOrganizationInfoByOrgCode(String orgCode){
        setDb(0, super.SLAVE);
        return sysOrganizationInfoDao.getSysOrganizationInfoByOrgCode(orgCode);
    }
    
    public List<Map<String, Object>> selectOrgToListOfMap(SysOrganizationInfo record){
      setDb(0, "sdb");
      return this.sysOrganizationInfoDao.selectOrgToListOfMap(record);
    }
    
}
