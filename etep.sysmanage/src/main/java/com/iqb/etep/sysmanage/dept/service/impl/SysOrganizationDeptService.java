package com.iqb.etep.sysmanage.dept.service.impl;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.base.service.BaseService;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.common.utils.JSONUtil;
import com.iqb.etep.common.utils.SysUserSession;
import com.iqb.etep.common.utils.Attr.CommonConst;
import com.iqb.etep.sysmanage.base.SysManageReturnInfo;
import com.iqb.etep.sysmanage.dept.bean.SysOrganizationDeptInfo;
import com.iqb.etep.sysmanage.dept.biz.SysOrganizationDeptBiz;
import com.iqb.etep.sysmanage.dept.service.ISysOrganizationDeptService;
import com.iqb.etep.sysmanage.hq.service.IHqSysUserService;
import com.iqb.etep.sysmanage.user.bean.SysUser;

@Service
public class SysOrganizationDeptService extends BaseService implements ISysOrganizationDeptService {
    
    @Autowired
    private SysOrganizationDeptBiz sysOrganizationDeptInfoBiz;
    
    @Autowired
    private SysUserSession sysUserSession;
    
    /**
     * 通用用户 Service
     */
    @Autowired
    private IHqSysUserService hqSysUserService;

    @Override
    public void insertSysOrganizationDeptInfo(JSONObject objs) throws IqbException, IqbSqlException {
        SysOrganizationDeptInfo bean = JSONUtil.toJavaObject(objs, SysOrganizationDeptInfo.class);
        //创建人,时间
        bean.setCreateUser(sysUserSession.getUserCode());
        bean.setCreateTime(NumberUtils.toInt(System.currentTimeMillis() / 1000 + ""));
        bean.setLastUser(sysUserSession.getUserCode());
        bean.setLastTime(NumberUtils.toInt(System.currentTimeMillis() / 1000 + ""));
        //可用状态
        bean.setDeleteFlag(1);
        //版本
        bean.setVersion(NumberUtils.toInt(CommonConst.Version));
        //构造参数bean,用于业务检查
        SysOrganizationDeptInfo parameterBean = new SysOrganizationDeptInfo();
        //部门数据,衡量数据重复的标志是orgId,deptName是否同时相同
        parameterBean.setOrgId(bean.getOrgId());
        parameterBean.setDeptName(bean.getDeptName());
        parameterBean.setDeleteFlag(1);   
        if(sysOrganizationDeptInfoBiz.selectCountSelective(parameterBean) < 1) {             	
            sysOrganizationDeptInfoBiz.insert(bean);
        } else {
        	//部门名称已存在
            throw new IqbException(SysManageReturnInfo.SYS_STATION_01090001);
        }
    }
    
    @Override
    public void deleteSysOrganizationDeptInfoById(JSONObject objs) throws IqbException,
        IqbSqlException {
        SysOrganizationDeptInfo bean = JSONUtil.toJavaObject(objs, SysOrganizationDeptInfo.class);
        if(sysOrganizationDeptInfoBiz.selectByPrimaryKey(bean.getId()) != null) { 
        	SysUser sysUser = new SysUser();
        	sysUser.setDeptId(bean.getId());
        	if(!hqSysUserService.isOrgIdBeUsedFromHqUser(sysUser)) {
        		sysOrganizationDeptInfoBiz.deleteByPrimaryKey(bean.getId());
        	} else {
        		//部门已被用户使用
                throw new IqbException(SysManageReturnInfo.SYS_STATION_01090004);
        	}            
        } else {
        	//部门数据不存在
            throw new IqbException(SysManageReturnInfo.SYS_STATION_01090002);
        }       
    }

    @Override
    public void updateSysOrganizationDeptInfoById(JSONObject objs) throws IqbException,
        IqbSqlException {
        SysOrganizationDeptInfo bean = JSONUtil.toJavaObject(objs, SysOrganizationDeptInfo.class);
        //修改人,时间
      	bean.setLastUser(sysUserSession.getUserCode());
        bean.setLastTime(NumberUtils.toInt(System.currentTimeMillis() / 1000 + ""));
        //构造参数bean,用于业务检查
        SysOrganizationDeptInfo parameterBean = new SysOrganizationDeptInfo();
        //部门数据,衡量数据重复的标志是orgId,deptName是否同时相同
        parameterBean.setOrgId(bean.getOrgId());
        parameterBean.setDeptName(bean.getDeptName());
        parameterBean.setDeleteFlag(1);   
        //线程同步
        synchronized(SysOrganizationDeptService.class) {
        	SysOrganizationDeptInfo resultBean = sysOrganizationDeptInfoBiz.selectByPrimaryKey(bean.getId());
        	if(resultBean != null) {
        		if(resultBean.getDeptName().equals(bean.getDeptName())) {
        			sysOrganizationDeptInfoBiz.updateByPrimaryKeySelective(bean);
        		} else{
        			if(sysOrganizationDeptInfoBiz.selectCountSelective(parameterBean) < 1) {
        				sysOrganizationDeptInfoBiz.updateByPrimaryKeySelective(bean);
                	} else {
                		//部门名称已存在
                        throw new IqbException(SysManageReturnInfo.SYS_STATION_01090001);
                	}     
        		}
        	} else {
        		//部门数据不存在
                throw new IqbException(SysManageReturnInfo.SYS_STATION_01090002);
        	}
        }
        
    }
    
    @Override
    public SysOrganizationDeptInfo selectSysOrganizationDeptInfoById(JSONObject objs)
        throws IqbException, IqbSqlException {
        SysOrganizationDeptInfo bean = JSONUtil.toJavaObject(objs, SysOrganizationDeptInfo.class); 
        SysOrganizationDeptInfo resultBean = sysOrganizationDeptInfoBiz.selectByPrimaryKey(bean.getId());
        if(resultBean != null) {         
            return resultBean;
        } else {
        	//部门数据不存在
            throw new IqbException(SysManageReturnInfo.SYS_STATION_01090002);
        }

    }
    
    @Override
    public PageInfo<SysOrganizationDeptInfo> selectSysOrganizationDeptInfoForGrid(JSONObject objs) throws IqbException, IqbSqlException {
    	//分页插件查询结果需要对外包装为PageInfo<T>类型,其中除了数据集合还会额外封装一些分页参数(如:total)
        return new PageInfo<SysOrganizationDeptInfo>(sysOrganizationDeptInfoBiz.selectSelective(objs));
    }

	@Override
	public void deleteSysOrganizationDeptInfoByOrgId(Integer orgId)
			throws IqbException, IqbSqlException {
		sysOrganizationDeptInfoBiz.deleteByOrgId(orgId);
	}

    
}
