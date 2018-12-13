package iqb.testcase.wf;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.httpclient.util.DateUtil;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

@SuppressWarnings("rawtypes")
public class WfProcTaskRestTestCase {
	private String baseUrl = "http://localhost/etep.front/";

	@Test	
	public void startAndCommitProcess() {
		String strDate = DateUtil.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd");
		String no = String.format("%05d", new Random().nextInt(10000));
		
		String procBizId = strDate + no;
		String procBizType = "1001";
		String procOrgCode = "1001001";
		String procCallback = "1"; // 1:前后回调不抛异常;2:前回调抛异常;3:前回调不抛异常,后回调抛异常
		
		// int type = 10; // 启动流程
//		 int type = 11; // 通过Token启动流程
		// int type = 20; // 启动并提交流程
//		 int type = 21; // 通过Token启动并提交流程
		// int type = 30; // 通过Session方式签收流程任务
//		 int type = 31; // 通过Token方式签收流程任务
		// int type = 40; // 通过Session方式取消签收流程任务
//		 int type = 41; // 通过Token方式取消签收流程任务
		// int type = 50; // 通过流程任务ID审批流程任务
//		int type = 51; // 通过流程任务代码审批流程任务
		// int type = 60; // 通过Session方式流删除程
//		 int type = 61; // 通过Token方式删除流程
		// int type = 70; // 通过流程任务ID委托流程
		// int type = 71; // 通过流程任务代码委托流程
		// int type = 80; // 通过Session方式取消流程
//		 int type = 81; // 通过Token方式取消流程
		// int type = 90; // 通过Session方式撤回流程
		 int type = 91; // 通过Token方+式撤回流程
		// int type = 100; // 通过Session方式终止流程
//		 int type = 101; // 通过Token方式终止流程

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Accept-Charset", "UTF-8");
		Object data = JSON.toJSON(getRequestData(type, procBizId, procBizType,
				procOrgCode, procCallback));
		System.out.println(data);
		HttpEntity entity = new HttpEntity<Object>(data, headers);
		ResponseEntity<Map> response = restTemplate.exchange(
				getRequestUrl(type), HttpMethod.POST, entity, Map.class);
		System.out.println("&&&&&&&&&&&&&&&&&&&" + response);
		LinkedHashMap responseMap = (LinkedHashMap) response.getBody();
		System.out.print("*******************" + responseMap);
		assertTrue(true);
	}

	private String getRequestUrl(int type) {
		switch (type) {
		case 10:
			return baseUrl + "WfTask/startProcess";
		case 11:
			return baseUrl + "WfTask/startProcess";
		case 20:
			return baseUrl + "WfTask/startAndCommitProcess";
		case 21:
			return baseUrl + "WfTask/startAndCommitProcessByToken";
		case 30:
			return baseUrl + "WfTask/claimProcess";
		case 31:
			return baseUrl + "WfTask/claimProcess";
		case 40:
			return baseUrl + "WfTask/unclaimProcess";
		case 41:
			return baseUrl + "WfTask/unclaimProcess";
		case 50:
			return baseUrl + "WfTask/completeProcess";
		case 51:
			return baseUrl + "WfTask/completeProcessByTaskCode";
		case 60:
			return baseUrl + "WfTask/deleteProcess";
		case 61:
			return baseUrl + "WfTask/deleteProcess";
		case 70:
			return baseUrl + "WfTask/delegateProcess";
		case 71:
			return baseUrl + "WfTask/delegateProcess";
		case 80:
			return baseUrl + "WfTask/cancelProcess";
		case 81:
			return baseUrl + "WfTask/cancelProcess";
		case 90:
			return baseUrl + "WfTask/retrieveProcess";
		case 91:
			return baseUrl + "WfTask/retrieveProcess";
		case 100:
			return baseUrl + "WfTask/endProcess";
		case 101:
			return baseUrl + "WfTask/endProcess";
		default:
			return null;
		}
	}

	private Map getRequestData(int type, String procBizId, String procBizType,
			String procOrgCode, String procCallback) {
		Map map = new HashMap();

		switch (type) {
		case 10:
			return getStartProcessData(procBizId, procBizType, procOrgCode, procCallback);
		case 11:
			return getStartProcessDataByToken(procBizId, procBizType, procOrgCode, procCallback);
		case 20:
			return getStartAndCompleteProcessData(procBizId, procBizType, procOrgCode, procCallback);
		case 21:
			return getStartAndCompleteProcessDataByToken(procBizId, procBizType, procOrgCode, procCallback);
		case 30:
			return getClaimProcessData(procBizId, procBizType, procOrgCode, procCallback);
		case 31:
			return getClaimProcessDataByToken(procBizId, procBizType, procOrgCode, procCallback);
		case 40:
			return getUnclaimProcessData(procBizId, procBizType, procOrgCode, procCallback);
		case 41:
			return getUnclaimProcessDataByToken(procBizId, procBizType, procOrgCode, procCallback);
		case 50:
			return getcompleteProcessData(procBizId, procBizType, procOrgCode, procCallback);
		case 51:
			return getcompleteProcessDataByCode(procBizId, procBizType, procOrgCode, procCallback);
		case 60:
			return getDeleteProcessData(procBizId, procBizType, procOrgCode, procCallback);
		case 61:
			return getDeleteProcessDataByToken(procBizId, procBizType, procOrgCode, procCallback);
		case 70:
			return getDelegateProcessData();
		case 71:
			return getDelegateProcessDataByToken();
		case 80:
			return getCancelProcessData(procBizId, procBizType, procOrgCode, procCallback);
		case 81:
			return getCancelProcessDataByToken(procBizId, procBizType, procOrgCode, procCallback);
		case 90:
			return getRetrieveProcessData(procBizId, procBizType, procOrgCode, procCallback);
		case 91:
			return getRetrieveProcessDataByToken(procBizId, procBizType, procOrgCode, procCallback);
		case 100:
			return getEndProcessData(procBizId, procBizType, procOrgCode, procCallback);
		case 101:
			return getEndProcessDataByToken(procBizId, procBizType, procOrgCode, procCallback);
		default:
			break;
		}

		return map;
	}

	private Map<String, Map<String, Object>> getStartProcessData(
			String procBizId, String procBizType, String procOrgCode, String procCallback) {
		Map<String, Map<String, Object>> reqData = new HashMap<String, Map<String, Object>>();

		Map<String, Object> hmProcData = new HashMap<String, Object>();
		hmProcData.put("procDefKey", "penalty_derate");

		Map<String, Object> hmAuthData = new HashMap<String, Object>();
		hmAuthData.put("procAuthType", "2"); // 1：token认证；2：session认证
		hmAuthData.put("procTaskUser", "sysadmin");
		hmAuthData.put("procTaskRole", "sys_manage");

		Map<String, Object> hmVariables = new HashMap<String, Object>();
		// hmVariables.put("procAuthType", "2"); // 1：token认证；2：session认证
		// hmVariables.put("procTaskUser", "sysadmin");
		// hmVariables.put("procTaskRole", "sys_manage");

		Map<String, Object> hmBizData = new HashMap<String, Object>();
		hmBizData.put("procBizId", procBizId);
		hmBizData.put("procBizType", procBizType);
		hmBizData.put("procOrgCode", procOrgCode);
		hmBizData.put("procBizMemo", "业务流程测试:" + procBizId);
		hmBizData.put("procCallback", procCallback);
		
		reqData.put("procData", hmProcData);
		reqData.put("variableData", hmVariables);
		reqData.put("authData", hmAuthData);
		reqData.put("bizData", hmBizData);

		return reqData;
	}

	private Map<String, Map<String, Object>> getStartProcessDataByToken(
			String procBizId, String procBizType, String procOrgCode, String procCallback) {
		Map<String, Map<String, Object>> reqData = new HashMap<String, Map<String, Object>>();

		Map<String, Object> hmProcData = new HashMap<String, Object>();
		hmProcData.put("procDefKey", "penalty_derate");

		Map<String, Object> hmAuthData = new HashMap<String, Object>();
		hmAuthData.put("procAuthType", "1"); // 1：token认证；2：session认证
		hmAuthData.put("procTokenUser", "f53674938794c432e1021584ffd963a6");
		hmAuthData.put("procTokenPass", "331493b0b9d8815135f6361bd1f83a7c");
		hmAuthData.put("procTaskUser", "sysadmin");
		hmAuthData.put("procTaskRole", "sys_manage");

		Map<String, Object> hmVariables = new HashMap<String, Object>();
		// hmVariables.put("procAuthType", "1"); // 1：token认证；2：session认证
		// hmVariables.put("procTokenUser", "f53674938794c432e1021584ffd963a6");
		// hmVariables.put("procTokenPass", "331493b0b9d8815135f6361bd1f83a7c");
		// hmVariables.put("procTaskUser", "sysadmin");
		// hmVariables.put("procTaskRole", "sys_manage");

		Map<String, Object> hmBizData = new HashMap<String, Object>();
		hmBizData.put("procBizId", procBizId);
		hmBizData.put("procBizType", procBizType);
		hmBizData.put("procOrgCode", procOrgCode);
		hmBizData.put("procBizMemo", "业务流程测试:" + procBizId);
		hmBizData.put("procCallback", procCallback);
		
		reqData.put("procData", hmProcData);
		reqData.put("variableData", hmVariables);
		reqData.put("authData", hmAuthData);
		reqData.put("bizData", hmBizData);

		return reqData;
	}

	private Map<String, Map<String, Object>> getStartAndCompleteProcessData(
			String procBizId, String procBizType, String procOrgCode, String procCallback) {
		Map<String, Map<String, Object>> reqData = new HashMap<String, Map<String, Object>>();

		Map<String, Object> hmProcData = new HashMap<String, Object>();
		hmProcData.put("procDefKey", "penalty_derate");

		Map<String, Object> hmAuthData = new HashMap<String, Object>();
		hmAuthData.put("procAuthType", "2"); // 1：token认证；2：session认证
		hmAuthData.put("procTaskUser", "sysadmin");
		hmAuthData.put("procTaskRole", "sys_manage");

		Map<String, Object> hmVariables = new HashMap<String, Object>();
		// hmVariables.put("procAuthType", "2"); // 1：token认证；2：session认证
		// hmVariables.put("procTaskUser", "sysadmin");
		// hmVariables.put("procTaskRole", "sys_manage");
		hmVariables.put("procApprStatus", "1");
		hmVariables.put("procApprOpinion", "同意");
		hmVariables.put("procAssignee", "");
		hmVariables.put("procAppointTask", "");

		hmVariables.put("amount", 500001);
		hmVariables.put("Test1", "Test1");
		hmVariables.put("Test2", "Test2");
		hmVariables.put("Test3", "Test3");

		Map<String, Object> hmBizData = new HashMap<String, Object>();
		hmBizData.put("procBizId", procBizId);
		hmBizData.put("procBizType", procBizType);
		hmBizData.put("procOrgCode", procOrgCode);
		hmBizData.put("procBizMemo", "业务流程测试:" + procBizId);
		hmBizData.put("procCallback", procCallback);
		hmBizData.put("money1", 10);
		hmBizData.put("money2", 20);
		hmBizData.put("amount", 5001);

		reqData.put("procData", hmProcData);
		reqData.put("variableData", hmVariables);
		reqData.put("authData", hmAuthData);
		reqData.put("bizData", hmBizData);

		return reqData;
	}

	private Map<String, Map<String, Object>> getStartAndCompleteProcessDataByToken(
			String procBizId, String procBizType, String procOrgCode, String procCallback) {
		Map<String, Map<String, Object>> reqData = new HashMap<String, Map<String, Object>>();

		Map<String, Object> hmProcData = new HashMap<String, Object>();
		hmProcData.put("procDefKey", "penalty_derate");

		Map<String, Object> hmAuthData = new HashMap<String, Object>();
		hmAuthData.put("procAuthType", "1"); // 1：token认证；2：session认证
		hmAuthData.put("procTokenUser", "f53674938794c432e1021584ffd963a6");
		hmAuthData.put("procTokenPass", "331493b0b9d8815135f6361bd1f83a7c");
		hmAuthData.put("procTaskUser", "sysadmin");
		hmAuthData.put("procTaskRole", "sys_manage");

		Map<String, Object> hmVariables = new HashMap<String, Object>();
		// hmVariables.put("procAuthType", "1"); // 1：token认证；2：session认证
		// hmVariables.put("procTokenUser", "f53674938794c432e1021584ffd963a6");
		// hmVariables.put("procTokenPass", "331493b0b9d8815135f6361bd1f83a7c");
		// hmVariables.put("procTaskUser", "sysadmin");
		// hmVariables.put("procTaskRole", "sys_manage");

		hmVariables.put("procApprStatus", "1");
		hmVariables.put("procApprOpinion", "同意");
		hmVariables.put("procAssignee", "");
		hmVariables.put("procAppointTask", "");

		hmVariables.put("amount", 500001);
		hmVariables.put("Test1", "Test1");
		hmVariables.put("Test2", "Test2");
		hmVariables.put("Test3", "Test3");

		Map<String, Object> hmBizData = new HashMap<String, Object>();
		hmBizData.put("procBizId", procBizId);
		hmBizData.put("procBizType", procBizType);
		hmBizData.put("procOrgCode", procOrgCode);
		hmBizData.put("procBizMemo", "业务流程测试:" + procBizId);
		hmBizData.put("procCallback", procCallback);
		
		reqData.put("procData", hmProcData);
		reqData.put("variableData", hmVariables);
		reqData.put("authData", hmAuthData);
		reqData.put("bizData", hmBizData);

		return reqData;
	}

	private Map<String, Map<String, Object>> getClaimProcessData(
			String procBizId, String procBizType, String procOrgCode, String procCallback) {
		Map<String, Map<String, Object>> reqData = new HashMap<String, Map<String, Object>>();

		Map<String, Object> hmProcData = new HashMap<String, Object>();
		hmProcData.put("procTaskId", "6852d53c-3b9e-11e7-939d-80a589ac6c46");

		Map<String, Object> hmAuthData = new HashMap<String, Object>();
		hmAuthData.put("procAuthType", "2"); // 1：token认证；2：session认证
		// hmAuthData.put("procTaskUser", "sysadmin");
		// hmAuthData.put("procTaskRole", "derate_operation");

		Map<String, Object> hmVariables = new HashMap<String, Object>();
		// hmVariables.put("procAuthType", "2"); // 1：token认证；2：session认证
		// hmVariables.put("procTaskUser", "user5");
		// hmVariables.put("procTaskRole", "role5");

		Map<String, Object> hmBizData = new HashMap<String, Object>();
		hmBizData.put("procBizId", procBizId);
		hmBizData.put("procBizType", procBizType);
		hmBizData.put("procOrgCode", procOrgCode);
		hmBizData.put("procBizMemo", "业务流程测试:" + procBizId);
		hmBizData.put("procCallback", procCallback);
		
		reqData.put("procData", hmProcData);
		reqData.put("variableData", hmVariables);
		reqData.put("authData", hmAuthData);
		reqData.put("bizData", hmBizData);

		return reqData;
	}

	private Map<String, Map<String, Object>> getClaimProcessDataByToken(
			String procBizId, String procBizType, String procOrgCode, String procCallback) {
		Map<String, Map<String, Object>> reqData = new HashMap<String, Map<String, Object>>();

		Map<String, Object> hmProcData = new HashMap<String, Object>();
		hmProcData.put("procTaskId", "6852d53c-3b9e-11e7-939d-80a589ac6c46");

		Map<String, Object> hmAuthData = new HashMap<String, Object>();
		hmAuthData.put("procAuthType", "1"); // 1：token认证；2：session认证
		hmAuthData.put("procTokenUser", "f53674938794c432e1021584ffd963a6");
		hmAuthData.put("procTokenPass", "331493b0b9d8815135f6361bd1f83a7c");
		hmAuthData.put("procTaskUser", "sysadmin");
		hmAuthData.put("procTaskRole", "derate_operation");

		Map<String, Object> hmVariables = new HashMap<String, Object>();
		// hmVariables.put("procAuthType", "1"); // 1：token认证；2：session认证
		// hmVariables.put("procTokenUser", "f53674938794c432e1021584ffd963a6");
		// hmVariables.put("procTokenPass", "331493b0b9d8815135f6361bd1f83a7c");
		// hmVariables.put("procTaskUser", "user6");
		// hmVariables.put("procTaskRole", "role6");

		Map<String, Object> hmBizData = new HashMap<String, Object>();
		hmBizData.put("procBizId", procBizId);
		hmBizData.put("procBizType", procBizType);
		hmBizData.put("procOrgCode", procOrgCode);
		hmBizData.put("procBizMemo", "业务流程测试:" + procBizId);
		hmBizData.put("procCallback", procCallback);
		
		reqData.put("procData", hmProcData);
		reqData.put("variableData", hmVariables);
		reqData.put("authData", hmAuthData);
		reqData.put("bizData", hmBizData);

		return reqData;
	}

	private Map<String, Map<String, Object>> getUnclaimProcessData(
			String procBizId, String procBizType, String procOrgCode, String procCallback) {
		Map<String, Map<String, Object>> reqData = new HashMap<String, Map<String, Object>>();

		Map<String, Object> hmProcData = new HashMap<String, Object>();
		hmProcData.put("procTaskId", "6852d53c-3b9e-11e7-939d-80a589ac6c46");

		Map<String, Object> hmAuthData = new HashMap<String, Object>();
		hmAuthData.put("procAuthType", "2"); // 1：token认证；2：session认证
		hmAuthData.put("procTaskUser", "sysadmin");
		hmAuthData.put("procTaskRole", "derate_operation");

		Map<String, Object> hmVariables = new HashMap<String, Object>();
		// hmVariables.put("procAuthType", "2"); // 1：token认证；2：session认证
		// hmVariables.put("procTaskUser", "user2");
		// hmVariables.put("procTaskRole", "role2");

		Map<String, Object> hmBizData = new HashMap<String, Object>();
		hmBizData.put("procBizId", procBizId);
		hmBizData.put("procBizType", procBizType);
		hmBizData.put("procOrgCode", procOrgCode);
		hmBizData.put("procBizMemo", "业务流程测试:" + procBizId);
		hmBizData.put("procCallback", procCallback);
		hmBizData.put("amount", 5001);

		reqData.put("procData", hmProcData);
		reqData.put("variableData", hmVariables);
		reqData.put("authData", hmAuthData);
		reqData.put("bizData", hmBizData);

		return reqData;
	}

	private Map<String, Map<String, Object>> getUnclaimProcessDataByToken(
			String procBizId, String procBizType, String procOrgCode, String procCallback) {
		Map<String, Map<String, Object>> reqData = new HashMap<String, Map<String, Object>>();

		Map<String, Object> hmProcData = new HashMap<String, Object>();
		hmProcData.put("procTaskId", "6852d53c-3b9e-11e7-939d-80a589ac6c46");

		Map<String, Object> hmAuthData = new HashMap<String, Object>();
		hmAuthData.put("procAuthType", "1"); // 1：token认证；2：session认证
		hmAuthData.put("procTokenUser", "f53674938794c432e1021584ffd963a6");
		hmAuthData.put("procTokenPass", "331493b0b9d8815135f6361bd1f83a7c");
		hmAuthData.put("procTaskUser", "sysadmin");
		hmAuthData.put("procTaskRole", "derate_operation");

		Map<String, Object> hmVariables = new HashMap<String, Object>();
		// hmVariables.put("procAuthType", "1"); // 1：token认证；2：session认证
		// hmVariables.put("procTokenUser", "f53674938794c432e1021584ffd963a6");
		// hmVariables.put("procTokenPass", "331493b0b9d8815135f6361bd1f83a7c");
		// hmVariables.put("procTaskUser", "user2");
		// hmVariables.put("procTaskRole", "role2");

		Map<String, Object> hmBizData = new HashMap<String, Object>();
		hmBizData.put("procBizId", procBizId);
		hmBizData.put("procBizType", procBizType);
		hmBizData.put("procOrgCode", procOrgCode);
		hmBizData.put("procBizMemo", "业务流程测试:" + procBizId);
		hmBizData.put("procCallback", procCallback);
		
		reqData.put("procData", hmProcData);
		reqData.put("variableData", hmVariables);
		reqData.put("authData", hmAuthData);
		reqData.put("bizData", hmBizData);

		return reqData;
	}

	private Map<String, Map<String, Object>> getcompleteProcessData(
			String procBizId, String procBizType, String procOrgCode, String procCallback) {
		Map<String, Map<String, Object>> reqData = new HashMap<String, Map<String, Object>>();

		Map<String, Object> hmProcData = new HashMap<String, Object>();
		hmProcData.put("procTaskId", "6852d53c-3b9e-11e7-939d-80a589ac6c46");

		Map<String, Object> hmAuthData = new HashMap<String, Object>();
		hmAuthData.put("procAuthType", "2"); // 1：token认证；2：session认证
		hmAuthData.put("procTaskUser", "sysadmin");
		hmAuthData.put("procTaskRole", "derate_operation");

		Map<String, Object> hmVariables = new HashMap<String, Object>();
		// hmVariables.put("procAuthType", "2"); // 1：token认证；2：session认证
		// hmVariables.put("procTaskUser", "sysadmin");
		// hmVariables.put("procTaskRole", "lease_pledge_confirm");

		hmVariables.put("procApprStatus", "1");
		hmVariables.put("procApprOpinion", "同意");
		// hmVariables.put("procAssignee", "");
		// hmVariables.put("procAppointTask", "");

		hmVariables.put("amount", 500001);
		hmVariables.put("Test1", "Test1");
		hmVariables.put("Test2", "Test2");
		hmVariables.put("Test3", "Test3");

		Map<String, Object> hmBizData = new HashMap<String, Object>();
		hmBizData.put("procBizId", procBizId);
		hmBizData.put("procBizType", procBizType);
		hmBizData.put("procOrgCode", procOrgCode);
		hmBizData.put("procBizMemo", "业务流程测试:" + procBizId);
		hmBizData.put("procCallback", procCallback);
		hmBizData.put("amount", 500001);

		reqData.put("procData", hmProcData);
		reqData.put("variableData", hmVariables);
		reqData.put("authData", hmAuthData);
		reqData.put("bizData", hmBizData);

		return reqData;
	}

	private Map<String, Map<String, Object>> getcompleteProcessDataByCode(
			String procBizId, String procBizType, String procOrgCode, String procCallback) {
		Map<String, Map<String, Object>> reqData = new HashMap<String, Map<String, Object>>();

		Map<String, Object> hmProcData = new HashMap<String, Object>();
		hmProcData.put("procInstId", "f3cd5b1f-3c63-11e7-927e-80a589ac6c46");
		hmProcData.put("procTaskCode", "penalty_derate_oper");

		Map<String, Object> hmAuthData = new HashMap<String, Object>();
		hmAuthData.put("procAuthType", "1"); // 1：token认证；2：session认证
		hmAuthData.put("procTokenUser", "f53674938794c432e1021584ffd963a6");
		hmAuthData.put("procTokenPass", "331493b0b9d8815135f6361bd1f83a7c");
		hmAuthData.put("procTaskUser", "sysadmin");
		hmAuthData.put("procTaskRole", "derate_operation");

		Map<String, Object> hmVariables = new HashMap<String, Object>();
		// hmVariables.put("procAuthType", "1"); // 1：token认证；2：session认证
		// hmVariables.put("procTokenUser", "f53674938794c432e1021584ffd963a6");
		// hmVariables.put("procTokenPass", "331493b0b9d8815135f6361bd1f83a7c");
		// hmVariables.put("procTaskUser", "sysadmin");
		// hmVariables.put("procTaskRole", "lease_risk_approve");
		hmVariables.put("derateType", "2");
		hmVariables.put("cutAmt", 5000);
		
		hmVariables.put("procApprStatus", "1");
		hmVariables.put("procApprOpinion", "通过");
		hmVariables.put("procAssignee", "");
		hmVariables.put("procAppointTask", "");
		hmVariables.put("amount", 500001);

		Map<String, Object> hmBizData = new HashMap<String, Object>();
		hmBizData.put("procBizId", procBizId);
		hmBizData.put("procBizType", procBizType);
		hmBizData.put("procOrgCode", procOrgCode);
		hmBizData.put("procBizMemo", "业务流程测试:" + procBizId);
		hmBizData.put("procCallback", procCallback);
		hmBizData.put("amount", 500001);

		reqData.put("procData", hmProcData);
		reqData.put("variableData", hmVariables);
		reqData.put("authData", hmAuthData);
		reqData.put("bizData", hmBizData);

		return reqData;
	}

	private Map<String, Map<String, Object>> getDeleteProcessData(
			String procBizId, String procBizType, String procOrgCode, String procCallback) {
		Map<String, Map<String, Object>> reqData = new HashMap<String, Map<String, Object>>();

		Map<String, Object> hmProcData = new HashMap<String, Object>();
		hmProcData.put("procTaskId", "4f65721a-c817-11e6-8fb4-80a589ac6c46");

		Map<String, Object> hmAuthData = new HashMap<String, Object>();
		hmAuthData.put("procAuthType", "2"); // 1：token认证；2：session认证
		hmAuthData.put("procTaskUser", "sysadmin");
		hmAuthData.put("procTaskRole", "sys_manage");

		Map<String, Object> hmVariables = new HashMap<String, Object>();
		// hmVariables.put("procAuthType", "2"); // 1：token认证；2：session认证
		// hmVariables.put("procTaskUser", "user1");
		// hmVariables.put("procTaskRole", "role1");

		Map<String, Object> hmBizData = new HashMap<String, Object>();
		hmBizData.put("procBizId", procBizId);
		hmBizData.put("procBizType", procBizType);
		hmBizData.put("procOrgCode", procOrgCode);
		hmBizData.put("procBizMemo", "业务流程测试:" + procBizId);
		hmBizData.put("procCallback", procCallback);
		hmBizData.put("amount", 500001);

		reqData.put("procData", hmProcData);
		reqData.put("variableData", hmVariables);
		reqData.put("authData", hmAuthData);
		reqData.put("bizData", hmBizData);

		return reqData;
	}

	private Map<String, Map<String, Object>> getDeleteProcessDataByToken(
			String procBizId, String procBizType, String procOrgCode, String procCallback) {
		Map<String, Map<String, Object>> reqData = new HashMap<String, Map<String, Object>>();

		Map<String, Object> hmProcData = new HashMap<String, Object>();
		hmProcData.put("procTaskId", "0b254ba9-3c60-11e7-927e-80a589ac6c46");

		Map<String, Object> hmAuthData = new HashMap<String, Object>();
		hmAuthData.put("procAuthType", "1"); // 1：token认证；2：session认证
		hmAuthData.put("procTokenUser", "f53674938794c432e1021584ffd963a6");
		hmAuthData.put("procTokenPass", "331493b0b9d8815135f6361bd1f83a7c");
		hmAuthData.put("procTaskUser", "sysadmin");
		hmAuthData.put("procTaskRole", "sys_manage");

		Map<String, Object> hmVariables = new HashMap<String, Object>();
		// hmVariables.put("procAuthType", "1"); // 1：token认证；2：session认证
		// hmVariables.put("procTokenUser", "f53674938794c432e1021584ffd963a6");
		// hmVariables.put("procTokenPass", "331493b0b9d8815135f6361bd1f83a7c");
		// hmVariables.put("procTaskUser", "user1");
		// hmVariables.put("procTaskRole", "role1");

		Map<String, Object> hmBizData = new HashMap<String, Object>();
		hmBizData.put("procBizId", procBizId);
		hmBizData.put("procBizType", procBizType);
		hmBizData.put("procOrgCode", procOrgCode);
		hmBizData.put("procBizMemo", "业务流程测试:" + procBizId);
		hmBizData.put("procCallback", procCallback);
		hmBizData.put("amount", 500001);

		reqData.put("procData", hmProcData);
		reqData.put("variableData", hmVariables);
		reqData.put("authData", hmAuthData);
		reqData.put("bizData", hmBizData);

		return reqData;
	}

	private Map<String, Map<String, Object>> getDelegateProcessData() {
		Map<String, Map<String, Object>> reqData = new HashMap<String, Map<String, Object>>();

		Map<String, Object> hmProcData = new HashMap<String, Object>();
		hmProcData.put("procTaskId", "dbfb4148-9411-11e6-9de2-00163e0036f7");

		Map<String, Object> hmAuthData = new HashMap<String, Object>();
		hmAuthData.put("procAuthType", "2"); // 1：token认证；2：session认证
		// hmAuthData.put("procTaskUser", "sysadmin");
		// hmAuthData.put("procTaskRole", "sys_manage");

		Map<String, Object> hmVariables = new HashMap<String, Object>();
		// hmVariables.put("procAuthType", "2"); // 1：token认证；2：session认证
		// hmVariables.put("procTaskUser", "sysadmin");
		// hmVariables.put("procTaskRole", "sys_manage");
		hmVariables.put("procAssignee", "fuchongguang");

		reqData.put("procData", hmProcData);
		reqData.put("variableData", hmVariables);
		reqData.put("authData", hmAuthData);

		return reqData;
	}

	private Map<String, Map<String, Object>> getDelegateProcessDataByToken() {
		Map<String, Map<String, Object>> reqData = new HashMap<String, Map<String, Object>>();

		Map<String, Object> hmProcData = new HashMap<String, Object>();
		hmProcData.put("procTaskId", "26126711-c814-11e6-9c64-80a589ac6c46");

		Map<String, Object> hmAuthData = new HashMap<String, Object>();
		hmAuthData.put("procAuthType", "1"); // 1：token认证；2：session认证
		hmAuthData.put("procTokenUser", "f53674938794c432e1021584ffd963a6");
		hmAuthData.put("procTokenPass", "331493b0b9d8815135f6361bd1f83a7c");
		hmAuthData.put("procTaskUser", "sysadmin");
		hmAuthData.put("procTaskRole", "lease_price_confirm");

		Map<String, Object> hmVariables = new HashMap<String, Object>();
		// hmVariables.put("procAuthType", "1"); // 1：token认证；2：session认证
		// hmVariables.put("procTokenUser", "f53674938794c432e1021584ffd963a6");
		// hmVariables.put("procTokenPass", "331493b0b9d8815135f6361bd1f83a7c");
		// hmVariables.put("procTaskUser", "sysadmin");
		// hmVariables.put("procTaskRole", "sys_manage");
		hmVariables.put("procAssignee", "sysadmin1");

		reqData.put("procData", hmProcData);
		reqData.put("variableData", hmVariables);
		reqData.put("authData", hmAuthData);

		return reqData;
	}

	private Map<String, Map<String, Object>> getCancelProcessData(
			String procBizId, String procBizType, String procOrgCode, String procCallback) {
		Map<String, Map<String, Object>> reqData = new HashMap<String, Map<String, Object>>();

		Map<String, Object> hmProcData = new HashMap<String, Object>();
		hmProcData.put("procInstId", "a367e504-aa41-11e6-9901-80a589ac6c46");

		Map<String, Object> hmAuthData = new HashMap<String, Object>();
		hmAuthData.put("procAuthType", "2"); // 1：token认证；2：session认证
		// hmAuthData.put("procTaskUser", "sysadmin");
		// hmAuthData.put("procTaskRole", "sys_manage");

		Map<String, Object> hmVariables = new HashMap<String, Object>();
		// hmVariables.put("procAuthType", "2"); // 1：token认证；2：session认证
		// hmVariables.put("procTaskUser", "sysadmin1");
		// hmVariables.put("procTaskRole", "sys_manage");

		Map<String, Object> hmBizData = new HashMap<String, Object>();
		hmBizData.put("procBizId", procBizId);
		hmBizData.put("procBizType", procBizType);
		hmBizData.put("procOrgCode", procOrgCode);
		hmBizData.put("procBizMemo", "业务流程测试:" + procBizId);
		hmBizData.put("procCallback", procCallback);
		
		reqData.put("procData", hmProcData);
		reqData.put("variableData", hmVariables);
		reqData.put("authData", hmAuthData);
		reqData.put("bizData", hmBizData);

		return reqData;
	}

	private Map<String, Map<String, Object>> getCancelProcessDataByToken(
			String procBizId, String procBizType, String procOrgCode, String procCallback) {
		Map<String, Map<String, Object>> reqData = new HashMap<String, Map<String, Object>>();

		Map<String, Object> hmProcData = new HashMap<String, Object>();
		hmProcData.put("procInstId", "9b27e4e3-3c5c-11e7-927e-80a589ac6c46");

		Map<String, Object> hmAuthData = new HashMap<String, Object>();
		hmAuthData.put("procAuthType", "1"); // 1：token认证；2：session认证
		hmAuthData.put("procTokenUser", "f53674938794c432e1021584ffd963a6");
		hmAuthData.put("procTokenPass", "331493b0b9d8815135f6361bd1f83a7c");
		hmAuthData.put("procTaskUser", "sysadmin");
		hmAuthData.put("procTaskRole", "sys_manage");

		Map<String, Object> hmVariables = new HashMap<String, Object>();
		// hmVariables.put("procAuthType", "1"); // 1：token认证；2：session认证
		// hmVariables.put("procTokenUser", "f53674938794c432e1021584ffd963a6");
		// hmVariables.put("procTokenPass", "331493b0b9d8815135f6361bd1f83a7c");
		// hmVariables.put("procTaskUser", "mahairong");
		// hmVariables.put("procTaskRole", "lease_risk_approve");

		Map<String, Object> hmBizData = new HashMap<String, Object>();
		hmBizData.put("procBizId", procBizId);
		hmBizData.put("procBizType", procBizType);
		hmBizData.put("procOrgCode", procOrgCode);
		hmBizData.put("procBizMemo", "业务流程测试:" + procBizId);
		hmBizData.put("procCallback", procCallback);
		
		reqData.put("procData", hmProcData);
		reqData.put("variableData", hmVariables);
		reqData.put("authData", hmAuthData);
		reqData.put("bizData", hmBizData);

		return reqData;
	}

	private Map<String, Map<String, Object>> getRetrieveProcessData(
			String procBizId, String procBizType, String procOrgCode, String procCallback) {
		Map<String, Map<String, Object>> reqData = new HashMap<String, Map<String, Object>>();

		Map<String, Object> hmProcData = new HashMap<String, Object>();
		hmProcData.put("procTaskId", "4f65721a-c817-11e6-8fb4-80a589ac6c46");

		Map<String, Object> hmAuthData = new HashMap<String, Object>();
		hmAuthData.put("procAuthType", "2"); // 1：token认证；2：session认证
		// hmAuthData.put("procTaskUser", "sysadmin");
		// hmAuthData.put("procTaskRole", "sys_manage");

		Map<String, Object> hmVariables = new HashMap<String, Object>();
		// hmVariables.put("procAuthType", "2"); // 1：token认证；2：session认证
		// hmVariables.put("procTaskUser", "mahairong");
		// hmVariables.put("procTaskRole", "lease_risk_approve");

		Map<String, Object> hmBizData = new HashMap<String, Object>();
		hmBizData.put("procBizId", procBizId);
		hmBizData.put("procBizType", procBizType);
		hmBizData.put("procOrgCode", procOrgCode);
		hmBizData.put("procBizMemo", "业务流程测试:" + procBizId);
		hmBizData.put("procCallback", procCallback);
		
		reqData.put("procData", hmProcData);
		reqData.put("variableData", hmVariables);
		reqData.put("authData", hmAuthData);
		reqData.put("bizData", hmBizData);

		return reqData;
	}

	private Map<String, Map<String, Object>> getRetrieveProcessDataByToken(
			String procBizId, String procBizType, String procOrgCode, String procCallback) {
		Map<String, Map<String, Object>> reqData = new HashMap<String, Map<String, Object>>();

		Map<String, Object> hmProcData = new HashMap<String, Object>();
		hmProcData.put("procTaskId", "ff4dff4e-3c63-11e7-927e-80a589ac6c46");

		Map<String, Object> hmAuthData = new HashMap<String, Object>();
		hmAuthData.put("procAuthType", "1"); // 1：token认证；2：session认证
		hmAuthData.put("procTokenUser", "f53674938794c432e1021584ffd963a6");
		hmAuthData.put("procTokenPass", "331493b0b9d8815135f6361bd1f83a7c");
		hmAuthData.put("procTaskUser", "sysadmin");
		hmAuthData.put("procTaskRole", "derate_risk");

		Map<String, Object> hmVariables = new HashMap<String, Object>();
		// hmVariables.put("procAuthType", "1"); // 1：token认证；2：session认证
		// hmVariables.put("procTokenUser", "f53674938794c432e1021584ffd963a6");
		// hmVariables.put("procTokenPass", "331493b0b9d8815135f6361bd1f83a7c");
		// hmVariables.put("procTaskUser", "sysadmin");
		// hmVariables.put("procTaskRole", "sys_manage");

		Map<String, Object> hmBizData = new HashMap<String, Object>();
		hmBizData.put("procBizId", procBizId);
		hmBizData.put("procBizType", procBizType);
		hmBizData.put("procOrgCode", procOrgCode);
		hmBizData.put("procBizMemo", "业务流程测试:" + procBizId);
		hmBizData.put("procCallback", procCallback);
		
		reqData.put("procData", hmProcData);
		reqData.put("variableData", hmVariables);
		reqData.put("authData", hmAuthData);
		reqData.put("bizData", hmBizData);

		return reqData;
	}

	private Map<String, Map<String, Object>> getEndProcessData(
			String procBizId, String procBizType, String procOrgCode, String procCallback) {
		Map<String, Map<String, Object>> reqData = new HashMap<String, Map<String, Object>>();

		Map<String, Object> hmProcData = new HashMap<String, Object>();
		hmProcData.put("procInstId", "792fcaa7-c82c-11e6-851b-80a589ac6c46");

		Map<String, Object> hmAuthData = new HashMap<String, Object>();
		hmAuthData.put("procAuthType", "2"); // 1：token认证；2：session认证

		Map<String, Object> hmVariables = new HashMap<String, Object>();
		// hmVariables.put("procAuthType", "2"); // 1：token认证；2：session认证

		Map<String, Object> hmBizData = new HashMap<String, Object>();
		hmBizData.put("procBizId", procBizId);
		hmBizData.put("procBizType", procBizType);
		hmBizData.put("procOrgCode", procOrgCode);
		hmBizData.put("procBizMemo", "业务流程测试:" + procBizId);
		hmBizData.put("procCallback", procCallback);
		
		reqData.put("procData", hmProcData);
		reqData.put("variableData", hmVariables);
		reqData.put("authData", hmAuthData);
		reqData.put("bizData", hmBizData);

		return reqData;
	}

	private Map<String, Map<String, Object>> getEndProcessDataByToken(
			String procBizId, String procBizType, String procOrgCode, String procCallback) {
		Map<String, Map<String, Object>> reqData = new HashMap<String, Map<String, Object>>();

		Map<String, Object> hmProcData = new HashMap<String, Object>();
		hmProcData.put("procInstId", "6615684d-3b9e-11e7-939d-80a589ac6c46");

		Map<String, Object> hmAuthData = new HashMap<String, Object>();
		hmAuthData.put("procAuthType", "1"); // 1：token认证；2：session认证
		hmAuthData.put("procTokenUser", "f53674938794c432e1021584ffd963a6");
		hmAuthData.put("procTokenPass", "331493b0b9d8815135f6361bd1f83a7c");
		hmAuthData.put("procTaskUser", "sysadmin");

		Map<String, Object> hmVariables = new HashMap<String, Object>();
		// hmVariables.put("procAuthType", "1"); // 1：token认证；2：session认证
		// hmVariables.put("procTokenUser", "f53674938794c432e1021584ffd963a6");
		// hmVariables.put("procTokenPass", "331493b0b9d8815135f6361bd1f83a7c");
		// hmVariables.put("procTaskUser", "mahairong");

		Map<String, Object> hmBizData = new HashMap<String, Object>();
		hmBizData.put("procBizId", procBizId);
		hmBizData.put("procBizType", procBizType);
		hmBizData.put("procOrgCode", procOrgCode);
		hmBizData.put("procBizMemo", "业务流程测试:" + procBizId);
		hmBizData.put("procCallback", procCallback);
		
		reqData.put("procData", hmProcData);
		reqData.put("variableData", hmVariables);
		reqData.put("authData", hmAuthData);
		reqData.put("bizData", hmBizData);

		return reqData;
	}
}
