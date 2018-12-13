package com.iqb.etep.sysmanage.log.biz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iqb.etep.common.redis.RedisPlatformDao;
import com.iqb.etep.common.utils.Attr.MongoCollections;
import com.iqb.etep.common.utils.Attr.RedisIdeAttr;
import com.iqb.etep.common.utils.BeanUtil;
import com.iqb.etep.common.utils.JSONUtil;
import com.iqb.etep.common.utils.MongoUtil;
import com.iqb.etep.common.utils.NumberUtil;
import com.iqb.etep.common.utils.StringUtil;
import com.iqb.etep.sysmanage.base.SysManageBaseBiz;
import com.iqb.etep.sysmanage.log.bean.SysLogBean;
import com.iqb.etep.sysmanage.log.dao.SysLogDao;
import com.iqb.etep.sysmanage.sysmenu.bean.SysMenu;

/**
 * 
 * Description: 系统日志biz
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年8月22日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */
@Component
public class SysLogBiz extends SysManageBaseBiz{
    
    /**
     * 注入mongo工具类
     */
    @Autowired
    private MongoUtil mongoUtil;
    
    @Autowired
    private RedisPlatformDao redisPlatformDao;
    
    @Autowired
    private SysLogDao sysLogDao;

    /**
     * 
     * Description: 保存用户登录日志
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月23日 上午11:14:54
     */
    public void saveUserLoginLog(SysLogBean bean) {
        mongoUtil.saveUserLoginLog(bean);
    }

    /**
     * 
     * Description: 保存用户操作日志
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月23日 上午11:15:05
     */
    public void saveUserOperLog(SysLogBean bean) {
        mongoUtil.saveUserOperLog(bean);
    }

    /**
     * 
     * Description: 查询日志
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月23日 上午11:15:19
     */
    public List<SysLogBean> querySysLog(SysLogBean bean, Object i, Object j) {
        Integer pageNum = NumberUtil.toInt(i);
        Integer pageSize = NumberUtil.toInt(j);
        Integer start = pageSize * (pageNum - 1);
        Integer end = pageSize * pageNum;
        if(MongoCollections.SysLog.equals(bean.getOperType())){
            return mongoUtil.query(bean, start, end, MongoCollections.SysUserLogin);
        }else if(MongoCollections.BizLog.equals(bean.getOperType())){
            return mongoUtil.query(bean, start, end, MongoCollections.SysUserOper);
        }
        return null;
    }
    
    /**
     * 
     * Description: 查询日志
     * @param
     * @return void
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月23日 上午11:15:19
     */
    public List<SysLogBean> querySysLogTotal(SysLogBean bean) {
        if(MongoCollections.SysLog.equals(bean.getOperType())){
            return mongoUtil.queryTotal(bean, MongoCollections.SysUserLogin);
        }else if(MongoCollections.BizLog.equals(bean.getOperType())){
            return mongoUtil.queryTotal(bean, MongoCollections.SysUserOper);
        }
        return null;
    }

    /**
     * 
     * Description: 获取系统菜单列表
     * @param
     * @return String
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年8月30日 下午2:56:42
     */
    public List<SysMenu> getSysMenuForLog() {
        String sysMenuForLogKey = RedisIdeAttr.SysMenuForLog;
        String sysMenuForLogVal = redisPlatformDao.getValueByKey(sysMenuForLogKey);
        List<SysMenu> sysMenuForLogList = new ArrayList<SysMenu>();
        if(StringUtil.isEmpty(sysMenuForLogVal)){
            super.setDb(0, super.SLAVE);
            sysMenuForLogList = sysLogDao.getSysMenuForLog();
            if(sysMenuForLogList == null){
                return null;
            }
            redisPlatformDao.setKeyAndValueTimeout(sysMenuForLogKey, JSONUtil.objToJson(sysMenuForLogList),  NumberUtil.toInt(RedisIdeAttr.SysMenuContinue));
        }else{
            sysMenuForLogList = BeanUtil.toJavaList(sysMenuForLogVal, SysMenu.class);
        }
        return sysMenuForLogList;
    }
    

}
