package com.iqb.etep.sysmanage.sysmenu.service.impl;






import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iqb.etep.common.base.service.BaseService;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.common.utils.Attr.CommonConst;
import com.iqb.etep.common.utils.Attr.RedisIdeAttr;
import com.iqb.etep.common.utils.BeanUtil;
import com.iqb.etep.common.utils.JSONUtil;
import com.iqb.etep.common.utils.StringUtil;
import com.iqb.etep.common.utils.SysUserSession;
import com.iqb.etep.sysmanage.base.SysManageReturnInfo;
import com.iqb.etep.sysmanage.hq.service.IHqSysStationRoleService;
import com.iqb.etep.sysmanage.organization.service.ISysOrganizationService;
import com.iqb.etep.sysmanage.station.service.ISysStationRolePurviewService;
import com.iqb.etep.sysmanage.sysmenu.bean.SysMenu;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;
import com.iqb.etep.sysmanage.sysmenu.biz.SysMenuBiz;
import com.iqb.etep.sysmanage.sysmenu.service.ISysMenuService;

@Service
public class SysMenuService extends BaseService implements ISysMenuService {
	
	private static Gson gson = new Gson();
    
    @Autowired
    private SysMenuBiz sysMenuBiz;
    
    @Autowired
    private SysUserSession sysUserSession;
    
    /**
     * 组织结构 Service
     */
    @Autowired
    private ISysOrganizationService sysOrganizationService;
    
    /**
     * 通用角色 Service
     */
    @Autowired
    private IHqSysStationRoleService hqSysStationRoleService;
    
    /**
     * 角色菜单权限 Service
     */
    @Autowired
    private ISysStationRolePurviewService sysStationPurviewService;
       
    @Override
	public void insertSysMenu(JSONObject objs) throws IqbException,
			IqbSqlException {
    	SysMenu bean = JSONUtil.toJavaObject(objs, SysMenu.class);
    	//父级菜单
        SysMenu parentBean = sysMenuBiz.selectByPrimaryKey(bean.getParentId());
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
        //SysMenu parameterBean = new SysMenu();
        //菜单数据,衡量数据重复的标志是menuName是否相同
		//parameterBean.setMenuName(bean.getMenuName());
		//parameterBean.setDeleteFlag(1);   
        //if(sysMenuBiz.selectCountSelective(parameterBean) < 1) {
        	//菜单编码的编码方式以2位递进(如:10102)
        	String maxMenuCode = sysMenuBiz.selectMaxMenuCode(bean.getParentId());
        	if(StringUtil.isNull(maxMenuCode)) {
        		if(parentBean != null) {
        			bean.setMenuCode(parentBean.getMenuCode() + "01");
        			bean.setMenuLevel(parentBean.getMenuLevel() + 1);            			
        		} else {
        			bean.setMenuCode("1");
        			bean.setMenuLevel(1);
        		}            		
        	} else {
        		if(parentBean != null) {
        			bean.setMenuCode((Integer.parseInt(maxMenuCode) + 1) + "");  
        			bean.setMenuLevel(parentBean.getMenuLevel() + 1);   
        		} else {
        			bean.setMenuCode((Integer.parseInt(maxMenuCode) + 1) + "");
        			bean.setMenuLevel(1);
        		}     
        	}
        	sysMenuBiz.insert(bean);
        	sysMenuBiz.removeAllUserSysMenuList();
        //} else {
        	//菜单名称已存在
            //throw new IqbException(SysManageReturnInfo.SYS_STATION_01050001);
        //}	
	}

	@Override
	public void deleteSysMenuById(JSONObject objs) throws IqbException,
			IqbSqlException {
		SysMenu bean = JSONUtil.toJavaObject(objs, SysMenu.class); 	
    	if(sysMenuBiz.selectByPrimaryKey(bean.getId()) != null) {
    		if(sysMenuBiz.selectCountChildLevel(bean.getId()) < 1) {
    			if(sysOrganizationService.selectCountOrganizationPurviewByMenuId(bean.getId())){
        			if(!hqSysStationRoleService.isMenuIdBeUsedFromHqRole(bean.getId())) {
                		sysMenuBiz.deleteByPrimaryKey(bean.getId());
        			} else {
        				//菜单已被授权
                        throw new IqbException(SysManageReturnInfo.SYS_STATION_01050005);
        			}        		
        		} else {
        			//菜单已被授权
                    throw new IqbException(SysManageReturnInfo.SYS_STATION_01050005);
        		}    
    		} else {
    			//菜单存在子级菜单
                throw new IqbException(SysManageReturnInfo.SYS_STATION_01050004);
    		}    		    		
        } else {
        	//菜单数据不存在
            throw new IqbException(SysManageReturnInfo.SYS_STATION_01050002);
        }
	}

	@Override
	public void updateSysMenuById(JSONObject objs) throws IqbException,
			IqbSqlException {
		SysMenu bean = JSONUtil.toJavaObject(objs, SysMenu.class);
		//修改人,时间
      	bean.setLastUser(sysUserSession.getUserCode());
        bean.setLastTime(NumberUtils.toInt(System.currentTimeMillis() / 1000 + ""));
        //构造参数bean,用于业务检查
        //SysMenu parameterBean = new SysMenu();
        //菜单数据,衡量数据重复的标志是menuName是否相同
		//parameterBean.setMenuName(bean.getMenuName());
		//parameterBean.setDeleteFlag(1);
        //线程同步
        synchronized(SysMenuService.class) {
        	SysMenu resultBean = sysMenuBiz.selectByPrimaryKey(bean.getId());
        	if(resultBean != null){
        		if(resultBean.getMenuName().equals(bean.getMenuName())) {
        			sysMenuBiz.updateByPrimaryKeySelective(bean);   
        			sysMenuBiz.removeAllUserSysMenuList();
                } else {
                	//if(sysMenuBiz.selectCountSelective(parameterBean) < 1) {
                		sysMenuBiz.updateByPrimaryKeySelective(bean);   
                		sysMenuBiz.removeAllUserSysMenuList();
                	//} else {
                		//菜单名称已存在
                        //throw new IqbException(SysManageReturnInfo.SYS_STATION_01050001);
                	//}                   	
                }
        	}else{
        		//菜单数据不存在
                throw new IqbException(SysManageReturnInfo.SYS_STATION_01050002);
        	}
        }
	}

	@Override
	public SysMenu selectSysMenuById(JSONObject objs) throws IqbException,
			IqbSqlException {
		SysMenu bean = JSONUtil.toJavaObject(objs, SysMenu.class);   
		SysMenu resultBean = sysMenuBiz.selectByPrimaryKey(bean.getId());
		if(resultBean != null) {         
            return resultBean;
        } else {
        	//菜单数据不存在
            throw new IqbException(SysManageReturnInfo.SYS_STATION_01050002);
        }
	}

	@Override
	public List<TreeNode> selectSysMenuForTree(JSONObject objs) throws IqbException,
			IqbSqlException {
		return sysMenuBiz.selectSelective(objs);
	}

	@Override
    public List<TreeNode> getSysMenuListByRole() throws IqbException, IqbSqlException {
	    String userCode = sysUserSession.getUserCode();
	    List<String> stationCodeList = sysUserSession.getStationCodeList();
        if(StringUtil.isEmpty(userCode) || stationCodeList == null) {
            return null;
        }
        String menuRedisKey = RedisIdeAttr.SysMenu + userCode;
        String menuJson = sysMenuBiz.getSysMenuFromRedis(menuRedisKey);
        if(StringUtil.isEmpty(menuJson)){
            List<String> stationIdList = sysStationPurviewService.getStationIdList(stationCodeList);
            List<TreeNode> list = sysStationPurviewService.getSysRolePurview(stationIdList);
            menuJson = JSONUtil.objToJsonFromGson(list);
            sysMenuBiz.setSysMenuToRedis(menuRedisKey, menuJson);
            return list;
        }
        return  gson.fromJson(menuJson, new TypeToken<List<TreeNode>>(){}.getType());
    }

    @Override
    public String refreshSysMenuListByRole(String userCode, Integer roleId) throws IqbException, IqbSqlException {
        if(StringUtil.isEmpty(userCode) || roleId == null) {
            return null;
        }
        String menuRedisKey = RedisIdeAttr.SysMenu + userCode;
        List<TreeNode> list = sysStationPurviewService.getSysRolePurview(roleId);
        String menuJson = JSONUtil.objToJson(list);
        sysMenuBiz.setSysMenuToRedis(menuRedisKey, menuJson);        
        return menuJson;
    }

    @Override
    public void removeAllUserSysMenuList() throws IqbException, IqbSqlException {
        sysMenuBiz.removeAllUserSysMenuList(); 
    }
    

}
