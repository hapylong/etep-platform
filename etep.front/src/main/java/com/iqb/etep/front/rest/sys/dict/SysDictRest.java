package com.iqb.etep.front.rest.sys.dict;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.iqb.etep.common.base.CommonReturnInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.front.base.FrontBaseService;
import com.iqb.etep.sysmanage.dict.bean.SysDictItem;
import com.iqb.etep.sysmanage.dict.bean.SysDictType;
import com.iqb.etep.sysmanage.dict.bean.TreeNode;
import com.iqb.etep.sysmanage.dict.service.impl.SysDictService;

@Controller
@SuppressWarnings({"rawtypes"})
@RequestMapping("/sysDictRest")
public class SysDictRest extends FrontBaseService {
	
	/** 日志处理 **/
	private static Logger logger = LoggerFactory.getLogger(SysDictRest.class);
	

	@Autowired
	private SysDictService sysDictService;
	
	@ResponseBody
    @RequestMapping(value = { "/getSysDictType" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map getSysDictType(@RequestBody JSONObject objs,HttpServletRequest request) {
        try {                       
            logger.debug("IQB信息---开始分页查询数据...");
            List<TreeNode> list = sysDictService.selectSysDictTypeForTree(objs);
            logger.info("IQB信息---分页查询数据完成.");
            
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
            linkedHashMap.put("result",  list);
            return super.returnSuccessInfo(linkedHashMap);
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }
	
	@ResponseBody
    @RequestMapping(value = { "/getSysDictItem" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map getSysDictItem(@RequestBody JSONObject objs,HttpServletRequest request) {
        try {                       
            logger.debug("IQB信息---开始分页查询数据...");
            PageInfo<SysDictItem> pageInfo = sysDictService.selectSysDictItemForGrid(objs);
            logger.info("IQB信息---分页查询数据完成.");
            
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
            linkedHashMap.put("result",  pageInfo);
            return super.returnSuccessInfo(linkedHashMap);
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }
    
    @ResponseBody
    @RequestMapping(value = { "/insertSysDictType" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map insertSysDictType(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {           
            logger.debug("IQB信息---开始插入数据...");
            sysDictService.insertSysDictType(objs);
            logger.info("IQB信息---插入数据完成.");                 
            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] { });
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }
    
    @ResponseBody
    @RequestMapping(value = { "/insertSysDictItem" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map insertSysDictItem(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {           
            logger.debug("IQB信息---开始插入数据...");
            sysDictService.insertSysDictItem(objs);
            logger.info("IQB信息---插入数据完成.");                 
            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] { });
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }
    
    @ResponseBody
    @RequestMapping(value = { "/updateSysDictType" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map updateSysDictType(@RequestBody JSONObject objs, HttpServletRequest request) { 
        try {            
            logger.debug("IQB信息---开始更新数据...");
            sysDictService.updateSysDictTypeById(objs);
            logger.info("IQB信息---更新数据完成.");     
            
            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] { });
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }
    
    @ResponseBody
    @RequestMapping(value = { "/updateSysDictItem" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map updateSysDictItem(@RequestBody JSONObject objs, HttpServletRequest request) { 
        try {            
            logger.debug("IQB信息---开始更新数据...");
            sysDictService.updateSysDictItemById(objs);
            logger.info("IQB信息---更新数据完成.");     
            
            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] { });
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }
    
    @ResponseBody
    @RequestMapping(value = { "/deleteSysDictType" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map deleteSysDictType(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {            
            logger.debug("IQB信息---开始删除数据...");
            sysDictService.deleteSysDictTypeById(objs);
            logger.info("IQB信息---删除数据完成.");     
            
            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] { });
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }
    
    @ResponseBody
    @RequestMapping(value = { "/deleteSysDictItem" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map deleteSysDictItem(@RequestBody JSONObject objs, HttpServletRequest request) {
        try {            
            logger.debug("IQB信息---开始删除数据...");
            sysDictService.deleteSysDictItemById(objs);
            logger.info("IQB信息---删除数据完成.");     
            
            return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] { });
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }
    
    @ResponseBody
    @RequestMapping(value = { "/getSysDictTypeById" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map getSysDictTypeById(@RequestBody JSONObject objs, HttpServletRequest request) {   
        try {            
            logger.debug("IQB信息---开始根据ID查询数据...");
            SysDictType bean = sysDictService.selectSysDictTypeById(objs);
            logger.info("IQB信息---根据ID查询数据完成.");    
            
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
            linkedHashMap.put("result",  bean);
            return super.returnSuccessInfo(linkedHashMap);
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }
    
    @ResponseBody
    @RequestMapping(value = { "/getSysDictItemById" }, method = {RequestMethod.GET, RequestMethod.POST })
    public Map getSysDictItemById(@RequestBody JSONObject objs, HttpServletRequest request) {   
        try {            
            logger.debug("IQB信息---开始根据ID查询数据...");
            SysDictItem bean = sysDictService.selectSysDictItemById(objs);
            logger.info("IQB信息---根据ID查询数据完成.");    
            
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
            linkedHashMap.put("result",  bean);
            return super.returnSuccessInfo(linkedHashMap);
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }
    
    /**
     * 
     * Description: 根据字典type获取字典项list
     * @param
     * @return Map
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月22日 下午6:21:21
     */
    @ResponseBody
    @RequestMapping(value = { "/selectSysDictByType" }, method = { RequestMethod.POST })
    public Map selectSysDictByType(@RequestBody JSONObject objs, HttpServletRequest request) {   
        try {            
            logger.info("IQB信息---根据字典type获取字典项list数据...");
            List<Map<String, Object>> list = sysDictService.selectSysDictTypeToListOfMapFormRedis(objs);
            logger.info("IQB信息---根据字典type获取字典项list完成.");    
            
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
            linkedHashMap.put("result",  list);
            return super.returnSuccessInfo(linkedHashMap);
        }  catch (IqbException iqbe) {
            logger.error("IQB错误信息：" + iqbe.getRetInfo().getRetFactInfo(), iqbe);
            return super.returnFailtrueInfo(iqbe);
        } catch (Exception e) {// 系统发生异常
            logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
            return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
        }
    }
	
}