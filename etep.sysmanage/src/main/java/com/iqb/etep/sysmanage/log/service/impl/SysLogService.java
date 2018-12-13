package com.iqb.etep.sysmanage.log.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;
import com.iqb.etep.common.utils.Attr.MongoCollections;
import com.iqb.etep.common.utils.BeanUtil;
import com.iqb.etep.common.utils.JSONUtil;
import com.iqb.etep.common.utils.NumberUtil;
import com.iqb.etep.common.utils.PageUtil;
import com.iqb.etep.common.utils.StringUtil;
import com.iqb.etep.common.utils.SysUserSession;
import com.iqb.etep.sysmanage.base.SysManageReturnInfo;
import com.iqb.etep.sysmanage.log.bean.SysLogBean;
import com.iqb.etep.sysmanage.log.biz.SysLogBiz;
import com.iqb.etep.sysmanage.log.service.ISysLogService;
import com.iqb.etep.sysmanage.sysmenu.bean.SysMenu;
import com.iqb.etep.sysmanage.sysmenu.bean.TreeNode;
import com.iqb.etep.sysmanage.sysmenu.service.impl.SysMenuService;

/**
 * 
 * Description: 日志服务类
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年8月22日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@Service
public class SysLogService implements ISysLogService{
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 系统日志
     */
    @Autowired
    private SysLogBiz sysLogBiz;
    
    @Autowired
    private SysUserSession sysUserSession;
    
    @Autowired
    private HttpServletResponse response;
    
    @Autowired
    private SysMenuService sysMenuService;
   
    @Override
    public void insertUserLoginSysLog(Map map) throws IqbException, IqbSqlException {
        SysLogBean bean = BeanUtil.mapToBean(map, SysLogBean.class);
        synchronized (SysLogService.class) {
            sysLogBiz.saveUserLoginLog(bean);
        }
    }
    
    @Override
    public void insertUserOperSysLog(Map map) throws IqbException, IqbSqlException {
        SysLogBean bean = BeanUtil.mapToBean(map, SysLogBean.class);
        synchronized (SysLogService.class) {
            sysLogBiz.saveUserOperLog(bean);
        }
    }
    
    @Override
    public void insertUserBizLog(JSONObject objs) throws IqbException, IqbSqlException {
        Object urlParh = objs.get("urlParh");
        if(objs == null || urlParh == null){
            return;
        }
        try {
            this.checkAccessAuth(urlParh.toString());
        } catch (IOException e) {
           logger.error("", e);
        }
        List<SysMenu> sysMenuList = sysLogBiz.getSysMenuForLog();
        if(CollectionUtils.isEmpty(sysMenuList)){
            return;
        }
        SysMenu sysMenuBean = this.getMatchedSysLogBean(sysMenuList, urlParh.toString());
        if(sysMenuBean == null){
            return;
        }
        String userCode = sysUserSession.getUserCode();
        String orgName = sysUserSession.getOrgName();
        if(StringUtil.isEmpty(userCode)){
            return;
        }
        Map map = new HashMap();
        map.put("userCode", userCode);
        map.put("operType", MongoCollections.BizLog);
        map.put("operInfo", sysMenuBean.getMenuName());
        map.put("orgName", orgName);
        map.put("createUser", userCode);
        map.put("createTime", System.currentTimeMillis() / 1000);
        this.insertUserOperSysLog(map);
    }
    
    private SysMenu getMatchedSysLogBean(List<SysMenu> list, String url){
        for(SysMenu bean : list){
            String menuUrl = bean.getMenuUrl();
            if(StringUtil.isEmpty(menuUrl)){
                continue;
            }
            if(StringUtil.contains(menuUrl, url)){
                return bean;
            }
        }
        return null;
        
    }
    
    @Override
    public PageInfo querySysLog(JSONObject objs) throws IqbException, IqbSqlException {
        try {
            JSONUtil.formatDateTime(objs);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SysLogBean bean = JSONUtil.toJavaObject(objs, SysLogBean.class);
        Map m = PageUtil.getPagePara(objs);
        synchronized (SysLogService.class) {
            PageInfo pageInfo = new PageInfo(sysLogBiz.querySysLog(bean, m.get(PageUtil.PageNum), m.get(PageUtil.PageSize)));
            PageInfo pageInfoAll = new PageInfo(sysLogBiz.querySysLogTotal(bean));
            pageInfo.setTotal(pageInfoAll.getTotal());
            pageInfo.setPages(PageUtil.getPages((int)pageInfoAll.getTotal(), NumberUtil.toInt(m.get(PageUtil.PageSize))));
            pageInfo.setPageNum(NumberUtil.toInt(m.get(PageUtil.PageNum)));
            return pageInfo;
        }
    }

    @Override
    public List<SysMenu> getAllSysMenu() throws IqbException, IqbSqlException {
        return sysLogBiz.getSysMenuForLog();
    }

    /**
     * 
     * Description: 获取匹配的菜单bean
     * @param
     * @return SysMenu
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月5日 下午2:16:47
     */
    private TreeNode getMatchedTreeNodeBean(List<TreeNode> list, String url){
        if(list == null){
            return null;
        }
        for(TreeNode bean : list){
            String menuUrl = bean.getUrl();
            if(StringUtil.isEmpty(menuUrl)){
                continue;
            }
            if(StringUtil.contains(menuUrl, url)){
                return bean;
            }
        }
        return null;
    }
    
    /**
     * 
     * Description: 检查用户访问权限
     * @param
     * @return boolean
     * @throws IOException 
     * @throws IqbException 
     * @throws IqbSqlException 
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月12日 上午10:40:19
     */
    private void checkAccessAuth(String urlParh) throws IOException, IqbSqlException, IqbException{
        List<TreeNode> menuList = sysMenuService.getSysMenuListByRole();
        List<SysMenu> allSysMenuList = this.getAllSysMenu();
        /** 显示访问菜单拦截  **/
        if(this.getMatchedSysLogBean(allSysMenuList, urlParh) != null && this.getMatchedTreeNodeBean(menuList, urlParh) == null){
            logger.error("用户“{}”访问“{}”菜单失败", sysUserSession.getUserCode(), urlParh);
            throw new IqbException(SysManageReturnInfo.SYS_AUTH_01090001);
        }
    }

}
