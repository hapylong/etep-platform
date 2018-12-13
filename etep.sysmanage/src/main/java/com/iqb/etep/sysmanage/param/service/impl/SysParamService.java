package com.iqb.etep.sysmanage.param.service.impl;



import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.base.service.BaseService;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.common.utils.Attr.CommonConst;
import com.iqb.etep.common.utils.JSONUtil;
import com.iqb.etep.common.utils.SysUserSession;
import com.iqb.etep.sysmanage.base.SysManageReturnInfo;
import com.iqb.etep.sysmanage.param.bean.SysParam;
import com.iqb.etep.sysmanage.param.biz.SysParamBiz;
import com.iqb.etep.sysmanage.param.redis.ParamRedis;
import com.iqb.etep.sysmanage.param.service.ISysParamService;

@Service
public class SysParamService extends BaseService implements ISysParamService {
	
	@Autowired
	private SysParamBiz sysParamBiz;
	
	@Autowired
	private SysUserSession sysUserSession;
	
	@Autowired
	private ParamRedis paramRedis;

	@Override
	public void insertSysParam(JSONObject objs) throws IqbException,
			IqbSqlException {
		SysParam bean = JSONUtil.toJavaObject(objs, SysParam.class);
		//创建人,时间
        bean.setCreateUid(sysUserSession.getUserCode());
        bean.setCreateTime(NumberUtils.toInt(System.currentTimeMillis() / 1000 + ""));
        bean.setLastUid(sysUserSession.getUserCode());
        bean.setLastTime(NumberUtils.toInt(System.currentTimeMillis() / 1000 + ""));
        //可用状态
        bean.setDeleteFlag(1);
        //版本
        bean.setVersion(NumberUtils.toInt(CommonConst.Version));    
        //构造参数bean,用于业务检查
        SysParam parameterBean = new SysParam();
        //参数数据,衡量数据重复的标志是itemKey是否相同
        parameterBean.setItemKey(bean.getItemKey());
        parameterBean.setDeleteFlag(1);
        if(sysParamBiz.selectCountSelective(parameterBean) < 1) {             	
        	sysParamBiz.insert(bean);
        } else {
        	//参数代码已存在
            throw new IqbException(SysManageReturnInfo.SYS_STATION_01080001);
        }
	}

	@Override
	public void deleteSysParamById(JSONObject objs) throws IqbException,
			IqbSqlException {
		SysParam bean = JSONUtil.toJavaObject(objs, SysParam.class);
		SysParam resultBean = sysParamBiz.selectByPrimaryKey(bean.getId());
		//由于参数表没有其它关联表,故如果数据存在,则可以直接删除
        if(resultBean != null) {         
        	sysParamBiz.deleteByPrimaryKey(bean.getId());
        	//清除缓存
        	paramRedis.removeSysParamFromRedis(resultBean.getItemKey());
        } else {
        	//参数数据不存在
            throw new IqbException(SysManageReturnInfo.SYS_STATION_01080002);
        }  
	}

	@Override
	public void updateSysParamById(JSONObject objs) throws IqbException,
			IqbSqlException {
		SysParam bean = JSONUtil.toJavaObject(objs, SysParam.class);
		//修改人,时间
      	bean.setLastUid(sysUserSession.getUserCode());
        bean.setLastTime(NumberUtils.toInt(System.currentTimeMillis() / 1000 + ""));
		//构造参数bean,用于业务检查
		SysParam parameterBean = new SysParam();
		//参数数据,衡量数据重复的标志是itemKey是否相同
        parameterBean.setItemKey(bean.getItemKey());
        parameterBean.setDeleteFlag(1);
        //线程同步
		synchronized(SysParamService.class) {
			SysParam resultBean = sysParamBiz.selectByPrimaryKey(bean.getId());
			if(resultBean != null) {
				if(bean.getItemKey().equals(resultBean.getItemKey())) {
					sysParamBiz.updateByPrimaryKeySelective(bean);
					//清除缓存
		        	paramRedis.removeSysParamFromRedis(bean.getItemKey());
				} else {
					 if(sysParamBiz.selectCountSelective(parameterBean) < 1) {         
						 sysParamBiz.updateByPrimaryKeySelective(bean);
						//清除缓存
				        paramRedis.removeSysParamFromRedis(resultBean.getItemKey());
		             } else {
		            	//参数代码已存在
		                throw new IqbException(SysManageReturnInfo.SYS_STATION_01080001);
		             }
				}
			} else {
				//参数数据不存在
				throw new IqbException(SysManageReturnInfo.SYS_STATION_01080002);
			}			
		}		
	}

	@Override
	public SysParam selectSysParamById(JSONObject objs) throws IqbException,
			IqbSqlException {
		SysParam bean = JSONUtil.toJavaObject(objs, SysParam.class); 
		SysParam resultBean = sysParamBiz.selectByPrimaryKey(bean.getId());
		if(resultBean != null) {         
            return resultBean;
        } else {
        	//参数数据不存在
            throw new IqbException(SysManageReturnInfo.SYS_STATION_01080002);
        }
	}

	@Override
	public PageInfo<SysParam> selectSysParamForGrid(JSONObject objs)
			throws IqbException, IqbSqlException {
		//分页插件查询结果需要对外包装为PageInfo<T>类型,其中除了数据集合还会额外封装一些分页参数(如:total)
        return new PageInfo<SysParam>(sysParamBiz.selectSelective(objs));
	}

	@Override
	public SysParam selectSysParamFormRedis(JSONObject objs) throws IqbException, IqbSqlException {
		SysParam bean = JSONUtil.toJavaObject(objs, SysParam.class);
		//取出缓存
		SysParam sysParam = paramRedis.getSysParamFromRedis(bean.getItemKey());
		if(sysParam != null) {
			return sysParam;
		} else {
			//构造参数bean，用于业务检查
			SysParam parameterBean = JSONUtil.toJavaObject(objs, SysParam.class);
			if(sysParamBiz.selectCountSelective(parameterBean) > 0) {			
				parameterBean.setIsEnable(1);
				if(sysParamBiz.selectCountSelective(parameterBean) > 0) {
					SysParam resultBean = sysParamBiz.selectByItemKey(bean.getItemKey());
					//放入缓存
					paramRedis.setSysParamToRedis(bean.getItemKey(), resultBean);
					return resultBean;
				} else {
					//参数数据已停用
		    		throw new IqbException(SysManageReturnInfo.SYS_STATION_01080004);
				}
			} else {
				//参数数据不存在
	    		throw new IqbException(SysManageReturnInfo.SYS_STATION_01080002);
			}
		}		
	}
	

}
