package com.iqb.etep.sysmanage.sysmenu.biz;





import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.common.base.biz.BaseBiz;
import com.iqb.etep.common.redis.RedisPlatformDao;
import com.iqb.etep.common.utils.Attr.RedisIdeAttr;
import com.iqb.etep.common.utils.NumberUtil;
import com.iqb.etep.sysmanage.sysmenu.bean.SysMenu;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;
import com.iqb.etep.sysmanage.sysmenu.dao.SysMenuDao;

@Component
public class SysMenuBiz extends BaseBiz {

    @Autowired
    private SysMenuDao sysMenuDao;
    
    @Autowired
    private RedisPlatformDao redisPlatformDao;
    
    /**
     * 插入数据
     * @param record
     * @return
     * @author baiyapeng
     */
    public int insert(SysMenu record) {
    	//设置数据源
    	this.setDb(0, super.MASTER); 
        return sysMenuDao.insert(record);
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
        return sysMenuDao.deleteByPrimaryKey(id);
    };

    /**
     * 选择性更新数据
     * @param record
     * @return
     * @author baiyapeng
     */
    public int updateByPrimaryKeySelective(SysMenu record) {
    	//设置数据源
    	this.setDb(0, super.MASTER); 
        return sysMenuDao.updateByPrimaryKeySelective(record);
    };

    /**
     * 更新数据
     * @param record
     * @return
     * @author baiyapeng
     */
    public int updateByPrimaryKey(SysMenu record) {
    	//设置数据源
    	this.setDb(0, super.MASTER); 
        return sysMenuDao.updateByPrimaryKey(record);
    };
    
    /**
     * 根据主键查询数据
     * @param id
     * @return
     * @author baiyapeng
     */
    public SysMenu selectByPrimaryKey(Integer id) {
    	//设置数据源
    	setDb(0, super.SLAVE); 
        return sysMenuDao.selectByPrimaryKey(id);
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
        return sysMenuDao.selectSelective(objs);
    };
    
    /**
     * 选择性统计数据
     * @param record
     * @return
     * @author baiyapeng
     */
    public int selectCountSelective(SysMenu record) {
    	//设置数据源
    	setDb(0, super.SLAVE); 
        return sysMenuDao.selectCountSelective(record);
    };    
    
    /**
     * 查询最大菜单编码
     * @param parentId
     * @return
     * @author baiyapeng
     */
    public String selectMaxMenuCode(Integer parentId) {
    	//设置数据源
    	setDb(0, super.SLAVE); 
        return sysMenuDao.selectMaxMenuCode(parentId);
    };
    
    /**
     * 统计子级菜单
     * @param id
     * @return
     * @author baiyapeng
     */
    public int selectCountChildLevel(Integer id) {
    	//设置数据源
    	setDb(0, super.SLAVE); 
        return sysMenuDao.selectCountChildLevel(id);
    };

    /**
     * 缓存菜单到Redis
     * @param key
     * @param value
     * @return 
     * @author wangxinbang
     */
    public void setSysMenuToRedis(String key, String value){
        redisPlatformDao.setKeyAndValueTimeout(key, value, NumberUtil.toInt(RedisIdeAttr.SysMenuContinue));
    }
    
    /**
     * 从Redis取菜单缓存
     * @param key
     * @return 
     * @author wangxinbang
     */
    public String getSysMenuFromRedis(String key){
        return redisPlatformDao.getValueByKey(key);
    }

    /**
     * 移除所有菜单缓存
     * @param
     * @return void
     * @author wangxinbang
     */
    public void removeAllUserSysMenuList() {
        String userMenuKey = RedisIdeAttr.SysMenu + "*";
        Set<String> keySet = redisPlatformDao.getKeys(userMenuKey);
        if(CollectionUtils.isEmpty(keySet)){
            return;
        }
        for(String k : keySet){
            redisPlatformDao.removeValueByKey(k);
        }
    }
    
    
}
