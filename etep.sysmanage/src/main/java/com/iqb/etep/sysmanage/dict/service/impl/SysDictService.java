package com.iqb.etep.sysmanage.dict.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.base.service.BaseService;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.common.utils.BeanUtil;
import com.iqb.etep.common.utils.JSONUtil;
import com.iqb.etep.common.utils.SysUserSession;
import com.iqb.etep.common.utils.Attr.CommonConst;
import com.iqb.etep.sysmanage.base.SysManageReturnInfo;
import com.iqb.etep.sysmanage.dict.bean.SysDictItem;
import com.iqb.etep.sysmanage.dict.bean.SysDictType;
import com.iqb.etep.sysmanage.dict.bean.TreeNode;
import com.iqb.etep.sysmanage.dict.biz.SysDictItemBiz;
import com.iqb.etep.sysmanage.dict.biz.SysDictTypeBiz;
import com.iqb.etep.sysmanage.dict.redis.DictRedis;
import com.iqb.etep.sysmanage.dict.service.ISysDictService;
@Service
@SuppressWarnings("unchecked")
public class SysDictService extends BaseService implements ISysDictService {

	@Autowired
	private SysDictTypeBiz sysDictTypeBiz;
	 
	@Autowired
	private SysDictItemBiz sysDictItemBiz;
	
	@Autowired
	private SysUserSession sysUserSession;
	
	@Autowired
	private DictRedis dictRedis;
	
	/**
	 * 生成n位随机码
	 * */
	public static String getRandomNumber(int digCount) {  
	    StringBuilder sb = new StringBuilder(digCount);
	    Random rnd = new Random();
	    for(int i=0; i < digCount; i++) {
	    	sb.append((char)('0' + rnd.nextInt(10)));
	    }	          
	    return sb.toString();  
	}  

	@Override
	public void insertSysDictType(JSONObject objs) throws IqbException,
			IqbSqlException {
		SysDictType bean = JSONUtil.toJavaObject(objs, SysDictType.class);
		//创建人,时间
        bean.setCreateUser(sysUserSession.getUserCode());
        bean.setCreateTime(NumberUtils.toInt(System.currentTimeMillis() / 1000 + ""));
        bean.setLastUser(sysUserSession.getUserCode());
        bean.setLastTime(NumberUtils.toInt(System.currentTimeMillis() / 1000 + ""));
        //如果字典类别是目录类型,则后台随机生成字典类别代码
        if(bean.getIsContent() == 1){       	
        	bean.setDictTypeCode(System.currentTimeMillis() + getRandomNumber(2));     	
        }
        //可用状态
        bean.setDeleteFlag(1);
        //版本
        bean.setVersion(NumberUtils.toInt(CommonConst.Version));
        //构造参数bean,用于业务检查
        SysDictType parameterBean = new SysDictType();
        //字典类别数据,衡量数据重复的标志是dictTypeCode是否相同
        parameterBean.setDictTypeCode(bean.getDictTypeCode());
        parameterBean.setDeleteFlag(1);   
        if(sysDictTypeBiz.selectCountSelective(parameterBean) < 1) {         
        	sysDictTypeBiz.insert(bean);
        } else {
        	//字典类别代码已存在
        	throw new IqbException(SysManageReturnInfo.SYS_STATION_01060001);
        }		
	}

	@Override
	public void deleteSysDictTypeById(JSONObject objs) throws IqbException,
			IqbSqlException {
		SysDictType bean = JSONUtil.toJavaObject(objs, SysDictType.class);
		SysDictType resultBean = sysDictTypeBiz.selectByPrimaryKey(bean.getId());
		if(resultBean != null) {  		
            sysDictTypeBiz.updateByPrimaryKeySelective(bean);
            if(bean.getIsEnable() == 2) {
				//清除缓存
				dictRedis.removeSysDictTypeFromRedis(resultBean.getDictTypeCode());
			} 
        } else {
        	//字典类别数据不存在
    		throw new IqbException(SysManageReturnInfo.SYS_STATION_01060002);
        }
	}

	@Override
	public void updateSysDictTypeById(JSONObject objs) throws IqbException,
			IqbSqlException {
		SysDictType bean = JSONUtil.toJavaObject(objs, SysDictType.class);
		//修改人,时间
		bean.setLastUser(sysUserSession.getUserCode());
        bean.setLastTime(NumberUtils.toInt(System.currentTimeMillis() / 1000 + ""));
		//构造参数bean，用于业务检查
		SysDictType parameterBean = new SysDictType();
		//字典类别数据,衡量数据重复的标志是dictTypeCode是否相同
        parameterBean.setDictTypeCode(bean.getDictTypeCode());
        parameterBean.setDeleteFlag(1);
        synchronized(SysDictService.class) {
        	SysDictType resultBean = sysDictTypeBiz.selectByPrimaryKey(bean.getId());
        	if(resultBean != null) {
        		if(bean.getDictTypeCode().equals(resultBean.getDictTypeCode())) {
        			sysDictTypeBiz.updateByPrimaryKeySelective(bean);  
        			//清除缓存
        			dictRedis.removeSysDictTypeFromRedis(bean.getDictTypeCode());	
        		} else {
        			if(sysDictTypeBiz.selectCountSelective(parameterBean) < 1) {
        				sysDictTypeBiz.updateByPrimaryKeySelective(bean); 
        				//清除缓存
            			dictRedis.removeSysDictTypeFromRedis(resultBean.getDictTypeCode());
                    } else {
                    	//字典类别代码已存在
		                throw new IqbException(SysManageReturnInfo.SYS_STATION_01060001);
                    }
        		}
        	} else {
        		//字典类别数据不存在
        		throw new IqbException(SysManageReturnInfo.SYS_STATION_01060002);
        	}
        }
	}

	@Override
	public SysDictType selectSysDictTypeById(JSONObject objs) throws IqbException,
			IqbSqlException {
		SysDictType bean = JSONUtil.toJavaObject(objs, SysDictType.class);
		SysDictType resultBean = sysDictTypeBiz.selectByPrimaryKey(bean.getId());
		if(resultBean != null) {         
            return resultBean;
        } else {
        	//字典类别数据不存在
    		throw new IqbException(SysManageReturnInfo.SYS_STATION_01060002);
        }
	}

	@Override
	public List<TreeNode> selectSysDictTypeForTree(JSONObject objs)
			throws IqbException, IqbSqlException {
		return sysDictTypeBiz.selectSelective(objs);
	}
	
	
	@Override
	@SuppressWarnings("rawtypes")
	public void insertSysDictItem(JSONObject objs) throws IqbException,
			IqbSqlException {
		SysDictItem bean = JSONUtil.toJavaObject(objs, SysDictItem.class);
		//可用状态
        bean.setDeleteFlag(1);
        //版本
        bean.setVersion(NumberUtils.toInt(CommonConst.Version));
        //构造参数bean,用于业务检查
        SysDictItem parameterBean = new SysDictItem();
        //字典项数据,衡量数据重复的标志是dictTypeCode和dictCode是否同时相同
        parameterBean.setDictTypeCode(bean.getDictTypeCode());
        parameterBean.setDictCode(bean.getDictCode());
        parameterBean.setDeleteFlag(1);      
        if(sysDictItemBiz.selectCountSelective(parameterBean) < 1) {     
        	sysDictItemBiz.insert(bean);
        	List<Map> redisListOfMap = dictRedis.getSysDictItemFromRedis(bean.getDictTypeCode());
    		if(redisListOfMap != null) {
    			if(bean.getIsEnable() == 1) {
            		//放入缓存
            		dictRedis.setSysDictToRedis(bean.getDictTypeCode(), bean);
            	}
    		}        	
        } else {
        	//字典项代码已存在
        	throw new IqbException(SysManageReturnInfo.SYS_STATION_01070001);
        }
	}

	
	@Override
	@SuppressWarnings("rawtypes")
	public void updateSysDictItemById(JSONObject objs) throws IqbException,
			IqbSqlException {
		SysDictItem bean = JSONUtil.toJavaObject(objs, SysDictItem.class);
		//构造参数bean，用于业务检查
        SysDictItem parameterBean = new SysDictItem();
        //字典项数据,衡量数据重复的标志是dictTypeCode和dictCode是否同时相同
        parameterBean.setDictTypeCode(bean.getDictTypeCode());
        parameterBean.setDictCode(bean.getDictCode());
        parameterBean.setDeleteFlag(1);
        synchronized(SysDictService.class) {
        	SysDictItem resultBean = sysDictItemBiz.selectByPrimaryKey(bean.getId());
        	if(resultBean != null) {
        		if(bean.getDictCode().equals(resultBean.getDictCode())) {
        			sysDictItemBiz.updateByPrimaryKeySelective(bean);
        			List<Map> redisListOfMap = dictRedis.getSysDictItemFromRedis(bean.getDictTypeCode());
            		if(redisListOfMap != null) {
            			//清除缓存
                		dictRedis.removeSysDictItemFromRedis(bean.getDictTypeCode(), bean.getDictCode());
            			if(bean.getIsEnable() == 1) {        				
                    		//放入缓存
                    		dictRedis.setSysDictToRedis(bean.getDictTypeCode(), bean);
                    	} 
            		}           			
        		} else {
        			if(sysDictItemBiz.selectCountSelective(parameterBean) < 1) {       
        				sysDictItemBiz.updateByPrimaryKeySelective(bean);
        				List<Map> redisListOfMap = dictRedis.getSysDictItemFromRedis(bean.getDictTypeCode());
                		if(redisListOfMap != null) {
                			//清除缓存
                    		dictRedis.removeSysDictItemFromRedis(bean.getDictTypeCode(), resultBean.getDictCode());
            				if(bean.getIsEnable() == 1) {
                				//放入缓存
                        		dictRedis.setSysDictToRedis(bean.getDictTypeCode(), bean);
                        	} 
                		}          				
                    } else {
                    	//字典项代码已存在
		                throw new IqbException(SysManageReturnInfo.SYS_STATION_01070001);
                    }
        		}
        	} else {
        		//字典项数据不存在
				throw new IqbException(SysManageReturnInfo.SYS_STATION_01070002);
        	}
        }   
	}

	@Override
	public void deleteSysDictItemById(JSONObject objs) throws IqbException,
			IqbSqlException {
		//TODO(暂不开放)
	}

	@Override
	public SysDictItem selectSysDictItemById(JSONObject objs) throws IqbException,
			IqbSqlException {
		SysDictItem bean = JSONUtil.toJavaObject(objs, SysDictItem.class);     
		SysDictItem resultBean = sysDictItemBiz.selectByPrimaryKey(bean.getId());
        if(resultBean != null) {         
            return resultBean;
        } else {
        	//字典项数据不存在
        	throw new IqbException(SysManageReturnInfo.SYS_STATION_01070002);
        }
	}

	@Override
	public PageInfo<SysDictItem> selectSysDictItemForGrid(JSONObject objs)
			throws IqbException, IqbSqlException {
		//分页插件查询结果需要对外包装为PageInfo<T>类型,其中除了数据集合还会额外封装一些分页参数(如:total)
		return new PageInfo<SysDictItem>(sysDictItemBiz.selectSelective(objs));
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List<SysDictItem> selectSysDictTypeToListOfBeanFormRedis(JSONObject objs)
			throws IqbException, IqbSqlException {
		SysDictItem bean = JSONUtil.toJavaObject(objs, SysDictItem.class);
		//取出缓存
		List<Map> redisListOfMap = dictRedis.getSysDictItemFromRedis(bean.getDictTypeCode());
		if(redisListOfMap != null) {
			List<SysDictItem> redisListOfBean = new ArrayList<SysDictItem>();
			for(Map map : redisListOfMap) {
				SysDictItem item = BeanUtil.mapToBean(map, SysDictItem.class);
				redisListOfBean.add(item);
			}
			return redisListOfBean;
		} else {
			//构造参数bean，用于业务检查
			SysDictType parameterBean = JSONUtil.toJavaObject(objs, SysDictType.class);
			if(sysDictTypeBiz.selectCountSelective(parameterBean) > 0) {			
				parameterBean.setIsEnable(1);
				if(sysDictTypeBiz.selectCountSelective(parameterBean) > 0) {
					List<SysDictItem> resultList = sysDictItemBiz.selectSysDictTypeToListOfBean(bean);
					//放入缓存
					dictRedis.setSysDictListToRedis(resultList);
					return resultList;
				} else {
					//字典类别已停用
		    		throw new IqbException(SysManageReturnInfo.SYS_STATION_01060004);
				}
			} else {
				//字典类别数据不存在
	    		throw new IqbException(SysManageReturnInfo.SYS_STATION_01060002);
			}
		}		
	}

	@Override
	public List<Map<String, Object>> selectSysDictTypeToListOfMapFormRedis(
			JSONObject objs) throws IqbException, IqbSqlException {
		List<SysDictItem> listOfBean = selectSysDictTypeToListOfBeanFormRedis(objs);
		List<Map<String, Object>> listOfMap = new ArrayList<Map<String, Object>>();
		for(SysDictItem item : listOfBean) {
			Map<String, Object> map = new HashMap<String, Object>();			
			map.put("id", item.getDictValue());
			map.put("text", item.getDictName());
			listOfMap.add(map);
		}		
		return listOfMap;
	}

	
}
