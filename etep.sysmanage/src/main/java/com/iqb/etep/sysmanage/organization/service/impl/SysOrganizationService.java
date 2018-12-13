package com.iqb.etep.sysmanage.organization.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.common.base.service.BaseService;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.common.utils.Attr.CommonConst;
import com.iqb.etep.common.utils.BeanUtil;
import com.iqb.etep.common.utils.DateTools;
import com.iqb.etep.common.utils.GenerationUtil;
import com.iqb.etep.common.utils.JSONUtil;
import com.iqb.etep.common.utils.StringUtil;
import com.iqb.etep.common.utils.SysUserSession;
import com.iqb.etep.sysmanage.base.SysManageAttr.DictConst;
import com.iqb.etep.sysmanage.base.SysManageAttr.HttpInterMode;
import com.iqb.etep.sysmanage.base.SysManageAttr.InterPushAttr;
import com.iqb.etep.sysmanage.base.SysManageAttr.SysPushInitiateAttr;
import com.iqb.etep.sysmanage.base.SysManageAttr.SysPushReceiveAttr;
import com.iqb.etep.sysmanage.base.SysManageReturnInfo;
import com.iqb.etep.sysmanage.base.config.SysManageConfig;
import com.iqb.etep.sysmanage.dept.service.ISysOrganizationDeptService;
import com.iqb.etep.sysmanage.dict.service.ISysDictService;
import com.iqb.etep.sysmanage.hq.service.IHqSysUserService;
import com.iqb.etep.sysmanage.interpush.bean.InterPushRecordBean;
import com.iqb.etep.sysmanage.interpush.service.IInterPushRecordService;
import com.iqb.etep.sysmanage.organization.bean.SysOrganizationInfo;
import com.iqb.etep.sysmanage.organization.biz.SysOrganizationInfoBiz;
import com.iqb.etep.sysmanage.organization.biz.SysOrganizationPurviewBiz;
import com.iqb.etep.sysmanage.organization.service.ISysOrganizationService;
import com.iqb.etep.sysmanage.station.service.ISysStationRoleService;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;
import com.iqb.etep.sysmanage.user.bean.SysUser;

@SuppressWarnings({"rawtypes", "unchecked"})
@Service
public class SysOrganizationService extends BaseService implements ISysOrganizationService{
    
    /**
     *  日志
     */
    private static Logger logger = LoggerFactory.getLogger(SysOrganizationService.class);
    
    @Autowired
    private SysOrganizationInfoBiz sysOrganizationInfoBiz;
    
    @Autowired
    private SysOrganizationPurviewBiz sysOrganizationPurviewBiz;
    
    @Autowired
    private SysUserSession sysUserSession;
    
    /**
     * 角色 Service
     */
    @Autowired
    private ISysStationRoleService sysStationRoleService;
    
    /**
     * 通用用户 Service
     */
    @Autowired
    private IHqSysUserService hqSysUserService;
    
    /**
     * 部门 Service
     * */
    @Autowired
    private ISysOrganizationDeptService sysOrganizationDeptService;
    
    /**
     * 数据字典 Service
     * */
    @Autowired
    private ISysDictService sysDictService;
    
    @Autowired
    private IInterPushRecordService interPushRecordServiceImpl;
    
    @Autowired
    private SysManageConfig sysManageConfig;

	@Override
	public void insertSysOrganizationInfo(JSONObject objs) throws IqbException,
			IqbSqlException {
		SysOrganizationInfo bean = JSONUtil.toJavaObject(objs, SysOrganizationInfo.class);		
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
		SysOrganizationInfo parameterBean = new SysOrganizationInfo();
		//机构数据,衡量数据重复的标志是orgName是否相同
		parameterBean.setOrgName(bean.getOrgName());
		parameterBean.setDeleteFlag(1);       
        if(sysOrganizationInfoBiz.selectCountSelective(parameterBean) < 1) {   
        	//父级机构
        	SysOrganizationInfo parentBean = sysOrganizationInfoBiz.selectByPrimaryKey(bean.getParentId());
        	//机构编码的编码方式以3位递进(如:1001001)
        	String maxOrgCode = sysOrganizationInfoBiz.selectMaxOrgCode(bean.getParentId());
        	if(StringUtil.isNull(maxOrgCode)) {
        		bean.setOrgCode((parentBean.getOrgCode() + "001"));
        		bean.setOrgLevel((parentBean.getOrgLevel() + 1));
        	} else{
                bean.setOrgCode(((Long.parseLong(maxOrgCode) + 1) + ""));
        		bean.setOrgLevel((parentBean.getOrgLevel() + 1));
        	}
        	sysOrganizationInfoBiz.insert(bean);
        } else {
        	//机构名称已存在
            throw new IqbException(SysManageReturnInfo.SYS_STATION_01040001);
        }        	
	}

	
	@Override
	public void deleteSysOrganizationInfoById(JSONObject objs) throws IqbException,
			IqbSqlException {
		SysOrganizationInfo bean = JSONUtil.toJavaObject(objs, SysOrganizationInfo.class);
    	SysOrganizationInfo resultBean = sysOrganizationInfoBiz.selectByPrimaryKey(bean.getId());
    	if(resultBean != null) {  
    		if(resultBean.getOrgLevel() > 1) {
    			if(sysOrganizationInfoBiz.selectCountChildLevel(bean.getId()) < 1) {
        			if(sysStationRoleService.selectOrganizationStations(bean.getId())) {
        				SysUser sysUser = new SysUser();
        				sysUser.setOrgId(bean.getId());
        				if(!hqSysUserService.isOrgIdBeUsedFromHqUser(sysUser)) {
        					sysOrganizationDeptService.deleteSysOrganizationDeptInfoByOrgId(bean.getId());
        					sysOrganizationPurviewBiz.deleteByOrgId(bean.getId());
        					sysOrganizationInfoBiz.deleteByPrimaryKey(bean.getId());  					
        				} else{
        					//机构已被用户使用
                            throw new IqbException(SysManageReturnInfo.SYS_STATION_01040007);
        				}
        			} else {
        				//机构已被角色使用
                        throw new IqbException(SysManageReturnInfo.SYS_STATION_01040006);
        			}
    			} else {
    				//机构存在子级机构
                    throw new IqbException(SysManageReturnInfo.SYS_STATION_01040005);
    			}
    		} else {
    			//机构是根机构
                throw new IqbException(SysManageReturnInfo.SYS_STATION_01040004);
    		}
        } else {
        	//机构数据不存在
            throw new IqbException(SysManageReturnInfo.SYS_STATION_01040002);
        }
	}

	@Override
	public void updateSysOrganizationInfoById(JSONObject objs) throws IqbException,
			IqbSqlException {
		SysOrganizationInfo bean = JSONUtil.toJavaObject(objs, SysOrganizationInfo.class);
		//修改人,时间
		bean.setLastUser(sysUserSession.getUserCode());
	    bean.setLastTime(NumberUtils.toInt(System.currentTimeMillis() / 1000 + ""));
	    //构造参数bean,用于业务检查
	  	SysOrganizationInfo parameterBean = new SysOrganizationInfo();
	  	//机构数据,衡量数据重复的标志是orgName是否相同
	  	parameterBean.setOrgName(bean.getOrgName());
	  	parameterBean.setDeleteFlag(1);
	  	//线程同步
        synchronized(SysOrganizationService.class) {
        	SysOrganizationInfo resultBean = sysOrganizationInfoBiz.selectByPrimaryKey(bean.getId());
        	if(resultBean != null) {
        		if(resultBean.getOrgName().equals(bean.getOrgName())) {
        			sysOrganizationInfoBiz.updateByPrimaryKeySelective(bean);
        		} else {
        			if(sysOrganizationInfoBiz.selectCountSelective(parameterBean) < 1) {   
        				sysOrganizationInfoBiz.updateByPrimaryKeySelective(bean);
                    } else {
                    	//机构名称已存在
                        throw new IqbException(SysManageReturnInfo.SYS_STATION_01040001);
                    }
        		}
        	} else {
        		//机构数据不存在
                throw new IqbException(SysManageReturnInfo.SYS_STATION_01040002);
        	}
        }
	}

	@Override
	public SysOrganizationInfo selectSysOrganizationInfoById(JSONObject objs)
			throws IqbException, IqbSqlException {
		SysOrganizationInfo bean = JSONUtil.toJavaObject(objs, SysOrganizationInfo.class);    
		SysOrganizationInfo resultBean = sysOrganizationInfoBiz.selectByPrimaryKey(bean.getId());
		if(resultBean != null) {         
            return resultBean;
        } else {
        	//机构数据不存在
            throw new IqbException(SysManageReturnInfo.SYS_STATION_01040002);
        }
	}

	@Override
	public List<TreeNode> selectSysOrganizationInfoForTree(
			JSONObject objs) throws IqbException, IqbSqlException {
		return sysOrganizationInfoBiz.selectSelective(objs);
	}

	
	@Override
	public boolean selectCountOrganizationPurviewByMenuId(Integer menuId) throws IqbException, IqbSqlException {
		return sysOrganizationPurviewBiz.selectCountByMenuId(menuId) < 1;
	}
	
	@Override
	public void insertSysOrganizationPurview(JSONObject objs)
			throws IqbException, IqbSqlException {   
		objs.put("createUser", sysUserSession.getUserCode());
		objs.put("createTime", NumberUtils.toInt(System.currentTimeMillis() / 1000 + ""));
		objs.put("lastUser", sysUserSession.getUserCode());
		objs.put("lastTime", NumberUtils.toInt(System.currentTimeMillis() / 1000 + ""));
		//线程同步
		synchronized(SysOrganizationService.class) {
			System.out.println("线程同步开始执行...");
			List<Integer> oldMenuIds = sysOrganizationPurviewBiz.selectSelective(objs);
			if(oldMenuIds.size() > 0) {		
				List<Integer> newMenuIds = (List<Integer>) objs.get("newMenuIds");
				if(newMenuIds == null) {
					newMenuIds = new ArrayList<>();
				}
				for(Integer i : newMenuIds) {
					if(oldMenuIds.contains(i)) {						
						oldMenuIds.remove(oldMenuIds.indexOf(i));						
					}
				}
				if(oldMenuIds.size() > 0) {
					objs.put("oldMenuIds", oldMenuIds);
					if(sysOrganizationPurviewBiz.selectCountSysMenuPurview(objs) < 1) {
						sysOrganizationPurviewBiz.deleteByOrgId(objs.getInteger("orgId"));			
						if(objs.get("newMenuIds") != null){
							sysOrganizationPurviewBiz.insert(objs);
						}  
					} else {
						//取消勾选菜单已被该机构下属角色授权
			            throw new IqbException(SysManageReturnInfo.SYS_STATION_01040008);
					}
				} else {
					sysOrganizationPurviewBiz.deleteByOrgId(objs.getInteger("orgId"));			
					if(objs.get("newMenuIds") != null){
						sysOrganizationPurviewBiz.insert(objs);
					}  
				}				
			} else {
	        	if(objs.get("newMenuIds") != null){
	        		sysOrganizationPurviewBiz.insert(objs);
	        	}  
			}        	     	
        }
	}

	@Override
	public List<Integer> selectSysOrganizationPurview(
			JSONObject objs) throws IqbException, IqbSqlException {
		return sysOrganizationPurviewBiz.selectSelective(objs);
	}
	
	@Override
    public List<Map<Integer, String>> getAllOrgInfo() throws IqbException, IqbSqlException {
        return sysOrganizationPurviewBiz.getAllOrgInfo();
    }


    @Override
    public List<Map<Integer, String>> selectOrganizationDept(JSONObject objs)throws IqbException ,IqbSqlException {
        synchronized (SysOrganizationService.class) {
            String orgId = objs.get("orgId").toString();          
            return  sysOrganizationInfoBiz.selectOrganizationDept(orgId);        
        }
    }


	@Override
	public List<Map<String, Object>> selectOrgTpye(JSONObject objs)
			throws IqbException, IqbSqlException {
		objs.put("dictTypeCode", DictConst.SYS_DICT_ORGTYPE);
		return sysDictService.selectSysDictTypeToListOfMapFormRedis(objs);
	}

	@Override
    public void pushOrgInfoToCRM(JSONObject objs) throws IqbException {
	    
	    /** 校验传递信息是否为空  **/
        if(CollectionUtils.isEmpty(objs)){
            throw new IqbException(SysManageReturnInfo.SYS_CRM_PUSH_01091002);
        }
        SysOrganizationInfo bean = BeanUtil.mapToBean(objs, SysOrganizationInfo.class);
        /** 校验返回信息  **/
        if(bean == null || StringUtil.isEmpty(bean.getOrgCode())){
            throw new IqbException(SysManageReturnInfo.SYS_CRM_PUSH_01091003);
        }
        SysOrganizationInfo sysOrganizationInfo = sysOrganizationInfoBiz.getSysOrganizationInfoByOrgCode(bean.getOrgCode());
        String uuid = GenerationUtil.getUUID();
        
        /** 封装记录信息  **/
        InterPushRecordBean interPushRecordBean = new InterPushRecordBean();
        interPushRecordBean.setCommonCode(bean.getOrgCode());
        interPushRecordBean.setUuid(uuid);
        interPushRecordBean.setPushType(Integer.parseInt(InterPushAttr.INTER_PUSH_TYPE_ADD));
        interPushRecordBean.setCreateTime(DateTools.getCurrTime());
        interPushRecordBean.setInitiate(SysPushInitiateAttr.INITIATE_ORG);
        interPushRecordBean.setReceive(SysPushReceiveAttr.RECEIVE_CRM);
        interPushRecordBean.setPushStatus(Integer.parseInt(InterPushAttr.INTER_PUSH_STATUS_START));
        interPushRecordServiceImpl.insertInterPushPushRecord(interPushRecordBean);
        
        /** 开始推送客户信息  **/
        try {
            synchronized (this) {
                logger.info("推送机构信息开始，机构信息" + JSONUtil.objToJson(sysOrganizationInfo));
                String retStr = "";
                logger.info("http请求方式：" + sysManageConfig.getHttpInterfaceInteractionMode());
                if(HttpInterMode.HTTPINTERMODE_HTTP.equals(sysManageConfig.getHttpInterfaceInteractionMode())){
                    retStr = interPushRecordServiceImpl.sysPushByPost(sysManageConfig.getUrlSysmanageOrgCrmCustomerPush(), this.SysOrganizationToCRMMap(sysOrganizationInfo));
                }
                if(HttpInterMode.HTTPINTERMODE_HTTPS.equals(sysManageConfig.getHttpInterfaceInteractionMode())){
                    retStr = interPushRecordServiceImpl.sysPushByPostByHttps(sysManageConfig.getUrlSysmanageOrgCrmCustomerPush(), this.SysOrganizationToCRMMap(sysOrganizationInfo));
                }
                logger.info("crm返回信息：" + retStr);
                if(StringUtil.isEmpty(retStr)){
                    throw new IqbException(SysManageReturnInfo.SYS_CRM_PUSH_01091004);
                }
            }
        } catch (Exception e) {
            /** 推送异常  **/
            logger.error("推送机构信息至" + interPushRecordBean.getReceive() + "异常", e);
            interPushRecordBean.setCreateTime(DateTools.getCurrTime());
            interPushRecordBean.setPushStatus(Integer.parseInt(InterPushAttr.INTER_PUSH_STATUS_ERROE));
            interPushRecordServiceImpl.insertInterPushPushRecord(interPushRecordBean);
            throw new IqbException(SysManageReturnInfo.SYS_CRM_PUSH_01091004);
        }
        
        /** 推送结束  **/
        interPushRecordBean.setCreateTime(DateTools.getCurrTime());
        interPushRecordBean.setPushStatus(Integer.parseInt(InterPushAttr.INTER_PUSH_STATUS_END));
        interPushRecordServiceImpl.insertInterPushPushRecord(interPushRecordBean);
        
    }


    /**
     * 
     * Description: 将客户信息bean转成消费金融map
     * @param
     * @return Map
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月22日 下午2:28:01
     */
    private Map SysOrganizationToCRMMap(SysOrganizationInfo sysOrganizationInfo){
        Map retMap = new HashMap();
        retMap.put("customerName", sysOrganizationInfo.getOrgName());
        retMap.put("customerCode", sysOrganizationInfo.getOrgCode());
        retMap.put("orgCode", sysOrganizationInfo.getOrgCode());
        retMap.put("customerShortName", sysOrganizationInfo.getOrgShortName());
        retMap.put("customerType", sysOrganizationInfo.getOrgType());
        retMap.put("addressDetail", sysOrganizationInfo.getOrgAddr());
        return retMap;
    }
    
    @Override
    public List<Map<String, Object>> selectOrgToListOfMap(JSONObject objs)
            throws IqbException, IqbSqlException
          {
            SysOrganizationInfo bean = null;
            if (objs != null) {
              bean = JSONUtil.toJavaObject(objs, SysOrganizationInfo.class);
            } else {
              bean = new SysOrganizationInfo();
            }
            return this.sysOrganizationInfoBiz.selectOrgToListOfMap(bean);
          }
    
}
