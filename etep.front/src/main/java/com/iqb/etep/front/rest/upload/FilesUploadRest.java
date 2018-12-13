package com.iqb.etep.front.rest.upload;

import java.util.LinkedHashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.common.base.CommonReturnInfo;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.front.base.FrontBaseService;
import com.iqb.etep.sysmanage.upload.service.impl.FilesUploadService;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping("/fileUpload")
public class FilesUploadRest extends FrontBaseService {

	/**
	 * 日志记录器
	 * */
	private static Logger logger = LoggerFactory.getLogger(FilesUploadRest.class);

	/**
	 * 上传文件 Service
	 * */
	@Autowired
	private FilesUploadService filesUploadService;

	
	@ResponseBody
	@RequestMapping(value = {"/upload/{fileType}/{moduleName}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public Map upload(@PathVariable String fileType, @PathVariable String moduleName, @RequestParam(value = "qqfile", required = false) MultipartFile file, HttpServletRequest request) {
		try {
			logger.debug("IQB--开始上传文件...");
			String filePath = filesUploadService.saveUploadFile(fileType, moduleName, file);
			logger.info("IQB信息--上传文件完成");
			LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
	        linkedHashMap.put("result", filePath);
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {//系统发生异常
			logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
			return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
		}
	}
	
	@ResponseBody
	@RequestMapping(value = {"/multiUpload/{fileType}/{moduleName}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public Map multiUpload(@PathVariable String fileType, @PathVariable String moduleName, @RequestParam(value = "qqfile", required = false) MultipartFile[] files, HttpServletRequest request) {
		try {
			logger.debug("IQB--开始上传文件...");
			String[] filePaths = filesUploadService.saveMultiUploadFile(fileType, moduleName, files);
			logger.info("IQB信息--上传文件完成");
			LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
	        linkedHashMap.put("result", filePaths);
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {//系统发生异常
			logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
			return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
		}
	}

	@ResponseBody
	@RequestMapping(value = {"/remove"}, method = {RequestMethod.GET, RequestMethod.POST})
	public Map remove(@RequestBody JSONObject objs, HttpServletRequest request) {
		try {
			logger.debug("IQB--开始删除文件...");
			filesUploadService.removeUploadedFile(objs.getString("filePath"));
			logger.info("IQB信息--删除文件完成");
			return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] { });
		} catch (Exception e) {//系统发生异常
			logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
			return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
		}
	}
	
	@RequestMapping(value = {"/download"}, method = {RequestMethod.GET, RequestMethod.POST})
	public Map download(HttpServletRequest request, HttpServletResponse response) {
		try {
			String fileName = request.getParameter("fileName");
			String filePath = request.getParameter("filePath");
			logger.debug("IQB--开始下载文件...");
			filesUploadService.downloadUploadedFile(fileName, filePath, request, response);
			logger.info("IQB信息--下载文件完成");
			return super.returnSuccessInfo(CommonReturnInfo.BASE00000000, new String[] { });
		} catch (Exception e) {//系统发生异常
			logger.error("IQB错误信息：" + CommonReturnInfo.BASE00000001.getRetFactInfo(), e);
			return super.returnFailtrueInfo(new IqbException(CommonReturnInfo.BASE00000001));
		}
	}
}
